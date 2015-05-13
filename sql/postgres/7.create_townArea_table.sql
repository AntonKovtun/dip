CREATE TABLE TOWN_AREA (
  TOWN_AREA_ID character varying(32) PRIMARY KEY,
  NAME character varying(255),
  DESCRIPTION character varying(255),
  STATUS character varying(32),
  TOWN_ID character varying(32) NOT NULL REFERENCES TOWN(TOWN_ID)
) WITH (OIDS=FALSE);