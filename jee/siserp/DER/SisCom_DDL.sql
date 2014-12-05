
CREATE TABLE public.cfop (
                cod_cfop INTEGER NOT NULL,
                descricao_cfop VARCHAR(100) NOT NULL,
                cod_cfop_principal INTEGER,
                CONSTRAINT cfop_pk PRIMARY KEY (cod_cfop)
);
COMMENT ON TABLE public.cfop IS 'Código Fiscal de Operações e Prestações';
COMMENT ON COLUMN public.cfop.cod_cfop IS 'Id - Código Fiscal de Operações e Prestações';
COMMENT ON COLUMN public.cfop.descricao_cfop IS 'Descrição  do Código Fiscal de Operações e Prestações';
COMMENT ON COLUMN public.cfop.cod_cfop_principal IS 'Id - Código Fiscal de Operações e Prestações';


CREATE SEQUENCE public.unidade_seq;

CREATE TABLE public.unidade (
                cod_unidade INTEGER NOT NULL DEFAULT nextval('public.unidade_seq'),
                descricao_unidade CHAR(5) NOT NULL,
                CONSTRAINT unidade_pk PRIMARY KEY (cod_unidade)
);
COMMENT ON TABLE public.unidade IS 'Armazena as diversas unidades do produto';
COMMENT ON COLUMN public.unidade.cod_unidade IS 'Codigo da Unidade';


ALTER SEQUENCE public.unidade_seq OWNED BY public.unidade.cod_unidade;

CREATE SEQUENCE public.tipo_pgto_seq;

CREATE TABLE public.tipo_pgto (
                cod_tipo_pgto INTEGER NOT NULL DEFAULT nextval('public.tipo_pgto_seq'),
                descricao_tipo_pgto VARCHAR(20) NOT NULL,
                CONSTRAINT tipo_pgto_pk PRIMARY KEY (cod_tipo_pgto)
);
COMMENT ON TABLE public.tipo_pgto IS 'Tipo de Pagamento/Recebimento';
COMMENT ON COLUMN public.tipo_pgto.cod_tipo_pgto IS 'Codigo do Tipo de Pagamento/Recebimento';


ALTER SEQUENCE public.tipo_pgto_seq OWNED BY public.tipo_pgto.cod_tipo_pgto;

CREATE SEQUENCE public.plano_conta_seq;

CREATE TABLE public.plano_conta (
                cod_plano_conta INTEGER NOT NULL DEFAULT nextval('public.plano_conta_seq'),
                descricao_plano_conta VARCHAR(20) NOT NULL,
                CONSTRAINT plano_conta_pk PRIMARY KEY (cod_plano_conta)
);
COMMENT ON TABLE public.plano_conta IS 'Toda vez que for cadastrar uma despesa, a depesa estara vinculada ao codigo do plano conta';
COMMENT ON COLUMN public.plano_conta.cod_plano_conta IS 'Codigo do Plano de Conta';


ALTER SEQUENCE public.plano_conta_seq OWNED BY public.plano_conta.cod_plano_conta;

CREATE SEQUENCE public.movimento_seq;

CREATE TABLE public.movimento (
                cod_movimento INTEGER NOT NULL DEFAULT nextval('public.movimento_seq'),
                cod_plano_conta INTEGER NOT NULL,
                mes_ano_movimento VARCHAR(7),
                num_cheque_movimento INTEGER,
                num_documento_movimento VARCHAR(20),
                data_cheque DATE,
                data_lancamento_movimento DATE,
                historico_movimento VARCHAR(50),
                vlr_documento DOUBLE PRECISION,
                CONSTRAINT movimento_pk PRIMARY KEY (cod_movimento)
);
COMMENT ON TABLE public.movimento IS 'Movimento Bancario';
COMMENT ON COLUMN public.movimento.cod_movimento IS 'Codigo do Movimento Bancario';


ALTER SEQUENCE public.movimento_seq OWNED BY public.movimento.cod_movimento;

CREATE SEQUENCE public.fornecedor_seq;

CREATE TABLE public.fornecedor (
                cod_fornecedor INTEGER NOT NULL DEFAULT nextval('public.fornecedor_seq'),
                nome_fornecedor VARCHAR(50) NOT NULL,
                cpf_fornecedor VARCHAR(11),
                rg_fornecedor VARCHAR(25),
                orgao_rg_fornecedor VARCHAR(10),
                cnpj_fornecedor VARCHAR(14),
                tipo_fornecedor CHAR(1),
                endereco_fornecedor VARCHAR(100),
                bairro_fornecedor VARCHAR(50),
                cidade_fornecedor VARCHAR(50),
                uf_fornecedor CHAR(2),
                cep_fornecedor VARCHAR(8),
                email_fornecedor VARCHAR(100),
                site_fornecedor VARCHAR(100),
                fone_fornecedor VARCHAR(10),
                celular_fornecedor VARCHAR(10),
                fax_fornecedor VARCHAR(10),
                contato_fornecedor VARCHAR(50),
                CONSTRAINT fornecedor_pk PRIMARY KEY (cod_fornecedor)
);
COMMENT ON TABLE public.fornecedor IS 'Fornecedor';
COMMENT ON COLUMN public.fornecedor.cod_fornecedor IS 'Codigo do Fornecedor';


