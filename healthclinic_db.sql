/* CSC43610 Java 3 Project, FA2016 */
/* Type of SQL : MySQL  */

DROP TABLE IF EXISTS PATIENT;

CREATE TABLE PATIENT (
	PATIENT_ID int not null AUTO_INCREMENT,
	P_USERNAME varchar(20) not null,
	P_PASSWORD varchar(20) not null,
	F_NAME varchar(25) not null,
	L_NAME varchar(25) not null,
	SSN	char(9) not null,
	ADDRESS varchar(30),
	ADDR_CITY varchar(20),
	ADDR_ZIP varchar(10),
	ADDR_COUNTY varchar(20),
	PHONE_NUM char(10),
	DOB varchar(20),
	GENDER varchar(10),
	IMMUN_STATUS varchar(15),
	EMRG_CONTACT varchar(50),
	EMRG_C_REL varchar(15),
	EMGR_PHONE varchar(10),
	INSURANCE varchar(50),
	APPT_DATE varchar(30),
	COMPLAINT varchar(100),
	CONSTRAINT pkCUSTOMER PRIMARY KEY (PATIENT_ID) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO PATIENT (PATIENT_ID, P_USERNAME, P_PASSWORD, F_NAME, L_NAME, SSN, ADDRESS, ADDR_CITY, ADDR_ZIP, ADDR_COUNTY, PHONE_NUM, DOB, GENDER, IMMUN_STATUS, EMRG_CONTACT, EMRG_C_REL, EMGR_PHONE,
INSURANCE, APPT_DATE, COMPLAINT)
VALUES (DEFAULT , 'jdoe01', 'mypsd1234', 'John','Doe','400103400', '123 Elm Street', 'Aurora', '60503', 'Kane', '6302341030', '01-Jan-1990', 'MALE', 'Up to Date','Mary Jane','SPOUSE', '6301020980', 'Medicaid #102023', '10-Apr-2016', 'Abdomal pain');
