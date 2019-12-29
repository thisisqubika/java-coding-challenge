DROP TABLE IF EXISTS wheels;

CREATE TABLE wheels(
  id    INT AUTO_INCREMENT  PRIMARY KEY,
  size  VARCHAR(30) NOT NULL,
  type  VARCHAR(30) NOT NULL
);

DROP TABLE IF EXISTS engines;

CREATE TABLE engines (
  id    INT AUTO_INCREMENT  PRIMARY KEY,
  power INT                 NOT NULL,
  type  VARCHAR(30)         NOT NULL
);

DROP TABLE IF EXISTS models;

CREATE TABLE models (
  id            INT AUTO_INCREMENT  PRIMARY KEY,
  name          VARCHAR(100)        NOT NULL,
  from          DATE                DEFAULT NULL,
  to            DATE                DEFAULT NULL,
  type          VARCHAR(50)         DEFAULT NULL,
  line          VARCHAR(50)         DEFAULT NULL,
  engine        INT                 NOT NULL,
  wheel         INT                 NOT NULL,
  parent        INT                 DEFAULT NULL
);

ALTER TABLE models ADD CONSTRAINT fk_model_models FOREIGN KEY (parent) REFERENCES models(id);
ALTER TABLE models ADD CONSTRAINT fk_model_engine FOREIGN KEY (engine) REFERENCES engines(id);
ALTER TABLE models ADD CONSTRAINT fk_model_wheels FOREIGN KEY (wheel) REFERENCES wheels(id);