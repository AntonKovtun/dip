CREATE TABLE JOURNAL (
  JOURNAL_ID character varying(32) PRIMARY KEY,
  SHEDULE_ID character varying(32) NOT NULL REFERENCES SHEDULES(SHEDULE_ID),
  DATE character varying(16) NOT NULL,
  USER_ID character varying(32) NOT NULL REFERENCES USERS(USER_ID),
  MARK character varying(32) NOT NULL,
  MARK_TYPE character varying(32) NOT NULL,
  TASK_TYPE character varying(32) NOT NULL,
  DESCRIPTION character varying(255),
  STATUS character varying(32) NOT NULL
) WITH (OIDS=FALSE);