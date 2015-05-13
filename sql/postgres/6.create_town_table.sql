CREATE TABLE TOWN (
  TOWN_ID character varying(32) PRIMARY KEY,
  NAME character varying(255),
  DESCRIPTION character varying(255),
  STATUS character varying(32),
  REGION_ID character varying(32) NOT NULL REFERENCES REGION(REGION_ID),
  DISTRICT_ID character varying(32) REFERENCES DISTRICT(DISTRICT_ID)
) WITH (OIDS=FALSE);