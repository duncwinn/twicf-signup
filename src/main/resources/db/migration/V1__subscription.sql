CREATE TABLE subscription (
  id      SERIAL PRIMARY KEY,
  email_address VARCHAR(256) NOT NULL,

  CONSTRAINT unique_email_address UNIQUE (email_address)
);