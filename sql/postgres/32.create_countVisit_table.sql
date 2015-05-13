CREATE TABLE COUNT_VISIT (
  COUNT_VISIT_ID character varying(32) PRIMARY KEY,
  NAME character varying(255) NOT NULL,
  DESCRIPTION character varying(255),
  STATUS character varying(32) NOT NULL
) WITH (OIDS=FALSE);