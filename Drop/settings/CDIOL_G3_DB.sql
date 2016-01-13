drop table DropPoint cascade constraints;
drop table Cliente cascade constraints;
drop table Classe_dimensao cascade constraints;
drop table Armario cascade constraints;
drop table Reserva cascade constraints;
drop table Prateleira cascade constraints;
drop table Recolha cascade constraints;
drop table Entrega cascade constraints;
drop table Gestor cascade constraints;
drop table Classe_Temperatura cascade constraints;
drop table Token cascade constraints;
drop table Tipo_Token cascade constraints;
drop table MORADA cascade constraints;
drop table Manutencao cascade constraints;
drop table Team cascade constraints;
drop table Team_Type cascade constraints;
drop table Employee cascade constraints;
drop table MAINTENANCE_PICKUP cascade constraints;
drop table Maintenance_Task cascade constraints;
drop table Preemptive_DP_Plan cascade constraints;
drop table Maintenance_Plan cascade constraints;
drop table Cell_Maintenance cascade constraints;
drop Table Incident_Type cascade constraints;
drop table Incident cascade constraints;
drop table Repair cascade constraints;
drop table Repair_Plan cascade constraints;

drop sequence seq_id_Incident;
drop sequence seq_id_Incident_Type;
drop sequence seq_id_maintenance;
drop sequence seq_id_cell_maintenance;

CREATE TABLE DropPoint (
	id_DropPoint number(10) NOT NULL,
	nome_DropPoint varchar2(255),
	id_gestor number(10),
	ID_MORADA number(10),
	free_days number(10),
	max_rate number
);

CREATE TABLE Cliente (
	id_Cliente number(10) NOT NULL,
	nome varchar2(255),
  nif varchar2(255),
  email varchar2(255)NOT NULL,
	telemovel number(10),
	username varchar2(255)NOT NULL UNIQUE,
	upassword varchar2(255)NOT NULL,
	ID_MORADA number(10)
);

CREATE TABLE Classe_dimensao (
	id_tipo_dimensao number(10)NOT NULL,
	descricao varchar2(255),
	altura number(10),
	largura number(10),
	comprimento number(10)
);

CREATE TABLE Armario (
	id_armario number(10)NOT NULL,
	id_DropPoint number(10)NOT NULL,
	nome varchar(50),
	manutencao number(1)
);

CREATE TABLE Reserva (
	id_reserva number(10)NOT NULL,
	id_DropPoint number(10),
	id_Cliente number(10),
	id_temperatura number(10),
	id_tipo_dimensao number(10)
);

CREATE TABLE Prateleira (
	id_prateleira number(10)NOT NULL,
	numero_prateleira varchar2(10),
	id_armario number(10),
	id_temperatura number(10),
	ativo number(1),
	ocupado number(1),
	id_tipo_dimensao number(10),
	is_operational number (1)
);

CREATE TABLE Recolha (
	id_recolha number(10)NOT NULL,
	data_abre_prateleira date,
	data_fecha_prateleira date,
	id_entrega number(10) NOT NULL UNIQUE,
	id_token_cliente number(10)NOT NULL UNIQUE
);

CREATE TABLE Entrega (
	id_entrega number(10)NOT NULL,
	data_abre_prateleira date,
	data_fecha_prateleira date,
	id_reserva number(10) NOT NULL UNIQUE,
	id_prateleira number(10),
	id_token_estafeta numeric(10)NOT NULL UNIQUE,
	id_token_colaborador numeric(10)
);

CREATE TABLE Gestor (
	id_gestor number(10)NOT NULL,
	nome varchar2(255),
  upassword varchar2(255)NOT NULL,
	username varchar2(255) NOT NULL UNIQUE
);

CREATE TABLE Classe_Temperatura (
	id_temperatura number(10)NOT NULL,
	descricao varchar2(250),
	temp_max number(10),
	temp_min number(10)
);

CREATE TABLE Token (
	id_token number(10)NOT NULL,
	data_geracao varchar2(250),
	data_validade varchar2(250),
	id_tipo_token number(10),
	ativo number(1),
	id_reserva number(10),
	codigo varchar2(255) NOT NULL UNIQUE
);

CREATE TABLE Tipo_token (
	id_tipo_token number(10)NOT NULL,
	descricao varchar2(250)
);

CREATE TABLE Morada (
	ID_MORADA number(10)NOT NULL,
	rua varchar2(255),
	numero number(10),
  codPostal varchar2(255),
	localidade varchar2(255),
  latitude varchar(100),
  longitude varchar(100)
  
);

create table Employee (
  e_number 	number(20) NOT NULL,
  cpassword varchar(255),
  e_name 		varchar(255),
  id_Team	number(10)
 );

create table Manutencao (
	id_manutencao	number(10) NOT NULL,
        index           number(5),
	id_DropPoint 	number(10),
	id_Maint_Plan	number(10),
	id_Maint_Ass	number(10),
	--id_armario number(10) NOT NULL,
	--id_equipa number(10) NOT NULL,
	data_inicio Date DEFAULT (sysdate),
	data_fim Date DEFAULT (sysdate)
);

create table Team_Type (
	id_Team_Type	number(10),
	team_type		varchar(50)
);

create table Team (
	id_Team			number(10) NOT NULL,
	id_Team_Type	number(10)
);

CREATE TABLE MAINTENANCE_PICKUP (
  maintenance_pickup_id number(10) NOT NULL,
  open_shelf_date date,
	close_shelf_date date,
	id_entrega number(10) NOT NULL,
  id_token_colaborador number(10)NOT NULL,
  photo_path varchar(255)
);

CREATE TABLE Maintenance_Task (
	id_task number(10) NOT NULL,
	description varchar(255),
	avg_duration number(3)
);

CREATE TABLE Preemptive_DP_Plan (
	id_pre_plan		number(10),
	id_DropPoint	number(10),
	id_task			number(10)
);

CREATE TABLE Maintenance_Plan (
	id_Maint_Plan	number(10),
	id_Maint_Team	number(10),
	maint_Plan_Date		date
);

CREATE TABLE Cell_Maintenance (
	id_Cell_Maint	number(10),
	id_Cell			number(10),
	id_Maintenance	number(10),
	observations	varchar2(255)
);

CREATE TABLE Incident_Type (
	id_Incident_Type	number(10),
	description			varchar(50)
);

CREATE TABLE Incident (
	id_Incident			number(10),
	id_Incident_Type	number(10),
	id_prateleira		number(10),
	incident_date		date,
	reporter			number(10)
);

CREATE TABLE Repair (
	id_Repair		number(10),
        index                   number(5),
	id_Incident		number(10),
	id_Repair_Plan          number(10),
	repair_date		date,
	observations	varchar2(255),
	parts_used		varchar2(255)
);

CREATE TABLE Repair_Plan (
	id_Repair_Plan		number(10),
	id_Repair_Team		number(10),
	repair_Plan_Date	date
);


CREATE SEQUENCE seq_id_Incident
MINVALUE 1
START WITH 1
INCREMENT BY 1
CACHE 10;


CREATE SEQUENCE seq_id_Incident_Type
MINVALUE 1
START WITH 1
INCREMENT BY 1
CACHE 10;


CREATE SEQUENCE seq_id_maintenance
MINVALUE 1
START WITH 1
INCREMENT BY 1
CACHE 10;


CREATE SEQUENCE seq_id_cell_maintenance
MINVALUE 1
START WITH 1
INCREMENT BY 1
CACHE 10;


-- Chaves primarias
ALTER TABLE DropPoint ADD PRIMARY KEY (id_DropPoint);
ALTER TABLE Cliente ADD PRIMARY KEY (id_Cliente);
ALTER TABLE Classe_dimensao ADD PRIMARY KEY (id_tipo_dimensao);
ALTER TABLE Armario ADD PRIMARY KEY (id_armario);
ALTER TABLE Reserva ADD PRIMARY KEY (id_reserva);
ALTER TABLE Prateleira ADD PRIMARY KEY (id_prateleira);
ALTER TABLE Recolha ADD PRIMARY KEY (id_recolha);
ALTER TABLE Entrega ADD PRIMARY KEY (id_entrega);
ALTER TABLE Gestor ADD PRIMARY KEY (id_gestor);
ALTER TABLE Classe_Temperatura ADD PRIMARY KEY (id_temperatura);
ALTER TABLE Token ADD PRIMARY KEY (id_token);
ALTER TABLE Tipo_token ADD PRIMARY KEY (id_tipo_token);
ALTER TABLE Morada ADD PRIMARY KEY (ID_MORADA);
ALTER TABLE Employee ADD PRIMARY KEY (e_number);
ALTER TABLE Team_Type ADD PRIMARY KEY (id_Team_Type);
ALTER TABLE Team ADD PRIMARY KEY (id_Team);
ALTER TABLE Manutencao ADD PRIMARY KEY (id_manutencao);
ALTER TABLE MAINTENANCE_PICKUP ADD PRIMARY KEY (MAINTENANCE_PICKUP_ID);
ALTER TABLE Maintenance_Task ADD PRIMARY KEY (id_task);
ALTER TABLE Preemptive_DP_Plan ADD PRIMARY KEY (id_pre_plan);
ALTER TABLE Maintenance_Plan ADD PRIMARY KEY (id_Maint_Plan);
ALTER TABLE Cell_Maintenance ADD PRIMARY KEY (id_Cell_Maint);
ALTER TABLE Incident_Type ADD PRIMARY KEY (id_Incident_Type);
ALTER TABLE Incident ADD PRIMARY KEY (id_Incident);
ALTER TABLE Repair ADD PRIMARY KEY (id_Repair);
ALTER TABLE Repair_Plan ADD PRIMARY KEY (id_Repair_Plan);


