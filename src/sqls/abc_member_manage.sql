create table abc_member_manage(
  member_manage_seq number,
  email varchar2(51) not null,
  total_invest_count number,
  current_invest_count number,
  total_profit number,
  stack_invest_money number,
  total_loan_count number,
  current_loan_count number,
  stack_loan_money number,
  stack_repay number
);

alter table abc_member_manage
add constraint pk_member_seq primary key(seq);

alter table abc_member_manage
add constraint fk_member_manage_email foreign key(email) references abc_member(email);

create sequence seq_member_mange;

comment on table abc_member_manage is '관리자가 관리하는 회원관리 테이블입니다.';
comment on column abc_member_manage.member_manage_seq is '회원관리 테이블을 식별하는 시퀀스입니다.';
comment on column abc_member_manage.email is '회원 테이블의 email 컬럼을 참조하는 참조키입니다. 회원관리에서 부가적으로 회원의 데이터가 필요할 때 사용합니다.';
comment on column abc_member_manage.total_invest_count is '총 투자횟수 입니다. 한 회원의 총 투자 횟수를 나타냅니다. 0이상의 정수를 가집니다.';
comment on column abc_member_manage.current_invest_count is '현재 투자 건수입니다. 현재 얼마나 투자중인지 나타냅니다. 0이상의 정수를 가집니다.';
comment on column abc_member_manage.total_profit is '투자를 해서 얼마나 벌었는지를 나타내는 총 수익금입니다. 0이상의 정수를 가집니다.';
comment on column abc_member_manage.stack_invest_money is '얼마나 투자 했는지를 나타내는 누적투자금입니다. 0이상의 정수를 가집니다.';
comment on column abc_member_manage.total_loan_count is '회원의 총 대출 건수를 나타냅니다. 0이상의 정수를 가집니다.';
comment on column abc_member_manage.current_loan_count is '현재 대출중인 건수를 나타냅니다. 0이상의 정수를 가집니다.';
comment on column abc_member_manage.stack_loan_money is '얼마나 대출했는지를 나타내는 누적대출금입니다. 0이상의 정수를 가집니다.';
comment on column abc_member_manage.stack_repay is '여태껏 상환한 금액을 합친 누적상환금입니다. 0이상의 정수를 가집니다.';