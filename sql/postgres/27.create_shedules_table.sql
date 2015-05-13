CREATE TABLE SHEDULES (
  SHEDULE_ID character varying(32) PRIMARY KEY,
  DISCIPLINE_ID character varying(32) NOT NULL REFERENCES DISCIPLINE(DISCIPLINE_ID),
  USER_ID character varying(32) NOT NULL REFERENCES USERS(USER_ID),
  GROUP_ID character varying(32) NOT NULL REFERENCES GROUPS(GROUP_ID),
  YEAR character varying(5) NOT NULL,
  CABINET character varying(32),
  FROM_TIMES character varying(32),
  TO_TIMES character varying(32),
  DESCRIPTION character varying(255),
  STATUS character varying(32) NOT NULL
) WITH (OIDS=FALSE);