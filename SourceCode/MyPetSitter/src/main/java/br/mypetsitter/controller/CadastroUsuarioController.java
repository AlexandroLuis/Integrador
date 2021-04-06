package br.mypetsitter.controller;

import br.mypetsitter.dao.PetSitterDAOJDBC;
import br.mypetsitter.dao.ClienteDAOJDBC;
import br.mypetsitter.model.PetSitter;
import br.mypetsitter.model.CadastroUsuarioFXML;
import br.mypetsitter.model.Cliente;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class CadastroUsuarioController {
	Object usuario;
	private ObservableList<String> estados = FXCollections.observableArrayList("AC", "AL", "AP", "AM", "BA", "CE", "ES",
			"GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE",
			"TO", "DF"

	);
	private ClienteDAOJDBC clienteDao = new ClienteDAOJDBC();
	private PetSitterDAOJDBC petSitterDao = new PetSitterDAOJDBC();
	private String tipo = "";
	@FXML
	private TextField tfdNome;

	@FXML
	private TextField tfdCpf;

	@FXML
	private TextField tfdCnpj;

	@FXML
	private TextField tfdCidade;

	@FXML
	private DatePicker tfdDataNascimento;

	@FXML
	private ToggleGroup tipoUsuario;

	@FXML
	private TextField tfdUsuario;

	@FXML
	private ChoiceBox<String> cbEstado;

	@FXML
	private TextField tfdTelefone;

	@FXML
	private TextField tfdEndereco;

	@FXML
	private TextField tfdBairro;

	@FXML
	private TextField tfdCep;

	@FXML
	private PasswordField tfdSenha;

	@FXML
	private PasswordField tfdConfirmaSenha;

	@FXML
	private RadioButton rbCliente;

	@FXML
	private RadioButton rbAutonomo;

	@FXML
	private void initialize() {
		cbEstado.setItems(estados);
	}

	public static boolean validaSenha(String senha, String confirmaSenha) {
		if (senha.equals(confirmaSenha)) {
			return true;
		}
		return false;
	}

	public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
		return java.sql.Date.valueOf(dateToConvert);
	}

	@FXML
	void radioSelect(ActionEvent event) {
		if (rbCliente.isSelected()) {
			this.tipo = "C";
		} else {
			if (rbAutonomo.isSelected()) {
				this.tipo = "A";
			}
		}

	}

	public void cadastraUsuario(ActionEvent event) {
		if ((!(tfdNome.getText().isEmpty()) && !(cbEstado.getValue().isEmpty()) && (tfdDataNascimento != null)
				&& !(tfdCpf.getText().isEmpty()) && !(tfdTelefone.getText().isEmpty()) && !(tfdNome.getText().isEmpty())
				&& !(tfdCidade.getText().isEmpty()) && !(tfdEndereco.getText().isEmpty())
				&& !(tfdBairro.getText().isEmpty()) && !(tfdSenha.getText().isEmpty()) && !(tipo.isEmpty()))) {
			int anoAtual = LocalDate.now().getYear();
			String userName = tfdUsuario.getText();
			String estado = cbEstado.getValue();
			LocalDate localDate = tfdDataNascimento.getValue();
			Date dataNascimento = Date.valueOf(localDate);
			System.out.println(LocalDate.now());
			int idade = anoAtual - localDate.getYear();
			if (idade > 17) {
				String cpf = tfdCpf.getText();
				String cnpj = tfdCnpj.getText();
				String telefone = tfdTelefone.getText();
				String nome = tfdNome.getText();
				String cidade = tfdCidade.getText();
				String endereco = tfdEndereco.getText();
				String bairro = tfdBairro.getText();
				String cep = tfdCep.getText();
				String senha = tfdSenha.getText();
				String confirmaSenha = tfdConfirmaSenha.getText();

				if (isCpf(cpf)) {
					if (senha.equals(confirmaSenha)) {
						if (this.tipo == "C") {
							usuario = new Cliente(cpf, cnpj, telefone, nome, cidade, endereco, bairro, cep, userName,
									senha, estado, dataNascimento

							);
						} else {
							usuario = new PetSitter(cpf, cnpj, telefone, nome, cidade, endereco, bairro, cep, userName,
									senha, estado, dataNascimento

							);
						}
						try {
							if (usuario instanceof Cliente) {
								clienteDao.insere((Cliente) usuario);
							} else {
								petSitterDao.insere((PetSitter) usuario);
							}

							Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
							alert.setTitle("Confirmação");
							alert.setContentText("Usuário salvo com sucesso!");
							alert.showAndWait();
							fechaJanela();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							System.out.println(e);
							Alert alert = new Alert(Alert.AlertType.ERROR);
							alert.setTitle("Erro");
							alert.setContentText(
									"Ocorreu um problema ao realizar o cadastro, certifique-se de que os campos foram preenchidos corretamente.");
							alert.show();
						}
					} else {
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setTitle("Senhas não conferem!");
						alert.setContentText(
								"Os valores inseridos nos campos 'Senha' e 'Confirmar Senha' precisam ser iguais.");
						alert.show();
					}
				} else {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Cpf Inválido!");
					alert.setContentText("Informe um CPF válido.");
					alert.show();
				}
			} else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Data de nascimento inválida!");
				alert.setContentText("A idade deve ser maior ou igual a 18 anos!");
				alert.show();
			}

		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Campos nulos!");
			alert.setContentText("Um ou mais campos obrigatórios não foram preenchidos.");
			alert.show();
		}

	}

	private void fechaJanela() {
		CadastroUsuarioFXML.getStage().close();
	}

	private boolean isCpf(String CPF) {
		// considera-se erro CPF's formados por uma sequencia de numeros iguais
		if (CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222")
				|| CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555")
				|| CPF.equals("66666666666") || CPF.equals("77777777777") || CPF.equals("88888888888")
				|| CPF.equals("99999999999") || (CPF.length() != 11))
			return (false);

		char dig10, dig11;
		int sm, i, r, num, peso;

		// "try" - protege o codigo para eventuais erros de conversao de tipo (int)
		try {
			// Calculo do 1o. Digito Verificador
			sm = 0;
			peso = 10;
			for (i = 0; i < 9; i++) {
				// converte o i-esimo caractere do CPF em um numero:
				// por exemplo, transforma o caractere '0' no inteiro 0
				// (48 eh a posicao de '0' na tabela ASCII)
				num = (int) (CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig10 = '0';
			else
				dig10 = (char) (r + 48); // converte no respectivo caractere numerico

			// Calculo do 2o. Digito Verificador
			sm = 0;
			peso = 11;
			for (i = 0; i < 10; i++) {
				num = (int) (CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig11 = '0';
			else
				dig11 = (char) (r + 48);

			// Verifica se os digitos calculados conferem com os digitos informados.
			if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
				return (true);
			else
				return (false);
		} catch (InputMismatchException erro) {
			return (false);
		}
	}
}