ALTER SEQUENCE public.fornecedor_seq OWNED BY public.fornecedor.cod_fornecedor;

CREATE TABLE public.produto (
                cod_produto VARCHAR(13) NOT NULL,
                cod_fornecedor INTEGER NOT NULL,
                cod_unidade INTEGER NOT NULL,
                descricao_produto VARCHAR(50),
                vlr_compra_produto DOUBLE PRECISION,
                vlr_venda_produto DOUBLE PRECISION,
                estoque_produto INTEGER,
                critico_produto INTEGER,
                CONSTRAINT produto_pk PRIMARY KEY (cod_produto)
);
COMMENT ON TABLE public.produto IS 'Produto';
COMMENT ON COLUMN public.produto.cod_produto IS 'Codigo do Produto';


CREATE TABLE public.nfe_cab (
                numero_nfe_cab INTEGER NOT NULL,
                cod_fornecedor INTEGER NOT NULL,
                emissao_nfe_cab DATE,
                entrada_nfe_cab DATE,
                total_nfe_cab DOUBLE PRECISION,
                cod_cfop INTEGER NOT NULL,
                CONSTRAINT nfe_cab_pk PRIMARY KEY (numero_nfe_cab)
);
COMMENT ON TABLE public.nfe_cab IS 'Cabecario da Nota Fical de Entrada
Obs.: Todas as Notas Ficais que entrarem na empresas serao armazenas aqui';
COMMENT ON COLUMN public.nfe_cab.numero_nfe_cab IS 'Numero da Nota Fical de Entrada';
COMMENT ON COLUMN public.nfe_cab.cod_cfop IS 'Id - Código Fiscal de Operações e Prestações';


CREATE TABLE public.nfe_det (
                cod_produto VARCHAR(13) NOT NULL,
                numero_nfe_cab INTEGER NOT NULL,
                qtd_nfe_det INTEGER,
                vlr_unit_nfe_det DOUBLE PRECISION,
                vlr_total_nfe_det DOUBLE PRECISION,
                CONSTRAINT nfe_det_pk PRIMARY KEY (cod_produto, numero_nfe_cab)
);
COMMENT ON TABLE public.nfe_det IS 'Detalhe da Nota Fical de Entrada';
COMMENT ON COLUMN public.nfe_det.cod_produto IS 'Codigo do Produto';
COMMENT ON COLUMN public.nfe_det.numero_nfe_cab IS 'Numero da Nota Fical de Entrada';


CREATE SEQUENCE public.fechamento_seq;

CREATE TABLE public.fechamento (
                cod_fechamento INTEGER NOT NULL DEFAULT nextval('public.fechamento_seq'),
                mes_ano_fechamento VARCHAR(7),
                saldo_anterior DOUBLE PRECISION,
                vlr_recebimentos DOUBLE PRECISION,
                vlr_pagamentos DOUBLE PRECISION,
                vlr_cheque_ncomp DOUBLE PRECISION,
                vlr_saldo_conta DOUBLE PRECISION,
                vlr_saldo_real DOUBLE PRECISION,
                CONSTRAINT fechamento_pk PRIMARY KEY (cod_fechamento)
);
COMMENT ON TABLE public.fechamento IS 'Fechamento';
COMMENT ON COLUMN public.fechamento.cod_fechamento IS 'Codigo do Fechamento';


ALTER SEQUENCE public.fechamento_seq OWNED BY public.fechamento.cod_fechamento;

CREATE SEQUENCE public.departamento_seq;

CREATE TABLE public.departamento (
                cod_departamento INTEGER NOT NULL DEFAULT nextval('public.departamento_seq'),
                nome_departamento VARCHAR(50) NOT NULL,
                CONSTRAINT departamento_pk PRIMARY KEY (cod_departamento)
);
COMMENT ON TABLE public.departamento IS 'Departamento';
COMMENT ON COLUMN public.departamento.cod_departamento IS 'Codigo do Departamento';


ALTER SEQUENCE public.departamento_seq OWNED BY public.departamento.cod_departamento;

CREATE SEQUENCE public.funcionario_seq;

CREATE TABLE public.funcionario (
                cod_funcionario INTEGER NOT NULL DEFAULT nextval('public.funcionario_seq'),
                cod_departamento INTEGER NOT NULL,
                nome_funcionario VARCHAR(50) NOT NULL,
                funcao_funcionario VARCHAR(20),
                login_funcionario VARCHAR(10) NOT NULL,
                senha_funcionario VARCHAR(10) NOT NULL,
                admissao_funcionario DATE,
                acesso_total CHAR(1),
                CONSTRAINT funcionario_pk PRIMARY KEY (cod_funcionario)
);
COMMENT ON TABLE public.funcionario IS 'Funcionario';
COMMENT ON COLUMN public.funcionario.cod_funcionario IS 'Codigo do Funcionario';


