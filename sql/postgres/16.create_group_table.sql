CREATE TABLE GROUPS (
  GROUP_ID character varying(32) PRIMARY KEY,
  ORGANIZATION_ID character varying(32) NOT NULL REFERENCES ORGANIZATION(ORGANIZATION_ID),
  NAME character varying(255) NOT NULL,
  NUMBER character varying(255) NOT NULL,
  DESCRIPTION character varying(512),
  STATUS character varying(32) NOT NULL,
  LAST_UPDATE_TIME timestamp,
  UPDATE_BY_USER_ID character varying(32),
  CREATE_TIME timestamp,
  CREATE_BY_USER_ID character varying(32)
) WITH (OIDS=FALSE);