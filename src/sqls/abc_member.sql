--drop table abc_member;
create table abc_member(
  email varchar2(51),
  pwd varchar2(20) not null,
  name varchar2(12) not null,
  birth varchar2(8) not null,
  gender varchar2(4) not null,
  credit_rating number(1) not null,
  loan_count number not null,
  invest_count number not null,
  grade number(1) not null,
  auth_code varchar2(50) not null
);

alter table abc_member
add constraint pk_member_email primary key(email);

comment on table abc_member is '크라우드 펀딩 회원 테이블입니다.';
comment on column abc_member.email is '회원의 이메일이며 총 51자의 길이제한을 갖는 기본키입니다.';
comment on column abc_member.pwd is '회원의 비밀번호입니다. 영소문자 및 숫자를 포함하여 최대20자입니다.';
comment on column abc_member.name is '회원의 이름입니다. 한글로 최대 6자입니다.';
comment on column abc_member.birth is '회원의 생년월일입니다. 구분기호 없이 붙여서 총 8자입니다.';
comment on column abc_member.gender is '회원의 성별입니다. 한글로 2자이며, 남자 또는 여자값만 갖습니다..';
comment on column abc_member.credit_rating is '회원의 신용등급입니다. 1~9까지 시스템에서 자동으로 부여됩니다.';
comment on column abc_member.loan_count is '회원의 대출건수입니다. 0이상의 정수를 가집니다.';
comment on column abc_member.invest_count is '회원의 투자건수입니다. 0이상의 정수를 가집니다.';
comment on column abc_member.grade is '회원의 등급입니다. 1은 일반회원, 2는 관리자이며 기본값은 1입니다.';
comment on column abc_member.auth_code is '회원인증을 위한 이메일 인증코드입니다.';