ALTER SEQUENCE public.funcionario_seq OWNED BY public.funcionario.cod_funcionario;

CREATE SEQUENCE public.req_cab_seq;

CREATE TABLE public.req_cab (
                cod_req_cab INTEGER NOT NULL DEFAULT nextval('public.req_cab_seq'),
                cod_funcionario INTEGER NOT NULL,
                data_req_cab DATE NOT NULL,
                CONSTRAINT req_cab_pk PRIMARY KEY (cod_req_cab)
);
COMMENT ON TABLE public.req_cab IS 'Cabeçario de Requisição';
COMMENT ON COLUMN public.req_cab.cod_req_cab IS 'Codigo do Cabeçario de Requisição';


ALTER SEQUENCE public.req_cab_seq OWNED BY public.req_cab.cod_req_cab;

CREATE TABLE public.req_det (
                cod_req_cab INTEGER NOT NULL,
                cod_produto VARCHAR(13) NOT NULL,
                qtde_req_det INTEGER,
                vlr_unit_req_det DOUBLE PRECISION,
                vlr_total_req_det DOUBLE PRECISION,
                CONSTRAINT req_det_pk PRIMARY KEY (cod_req_cab, cod_produto)
);
COMMENT ON TABLE public.req_det IS 'Detalhe de Requisição';
COMMENT ON COLUMN public.req_det.cod_req_cab IS 'Codigo do Cabeçario de Requisição';
COMMENT ON COLUMN public.req_det.cod_produto IS 'Codigo do Produto';


CREATE SEQUENCE public.cot_cab_seq;

CREATE TABLE public.cot_cab (
                cod_cot_cab INTEGER NOT NULL DEFAULT nextval('public.cot_cab_seq'),
                data_cot_cab DATE,
                forn1 INTEGER,
                forn2 INTEGER,
                forn3 INTEGER,
                forn4 INTEGER,
                forn5 INTEGER,
                condicoes1 VARCHAR(30),
                condicoes2 VARCHAR(30),
                condicoes3 VARCHAR(30),
                condicoes4 VARCHAR(30),
                condicoes5 VARCHAR(30),
                desconto1 DOUBLE PRECISION,
                desconto2 DOUBLE PRECISION,
                desconto3 DOUBLE PRECISION,
                desconto4 DOUBLE PRECISION,
                desconto5 DOUBLE PRECISION,
                entrega1 VARCHAR(30),
                entrega2 VARCHAR(30),
                entrega3 VARCHAR(30),
                entrega4 VARCHAR(30),
                entrega5 VARCHAR(30),
                total1 DOUBLE PRECISION,
                total2 DOUBLE PRECISION,
                total3 DOUBLE PRECISION,
                total4 DOUBLE PRECISION,
                total5 DOUBLE PRECISION,
                CONSTRAINT cot_cab_pk PRIMARY KEY (cod_cot_cab)
);
COMMENT ON TABLE public.cot_cab IS 'Cabeçario de Cotação';
COMMENT ON COLUMN public.cot_cab.cod_cot_cab IS 'Codigo do Cabeçario de Cotação';


ALTER SEQUENCE public.cot_cab_seq OWNED BY public.cot_cab.cod_cot_cab;

CREATE SEQUENCE public.pedido_cab_seq;

CREATE TABLE public.pedido_cab (
                cod_pedido_cab INTEGER NOT NULL DEFAULT nextval('public.pedido_cab_seq'),
                cod_fornecedor INTEGER NOT NULL,
                cod_cot_cab INTEGER NOT NULL,
                data_pedido DATE,
                endereco_entrega VARCHAR(50),
                endereco_cobranca VARCHAR(50),
                vlr_pedido_cab DOUBLE PRECISION,
                desconto_pedido_cab DOUBLE PRECISION,
                total_pedodo_cab DOUBLE PRECISION,
                CONSTRAINT pedido_cab_pk PRIMARY KEY (cod_pedido_cab)
);
COMMENT ON TABLE public.pedido_cab IS 'Cabeçario de Pedido';
COMMENT ON COLUMN public.pedido_cab.cod_pedido_cab IS 'Codigo do Cabeçario de Pedido';


ALTER SEQUENCE public.pedido_cab_seq OWNED BY public.pedido_cab.cod_pedido_cab;