--Chaves Estrangeiras
ALTER TABLE DropPoint ADD CONSTRAINT FK_DropPoint_idGest_Gest FOREIGN KEY (id_gestor) REFERENCES Gestor (id_gestor);
ALTER TABLE DropPoint ADD CONSTRAINT FK_DropPoint_idMora_Mora FOREIGN KEY (ID_MORADA) REFERENCES Morada (ID_MORADA);
ALTER TABLE Armario ADD CONSTRAINT FK_Armario_idDP_DP FOREIGN KEY (id_DropPoint) REFERENCES DropPoint (id_DropPoint);
ALTER TABLE Reserva ADD CONSTRAINT FK_Reserva_idDP_DP FOREIGN KEY (id_DropPoint) REFERENCES DropPoint (id_DropPoint);
ALTER TABLE Reserva ADD CONSTRAINT FK_Reserva_idCliente_Cliente FOREIGN KEY (id_Cliente) REFERENCES Cliente (id_Cliente);
ALTER TABLE Reserva ADD CONSTRAINT FK_Reserva_idTem_ClTemp FOREIGN KEY (id_temperatura) REFERENCES Classe_Temperatura (id_temperatura);
ALTER TABLE Reserva ADD CONSTRAINT FK_Reserva_idTipoDim_ClDim FOREIGN KEY (id_tipo_dimensao) REFERENCES Classe_dimensao (id_tipo_dimensao);
ALTER TABLE Prateleira ADD CONSTRAINT FK_Prateleira_idTem_ClTemp FOREIGN KEY (id_temperatura) REFERENCES Classe_Temperatura (id_temperatura);
ALTER TABLE Prateleira ADD CONSTRAINT FK_Prateleira_idTDim_ClDim FOREIGN KEY (id_tipo_dimensao) REFERENCES Classe_dimensao (id_tipo_dimensao);
ALTER TABLE Prateleira ADD CONSTRAINT FK_Prateleira_idArm_Arm FOREIGN KEY (id_armario) REFERENCES Armario (id_armario);
ALTER TABLE Recolha ADD CONSTRAINT FK_Recolha_idEnt_Ent FOREIGN KEY (id_entrega) REFERENCES Entrega (id_entrega);
ALTER TABLE Entrega ADD CONSTRAINT FK_Entrega_idRes_Res FOREIGN KEY (id_reserva) REFERENCES Reserva (id_reserva);
ALTER TABLE Entrega ADD CONSTRAINT FK_Entrega_idToken_Token FOREIGN KEY (id_token_estafeta) REFERENCES Token (id_token);
ALTER TABLE Entrega ADD CONSTRAINT FK_Entrega_idPrat_Prat FOREIGN KEY (id_prateleira) REFERENCES Prateleira (id_prateleira);
ALTER TABLE Entrega ADD CONSTRAINT FK_Entrega_idTokenCol_Token FOREIGN KEY (id_token_colaborador) REFERENCES Token (id_token);
ALTER TABLE Token ADD CONSTRAINT FK_Token_idTpTok_TipTok FOREIGN KEY (id_tipo_token) REFERENCES Tipo_token (id_tipo_token);
ALTER TABLE Token ADD CONSTRAINT FK_Token_idRes_Res FOREIGN KEY (id_reserva) REFERENCES Reserva (id_reserva);
ALTER TABLE Recolha ADD CONSTRAINT FK_Recolha_idTok_Tok FOREIGN KEY (id_token_cliente) REFERENCES Token (id_token);
ALTER TABLE Cliente ADD CONSTRAINT FK_Cliente_idMora_Mora FOREIGN KEY (ID_MORADA) REFERENCES Morada (ID_MORADA);
ALTER TABLE Team ADD CONSTRAINT FK_Team_idTT_TType FOREIGN KEY (id_Team_Type) REFERENCES Team_Type (id_Team_Type);
ALTER TABLE Employee ADD CONSTRAINT FK_Employee_idEqui_Eq FOREIGN KEY (id_Team) REFERENCES Team (id_Team);
--ALTER TABLE Employee ADD CONSTRAINT UN_Employee_idEq UNIQUE (id_Team);
ALTER TABLE Manutencao ADD CONSTRAINT FK_Manutencao_idDropPoint FOREIGN KEY (id_DropPoint) REFERENCES DROPPOINT (id_DropPoint);
ALTER TABLE Manutencao ADD CONSTRAINT FK_Manutencao_idMaintPlan FOREIGN KEY (id_Maint_Plan) REFERENCES Maintenance_Plan (id_Maint_Plan);
ALTER TABLE Manutencao ADD CONSTRAINT FK_Manutencao_numEmpl FOREIGN KEY (id_Maint_Ass) REFERENCES Employee (e_number);
ALTER TABLE MAINTENANCE_PICKUP ADD CONSTRAINT FK_MPickUp_idEnt_Ent FOREIGN KEY (id_entrega) REFERENCES Entrega (id_entrega);
ALTER TABLE Preemptive_DP_Plan ADD CONSTRAINT FK_PrePlan_idDP_DP FOREIGN KEY (id_DropPoint) REFERENCES DropPoint (id_DropPoint);
ALTER TABLE Preemptive_DP_Plan ADD CONSTRAINT FK_PrePlan_idTsk_Tsk FOREIGN KEY (id_task) REFERENCES Maintenance_Task (id_task);
ALTER TABLE Maintenance_Plan ADD CONSTRAINT FK_MaintPl_idTeam_Team FOREIGN KEY (id_Maint_Team) REFERENCES Team (id_Team);
ALTER TABLE Cell_Maintenance ADD CONSTRAINT FK_CellMnt_idCell_Prat FOREIGN KEY (id_Cell) REFERENCES PRATELEIRA (id_prateleira);
ALTER TABLE Cell_Maintenance ADD CONSTRAINT FK_CellMnt_idMnt_Man FOREIGN KEY (id_Maintenance) REFERENCES MANUTENCAO (id_manutencao);
ALTER TABLE Incident ADD CONSTRAINT FK_Incid_idIncTp_IncTp FOREIGN KEY (id_Incident_Type) REFERENCES Incident_Type (id_Incident_Type);
ALTER TABLE Incident ADD CONSTRAINT FK_Incid_idPrat_Prat FOREIGN KEY (id_prateleira) REFERENCES PRATELEIRA (id_prateleira);
ALTER TABLE Incident ADD CONSTRAINT FK_Incid_reporter_Empl FOREIGN KEY (reporter) REFERENCES Employee (e_number);
ALTER TABLE Repair ADD CONSTRAINT FK_Repair_idIncd_Incd FOREIGN KEY (id_Incident) REFERENCES Incident (id_Incident);
ALTER TABLE Repair ADD CONSTRAINT FK_Repair_idRpPlan_RpPlan FOREIGN KEY (id_Repair_Plan) REFERENCES Repair_Plan (id_Repair_Plan);


-- TRIGGER PARA MUDAR O VALOR DE OCUPA´AO DA PRATELEIRA CONSOANTE UMA ENTREGA

ALTER SESSION SET PLSCOPE_SETTINGS = 'IDENTIFIERS:NONE'; --necessario para nao dar erro de compilacao

CREATE OR REPLACE TRIGGER TRG_PRATELEIRA_OCUPACAO_E
BEFORE INSERT or UPDATE ON ENTREGA 
for each row
BEGIN
    update PRATELEIRA
     set OCUPADO = 1
    where ID_PRATELEIRA = :new.ID_PRATELEIRA;
END;
/

-- TRIGGER PARA MUDAR O VALOR DE OCUPA´AO DA PRATELEIRA CONSOANTE UMA RECOLHA

create or replace TRIGGER TRG_PRATELEIRA_OCUPACAO_R
BEFORE INSERT or UPDATE ON RECOLHA
for each row

DECLARE
  idPrateleira PRATELEIRA.id_prateleira%TYPE;

BEGIN
  --Atualizacao do estado da prateleira;
  begin
    select id_prateleira into idPrateleira
      from entrega
     where id_entrega = :new.id_entrega;
   
      update prateleira
         set OCUPADO = 0
      where ID_PRATELEIRA = idPrateleira;
  exception
    when others then
      raise_application_error(-20001, 'Nao foi possÃvel atualizar o estado da prateleira');
  end;
  
exception
  when others then
    raise_application_error(-20002, sqlerrm(sqlcode));
