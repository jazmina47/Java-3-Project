/* CSC43610 Java 3 Project, FA2016 */
/* SQL Server: MySQL  */

-- -----------------------------------------------------
-- Table `healthclinic_db`.`PATIENT`
-- -----------------------------------------------------

DROP TABLE IF EXISTS PATIENT;

CREATE TABLE PATIENT (
	PATIENT_ID varchar(5),
	P_PASSWORD varchar(20) not null,
	F_NAME varchar(25) not null,
	L_NAME varchar(25) not null,
	SSN	varchar(9) not null,
	ADDRESS varchar(30),
	ADDR_CITY varchar(20),
	ADDR_ZIP varchar(10),
	ADDR_COUNTY varchar(20),
	PHONE_NUM char(10),
	DOB varchar(20),
	GENDER varchar(20),
	IMMUN_STATUS varchar(15),
	EMRG_CONTACT varchar(50),
	EMRG_C_REL varchar(15),
	EMGR_PHONE varchar(10),
	INSURANCE varchar(50),
	APPT_DATE varchar(30),
	COMPLAINT varchar(100),
	CONSTRAINT pkCUSTOMER PRIMARY KEY (PATIENT_ID) 
);

/* Inserting patients */

INSERT INTO PATIENT (PATIENT_ID, P_PASSWORD, F_NAME, L_NAME, SSN, ADDRESS, ADDR_CITY, ADDR_ZIP, ADDR_COUNTY, PHONE_NUM, DOB, GENDER, IMMUN_STATUS, EMRG_CONTACT, EMRG_C_REL, EMGR_PHONE,
INSURANCE, APPT_DATE, COMPLAINT)
VALUES ('00001', 'mypsd1234', 'John','Doe','400103400', '123 Elm Street', 'Aurora', '60503', 'Kane', '6302341030', '01-Jan-1990', 'MALE', 'Up to Date','Mary Jane','SPOUSE', '6301020980', 'Medicaid #102023', '10-Apr-2016', 'Abdomal pain');


-- ------------------------------------------------------------------------------------------------------------
-- Tables if we want to seperate patient from its contact info into: PATIENT and PATIENT_CONTACT_INFO
-- The following two tables are not functioning as is yet. Needs to be tested ...  
-- ------------------------------------------------------------------------------------------------------------

/*

DROP TABLE IF EXISTS PATIENT;

CREATE TABLE PATIENT (
	PATIENT_ID int not null AUTO_INCREMENT,
	P_PASSWORD varchar(20) not null,
	FNAME varchar(25) not null,
	LNAME varchar(25) not null,
	SSN	char(9) not null,
	DOB varchar(20),
	GENDER varchar(20),
	IMMUN_STATUS varchar(15),
	EMRG_CONTACT varchar(50),
	EMRG_C_REL varchar(15),
	EMGR_PHONE varchar(10),
	INSURANCE varchar(50),
	CONSTRAINT pkCUSTOMER PRIMARY KEY (PATIENT_ID) 
);


INSERT INTO PATIENT (PATIENT_ID, F_NAME, L_NAME, SSN, DOB, GENDER, IMMUN_STATUS, EMRG_CONTACT, EMRG_C_REL, EMGR_PHONE, INSURANCE)
VALUES (DEFAULT, 'John', 'Doe','400103400', '01-Jan-1990', 'Male', 'Up to Date','Mary Jane','Spouse', '6301020980', 'Medicaid #102023');


DROP TABLE IF EXISTS PATIENT_CONTACT_INFO;

CREATE TABLE PATIENT_CONTACT_INFO (
	PATIENT_ID int not null,
	FNAME varchar(25) not null,
	LNAME varchar(25) not null,
	ADDRESS varchar(30),
	ADDR_CITY varchar(20),
	ADDR_ZIP varchar(10),
	ADDR_COUNTY varchar(20),
	PHONE_NUM char(10),
	FOREIGN KEY (PATIENT_ID) reference (PATIENT),
	FOREIGN KEY	(FNAME) references (PATIENT),
	FOREIGN KEY (LNAME) references (PATIENT)
);

INSERT INTO PATIENT_CONTACT_INFO (PATIENT_ID, F_NAME, L_NAME, ADDRESS, ADDR_CITY, ADDR_ZIP, ADDR_COUNTY, PHONE_NUM)
VALUES (DEFAULT, John, Doe, '123 Elm Street', 'Aurora', '60503', 'Kane', '6302341030');


*/

-- -----------------------------------------------------
-- Table `healthclinic_db`.`EMPLOYEE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS EMPLOYEE;

CREATE TABLE EMPLOYEE (
	EMP_ID varchar(5),
	PASSWORD varchar(25) not null,
	FNAME varchar(25) not null,
	LNAME varchar(25) not null,
	SSN varchar(9) not null,
	DOB varchar(20),
	GENDER varchar(20),
	TITLE varchar(45), /* POSITION in clinic: Doctor, Nurse, Receptionist, Administrator */
  	SPECIALTY varchar(45),
  	SALARY varchar(10),

  	CONSTRAINT pkEMPLOYEE PRIMARY KEY (EMP_ID) 
);

INSERT INTO EMPLOYEE (EMP_ID, PASSWORD, FNAME, LNAME, SSN, DOB, GENDER, TITLE, SPECIALTY, SALARY) 
VALUES ('10001', 'emp01', 'David', 'Cook', '345985100', '04-Mar-1980', 'Male', 'Receptionist', 'Front-desk', '$24,000');


-- -----------------------------------------------------
-- Table `healthclinic_db`.`APPOINTMENTS`
-- -----------------------------------------------------

DROP TABLE IF EXISTS APPOINTMENTS;

CREATE TABLE APPOINTMENTS (
	APPT_ID varchar(5) not null AUTO_INCREMENT,
	fkPATIENT_ID varchar(25),
	fkEMP_ID varchar(25),
	APPT_DATE varchar(15),
	APPT_TIME varchar(15),
	COMPLAINT varchar(100),

	CONSTRAINT pkAPPT_ID PRIMARY KEY (APPT_ID),
	CONSTRAINT fkPATIENT_ID FOREIGN KEY (fkPATIENT_ID) REFERENCES PATIENT(PATIENT_ID),
	CONSTRAINT fkEMP_ID FOREIGN KEY (fkEMP_ID) REFERENCES EMPLOYEE(EMP_ID)
);

-- -----------------------------------------------------
-- Table `healthclinic_db`.`BILLING`
-- -----------------------------------------------------

DROP TABLE IF EXISTS BILLING;

CREATE TABLE BILLING (
	BILL_ID varchar(25),
	BILL_AMOUNT varchar(25),
	fkPATIENT_ID varchar(25),


	CONSTRAINT pkBILL_ID PRIMARY KEY (BILL_ID),
	CONSTRAINT fkPATIENT_ID FOREIGN KEY (fkPATIENT_ID) REFERENCES PATIENT(PATIENT_ID)
);

-- -----------------------------------------------------
-- Table `healthclinic_db`.`DIAGNOSIS`
-- -----------------------------------------------------