CREATE TABLE public.cot_det (
                cod_cot_cab INTEGER NOT NULL,
                cod_produto VARCHAR(13) NOT NULL,
                cod_req_cab INTEGER NOT NULL,
                qtd_cot_det INTEGER,
                unit1 DOUBLE PRECISION,
                unit2 DOUBLE PRECISION,
                unit3 DOUBLE PRECISION,
                unit4 DOUBLE PRECISION,
                unit5 DOUBLE PRECISION,
                total1 DOUBLE PRECISION,
                total2 DOUBLE PRECISION,
                total3 DOUBLE PRECISION,
                total4 DOUBLE PRECISION,
                total5 DOUBLE PRECISION,
                CONSTRAINT cot_det_pk PRIMARY KEY (cod_cot_cab, cod_produto, cod_req_cab)
);
COMMENT ON TABLE public.cot_det IS 'Detalhe de Cotação';
COMMENT ON COLUMN public.cot_det.cod_cot_cab IS 'Codigo do Cabeçario de Cotação';
COMMENT ON COLUMN public.cot_det.cod_produto IS 'Codigo do Produto';
COMMENT ON COLUMN public.cot_det.cod_req_cab IS 'Codigo do Cabeçario de Requisição';


CREATE TABLE public.pedido_det (
                cod_req_cab INTEGER NOT NULL,
                cod_produto VARCHAR(13) NOT NULL,
                cod_cot_cab INTEGER NOT NULL,
                cod_pedido_cab INTEGER NOT NULL,
                qtde_pedido_det INTEGER,
                vlr_unit_pedido_det DOUBLE PRECISION,
                vlr_total_pedido_det DOUBLE PRECISION,
                CONSTRAINT pedido_det_pk PRIMARY KEY (cod_req_cab, cod_produto, cod_cot_cab, cod_pedido_cab)
);
COMMENT ON TABLE public.pedido_det IS 'Detalhe de Pedido';
COMMENT ON COLUMN public.pedido_det.cod_req_cab IS 'Codigo do Cabeçario de Requisição';
COMMENT ON COLUMN public.pedido_det.cod_produto IS 'Codigo do Produto';
COMMENT ON COLUMN public.pedido_det.cod_cot_cab IS 'Codigo do Cabeçario de Cotação';
COMMENT ON COLUMN public.pedido_det.cod_pedido_cab IS 'Codigo do Cabeçario de Pedido';


CREATE SEQUENCE public.conciliacao_seq;

CREATE TABLE public.conciliacao (
                cod_conciliacao INTEGER NOT NULL DEFAULT nextval('public.conciliacao_seq'),
                num_cheque_conciliacao INTEGER,
                data_cheque_conciliacao DATE,
                vlr_cheque_conciliacao DOUBLE PRECISION,
                mes_ano_conciliacao VARCHAR(7),
                historico_conciliacao VARCHAR(50),
                CONSTRAINT conciliacao_pk PRIMARY KEY (cod_conciliacao)
);
COMMENT ON TABLE public.conciliacao IS 'Conciliação Bancaria';
COMMENT ON COLUMN public.conciliacao.cod_conciliacao IS 'Codigo da Conciliação Bancaria';


ALTER SEQUENCE public.conciliacao_seq OWNED BY public.conciliacao.cod_conciliacao;

CREATE SEQUENCE public.cliente_seq;

CREATE TABLE public.cliente (
                cod_cliente INTEGER NOT NULL DEFAULT nextval('public.cliente_seq'),
                nome_cliente VARCHAR(50) NOT NULL,
                cpf_cliente VARCHAR(15),
                desde_cliente DATE,
                rg_cliente VARCHAR(25),
                orgao_rg_cliente VARCHAR(10),
                nascimento_cliente DATE,
                profissao_cliente VARCHAR(40),
                empresa_cliente VARCHAR(50),
                fone_empresa VARCHAR(15),
                renda_cliente DOUBLE PRECISION,
                tipo_cliente CHAR(1),
                cnpj_cliente VARCHAR(20),
                referencia_cliente VARCHAR(50),
                fone_referencia_cliente VARCHAR(15),
                email VARCHAR(100),
                CONSTRAINT cliente_pk PRIMARY KEY (cod_cliente)
);
COMMENT ON TABLE public.cliente IS 'Cliente';
COMMENT ON COLUMN public.cliente.cod_cliente IS 'Codigo do Cliente';
COMMENT ON COLUMN public.cliente.cpf_cliente IS 'CPF do Cliente';
COMMENT ON COLUMN public.cliente.fone_empresa IS 'Telefone da Empresa onde o Cliente trabalha';
COMMENT ON COLUMN public.cliente.cnpj_cliente IS 'CNJP do Cliente';
COMMENT ON COLUMN public.cliente.fone_referencia_cliente IS 'Telefone da Referencia do Cliente';


ALTER SEQUENCE public.cliente_seq OWNED BY public.cliente.cod_cliente;

CREATE SEQUENCE public.orc_cab_seq;

