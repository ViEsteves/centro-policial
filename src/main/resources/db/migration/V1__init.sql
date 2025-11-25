-- Roles
CREATE TABLE role (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL UNIQUE
);

-- Users
CREATE TABLE app_user (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  email VARCHAR(100) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  enabled BOOLEAN NOT NULL DEFAULT TRUE
);

-- User roles
CREATE TABLE user_roles (
  user_id BIGINT NOT NULL,
  role_id BIGINT NOT NULL,
  PRIMARY KEY (user_id, role_id)
);

-- Departamento
CREATE TABLE departamento (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(150) NOT NULL,
  descricao TEXT,
  criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Chamado
CREATE TABLE chamado (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  titulo VARCHAR(200) NOT NULL,
  descricao TEXT,
  data_abertura TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  status VARCHAR(50),
  imagem_path VARCHAR(500),
  departamento_id BIGINT,
  CONSTRAINT fk_chamado_departamento FOREIGN KEY (departamento_id) REFERENCES departamento(id) ON DELETE SET NULL
);

-- Foreign keys user_roles
ALTER TABLE user_roles
ADD CONSTRAINT fk_ur_user FOREIGN KEY (user_id) REFERENCES app_user (id) ON DELETE CASCADE,
ADD CONSTRAINT fk_ur_role FOREIGN KEY (role_id) REFERENCES role (id) ON DELETE CASCADE;

-- Seeds
INSERT INTO role (name) VALUES ('ROLE_ADMIN'), ('ROLE_USER');

-- BCrypt hashes (pre-generated)
-- admin123 -> $2b$10$bR/sEayDw3z.IR99uLqW1.e/nf39JDGmpWIOge7z4M4vH3BffRj.C
-- user123  -> $2b$10$6nASL9eptNZoi959jpgIJOZ4kT.aR.ljCsWEenSPMgttH0ynxudGm
INSERT INTO app_user (email, password, enabled) VALUES
('admin@exemplo.com', '$2b$10$bR/sEayDw3z.IR99uLqW1.e/nf39JDGmpWIOge7z4M4vH3BffRj.C', TRUE),
('user@exemplo.com', '$2b$10$6nASL9eptNZoi959jpgIJOZ4kT.aR.ljCsWEenSPMgttH0ynxudGm', TRUE);

INSERT INTO user_roles (user_id, role_id) VALUES
( (SELECT id FROM app_user WHERE email='admin@exemplo.com'), (SELECT id FROM role WHERE name='ROLE_ADMIN') ),
( (SELECT id FROM app_user WHERE email='admin@exemplo.com'), (SELECT id FROM role WHERE name='ROLE_USER') ),
( (SELECT id FROM app_user WHERE email='user@exemplo.com'), (SELECT id FROM role WHERE name='ROLE_USER') );
