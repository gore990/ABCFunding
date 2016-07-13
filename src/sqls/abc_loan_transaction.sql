--drop table abc_loan_transaction;
--drop sequence seq_loan_transaction;
create table abc_loan_transaction(
	loan_seq number not null,
	loan_code number not null,
	interest_money number not null,
	total_repay number not null,
	stack_repay number not null,
	progress varchar2(12) not null,
	collect_rate number(3) not null,
	round number(2) not null
);

create sequence seq_loan_transaction;

alter table abc_loan_transaction add(
	constraint pk_loan_seq primary key(loan_seq),
	constraint fk_loan_code foreign key(loan_code) references abc_loan(loan_code)
);

comment on table abc_loan_transaction is '대출에 관련된 내역 테이블입니다.';
comment on column abc_loan_transaction.loan_seq is '대출내역을 식별할 기본키입니다.';
comment on column abc_loan_transaction.loan_code is '어떤 대출의 내역인지 식별할 참조키입니다.';
comment on column abc_loan_transaction.interest_money is '이자금이며 대출금의 8%입니다.';
comment on column abc_loan_transaction.total_repay is '총상환금이며 대출금 + 이자금입니다.';
comment on column abc_loan_transaction.stack_repay is '총 상환금 / 대출기간 * 현재 회차수';
comment on column abc_loan_transaction.progress is '상환진행상황입니다. 최대 한글6자입니다.';
comment on column abc_loan_transaction.collect_rate is '회수비율입니다. 누적상황금을 총상환금으로 나눈 값입니다.';
comment on column abc_loan_transaction.round is '현재회차수이며 0이상의 정수 최대 2자리입니다.';