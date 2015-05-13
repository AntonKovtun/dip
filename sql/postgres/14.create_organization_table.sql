CREATE TABLE ORGANIZATION (
  ORGANIZATION_ID character varying(32) PRIMARY KEY,
  NAME character varying(255) NOT NULL,
  DESCRIPTION character varying(255),
  STATUS character varying(32) NOT NULL,
  PHONE character varying(64),
  ADDRESS character varying(255),
  LOCATION_ID character varying(32) NOT NULL REFERENCES LOCATION(LOCATION_ID)
) WITH (OIDS=FALSE);