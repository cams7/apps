insert into tipo_pgto(cod_tipo_pgto, descricao_tipo_pgto) values(nextval('tipo_pgto_seq'), 'DINHEIRO');
insert into tipo_pgto(cod_tipo_pgto, descricao_tipo_pgto) values(nextval('tipo_pgto_seq'), 'CHEQUE');
insert into tipo_pgto(cod_tipo_pgto, descricao_tipo_pgto) values(nextval('tipo_pgto_seq'), 'CARTÃO DE CRÉDITO');
insert into tipo_pgto(cod_tipo_pgto, descricao_tipo_pgto) values(nextval('tipo_pgto_seq'), 'CARTÃO DE DÉBITO');

select * from public.tipo_pgto;