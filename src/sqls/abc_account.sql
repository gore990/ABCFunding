create table abc_account(
	account_no varchar2(14) not null,
	email varchar2(51) not null,
	balance number not null,
	account_holder varchar2(12) not null
);

alter table abc_account
add constraint pk_account_no primary key(account_no);

alter table abc_account
add constraint fk_account_email foreign key(email) references abc_member(email);

comment on table abc_account is '회원의 가상계좌입니다.';
comment on column abc_account.account_no is '계좌번호입니다. 숫자3-숫자6숫자3 형식의 총 14자를 가집니다.';
comment on column abc_account.email is '회원의 이메일을 참조하는 참조키입니다. 어떤 회원의 계좌인지를 나타냅니다.';
comment on column abc_account.balance is '예치금(잔액)입니다. 0이상의 정수를 가집니다.';
comment on column abc_account.account_holder is '예금주입니다. 해당 계좌를 갖는 회원의 이름입니다.';