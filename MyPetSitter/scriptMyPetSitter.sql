CREATE TABLE Cliente (
  clienteId VARCHAR (20) NOT NULL,
  nome VARCHAR(44) NOT NULL,
  cpf VARCHAR(11) NOT NULL,
  cnpj VARCHAR(14) NULL,
  telefone VARCHAR(11) NOT NULL,
  cidade VARCHAR(20) NOT NULL,
  estado VARCHAR(2) NOT NULL,
  endereco VARCHAR(50) NOT NULL,
  bairro VARCHAR(15) NOT NULL,
  cep VARCHAR(10) NULL,
  dataNascimento DATE NOT NULL,
  dataCadastro DATE NOT NULL,
  senha VARCHAR(10) NOT NULL,
  PRIMARY KEY(clienteId)
);

CREATE TABLE PetSitter (
  petSitterId VARCHAR(20) NOT NULL,
  nome VARCHAR(44) NOT NULL,
  cpf VARCHAR(11) NOT NULL,
  cnpj VARCHAR(14) NULL,
  telefone VARCHAR(11) NOT NULL,
  cidade VARCHAR(20) NOT NULL,
  estado VARCHAR(2) NOT NULL,
  endereco VARCHAR(50) NOT NULL,
  bairro VARCHAR(15) NOT NULL,
  cep VARCHAR(10) NULL,
  dataNascimento DATE NOT NULL,
  dataCadastro DATE NOT NULL,
  senha VARCHAR(10) NOT NULL,
  PRIMARY KEY(petSitterId)
);

CREATE TABLE Categoria (
  categoriaId SERIAL NOT NULL, 
  nome VARCHAR(20) NOT NULL,
  PRIMARY KEY(categoriaId)
);

CREATE TABLE Servico (
  servicoId SERIAL NOT NULL,
  categoriaId INTEGER NOT NULL,
  nome VARCHAR(15) NOT NULL,
  PRIMARY KEY(servicoId),
  FOREIGN KEY(categoriaId)
    REFERENCES Categoria(categoriaId)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE ServicoPetSitter (
  servicoId SERIAL NOT NULL,
  petSitterId VARCHAR(20) NOT NULL,
  PRIMARY KEY(petSitterId, servicoId),
  FOREIGN KEY(petSitterId)
    REFERENCES PetSitter(petSitterId)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(servicoId)
    REFERENCES Servico(servicoId)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE SolicitacaoServico (
  solicitacaoServicoId SERIAL NOT NULL,
  servicoId INTEGER NOT NULL,
  clienteId VARCHAR(20) NOT NULL,
  petSitterId VARCHAR(20) NOT NULL,
  dataSolicitacao DATE NOT NULL,
  precoServico DOUBLE PRECISION NOT NULL,
  descricaoSolicitacao VARCHAR(255) NOT NULL,
  PRIMARY KEY(solicitacaoServicoId),
  FOREIGN KEY(servicoId)
    REFERENCES Servico(servicoId)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(petSitterId)
    REFERENCES PetSitter(petSitterId)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(clienteId)
    REFERENCES Cliente(clienteId)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE Avaliacao (
  avaliacaoId SERIAL NOT NULL,
  solicitacaoServicoId INTEGER NOT NULL,
  nota INTEGER NULL,
  comentario VARCHAR NULL,
  PRIMARY KEY(avaliacaoId),
  FOREIGN KEY(solicitacaoServicoId)
    REFERENCES SolicitacaoServico(solicitacaoServicoId)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);