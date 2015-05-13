CREATE TABLE DISTRICT (
  DISTRICT_ID character varying(32) PRIMARY KEY,
  REGION_ID character varying(32) NOT NULL REFERENCES REGION(REGION_ID),
  NAME character varying(255) NOT NULL,
  DESCRIPTION character varying(255),
  STATUS character varying(32) NOT NULL
) WITH (OIDS=FALSE);

