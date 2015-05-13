CREATE TABLE SETTINGS_MAIL (
  SETTINGS_MAIL_ID character varying(32) PRIMARY KEY,
  NAME character varying(255) NOT NULL,
  DESCRIPTION character varying(512),
  STATUS character varying(32),
  HOST character varying(32),
  PORT character varying(32),
  USER_NAME character varying(32),
  USER_PASSWORD character varying(32),
  DEFAULT_SENDER character varying(128),
  DEFAULT_SUBJECT_PREFIX character varying(128),
  ORGANIZATION_ID character varying(32)
) WITH (OIDS=FALSE);