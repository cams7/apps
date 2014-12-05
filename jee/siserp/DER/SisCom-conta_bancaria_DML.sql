insert into conta_bancaria(id_conta_bancaria, cod_banco, agencia_banco, conta_banco, gerente_banco, fone_banco) values(nextval('conta_bancaria_seq'), '001', 12345, 1234567, 'Jose Carlos', '3136521245');

select * from public.conta_bancaria;