CREATE TABLE public.orc_cab (
                cod_orc_cab INTEGER NOT NULL DEFAULT nextval('public.orc_cab_seq'),
                cod_funcionario INTEGER NOT NULL,
                cod_cliente INTEGER NOT NULL,
                data_orc_cab DATE,
                vlr_orc_cab DOUBLE PRECISION,
                CONSTRAINT orc_cab_pk PRIMARY KEY (cod_orc_cab)
);
COMMENT ON TABLE public.orc_cab IS 'Cabeçario de Orçamento';
COMMENT ON COLUMN public.orc_cab.cod_orc_cab IS 'Codigo do Cabeçario de Orçamento';


ALTER SEQUENCE public.orc_cab_seq OWNED BY public.orc_cab.cod_orc_cab;

CREATE TABLE public.orc_det (
                cod_orc_cab INTEGER NOT NULL,
                cod_produto VARCHAR(13) NOT NULL,
                qtd_orc_det INTEGER,
                vlr_unit_orc_det DOUBLE PRECISION,
                vlr_total_orc_det DOUBLE PRECISION,
                CONSTRAINT orc_det_pk PRIMARY KEY (cod_orc_cab, cod_produto)
);
COMMENT ON TABLE public.orc_det IS 'Detalhe de Orçamento';
COMMENT ON COLUMN public.orc_det.cod_orc_cab IS 'Codigo do Cabeçario de Orçamento';
COMMENT ON COLUMN public.orc_det.cod_produto IS 'Codigo do Produto';


CREATE SEQUENCE public.cobranca_seq;

CREATE TABLE public.cobranca (
                cod_cobranca INTEGER NOT NULL DEFAULT nextval('public.cobranca_seq'),
                cod_cliente INTEGER NOT NULL,
                assunto_cobranca VARCHAR(50),
                texto_cobranca TEXT,
                envio1 DATE,
                envio2 DATE,
                envio3 DATE,
                envio4 DATE,
                envio5 DATE,
                num_processo VARCHAR(30),
                data_processo DATE,
                advogado_processo VARCHAR(50),
                CONSTRAINT cobranca_pk PRIMARY KEY (cod_cobranca)
);
COMMENT ON TABLE public.cobranca IS 'Carta de Cobrança';
COMMENT ON COLUMN public.cobranca.cod_cobranca IS 'Codigo da Carta de Cobrança';


ALTER SEQUENCE public.cobranca_seq OWNED BY public.cobranca.cod_cobranca;

CREATE SEQUENCE public.cliente_telefone_seq;

CREATE TABLE public.cliente_telefone (
                cod_telefone INTEGER NOT NULL DEFAULT nextval('public.cliente_telefone_seq'),
                cod_cliente INTEGER NOT NULL,
                numero_telefone VARCHAR(10) NOT NULL,
                tipo_telefone CHAR(1) NOT NULL,
                CONSTRAINT cliente_telefone_pk PRIMARY KEY (cod_telefone)
);
COMMENT ON TABLE public.cliente_telefone IS 'Telefone do Cliente';
COMMENT ON COLUMN public.cliente_telefone.cod_telefone IS 'Codigo do Telefone do Cliente';


ALTER SEQUENCE public.cliente_telefone_seq OWNED BY public.cliente_telefone.cod_telefone;

CREATE SEQUENCE public.cliente_endereco_seq;

CREATE TABLE public.cliente_endereco (
                cod_endereco INTEGER NOT NULL DEFAULT nextval('public.cliente_endereco_seq'),
                cod_cliente INTEGER NOT NULL,
                logradouro_endereco VARCHAR(50) NOT NULL,
                complemento_endereco VARCHAR(50),
                cep_endereco VARCHAR(8),
                bairro_endereco VARCHAR(50),
                cidade_endereco VARCHAR(50) NOT NULL,
                uf_endereco CHAR(2) NOT NULL,
                CONSTRAINT cliente_endereco_pk PRIMARY KEY (cod_endereco)
);
COMMENT ON TABLE public.cliente_endereco IS 'Endereço do Cliente';
COMMENT ON COLUMN public.cliente_endereco.cod_endereco IS 'Codigo do Endereço do Cliente';


ALTER SEQUENCE public.cliente_endereco_seq OWNED BY public.cliente_endereco.cod_endereco;

CREATE SEQUENCE public.cheque_ncomp_seq;

CREATE TABLE public.cheque_ncomp (
                cod_cheque_ncomp INTEGER NOT NULL DEFAULT nextval('public.cheque_ncomp_seq'),
                num_cheque_ncomp INTEGER,
                data_cheque_ncomp DATE,
                vlr_cheque_ncomp DOUBLE PRECISION,
                mes_ano_ncomp VARCHAR(7),
                historico_ncomp VARCHAR(50),
                CONSTRAINT cheque_ncomp_pk PRIMARY KEY (cod_cheque_ncomp)
);
COMMENT ON TABLE public.cheque_ncomp IS 'Cheque nao compesado';
COMMENT ON COLUMN public.cheque_ncomp.cod_cheque_ncomp IS 'Codigo do Cheque nao compesado';


ALTER SEQUENCE public.cheque_ncomp_seq OWNED BY public.cheque_ncomp.cod_cheque_ncomp;

