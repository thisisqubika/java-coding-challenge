CREATE TABLE WHEELS (
  id    IDENTITY    PRIMARY KEY,
  size  VARCHAR(30) NOT NULL,
  type  VARCHAR(30) NOT NULL
);

CREATE TABLE ENGINES (
  id    IDENTITY            PRIMARY KEY,
  power INT                 NOT NULL,
  type  VARCHAR(30)         NOT NULL
);

CREATE TABLE MODELS (
  id            IDENTITY            PRIMARY KEY,
  name          VARCHAR(100)        NOT NULL,
  xrom          DATE                DEFAULT NULL,
  to            DATE                DEFAULT NULL,
  type          VARCHAR(50)         DEFAULT NULL,
  line          VARCHAR(50)         DEFAULT NULL,
  engine        INT                 NOT NULL,
  wheel         INT                 NOT NULL,
  parent_id     INT                 DEFAULT NULL,
  brand_id      INT                 NOT NULL
);

ALTER TABLE MODELS ADD CONSTRAINT fk_model_models FOREIGN KEY (parent_id) REFERENCES MODELS(id);
ALTER TABLE MODELS ADD CONSTRAINT fk_model_engine FOREIGN KEY (engine) REFERENCES ENGINES(id);
ALTER TABLE MODELS ADD CONSTRAINT fk_model_wheels FOREIGN KEY (wheel) REFERENCES WHEELS(id);

CREATE TABLE BRANDS (
    id            IDENTITY            PRIMARY KEY,
    name          VARCHAR(100)        NOT NULL
);

INSERT INTO BRANDS VALUES (null, 'FORD');

ALTER TABLE MODELS ADD CONSTRAINT fk_model_brands FOREIGN KEY (brand_id) REFERENCES BRANDS(id);

CREATE TABLE FILES_PROCESSED (
    id          IDENTITY        PRIMARY KEY,
    filename    VARCHAR(100)    NOT NULL,
    processed_date DATE         NOT NULL
);