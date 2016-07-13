--drop table abc_invest_transaction;
--drop sequence seq_invest_transaction;

create table abc_invest_transaction(
	invest_transaction_seq number not null,
	invest_seq number not null,
	intend_profit number not null,
	total_collect number not null,
	stack_collect number not null,
	progress varchar2(12) not null,
	collect_rate number(3) not null,
	round number(2) not null
);

create sequence seq_invest_transaction;

alter table abc_invest_transaction add(
	constraint pk_invest_transaction_seq primary key(invest_transaction_seq),
	constraint fk_invest_seq foreign key(invest_seq) references abc_invest(invest_seq)
);

comment on table abc_invest_transaction is '투자와 관련된 내역 테이블입니다.';
comment on column abc_invest_transaction.invest_transaction_seq is '투자내역 테이블을 식별하는 기본키입니다.';
comment on column abc_invest_transaction.invest_seq is '어느 투자 테이블의 내역인지를 식별하는 참조키입니다.';
comment on column abc_invest_transaction.intend_profit is '예정 수익금입니다. 투자금에 고정 수익률0.06을 곱한 값입니다.';
comment on column abc_invest_transaction.total_collect is '총 회수금입니다. 투자금에 예정수익금을 더한 값입니다.';
comment on column abc_invest_transaction.stack_collect is '누적회수금입니다. 총 회수금/대출기간x현회차수를 계산한 값입니다.';
comment on column abc_invest_transaction.progress is '상환진행상황입니다. 최대 한글6자입니다.';
comment on column abc_invest_transaction.collect_rate is '회수비율입니다. 누적회수금을 총회수금으로 나눈 값입니다.';
comment on column abc_invest_transaction.round is '현재회차수이며 0이상의 정수 최대 2자리입니다.';