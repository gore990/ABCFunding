create table abc_judge(
	judge_seq number not null,
	loan_code number not null,
	commentary varchar2(2000) not null,
	result number(1) not null
);

create sequence seq_judge;

alter table abc_judge add(
	constraint pk_judge_seq primary key(judge_seq),
	constraint fk_judge_loan_code foreign key(loan_code) references abc_loan(loan_code)
);

comment on table abc_judge is '대출신청에 관한 심사테이블입니다.';
comment on column abc_judge.judge_seq is '심사테이블을 식별할 기본키입니다.';
comment on column abc_judge.loan_code is '어떤 대출을 심사할 것인지를 식별하는 참조키입니다.';
comment on column abc_judge.commentary is '심사평입니다. 최대 2000byte입니다.';
comment on column abc_judge.result is '심사결과입니다. 0이면 심사대기, 1이면 승인, -1이면 거절입니다.';