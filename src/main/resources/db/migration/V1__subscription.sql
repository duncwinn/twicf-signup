CREATE TABLE subscription (
  id      SERIAL PRIMARY KEY,
  email_address VARCHAR(256) NOT NULL,
  password VARCHAR (255),
  role VARCHAR (255),

  CONSTRAINT unique_email_address UNIQUE (email_address)
);
