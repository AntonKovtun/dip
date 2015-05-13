CREATE TABLE NOTIFICATION (
  NOTIFICATION_ID character varying(32) PRIMARY KEY,
  NAME character varying(255) NOT NULL,
  DESCRIPTION character varying(255),
  TEXT TEXT,
  DATE_ON timestamp,
  TYPE_NOTIFICATION character varying(32),
  STATUS character varying(32),
  SETTING_MAIL_ID character varying(32),
  USER_ID character varying(32),
  GROUP_ID character varying(32),
  ORGANIZATION_ID character varying(32),
  CREATE_BY_USER_ID character varying(32),
  CREATE_TIME timestamp,
  UPDATE_BY_USER_ID character varying(32),
  LAST_UPDATE_TIME timestamp,
  USER_TYPE character varying(32)
) WITH (OIDS=FALSE);