END;
/

-- TRIGGER PARA QUE O TOKEN VALIDO DE UM ESTAFETA SEJA DESATIVADO DEPOIS DA ENTREGA

CREATE OR REPLACE TRIGGER TRG_TOKEN_ESTAFETA_DESATIVAR 
BEFORE INSERT OR UPDATE ON ENTREGA 
for each row
BEGIN
  update token
    set ATIVO = 0
  where ID_TOKEN = :new.id_token_estafeta;
END;
/

-- TRIGGER PARA QUE O TOKEN VALIDO DE UM CLIENTE SEJA DESATIVADO DEPOIS DA ENTREGA

CREATE OR REPLACE TRIGGER TRG_TOKEN_CLIENTE_DESATIVAR 
BEFORE INSERT OR UPDATE ON RECOLHA 
for each row
BEGIN
  update token
    set ATIVO = 0
  where ID_TOKEN = :new.id_token_cliente;
END;
/

-- TRIGGER PARA MUDAR O VALOR DE OCUPA«AO DA PRATELEIRA CONSOANTE UMA RECOLHA 
-- PELA EQUIPA DE MANUTENCAO

create or replace TRIGGER TRG_PRATELEIRA_OCUPACAO_MP
BEFORE INSERT or UPDATE ON MAINTENANCE_PICKUP
for each row

DECLARE
  idPrateleira PRATELEIRA.id_prateleira%TYPE;

BEGIN
  --Atualizacao do estado da prateleira;
  begin
    select id_prateleira into idPrateleira
      from entrega
     where id_entrega = :new.id_entrega;
   
      update prateleira
         set OCUPADO = 0
      where ID_PRATELEIRA = idPrateleira;
  exception
    when others then
      raise_application_error(-20001, 'N„o foi possÌvel atualizar o estado da prateleira');
  end;
  
exception
  when others then
    raise_application_error(-20002, sqlerrm(sqlcode));
END;
/

-- TRIGGER PARA MUDAR A PRATELEIRA para inoperacional CONSOANTE a inserÁ„o de um incidente 
-- PELA EQUIPA DE MANUTENCAO

create or replace TRIGGER TRG_CELL_NOT_OPERATIONAL_I
BEFORE INSERT or UPDATE ON INCIDENT
for each row
/*
DECLARE
  idPrateleira PRATELEIRA.id_prateleira%TYPE;
*/
BEGIN
  --Atualizacao do estado da prateleira;
  begin
      update prateleira
         set is_operational = 0
      where ID_PRATELEIRA = :new.id_prateleira;
  exception
    when others then
      raise_application_error(-20004, 'N„o foi possÌvel atualizar o estado da prateleira');
  end;
  
exception
  when others then
    raise_application_error(-20003, sqlerrm(sqlcode));
END;
/

-- Procedimento para obter armarios em manutencao

create or replace procedure cellsToMaintenance(idCabinet number, cur OUT SYS_REFCURSOR)
is
BEGIN
OPEN cur FOR select p.ID_PRATELEIRA, d.ID_DROPPOINT, p.NUMERO_PRATELEIRA, a.ID_ARMARIO from PRATELEIRA p 
	INNER JOIN ARMARIO a ON
		p.ID_ARMARIO= idCabinet
	INNER JOIN DROPPOINT d ON
		a.ID_DROPPOINT=d.ID_DROPPOINT
	WHERE a.MANUTENCAO = 1;	
END;
/

-- Procedure para inserir um incidente na base de dados
CREATE OR REPLACE PROCEDURE insertIncident(
	   i_type IN Incident.id_Incident_Type%TYPE,
	   i_cell IN Incident.id_prateleira%TYPE,
	   i_date IN Incident.incident_date%TYPE,
	   i_assist IN Incident.reporter%TYPE)
IS
BEGIN

INSERT INTO INCIDENT(id_Incident, id_Incident_Type, id_prateleira, incident_date, reporter)
VALUES (seq_id_incident.nextval, i_type,i_cell,i_date,i_assist);

  COMMIT;

END;
/

CREATE OR REPLACE PROCEDURE insertMaintenance(
	m_drop IN Manutencao.id_DropPoint%TYPE,
	m_plan IN Manutencao.id_Maint_Plan%TYPE,
	m_assist IN Manutencao.id_Maint_Ass%TYPE,
	m_dateS IN Manutencao.data_inicio%TYPE,
	m_dateF IN Manutencao.data_fim%TYPE,
  m_id OUT Manutencao.id_manutencao%TYPE)

IS
BEGIN

  m_id := seq_id_maintenance.nextval;
  
INSERT INTO Manutencao(id_manutencao, id_DropPoint, id_Maint_Plan, id_Maint_Ass, data_inicio, data_fim)
VALUES (m_id, m_drop, m_plan, m_assist, m_dateS, m_dateF);
  
  COMMIT;

END;
/

CREATE OR REPLACE PROCEDURE insertCellMaintenance(
	m_drop IN Manutencao.id_DropPoint%TYPE,
	m_plan IN Manutencao.id_Maint_Plan%TYPE,
	m_assist IN Manutencao.id_Maint_Ass%TYPE,
	m_dateS IN Manutencao.data_inicio%TYPE,
	m_dateF IN Manutencao.data_fim%TYPE)
IS
BEGIN

INSERT INTO Manutencao(id_manutencao, id_DropPoint, id_Maint_Plan, id_Maint_Ass, data_inicio, data_fim)
VALUES (seq_id_maintenance.nextval, m_drop,m_plan,m_assist,m_dateS,m_dateF);

  COMMIT;

END;
/
/*
begin 
declare c is cursor
CELLSTOMAINTENANCE(1);
end;
/*/
-----------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------
---------------------------------      DADOS      ---------------------------------------------------
-----------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------
--Dados para testes da Base de Dados, (Inserts)

  --MORADA -> INSERT INTO Morada (ID_MORADA,RUA,CODPOSTAL,LOCALIDADE) VALUES (Int, String, String, String);
INSERT INTO Morada (ID_MORADA,RUA,NUMERO,CODPOSTAL,LOCALIDADE,LATITUDE,LONGITUDE) 
  VALUES (1, 'Rua Dr. Ant€nio Bernardino de Almeida',431, '4200-072', 'Porto','41.1778497','-8.6102893,17');

INSERT INTO Morada (ID_MORADA,RUA,NUMERO,CODPOSTAL,LOCALIDADE,LATITUDE,LONGITUDE) 
  VALUES (2, 'Rua Jaime Lopes Amorim',20, '4465-004', 'S. Mamede de Infesta','41.1898662','-8.6015942,17');

INSERT INTO Morada (ID_MORADA,RUA,NUMERO,CODPOSTAL,LOCALIDADE,LATITUDE,LONGITUDE) 
  VALUES (3, 'Rua Roberto Frias',602, '4200-465', 'Porto','41.1801581','-8.597438,119');

INSERT INTO Morada (ID_MORADA,RUA,NUMERO,CODPOSTAL,LOCALIDADE,LATITUDE,LONGITUDE) 
  VALUES (4, 'Rua Valente Perfeito',322, '4400-330', 'Vila Nova de Gaia','41.1330481','-8.6228943,17');

INSERT INTO Morada (ID_MORADA,RUA,NUMERO,CODPOSTAL,LOCALIDADE,LATITUDE,LONGITUDE) 
  VALUES (5, 'Rua da Alegria',503, '4000-045','Porto','41.1544937','-8.6053933,17');

INSERT INTO Morada (ID_MORADA,RUA,NUMERO,CODPOSTAL,LOCALIDADE,LATITUDE,LONGITUDE) 
  VALUES (6, 'Rua Sara Afonso',105, '4460-841', 'Senhora da Hora','41.1807708','-8.6567255,17');

INSERT INTO Morada (ID_MORADA,RUA,NUMERO,CODPOSTAL,LOCALIDADE,LATITUDE,LONGITUDE) 
  VALUES (7, 'Pra¡a de Almeida Garrett',46, '4000-069', 'Porto','41.1455754','-8.6106484,19');

INSERT INTO Morada (ID_MORADA,RUA,NUMERO,CODPOSTAL,LOCALIDADE,LATITUDE,LONGITUDE) 
  VALUES (8, 'Rua de Cam?es',19, '4000-124', 'Porto','41.1524433','-8.6096905,17.75');

INSERT INTO Morada (ID_MORADA,RUA,NUMERO,CODPOSTAL,LOCALIDADE,LATITUDE,LONGITUDE) 
  VALUES (9, 'Rua DR. Pl∑cido da Costa',40, '4200-450', 'Porto','41.1804142','-8.6063489,17');

  --CLIENTE -> INSERT INTO Cliente (ID_CLIENTE,NOME,NIF,EMAIL,TELEMOVEL,USERNAME,UPASSWORD,ID_MORADA) VALUES (int, String, String, String,int,String, String, int);
