CREATE TABLE BILL_USER (
  BILL_USER_ID character varying(32) PRIMARY KEY,
  NAME character varying(255) NOT NULL,
  DESCRIPTION character varying(255),
  STATUS character varying(32) NOT NULL,
  STATUS_BILL character varying(32) NOT NULL,
  USER_PHONE character varying(32) NOT NULL,
  AMOUNT DOUBLE PRECISION NOT NULL,
  CCY character varying(32) NOT NULL,
  COMMENT character varying(32),
  LIFETIME timestamp,
  PAY_SOURCE character varying(32),
  PRV_NAME character varying(32),
  PRV_ID character varying(32),
  USER_ID character varying(32) REFERENCES USERS(USER_ID)
) WITH (OIDS=FALSE);