CREATE SEQUENCE public.cartao_credito_seq;

CREATE TABLE public.cartao_credito (
                id_cartao_credito INTEGER NOT NULL DEFAULT nextval('public.cartao_credito_seq'),
                empresa VARCHAR(50) NOT NULL,
                juros_rotativo VARCHAR(20) NOT NULL,
                pagto_minimo DOUBLE PRECISION NOT NULL,
                juros_parcelado DOUBLE PRECISION NOT NULL,
                multa DOUBLE PRECISION,
                multa_sem_minimo_total CHAR(6),
                multa_cobrada_dia INTEGER,
                CONSTRAINT cartao_credito_pk PRIMARY KEY (id_cartao_credito)
);
COMMENT ON TABLE public.cartao_credito IS 'Cartão de Crédito';
COMMENT ON COLUMN public.cartao_credito.id_cartao_credito IS 'Id do Cartão de Crédito';


ALTER SEQUENCE public.cartao_credito_seq OWNED BY public.cartao_credito.id_cartao_credito;

CREATE SEQUENCE public.venda_cab_seq;

CREATE TABLE public.venda_cab (
                cod_venda_cab INTEGER NOT NULL DEFAULT nextval('public.venda_cab_seq'),
                id_cartao_credito INTEGER NOT NULL,
                cod_cfop INTEGER NOT NULL,
                cod_tipo_pgto INTEGER NOT NULL,
                cod_funcionario INTEGER NOT NULL,
                cod_cliente INTEGER NOT NULL,
                data_venda_cab DATE,
                vlr_venda_cab DOUBLE PRECISION,
                desconto_venda_cab DOUBLE PRECISION,
                total_venda_cab DOUBLE PRECISION,
                num_parcelas_venda_cab INTEGER,
                pri_venci_venda_cab DATE,
                num_nf_venda_cab INTEGER,
                CONSTRAINT venda_cab_pk PRIMARY KEY (cod_venda_cab)
);
COMMENT ON TABLE public.venda_cab IS 'Cabeçario de Venda';
COMMENT ON COLUMN public.venda_cab.cod_venda_cab IS 'Codigo do Cabeçario de Venda';


ALTER SEQUENCE public.venda_cab_seq OWNED BY public.venda_cab.cod_venda_cab;

CREATE TABLE public.venda_det (
                cod_produto VARCHAR(13) NOT NULL,
                cod_venda_cab INTEGER NOT NULL,
                qtd_venda_det INTEGER,
                vlr_unit_venda_det DOUBLE PRECISION,
                vlr_total_venda_det DOUBLE PRECISION,
                CONSTRAINT venda_det_pk PRIMARY KEY (cod_produto, cod_venda_cab)
);
COMMENT ON TABLE public.venda_det IS 'Detalhe de Venda';
COMMENT ON COLUMN public.venda_det.cod_produto IS 'Codigo do Produto';
COMMENT ON COLUMN public.venda_det.cod_venda_cab IS 'Codigo do Cabeçario de Venda';


CREATE TABLE public.banco (
                cod_banco CHAR(5) NOT NULL,
                nome_banco VARCHAR(100) NOT NULL,
                site_banco VARCHAR(100),
                CONSTRAINT banco_pk PRIMARY KEY (cod_banco)
);
COMMENT ON TABLE public.banco IS 'Banco';
COMMENT ON COLUMN public.banco.cod_banco IS 'Codigo do Banco';


CREATE SEQUENCE public.conta_bancaria_seq;

CREATE TABLE public.conta_bancaria (
                id_conta_bancaria INTEGER NOT NULL DEFAULT nextval('public.conta_bancaria_seq'),
                cod_banco CHAR(5) NOT NULL,
                agencia_banco INTEGER NOT NULL,
                conta_banco INTEGER NOT NULL,
                gerente_banco VARCHAR(20),
                fone_banco VARCHAR(10),
                CONSTRAINT conta_bancaria_pk PRIMARY KEY (id_conta_bancaria)
);
COMMENT ON TABLE public.conta_bancaria IS 'Conta bancária';
COMMENT ON COLUMN public.conta_bancaria.id_conta_bancaria IS 'Id da Conta bancária';


ALTER SEQUENCE public.conta_bancaria_seq OWNED BY public.conta_bancaria.id_conta_bancaria;

CREATE SEQUENCE public.recebimento_seq;

