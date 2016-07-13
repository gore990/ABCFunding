create table abc_invest(
	invest_seq number not null,
	email varchar2(51) not null,
	loan_code number not null,
	invest_money number(4) not null
);

create sequence seq_invest;

alter table abc_invest add(
	constraint pk_invest_seq primary key(invest_seq),
	constraint fk_invest_email foreign key(email) references abc_member(email),
	constraint fk_invest_loan_code foreign key(loan_code) references abc_loan(loan_code)
);

comment on table abc_invest is '투자관련 테이블입니다.';
comment on column abc_invest.invest_seq is '투자테이블을 식별할 기본키입니다.';
comment on column abc_invest.email is '어떤 회원의 투자 레코드인지 식별하는 참조키입니다.';
comment on column abc_invest.loan_code is '어떤 대출의 투자 레코드인지 식별하는 참조키입니다.';
comment on column abc_invest.invest_money is '투자금이며 0이상의 정수입니다.';