INSERT INTO Cliente (ID_CLIENTE,NOME,NIF,EMAIL,TELEMOVEL,USERNAME,UPASSWORD,ID_MORADA) 
  VALUES (1, 'Andre', '123456789', '1130874@isep.ipp.pt',911111111,'andre', 'pass1', 1);

INSERT INTO Cliente (ID_CLIENTE,NOME,NIF,EMAIL,TELEMOVEL,USERNAME,UPASSWORD,ID_MORADA)
  VALUES (2, 'vasco', '256987412', '1140779@isep.ipp.pt',912222222,'vasco', 'pass2', 1);

INSERT INTO Cliente (ID_CLIENTE,NOME,NIF,EMAIL,TELEMOVEL,USERNAME,UPASSWORD,ID_MORADA)
  VALUES (3, 'Marco', '321654895', '1140864@isep.ipp.pt',913333333,'marco', 'pass3', 2);

INSERT INTO Cliente (ID_CLIENTE,NOME,NIF,EMAIL,TELEMOVEL,USERNAME,UPASSWORD,ID_MORADA)
  VALUES (4, 'Nuno', '326598741', '1140358@isep.ipp.pt',914444444,'nuno', 'pass4', 3);

INSERT INTO Cliente (ID_CLIENTE,NOME,NIF,EMAIL,TELEMOVEL,USERNAME,UPASSWORD,ID_MORADA)
  VALUES (5, 'Ruben', '147852369', '1140780@isep.ipp.pt',915555555,'ruben', 'pass5', 4);

INSERT INTO Cliente (ID_CLIENTE,NOME,NIF,EMAIL,TELEMOVEL,USERNAME,UPASSWORD,ID_MORADA)
  VALUES (6, 'Afonso', '523698741', '1140278@isep.ipp.pt',916666666,'afonso', 'pass6', 5);

/*
INSERT INTO Cliente (id_cliente,NOME,NIF,EMAIL,TELEMOVEL,USERNAME,UPASSWORD,ID_MORADA) VALUES (teste,'Afonso III', '5236989441', 'afonsoIII@mail.pt',915666466,'afonsoIII', 'pass8', 5);
select * from cliente;
select * from user_constraints where constraint_name = 'SYS_C009012';
*/

  --GESTOR -> INSERT INTO Gestor (ID_GESTOR,NOME,UPASSWORD,USERNAME) VALUES (int, Sring,String,String);
INSERT INTO Gestor (ID_GESTOR,NOME,UPASSWORD,USERNAME) VALUES (1, 'Gestor XPTO','Gest1','pass1');
INSERT INTO Gestor (ID_GESTOR,NOME,UPASSWORD,USERNAME) VALUES (2, 'Gestor KTYU','Gest2','pass2');
INSERT INTO Gestor (ID_GESTOR,NOME,UPASSWORD,USERNAME) VALUES (3, 'Gestor LPOUY','Gest3','pass3');
INSERT INTO Gestor (ID_GESTOR,NOME,UPASSWORD,USERNAME) VALUES (4, 'Gestor POLGH','Gest4','pass4');

  --DropPoint
INSERT INTO DropPoint (ID_DROPPOINT,ID_MORADA,ID_GESTOR,NOME_DROPPOINT,FREE_DAYS,MAX_RATE) VALUES (1, 6, 1,'DP NorteShopping',0,0.8);
INSERT INTO DropPoint (ID_DROPPOINT,ID_MORADA,ID_GESTOR,NOME_DROPPOINT,FREE_DAYS,MAX_RATE) VALUES (2, 7, 2,'DP Estação CP São Bento',0,0.55);
INSERT INTO DropPoint (ID_DROPPOINT,ID_MORADA,ID_GESTOR,NOME_DROPPOINT,FREE_DAYS,MAX_RATE) VALUES (3, 8, 3,'DP Estação Metro Trindade',0,0.75);
INSERT INTO DropPoint (ID_DROPPOINT,ID_MORADA,ID_GESTOR,NOME_DROPPOINT,FREE_DAYS,MAX_RATE) VALUES (4, 9, 4,'DP Campus S. João',0,0.9);

  --ARMARIO
INSERT INTO Armario (ID_ARMARIO,ID_DROPPOINT,MANUTENCAO,NOME) VALUES (111, 1, 0,'DP1ARM1');
INSERT INTO Armario (ID_ARMARIO,ID_DROPPOINT,MANUTENCAO,NOME) VALUES (112, 1, 0,'DP1ARM2');
INSERT INTO Armario (ID_ARMARIO,ID_DROPPOINT,MANUTENCAO,NOME) VALUES (121, 2, 0,'DP2ARM1');
INSERT INTO Armario (ID_ARMARIO,ID_DROPPOINT,MANUTENCAO,NOME) VALUES (122, 2, 0,'DP2ARM2');
INSERT INTO Armario (ID_ARMARIO,ID_DROPPOINT,MANUTENCAO,NOME) VALUES (131, 3, 0,'DP3ARM1');
INSERT INTO Armario (ID_ARMARIO,ID_DROPPOINT,MANUTENCAO,NOME) VALUES (141, 4, 0,'DP4ARM1');
INSERT INTO Armario (ID_ARMARIO,ID_DROPPOINT,MANUTENCAO,NOME) VALUES (142, 4, 0,'DP4ARM2');

  --PRATELEIRAS
    --CLASSE TEMPERATURA
INSERT INTO CLASSE_TEMPERATURA (ID_TEMPERATURA,DESCRICAO,TEMP_MAX,TEMP_MIN) VALUES (1,'Ambiente',25,20);
INSERT INTO CLASSE_TEMPERATURA (ID_TEMPERATURA,DESCRICAO,TEMP_MAX,TEMP_MIN) VALUES (2,'Frio',8,5);
INSERT INTO CLASSE_TEMPERATURA (ID_TEMPERATURA,DESCRICAO,TEMP_MAX,TEMP_MIN) VALUES (3,'Congelado',-5,-15);

    --CLASSE DIMENSAO
INSERT INTO CLASSE_DIMENSAO (ID_TIPO_DIMENSAO,DESCRICAO,ALTURA,LARGURA,COMPRIMENTO)VALUES (1,'S',20,20,20);
INSERT INTO CLASSE_DIMENSAO (ID_TIPO_DIMENSAO,DESCRICAO,ALTURA,LARGURA,COMPRIMENTO)VALUES (2,'M',30,30,30);
INSERT INTO CLASSE_DIMENSAO (ID_TIPO_DIMENSAO,DESCRICAO,ALTURA,LARGURA,COMPRIMENTO)VALUES (3,'L',40,40,40);
INSERT INTO CLASSE_DIMENSAO (ID_TIPO_DIMENSAO,DESCRICAO,ALTURA,LARGURA,COMPRIMENTO)VALUES (4,'X',50,50,50);
INSERT INTO CLASSE_DIMENSAO (ID_TIPO_DIMENSAO,DESCRICAO,ALTURA,LARGURA,COMPRIMENTO)VALUES (5,'XL',60,60,60);

-- PRATELEIRAS
  --DROPPOINT 1 - ARMARIO 1
INSERT INTO Prateleira (ID_PRATELEIRA,NUMERO_PRATELEIRA,ID_ARMARIO,ID_TEMPERATURA,ID_TIPO_DIMENSAO,ATIVO,OCUPADO, is_operational) VALUES (1,'DP1P001',111,1,1,1,0,1);
INSERT INTO Prateleira (ID_PRATELEIRA,NUMERO_PRATELEIRA,ID_ARMARIO,ID_TEMPERATURA,ID_TIPO_DIMENSAO,ATIVO,OCUPADO, is_operational) VALUES (2,'DP1P002',111,2,2,1,0,1);
INSERT INTO Prateleira (ID_PRATELEIRA,NUMERO_PRATELEIRA,ID_ARMARIO,ID_TEMPERATURA,ID_TIPO_DIMENSAO,ATIVO,OCUPADO, is_operational) VALUES (3,'DP1P003',111,3,2,1,0,1);
INSERT INTO Prateleira (ID_PRATELEIRA,NUMERO_PRATELEIRA,ID_ARMARIO,ID_TEMPERATURA,ID_TIPO_DIMENSAO,ATIVO,OCUPADO, is_operational) VALUES (4,'DP1P004',111,1,3,1,0,1);
INSERT INTO Prateleira (ID_PRATELEIRA,NUMERO_PRATELEIRA,ID_ARMARIO,ID_TEMPERATURA,ID_TIPO_DIMENSAO,ATIVO,OCUPADO, is_operational) VALUES (5,'DP1P005',111,1,4,1,0,1);
INSERT INTO Prateleira (ID_PRATELEIRA,NUMERO_PRATELEIRA,ID_ARMARIO,ID_TEMPERATURA,ID_TIPO_DIMENSAO,ATIVO,OCUPADO, is_operational) VALUES (6,'DP1P006',111,1,5,1,0,1);
                --ARMARIO 2
