CREATE TABLE DISCIPLINE (
  DISCIPLINE_ID character varying(32) PRIMARY KEY,
  NAME character varying(32) NOT NULL,
  DESCRIPTION character varying(512),
  STATUS character varying(32) NOT NULL
) WITH (OIDS=FALSE);