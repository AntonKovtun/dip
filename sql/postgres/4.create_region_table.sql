CREATE TABLE REGION (
  REGION_ID character varying(32) PRIMARY KEY,
  NAME character varying(255) NOT NULL,
  DESCRIPTION character varying(512),
  STATUS character varying(32) NOT NULL,
  COUNTRY_ID character varying(32) NOT NULL REFERENCES COUNTRY(COUNTRY_ID)
) WITH (OIDS=FALSE);