INSERT INTO Prateleira (ID_PRATELEIRA,NUMERO_PRATELEIRA,ID_ARMARIO,ID_TEMPERATURA,ID_TIPO_DIMENSAO,ATIVO,OCUPADO, is_operational) VALUES (7,'DP1P007',112,1,1,1,0,1);
INSERT INTO Prateleira (ID_PRATELEIRA,NUMERO_PRATELEIRA,ID_ARMARIO,ID_TEMPERATURA,ID_TIPO_DIMENSAO,ATIVO,OCUPADO, is_operational) VALUES (8,'DP1P008',112,2,2,1,0,1);
INSERT INTO Prateleira (ID_PRATELEIRA,NUMERO_PRATELEIRA,ID_ARMARIO,ID_TEMPERATURA,ID_TIPO_DIMENSAO,ATIVO,OCUPADO, is_operational) VALUES (9,'DP1P009',112,3,2,1,0,1);
INSERT INTO Prateleira (ID_PRATELEIRA,NUMERO_PRATELEIRA,ID_ARMARIO,ID_TEMPERATURA,ID_TIPO_DIMENSAO,ATIVO,OCUPADO, is_operational) VALUES (10,'DP1P010',112,1,3,1,0,1);
INSERT INTO Prateleira (ID_PRATELEIRA,NUMERO_PRATELEIRA,ID_ARMARIO,ID_TEMPERATURA,ID_TIPO_DIMENSAO,ATIVO,OCUPADO, is_operational) VALUES (11,'DP1P011',112,1,4,1,0,1);
INSERT INTO Prateleira (ID_PRATELEIRA,NUMERO_PRATELEIRA,ID_ARMARIO,ID_TEMPERATURA,ID_TIPO_DIMENSAO,ATIVO,OCUPADO, is_operational) VALUES (12,'DP1P012',112,1,5,1,0,1);
 
  --DROPPOINT 2 --ARMARIO 1
INSERT INTO Prateleira (ID_PRATELEIRA,NUMERO_PRATELEIRA,ID_ARMARIO,ID_TEMPERATURA,ID_TIPO_DIMENSAO,ATIVO,OCUPADO, is_operational) VALUES (13,'DP2P001',121,1,1,1,0,1);
INSERT INTO Prateleira (ID_PRATELEIRA,NUMERO_PRATELEIRA,ID_ARMARIO,ID_TEMPERATURA,ID_TIPO_DIMENSAO,ATIVO,OCUPADO, is_operational) VALUES (14,'DP2P002',121,2,2,1,0,1);
INSERT INTO Prateleira (ID_PRATELEIRA,NUMERO_PRATELEIRA,ID_ARMARIO,ID_TEMPERATURA,ID_TIPO_DIMENSAO,ATIVO,OCUPADO, is_operational) VALUES (15,'DP2P003',121,3,2,1,0,1);
INSERT INTO Prateleira (ID_PRATELEIRA,NUMERO_PRATELEIRA,ID_ARMARIO,ID_TEMPERATURA,ID_TIPO_DIMENSAO,ATIVO,OCUPADO, is_operational) VALUES (16,'DP2P004',121,1,3,1,0,1);
INSERT INTO Prateleira (ID_PRATELEIRA,NUMERO_PRATELEIRA,ID_ARMARIO,ID_TEMPERATURA,ID_TIPO_DIMENSAO,ATIVO,OCUPADO, is_operational) VALUES (17,'DP2P005',121,1,4,1,0,1);
INSERT INTO Prateleira (ID_PRATELEIRA,NUMERO_PRATELEIRA,ID_ARMARIO,ID_TEMPERATURA,ID_TIPO_DIMENSAO,ATIVO,OCUPADO, is_operational) VALUES (18,'DP2P006',121,1,5,1,0,1);  
                --ARMARIO 2
INSERT INTO Prateleira (ID_PRATELEIRA,NUMERO_PRATELEIRA,ID_ARMARIO,ID_TEMPERATURA,ID_TIPO_DIMENSAO,ATIVO,OCUPADO, is_operational) VALUES (19,'DP2P007',122,1,1,1,0,1);
INSERT INTO Prateleira (ID_PRATELEIRA,NUMERO_PRATELEIRA,ID_ARMARIO,ID_TEMPERATURA,ID_TIPO_DIMENSAO,ATIVO,OCUPADO, is_operational) VALUES (20,'DP2P008',122,2,2,1,0,1);
INSERT INTO Prateleira (ID_PRATELEIRA,NUMERO_PRATELEIRA,ID_ARMARIO,ID_TEMPERATURA,ID_TIPO_DIMENSAO,ATIVO,OCUPADO, is_operational) VALUES (21,'DP2P009',122,3,2,1,0,1);
INSERT INTO Prateleira (ID_PRATELEIRA,NUMERO_PRATELEIRA,ID_ARMARIO,ID_TEMPERATURA,ID_TIPO_DIMENSAO,ATIVO,OCUPADO, is_operational) VALUES (22,'DP2P010',122,1,3,1,0,1);
INSERT INTO Prateleira (ID_PRATELEIRA,NUMERO_PRATELEIRA,ID_ARMARIO,ID_TEMPERATURA,ID_TIPO_DIMENSAO,ATIVO,OCUPADO, is_operational) VALUES (23,'DP2P011',122,1,4,1,0,1);
INSERT INTO Prateleira (ID_PRATELEIRA,NUMERO_PRATELEIRA,ID_ARMARIO,ID_TEMPERATURA,ID_TIPO_DIMENSAO,ATIVO,OCUPADO, is_operational) VALUES (24,'DP2P012',122,1,5,1,0,1);

  --DROPPOINT 3 -- ARMARIO 1
INSERT INTO Prateleira (ID_PRATELEIRA,NUMERO_PRATELEIRA,ID_ARMARIO,ID_TEMPERATURA,ID_TIPO_DIMENSAO,ATIVO,OCUPADO, is_operational) VALUES (25,'DP3P001',131,1,1,1,0,1);
INSERT INTO Prateleira (ID_PRATELEIRA,NUMERO_PRATELEIRA,ID_ARMARIO,ID_TEMPERATURA,ID_TIPO_DIMENSAO,ATIVO,OCUPADO, is_operational) VALUES (26,'DP3P002',131,2,2,1,0,1);
INSERT INTO Prateleira (ID_PRATELEIRA,NUMERO_PRATELEIRA,ID_ARMARIO,ID_TEMPERATURA,ID_TIPO_DIMENSAO,ATIVO,OCUPADO, is_operational) VALUES (27,'DP3P003',131,3,2,1,0,1);
INSERT INTO Prateleira (ID_PRATELEIRA,NUMERO_PRATELEIRA,ID_ARMARIO,ID_TEMPERATURA,ID_TIPO_DIMENSAO,ATIVO,OCUPADO, is_operational) VALUES (28,'DP3P004',131,1,3,1,0,1);
INSERT INTO Prateleira (ID_PRATELEIRA,NUMERO_PRATELEIRA,ID_ARMARIO,ID_TEMPERATURA,ID_TIPO_DIMENSAO,ATIVO,OCUPADO, is_operational) VALUES (29,'DP3P005',131,1,4,1,0,1);
INSERT INTO Prateleira (ID_PRATELEIRA,NUMERO_PRATELEIRA,ID_ARMARIO,ID_TEMPERATURA,ID_TIPO_DIMENSAO,ATIVO,OCUPADO, is_operational) VALUES (30,'DP3P006',131,1,5,1,0,1);

  --DROPPOINT 4 -- ARMARIO 1
INSERT INTO Prateleira (ID_PRATELEIRA,NUMERO_PRATELEIRA,ID_ARMARIO,ID_TEMPERATURA,ID_TIPO_DIMENSAO,ATIVO,OCUPADO, is_operational) VALUES (31,'DP4P001',141,1,1,1,0,1);
INSERT INTO Prateleira (ID_PRATELEIRA,NUMERO_PRATELEIRA,ID_ARMARIO,ID_TEMPERATURA,ID_TIPO_DIMENSAO,ATIVO,OCUPADO, is_operational) VALUES (32,'DP4P002',141,2,2,1,0,1);
INSERT INTO Prateleira (ID_PRATELEIRA,NUMERO_PRATELEIRA,ID_ARMARIO,ID_TEMPERATURA,ID_TIPO_DIMENSAO,ATIVO,OCUPADO, is_operational) VALUES (33,'DP4P003',141,3,2,1,0,1);
INSERT INTO Prateleira (ID_PRATELEIRA,NUMERO_PRATELEIRA,ID_ARMARIO,ID_TEMPERATURA,ID_TIPO_DIMENSAO,ATIVO,OCUPADO, is_operational) VALUES (34,'DP4P004',141,1,3,1,0,1);
INSERT INTO Prateleira (ID_PRATELEIRA,NUMERO_PRATELEIRA,ID_ARMARIO,ID_TEMPERATURA,ID_TIPO_DIMENSAO,ATIVO,OCUPADO, is_operational) VALUES (35,'DP4P005',141,1,4,1,0,1);
INSERT INTO Prateleira (ID_PRATELEIRA,NUMERO_PRATELEIRA,ID_ARMARIO,ID_TEMPERATURA,ID_TIPO_DIMENSAO,ATIVO,OCUPADO, is_operational) VALUES (36,'DP4P006',141,1,5,1,0,1);

                --ARMARIO 2
