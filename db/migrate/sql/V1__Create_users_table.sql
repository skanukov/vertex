CREATE TYPE role AS ENUM ('user', 'admin');

CREATE TABLE users (
  id              SERIAL PRIMARY KEY,
  email           VARCHAR(255) NOT NULL,
  password_digest VARCHAR(255) NOT NULL,
  remember_token  VARCHAR(255) NOT NULL,
  role            role         NOT NULL DEFAULT 'user',
  created_at      TIMESTAMPTZ  NOT NULL DEFAULT now(),
  updated_at      TIMESTAMPTZ  NOT NULL DEFAULT now()
);

CREATE UNIQUE INDEX idx__users__email
  ON users (email);

CREATE UNIQUE INDEX idx__users__remember_token
  ON users (remember_token);