CREATE TABLE public.recebimento (
                cod_recebimento INTEGER NOT NULL DEFAULT nextval('public.recebimento_seq'),
                id_conta_bancaria INTEGER NOT NULL,
                cod_cliente INTEGER NOT NULL,
                cod_tipo_pgto INTEGER NOT NULL,
                num_documento VARCHAR(20),
                vlr_total_recebimento DOUBLE PRECISION,
                vlr_juro_recebimento DOUBLE PRECISION,
                vlr_multa_recebimento DOUBLE PRECISION,
                vlr_desconto_recebimento DOUBLE PRECISION,
                vlr_recebido DOUBLE PRECISION,
                emissao_recebimento DATE,
                lancamento_recebimento DATE,
                vencimento_recebimento DATE,
                data_recebimento DATE,
                CONSTRAINT recebimento_pk PRIMARY KEY (cod_recebimento)
);
COMMENT ON TABLE public.recebimento IS 'Recebimento';
COMMENT ON COLUMN public.recebimento.cod_recebimento IS 'Codigo do Recebimento';


ALTER SEQUENCE public.recebimento_seq OWNED BY public.recebimento.cod_recebimento;

CREATE SEQUENCE public.pagamento_seq;

CREATE TABLE public.pagamento (
                cod_pagamento INTEGER NOT NULL DEFAULT nextval('public.pagamento_seq'),
                id_conta_bancaria INTEGER NOT NULL,
                cod_fornecedor INTEGER NOT NULL,
                cod_tipo_pgto INTEGER NOT NULL,
                cod_plano_conta INTEGER NOT NULL,
                num_doc_pagamento VARCHAR(20),
                vlr_total_pagamento DOUBLE PRECISION,
                vlr_juro_pagamento DOUBLE PRECISION,
                vlr_multa_pagamento DOUBLE PRECISION,
                vlr_desconto_pagamento DOUBLE PRECISION,
                vlr_pago_pagamento DOUBLE PRECISION,
                num_cheque_pagamento INTEGER,
                nominal_pagamento VARCHAR(50),
                emissao_pagamento DATE,
                lancamento_pagamento DATE,
                vencimento_pagamento DATE,
                data_pagamento DATE,
                data_cheque_pagamento DATE,
                CONSTRAINT pagamento_pk PRIMARY KEY (cod_pagamento)
);
COMMENT ON TABLE public.pagamento IS 'Pagamento';
COMMENT ON COLUMN public.pagamento.cod_pagamento IS 'Codigo do Pagamento';


ALTER SEQUENCE public.pagamento_seq OWNED BY public.pagamento.cod_pagamento;