INSERT INTO Prateleira (ID_PRATELEIRA,NUMERO_PRATELEIRA,ID_ARMARIO,ID_TEMPERATURA,ID_TIPO_DIMENSAO,ATIVO,OCUPADO, is_operational) VALUES (37,'DP4P007',142,1,1,1,0,1);
INSERT INTO Prateleira (ID_PRATELEIRA,NUMERO_PRATELEIRA,ID_ARMARIO,ID_TEMPERATURA,ID_TIPO_DIMENSAO,ATIVO,OCUPADO, is_operational) VALUES (38,'DP4P008',142,2,2,1,0,1);
INSERT INTO Prateleira (ID_PRATELEIRA,NUMERO_PRATELEIRA,ID_ARMARIO,ID_TEMPERATURA,ID_TIPO_DIMENSAO,ATIVO,OCUPADO, is_operational) VALUES (39,'DP4P009',142,3,2,1,0,1);
INSERT INTO Prateleira (ID_PRATELEIRA,NUMERO_PRATELEIRA,ID_ARMARIO,ID_TEMPERATURA,ID_TIPO_DIMENSAO,ATIVO,OCUPADO, is_operational) VALUES (40,'DP4P010',142,1,3,1,0,1);
INSERT INTO Prateleira (ID_PRATELEIRA,NUMERO_PRATELEIRA,ID_ARMARIO,ID_TEMPERATURA,ID_TIPO_DIMENSAO,ATIVO,OCUPADO, is_operational) VALUES (41,'DP4P011',142,1,4,1,0,1);
INSERT INTO Prateleira (ID_PRATELEIRA,NUMERO_PRATELEIRA,ID_ARMARIO,ID_TEMPERATURA,ID_TIPO_DIMENSAO,ATIVO,OCUPADO, is_operational) VALUES (42,'DP4P012',142,1,5,1,0,1);

---Tokens
    --TIPO TOKENS
INSERT INTO Tipo_Token (ID_TIPO_TOKEN, DESCRICAO) VALUES (1, 'Estafeta');
INSERT INTO Tipo_Token (ID_TIPO_TOKEN,DESCRICAO) VALUES (2, 'Cliente');
INSERT INTO Tipo_Token (ID_TIPO_TOKEN,DESCRICAO) VALUES (3, 'Colaborador');

--RESERVAS
INSERT INTO Reserva(ID_RESERVA,ID_CLIENTE,ID_DROPPOINT,ID_TEMPERATURA,ID_TIPO_DIMENSAO) VALUES (1,1,1,1,1);
INSERT INTO Reserva(ID_RESERVA,ID_CLIENTE,ID_DROPPOINT,ID_TEMPERATURA,ID_TIPO_DIMENSAO) VALUES (2,2,1,2,2);
INSERT INTO Reserva(ID_RESERVA,ID_CLIENTE,ID_DROPPOINT,ID_TEMPERATURA,ID_TIPO_DIMENSAO) VALUES (3,3,1,3,2);
INSERT INTO Reserva(ID_RESERVA,ID_CLIENTE,ID_DROPPOINT,ID_TEMPERATURA,ID_TIPO_DIMENSAO) VALUES (4,4,1,1,3);
INSERT INTO Reserva(ID_RESERVA,ID_CLIENTE,ID_DROPPOINT,ID_TEMPERATURA,ID_TIPO_DIMENSAO) VALUES (5,5,2,1,1);
INSERT INTO Reserva(ID_RESERVA,ID_CLIENTE,ID_DROPPOINT,ID_TEMPERATURA,ID_TIPO_DIMENSAO) VALUES (6,6,2,2,2);
INSERT INTO Reserva(ID_RESERVA,ID_CLIENTE,ID_DROPPOINT,ID_TEMPERATURA,ID_TIPO_DIMENSAO) VALUES (7,6,2,1,1);
INSERT INTO Reserva(ID_RESERVA,ID_CLIENTE,ID_DROPPOINT,ID_TEMPERATURA,ID_TIPO_DIMENSAO) VALUES (8,6,2,3,2);
INSERT INTO Reserva(ID_RESERVA,ID_CLIENTE,ID_DROPPOINT,ID_TEMPERATURA,ID_TIPO_DIMENSAO) VALUES (9,6,2,1,3);
INSERT INTO Reserva(ID_RESERVA,ID_CLIENTE,ID_DROPPOINT,ID_TEMPERATURA,ID_TIPO_DIMENSAO) VALUES (10,5,2,1,4);
INSERT INTO Reserva(ID_RESERVA,ID_CLIENTE,ID_DROPPOINT,ID_TEMPERATURA,ID_TIPO_DIMENSAO) VALUES (11,4,2,1,5);
INSERT INTO Reserva(ID_RESERVA,ID_CLIENTE,ID_DROPPOINT,ID_TEMPERATURA,ID_TIPO_DIMENSAO) VALUES (12,3,2,2,2);

---Tokens
    --TOKENS
    --Reserva 1 - entrega
INSERT INTO Token (ID_TOKEN,DATA_GERACAO,DATA_VALIDADE,ID_TIPO_TOKEN,ID_RESERVA,CODIGO,ATIVO) 
  VALUES (1,TO_DATE('20-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),TO_DATE('27-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),1,1,'KRCf34t5',1);
    --Reserva 1 - recolha
INSERT INTO Token (ID_TOKEN,DATA_GERACAO,DATA_VALIDADE,ID_TIPO_TOKEN,ID_RESERVA,CODIGO,ATIVO) 
  VALUES (2,TO_DATE('20-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),TO_DATE('27-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),2,1,'ghTY85RF',1);

    --Reserva 2 - entrega
