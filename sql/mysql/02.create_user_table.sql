CREATE TABLE USERS (
  USER_ID varchar(32) NOT NULL,
  NAME varchar(512) NOT NULL,
  DESCRIPTION varchar(512),
  EMAIL varchar(128) NOT NULL,
  LOGIN varchar(128) NOT NULL,
  PASSWORD varchar(32) NOT NULL,
  PHONE varchar(32) NOT NULL,
  USER_TYPE varchar(32) NOT NULL,
  STATUS varchar(32) NOT NULL,
  IS_FAKE char(1) default 'N' NOT NULL,
  LAST_UPDATE_TIME DATETIME,
  UPDATE_BY_USER_ID varchar(32),
  CREATE_TIME DATETIME,
  CREATE_BY_USER_ID varchar(32),
  PRIMARY KEY (USER_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into USERS(USER_ID, NAME, DESCRIPTION, EMAIL, LOGIN, PASSWORD,PHONE,USER_TYPE,STATUS) values('1',"Admin", "","dip@dip.com", "dip@dip.com", "123","","SuperAdmin","Active");