ALTER TABLE public.nfe_cab ADD CONSTRAINT cfop_nfe_cab_fk
FOREIGN KEY (cod_cfop)
REFERENCES public.cfop (cod_cfop)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.venda_cab ADD CONSTRAINT cfop_venda_cab_fk
FOREIGN KEY (cod_cfop)
REFERENCES public.cfop (cod_cfop)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.cfop ADD CONSTRAINT cfop_cfop_fk
FOREIGN KEY (cod_cfop_principal)
REFERENCES public.cfop (cod_cfop)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.produto ADD CONSTRAINT produto_cod_unidade_fkey
FOREIGN KEY (cod_unidade)
REFERENCES public.unidade (cod_unidade)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.pagamento ADD CONSTRAINT pagamento_cod_tipo_pgto_fkey
FOREIGN KEY (cod_tipo_pgto)
REFERENCES public.tipo_pgto (cod_tipo_pgto)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.recebimento ADD CONSTRAINT recebimento_cod_tipo_pgto_fkey
FOREIGN KEY (cod_tipo_pgto)
REFERENCES public.tipo_pgto (cod_tipo_pgto)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.venda_cab ADD CONSTRAINT venda_cab_cod_tipo_pgto_fkey
FOREIGN KEY (cod_tipo_pgto)
REFERENCES public.tipo_pgto (cod_tipo_pgto)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.movimento ADD CONSTRAINT movimento_cod_plano_conta_fkey
FOREIGN KEY (cod_plano_conta)
REFERENCES public.plano_conta (cod_plano_conta)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.pagamento ADD CONSTRAINT pagamento_cod_plano_conta_fkey
FOREIGN KEY (cod_plano_conta)
REFERENCES public.plano_conta (cod_plano_conta)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.nfe_cab ADD CONSTRAINT nfe_cab_cod_fornecedor_fkey
FOREIGN KEY (cod_fornecedor)
REFERENCES public.fornecedor (cod_fornecedor)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.pagamento ADD CONSTRAINT pagamento_cod_fornecedor_fkey
FOREIGN KEY (cod_fornecedor)
REFERENCES public.fornecedor (cod_fornecedor)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.pedido_cab ADD CONSTRAINT pedido_cab_cod_fornecedor_fkey
FOREIGN KEY (cod_fornecedor)
REFERENCES public.fornecedor (cod_fornecedor)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.produto ADD CONSTRAINT produto_cod_fornecedor_fkey
FOREIGN KEY (cod_fornecedor)
REFERENCES public.fornecedor (cod_fornecedor)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.nfe_det ADD CONSTRAINT nfe_det_cod_produto_fkey
FOREIGN KEY (cod_produto)
REFERENCES public.produto (cod_produto)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.orc_det ADD CONSTRAINT orc_det_cod_produto_fkey
FOREIGN KEY (cod_produto)
REFERENCES public.produto (cod_produto)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.req_det ADD CONSTRAINT req_det_cod_produto_fkey
FOREIGN KEY (cod_produto)
REFERENCES public.produto (cod_produto)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.venda_det ADD CONSTRAINT venda_det_cod_produto_fkey
FOREIGN KEY (cod_produto)
REFERENCES public.produto (cod_produto)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.nfe_det ADD CONSTRAINT nfe_det_numero_nfe_cab_fkey
FOREIGN KEY (numero_nfe_cab)
REFERENCES public.nfe_cab (numero_nfe_cab)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.funcionario ADD CONSTRAINT funcionario_cod_departamento_fkey
FOREIGN KEY (cod_departamento)
REFERENCES public.departamento (cod_departamento)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.orc_cab ADD CONSTRAINT orc_cab_cod_funcionario_fkey
FOREIGN KEY (cod_funcionario)
REFERENCES public.funcionario (cod_funcionario)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.req_cab ADD CONSTRAINT req_cab_cod_funcionario_fkey
FOREIGN KEY (cod_funcionario)
REFERENCES public.funcionario (cod_funcionario)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.venda_cab ADD CONSTRAINT venda_cab_cod_funcionario_fkey
FOREIGN KEY (cod_funcionario)
REFERENCES public.funcionario (cod_funcionario)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.req_det ADD CONSTRAINT req_det_cod_req_cab_fkey
FOREIGN KEY (cod_req_cab)
REFERENCES public.req_cab (cod_req_cab)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.cot_det ADD CONSTRAINT cot_det_cod_req_cab_fkey
FOREIGN KEY (cod_req_cab, cod_produto)
REFERENCES public.req_det (cod_req_cab, cod_produto)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.cot_det ADD CONSTRAINT cot_det_cod_cot_cab_fkey
FOREIGN KEY (cod_cot_cab)
REFERENCES public.cot_cab (cod_cot_cab)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.pedido_cab ADD CONSTRAINT pedido_cab_cod_cot_cab_fkey
FOREIGN KEY (cod_cot_cab)
REFERENCES public.cot_cab (cod_cot_cab)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.pedido_det ADD CONSTRAINT pedido_det_cod_pedido_cab_fkey
FOREIGN KEY (cod_pedido_cab)
REFERENCES public.pedido_cab (cod_pedido_cab)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.pedido_det ADD CONSTRAINT pedido_det_cod_cot_cab_fkey
FOREIGN KEY (cod_cot_cab, cod_produto, cod_req_cab)
REFERENCES public.cot_det (cod_cot_cab, cod_produto, cod_req_cab)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.cliente_endereco ADD CONSTRAINT cliente_endereco_cod_cliente_fkey
FOREIGN KEY (cod_cliente)
REFERENCES public.cliente (cod_cliente)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.cliente_telefone ADD CONSTRAINT cliente_telefone_cod_cliente_fkey
FOREIGN KEY (cod_cliente)
REFERENCES public.cliente (cod_cliente)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.cobranca ADD CONSTRAINT cobranca_cod_cliente_fkey
FOREIGN KEY (cod_cliente)
REFERENCES public.cliente (cod_cliente)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.orc_cab ADD CONSTRAINT orc_cab_cod_cliente_fkey
FOREIGN KEY (cod_cliente)
REFERENCES public.cliente (cod_cliente)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.recebimento ADD CONSTRAINT recebimento_cod_cliente_fkey
FOREIGN KEY (cod_cliente)
REFERENCES public.cliente (cod_cliente)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.venda_cab ADD CONSTRAINT venda_cab_cod_cliente_fkey
FOREIGN KEY (cod_cliente)
REFERENCES public.cliente (cod_cliente)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.orc_det ADD CONSTRAINT orc_det_cod_orc_cab_fkey
FOREIGN KEY (cod_orc_cab)
REFERENCES public.orc_cab (cod_orc_cab)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.venda_cab ADD CONSTRAINT venda_cab_id_cartao_credito_fkey
FOREIGN KEY (id_cartao_credito)
REFERENCES public.cartao_credito (id_cartao_credito)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.venda_det ADD CONSTRAINT venda_det_cod_venda_cab_fkey
FOREIGN KEY (cod_venda_cab)
REFERENCES public.venda_cab (cod_venda_cab)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.conta_bancaria ADD CONSTRAINT conta_bancaria_cod_banco_fkey
FOREIGN KEY (cod_banco)
REFERENCES public.banco (cod_banco)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.pagamento ADD CONSTRAINT pagamento_id_conta_bancaria_fkey
FOREIGN KEY (id_conta_bancaria)
REFERENCES public.conta_bancaria (id_conta_bancaria)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.recebimento ADD CONSTRAINT recebimento_id_conta_bancaria_fkey
FOREIGN KEY (id_conta_bancaria)
REFERENCES public.conta_bancaria (id_conta_bancaria)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