INSERT INTO Token (ID_TOKEN,DATA_GERACAO,DATA_VALIDADE,ID_TIPO_TOKEN,ID_RESERVA,CODIGO,ATIVO) 
  VALUES (3,TO_DATE('20-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),TO_DATE('27-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),1,2,'LKO43qRF',1);
    --Reserva 2 - recolha
  INSERT INTO Token (ID_TOKEN,DATA_GERACAO,DATA_VALIDADE,ID_TIPO_TOKEN,ID_RESERVA,CODIGO,ATIVO) 
  VALUES (4,TO_DATE('20-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),TO_DATE('27-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),2,2,'uy56ghVC',1);
  
      --Reserva 3 - entrega
  INSERT INTO Token (ID_TOKEN,DATA_GERACAO,DATA_VALIDADE,ID_TIPO_TOKEN,ID_RESERVA,CODIGO,ATIVO) 
  VALUES (5,TO_DATE('20-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),TO_DATE('27-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),1,3,'76tfcdGB',1);
       --Reserva 3 - recolha
  INSERT INTO Token (ID_TOKEN,DATA_GERACAO,DATA_VALIDADE,ID_TIPO_TOKEN,ID_RESERVA,CODIGO,ATIVO) 
  VALUES (6,TO_DATE('20-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),TO_DATE('27-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),2,3,'tr578fgV',1);
  
      --Reserva 4 - entrega
  INSERT INTO Token (ID_TOKEN,DATA_GERACAO,DATA_VALIDADE,ID_TIPO_TOKEN,ID_RESERVA,CODIGO,ATIVO) 
  VALUES (7,TO_DATE('20-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),TO_DATE('27-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),1,4,'DFgk76yg',1);
    --Reserva 4 - recolha
  INSERT INTO Token (ID_TOKEN,DATA_GERACAO,DATA_VALIDADE,ID_TIPO_TOKEN,ID_RESERVA,CODIGO,ATIVO) 
  VALUES (8,TO_DATE('20-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),TO_DATE('27-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),2,4,'09utf4XZ',1);
  
      --Reserva 5 - entrega
  INSERT INTO Token (ID_TOKEN,DATA_GERACAO,DATA_VALIDADE,ID_TIPO_TOKEN,ID_RESERVA,CODIGO,ATIVO) 
  VALUES (9,TO_DATE('20-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),TO_DATE('27-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),1,5,'az34rtDD',1);
    --Reserva 5 - recolha
  INSERT INTO Token (ID_TOKEN,DATA_GERACAO,DATA_VALIDADE,ID_TIPO_TOKEN,ID_RESERVA,CODIGO,ATIVO) 
  VALUES (10,TO_DATE('20-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),TO_DATE('27-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),2,5,'wwe67GV',1);
  
      --Reserva 6 - entrega
  INSERT INTO Token (ID_TOKEN,DATA_GERACAO,DATA_VALIDADE,ID_TIPO_TOKEN,ID_RESERVA,CODIGO,ATIVO) 
  VALUES (11,TO_DATE('20-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),TO_DATE('27-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),1,6,'plk3kwDC',1);
    --Reserva 6 - recolha
  INSERT INTO Token (ID_TOKEN,DATA_GERACAO,DATA_VALIDADE,ID_TIPO_TOKEN,ID_RESERVA,CODIGO,ATIVO) 
  VALUES (12,TO_DATE('20-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),TO_DATE('27-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),2,6,'po5g6dSP',1);
  
        --Reserva 7 - entrega
  INSERT INTO Token (ID_TOKEN,DATA_GERACAO,DATA_VALIDADE,ID_TIPO_TOKEN,ID_RESERVA,CODIGO,ATIVO) 
  VALUES (13,TO_DATE('20-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),TO_DATE('27-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),1,7,'plk3kw4F',1);
    --Reserva 7 - recolha
  INSERT INTO Token (ID_TOKEN,DATA_GERACAO,DATA_VALIDADE,ID_TIPO_TOKEN,ID_RESERVA,CODIGO,ATIVO) 
  VALUES (14,TO_DATE('20-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),TO_DATE('27-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),2,7,'po5g6dlo',1);
  
        --Reserva 8 - entrega
  INSERT INTO Token (ID_TOKEN,DATA_GERACAO,DATA_VALIDADE,ID_TIPO_TOKEN,ID_RESERVA,CODIGO,ATIVO) 
  VALUES (15,TO_DATE('20-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),TO_DATE('27-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),1,8,'p4frkwDC',1);
    --Reserva 8 - recolha
  INSERT INTO Token (ID_TOKEN,DATA_GERACAO,DATA_VALIDADE,ID_TIPO_TOKEN,ID_RESERVA,CODIGO,ATIVO) 
  VALUES (16,TO_DATE('20-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),TO_DATE('27-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),2,8,'y6Rg6dSP',1);
  
        --Reserva 9 - entrega
  INSERT INTO Token (ID_TOKEN,DATA_GERACAO,DATA_VALIDADE,ID_TIPO_TOKEN,ID_RESERVA,CODIGO,ATIVO) 
  VALUES (17,TO_DATE('20-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),TO_DATE('27-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),1,9,'jKLOkwDC',1);
    --Reserva 9 - recolha
  INSERT INTO Token (ID_TOKEN,DATA_GERACAO,DATA_VALIDADE,ID_TIPO_TOKEN,ID_RESERVA,CODIGO,ATIVO) 
  VALUES (18,TO_DATE('20-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),TO_DATE('27-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),2,9,'po5g6HGS',1);
  
        --Reserva 10 - entrega
  INSERT INTO Token (ID_TOKEN,DATA_GERACAO,DATA_VALIDADE,ID_TIPO_TOKEN,ID_RESERVA,CODIGO,ATIVO) 
  VALUES (19,TO_DATE('20-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),TO_DATE('27-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),1,10,'plk3kwDC',1);
    --Reserva 10 - recolha
  INSERT INTO Token (ID_TOKEN,DATA_GERACAO,DATA_VALIDADE,ID_TIPO_TOKEN,ID_RESERVA,CODIGO,ATIVO) 
  VALUES (20,TO_DATE('20-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),TO_DATE('27-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),2,10,'po5g6dSP',1);
  
        --Reserva 11 - entrega
  INSERT INTO Token (ID_TOKEN,DATA_GERACAO,DATA_VALIDADE,ID_TIPO_TOKEN,ID_RESERVA,CODIGO,ATIVO) 
  VALUES (21,TO_DATE('20-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),TO_DATE('27-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),1,11,'plKDJwDC',1);
    --Reserva 11 - recolha
  INSERT INTO Token (ID_TOKEN,DATA_GERACAO,DATA_VALIDADE,ID_TIPO_TOKEN,ID_RESERVA,CODIGO,ATIVO) 
  VALUES (22,TO_DATE('20-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),TO_DATE('27-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),2,11,'poMjhdSe',1);
  
    --Reserva 12 - entrega
  INSERT INTO Token (ID_TOKEN,DATA_GERACAO,DATA_VALIDADE,ID_TIPO_TOKEN,ID_RESERVA,CODIGO,ATIVO) 
  VALUES (23,TO_DATE('20-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),TO_DATE('27-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),1,12,'plKDJwDC',1);
    --Reserva 12 - recolha
  INSERT INTO Token (ID_TOKEN,DATA_GERACAO,DATA_VALIDADE,ID_TIPO_TOKEN,ID_RESERVA,CODIGO,ATIVO) 
  VALUES (24,TO_DATE('20-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),TO_DATE('27-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),2,12,'poMjhdSe',1);
  
 
  
 
  
 
  
 
  
 

  INSERT INTO Token (ID_TOKEN,DATA_GERACAO,DATA_VALIDADE,ID_TIPO_TOKEN,ID_RESERVA,CODIGO,ATIVO) 
  VALUES (23,TO_DATE('20-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),TO_DATE('27-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),3,null,'3pW45xf7Q',1);

--  ENTREGAS
INSERT INTO ENTREGA (ID_ENTREGA,ID_PRATELEIRA,ID_RESERVA,ID_TOKEN_ESTAFETA,ID_TOKEN_COLABORADOR,DATA_ABRE_PRATELEIRA,DATA_FECHA_PRATELEIRA) 
  VALUES (1,1,1,1,13,TO_DATE('21-10-2015 15:00', 'dd-mm-yyyy HH24:MI'),TO_DATE('21-10-2015 15:10', 'dd-mm-yyyy HH24:MI'));

INSERT INTO ENTREGA (ID_ENTREGA,ID_PRATELEIRA,ID_RESERVA,ID_TOKEN_ESTAFETA,ID_TOKEN_COLABORADOR,DATA_ABRE_PRATELEIRA,DATA_FECHA_PRATELEIRA) 
  VALUES (2,2,2,3,13,TO_DATE('22-10-2015 14:00', 'dd-mm-yyyy HH24:MI'),TO_DATE('22-10-2015 14:10', 'dd-mm-yyyy HH24:MI'));

