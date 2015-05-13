CREATE TABLE COUNTRY (
  COUNTRY_ID character varying(32) PRIMARY KEY,
  NAME character varying(255),
  DESCRIPTION character varying(255),
  STATUS character varying(32)
) WITH (OIDS=FALSE);