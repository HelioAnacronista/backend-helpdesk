INSERT INTO tb_user (email, senha) VALUES ('helio@gmail.com', '$2a$12$OruQnsYKy3iJ6s8s.cSvQejvgCYRjL8Ds4LRfJErKPl2vMKu8c3s')
INSERT INTO tb_user (email, senha) VALUES ('maria@gmail.com', '$2a$12$uDmWEQUtShoPVVh5ZMNSFeRqzr65O7UeT8yvei1R8hF.USuzMIqH2')

INSERT INTO tb_role (authority) VALUES ('ROLE_CLIENT');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);


-- Insert tb_cliente
INSERT INTO TB_TECNICO (cpf,email,data_criacao, nome,senha) VALUES ('550.482.150-95', 'joaotecnico@gmail.com', '2022-10-31' ,'joao silva Tecnico', '$2a$10$pApv4VHvwYGIX59PHOCgVOW8nd96R9yYUEarzE/dDcpCyLBW5stnK');

INSERT INTO tb_tecnico_role (tecnico_id, role_id) VALUES (1, 1);

--- Insert tb_cliente
INSERT INTO tb_cliente (cpf,email,data_criacao, nome,senha) VALUES ('41.837.556-2', 'joaoCliente@gmail.com', '2022-10-31' ,'joao silva Cliente', '$2a$10$pApv4VHvwYGIX59PHOCgVOW8nd96R9yYUEarzE/dDcpCyLBW5stnK');

INSERT INTO tb_cliente_role (cliente_id, role_id) VALUES (1, 1);