INSERT INTO ENTREGA (ID_ENTREGA,ID_PRATELEIRA,ID_RESERVA,ID_TOKEN_ESTAFETA,ID_TOKEN_COLABORADOR,DATA_ABRE_PRATELEIRA,DATA_FECHA_PRATELEIRA) 
  VALUES (3,3,3,5,13,TO_DATE('23-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),TO_DATE('23-10-2015 17:10', 'dd-mm-yyyy HH24:MI'));
  
INSERT INTO ENTREGA (ID_ENTREGA,ID_PRATELEIRA,ID_RESERVA,ID_TOKEN_ESTAFETA,ID_TOKEN_COLABORADOR,DATA_ABRE_PRATELEIRA,DATA_FECHA_PRATELEIRA) 
  VALUES (4,13,5,9,10,TO_DATE('23-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),TO_DATE('23-10-2015 17:10', 'dd-mm-yyyy HH24:MI'));
  
INSERT INTO ENTREGA (ID_ENTREGA,ID_PRATELEIRA,ID_RESERVA,ID_TOKEN_ESTAFETA,ID_TOKEN_COLABORADOR,DATA_ABRE_PRATELEIRA,DATA_FECHA_PRATELEIRA) 
  VALUES (5,14,6,11,12,TO_DATE('23-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),TO_DATE('23-10-2015 17:10', 'dd-mm-yyyy HH24:MI'));
  
INSERT INTO ENTREGA (ID_ENTREGA,ID_PRATELEIRA,ID_RESERVA,ID_TOKEN_ESTAFETA,ID_TOKEN_COLABORADOR,DATA_ABRE_PRATELEIRA,DATA_FECHA_PRATELEIRA) 
  VALUES (6,19,7,13,14,TO_DATE('23-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),TO_DATE('23-10-2015 17:10', 'dd-mm-yyyy HH24:MI'));
  
INSERT INTO ENTREGA (ID_ENTREGA,ID_PRATELEIRA,ID_RESERVA,ID_TOKEN_ESTAFETA,ID_TOKEN_COLABORADOR,DATA_ABRE_PRATELEIRA,DATA_FECHA_PRATELEIRA) 
  VALUES (7,15,8,15,16,TO_DATE('23-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),TO_DATE('23-10-2015 17:10', 'dd-mm-yyyy HH24:MI'));
  
INSERT INTO ENTREGA (ID_ENTREGA,ID_PRATELEIRA,ID_RESERVA,ID_TOKEN_ESTAFETA,ID_TOKEN_COLABORADOR,DATA_ABRE_PRATELEIRA,DATA_FECHA_PRATELEIRA) 
  VALUES (8,16,9,17,18,TO_DATE('23-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),TO_DATE('23-10-2015 17:10', 'dd-mm-yyyy HH24:MI'));
  
INSERT INTO ENTREGA (ID_ENTREGA,ID_PRATELEIRA,ID_RESERVA,ID_TOKEN_ESTAFETA,ID_TOKEN_COLABORADOR,DATA_ABRE_PRATELEIRA,DATA_FECHA_PRATELEIRA) 
  VALUES (9,17,10,19,20,TO_DATE('23-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),TO_DATE('23-10-2015 17:10', 'dd-mm-yyyy HH24:MI'));
  
INSERT INTO ENTREGA (ID_ENTREGA,ID_PRATELEIRA,ID_RESERVA,ID_TOKEN_ESTAFETA,ID_TOKEN_COLABORADOR,DATA_ABRE_PRATELEIRA,DATA_FECHA_PRATELEIRA) 
  VALUES (10,18,11,21,22,TO_DATE('23-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),TO_DATE('23-10-2015 17:10', 'dd-mm-yyyy HH24:MI'));
  
INSERT INTO ENTREGA (ID_ENTREGA,ID_PRATELEIRA,ID_RESERVA,ID_TOKEN_ESTAFETA,ID_TOKEN_COLABORADOR,DATA_ABRE_PRATELEIRA,DATA_FECHA_PRATELEIRA) 
  VALUES (11,20,12,23,24,TO_DATE('23-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),TO_DATE('23-10-2015 17:10', 'dd-mm-yyyy HH24:MI'));

-- TEAM TYPEs
INSERT INTO TEAM_TYPE (ID_TEAM_TYPE,TEAM_TYPE)
  VALUES (1,'Maintenance');
  
INSERT INTO TEAM_TYPE (ID_TEAM_TYPE,TEAM_TYPE)
  VALUES (2,'Repair');
  
-- TEAMs

INSERT INTO Team (id_Team, id_Team_Type)
  VALUES (1, 1);
  
INSERT INTO Team (id_Team, id_Team_Type)
  VALUES (2, 1);
  
-- EMPLOYEES
INSERT INTO Employee (e_number, cpassword, e_name, id_team)
  VALUES(1601091, 'qwerty', 'Alfredo',1);
  
INSERT INTO Employee (e_number, cpassword, e_name, id_team)
  VALUES(1601092, 'qwerty', 'Joaquim',1);
  
-- MAINTENANCE TASKS  
INSERT INTO MAINTENANCE_TASK (ID_TASK,DESCRIPTION,AVG_DURATION)
	VALUES (1,'Check Locks',10);

INSERT INTO MAINTENANCE_TASK (ID_TASK,DESCRIPTION,AVG_DURATION)
	VALUES (2,'Clean Cells',15);
	
INSERT INTO MAINTENANCE_TASK (ID_TASK,DESCRIPTION,AVG_DURATION)
	VALUES (3,'Check Console',5);

-- DROPPOINT MAINTENANCE PREEMPTIVE PLANs
-- DP 1
INSERT INTO Preemptive_DP_Plan (id_pre_plan,id_DropPoint,id_task)
	VALUES (1,1,1);
	
INSERT INTO Preemptive_DP_Plan (id_pre_plan,id_DropPoint,id_task)
	VALUES (2,1,2);

INSERT INTO Preemptive_DP_Plan (id_pre_plan,id_DropPoint,id_task)
	VALUES (3,1,3);
  
-- DP 2
INSERT INTO Preemptive_DP_Plan (id_pre_plan,id_DropPoint,id_task)
	VALUES (4,2,2);
	
INSERT INTO Preemptive_DP_Plan (id_pre_plan,id_DropPoint,id_task)
	VALUES (5,2,3);

-- DP 3
INSERT INTO Preemptive_DP_Plan (id_pre_plan,id_DropPoint,id_task)
	VALUES (6,3,2);

-- MAINTENANCE_PLAN
INSERT INTO Maintenance_Plan (id_Maint_Plan,id_Maint_Team,maint_Plan_Date)
	VALUES (1,1,TO_DATE('10-01-2016', 'dd-mm-yyyy'));
	
-- Incident Types
INSERT INTO Incident_type ( id_Incident_Type, description)
	VALUES(seq_id_incident_type.nextval, 'Rust in hinge');
	
INSERT INTO Incident_type ( id_Incident_Type, description)
	VALUES(seq_id_incident_type.nextval, 'Broken Door');
	
INSERT INTO Incident_type ( id_Incident_Type, description)
	VALUES(seq_id_incident_type.nextval, 'Broken lock');
	
-- Incidents
INSERT INTO Incident (id_Incident, id_Incident_Type, id_prateleira, incident_date, reporter)
VALUES (seq_id_incident.nextval,1,42,TO_DATE('23-10-2015 17:00', 'dd-mm-yyyy HH24:MI'),1601091);
	
COMMIT;

 
/*
---SELECT•S SPRINT 4
--Select's Ocupa¡Ño
    --Total prateleiras de um droppoint
SELECT COUNT(ID_PRATELEIRA) FROM (DROPPOINT JOIN (Armario JOIN Prateleira USING (ID_ARMARIO)) USING (ID_DROPPOINT)) WHERE ID_DROPPOINT = 1;
    --Total de prateleiras ocupadas
select COUNT(ID_PRATELEIRA) from PRATELEIRA p, ARMARIO a
where p.OCUPADO = 1 
and p.ID_ARMARIO = a.ID_ARMARIO
and a.ID_DROPPOINT = 1;

-- Listar DropPoints
SELECT droppoint.ID_DROPPOINT, Morada.RUA 
FROM DropPoint 
INNER JOIN Morada 
ON Morada.ID_MORADA = DropPoint.ID_MORADA;

--Select Entrega
select e.DATA_FECHA_PRATELEIRA, p.ID_PRATELEIRA, e.ID_TOKEN_ESTAFETA 
  from entrega e, prateleira p, armario a, droppoint d
 where e.ID_PRATELEIRA = p.ID_PRATELEIRA
   and p.ID_ARMARIO = a.ID_ARMARIO
   and a.ID_DROPPOINT = d.ID_DROPPOINT
   and d.ID_DROPPOINT = 1;

-- Select Recolhas
select r.DATA_FECHA_PRATELEIRA, p.ID_PRATELEIRA, r.ID_TOKEN_CLIENTE 
  from recolha r, entrega e, prateleira p, armario a, droppoint d
 where r.ID_ENTREGA = e.ID_ENTREGA
   and e.ID_PRATELEIRA = p.ID_PRATELEIRA
   and p.ID_ARMARIO = a.ID_ARMARIO
   and a.ID_DROPPOINT = d.ID_DROPPOINT
   and d.ID_DROPPOINT = 1;

--SELECT•S SPRINT 5
  ---ABRIR PRATELEIRA  - Estafeta
  select p.NUMERO_PRATELEIRA,p.ID_PRATELEIRA
  from prateleira p, reserva r, armario a
  where p.ocupado = 0
    and p.id_temperatura = r.ID_TEMPERATURA and p.id_tipo_dimensao = r.ID_TIPO_DIMENSAO
    and p.id_armario = a.id_armario
    and a.id_droppoint = 1 and a.id_droppoint = r.id_droppoint
    and exists(select *
                 from reserva r, token t
                where r.id_temperatura = p.id_temperatura and r.id_tipo_dimensao = p.id_tipo_dimensao
                  and r.id_reserva = t.id_reserva
                  and t.codigo = 'qwe123' and t.ATIVO = 1)
    and ROWNUM = 1;

  ---ABRIR PRATELEIRA  - Cliente
  select p.NUMERO_PRATELEIRA,p.ID_PRATELEIRA
  from prateleira p, entrega e, reserva r, TOKEN T
  where p.ocupado = 1
  and p.ID_PRATELEIRA = e.ID_PRATELEIRA
  and e.ID_RESERVA = r.ID_RESERVA
  and r.ID_RESERVA = T.ID_RESERVA
  and T.CODIGO = '123abc' and T.ATIVO = 1;
  
  -- OBTER TIPO DE TOKEN
  select t.*,k.DESCRICAO
  from TOKEN t, TIPO_TOKEN k
  where t.ATIVO = 1
  and t.ID_TIPO_TOKEN = k.ID_TIPO_TOKEN
  and t.CODIGO = '123abc';
  
  --- OBTER ID PROXIMO CLIENTE
  select nvl(max(id_cliente),0)+1 from cliente;
  
  create or replace TRIGGER TRG_CLIENTE_AI 
before INSERT ON CLIENTE 
for each row
BEGIN
  select nvl(max(id_cliente), 0)+1 into :new.id_cliente
    from cliente;
END;

create or replace function teste return int is
  v_ret number;
begin
  select nvl(max(id_cliente), 0)+1 into v_ret
    from cliente;
  return v_ret;
end;

select t.id_token, a.descricao, t.id_reserva from token t, tipo_token a where t.id_tipo_token = a.id_tipo_token and t.codigo = 'KRCf34t5';
select p.NUMERO_PRATELEIRA,p.ID_PRATELEIRA
from prateleira p, reserva r, armario a
where p.ocupado = 0
and p.id_temperatura = r.ID_TEMPERATURA and p.id_tipo_dimensao = r.ID_TIPO_DIMENSAO
              and p.id_armario = a.id_armario
           and a.id_droppoint = 1 and a.id_droppoint = r.id_droppoint
             and exists(select *
                  from reserva r, token t
             where r.id_temperatura = p.id_temperatura and r.id_tipo_dimensao = p.id_tipo_dimensao
 and r.id_reserva = t.id_reserva
and t.codigo = 'LKO43qRF' and t.ATIVO = 1)
and ROWNUM = 1;*/
