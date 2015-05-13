CREATE TABLE USERS (
  USER_ID character varying(32) PRIMARY KEY,
  NAME character varying(512) NOT NULL,
  DESCRIPTION character varying(512),
  EMAIL character varying(128) NOT NULL,
  LOGIN character varying(128) NOT NULL,
  PASSWORD character varying(32) NOT NULL,
  PHONE character varying(32) NOT NULL,
  USER_TYPE character varying(32) NOT NULL,
  STATUS character varying(32) NOT NULL,
  IS_FAKE char(1) default 'N' NOT NULL,
  LAST_UPDATE_TIME timestamp,
  UPDATE_BY_USER_ID character varying(32),
  CREATE_TIME timestamp,
  CREATE_BY_USER_ID character varying(32)
) WITH (OIDS=FALSE);

insert into USERS(USER_ID, NAME, DESCRIPTION, EMAIL, LOGIN, PASSWORD, PHONE, USER_TYPE, STATUS) values('1', 'Admin', 'description', 'info@my3o.ru', 'info@my3o.ru', 'geirby', '+79072348756', 'SuperAdmin', 'Active');