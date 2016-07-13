create table abc_loan(
	loan_code number not null,
	email varchar2(51) not null,
	progress varchar2(12) not null,
	current_money number(4) not null,
	join_count number not null,
	round number(2) not null,
	
	employ_type varchar2(12) not null,
	scale varchar2(8) not null,
	serve number(2) not null,
	salary number(7) not null,
	
	loan_type varchar2(12) not null,
	repay_type varchar2(14) not null,
	interest_rate number(1) not null,
	loan_money number(4) not null,
	loan_date number(2) not null,
	repay number(2) not null,
	
	title varchar2(100) not null,
	fname varchar2(4000) not null,
	intro varchar2(4000) not null,
	expiry_date number(1) not null
);

alter table abc_loan
add constraint pk_loan_code primary key(loan_code);

alter table abc_loan
add constraint fk_loan_email foreign key(email) references abc_member(email);

create sequence seq_loan;

comment on table abc_loan is '대출관련 테이블입니다.';
comment on column abc_loan.loan_code is '대출상품을 식별할 대출코드입니다.';
comment on column abc_loan.email is '어떤 회원의 대출상품인지를 식별하는 참조키입니다.';
comment on column abc_loan.progress is '대출의 진행상태를 나타냅니다. 펀딩진행중,펀딩완료,상환중,상환완료를 나타냅니다. 한글로 최대6자입니다.';
comment on column abc_loan.current_money is '현재 투자된 금액을 나타냅니다. 0이상의 정수입니다.';
comment on column abc_loan.join_count is '현재 참여한 투자자 수입니다. 0이상의 정수입니다.';
comment on column abc_loan.round is '현재 회차 수를 나타냅니다. 0이상의 정수입니다.';
comment on column abc_loan.employ_type is '고용형태를 나타냅니다. 한글로 최대6자입니다.';
comment on column abc_loan.scale is '회사규모이며 한글로 최대 4자입니다.';
comment on column abc_loan.serve is '재직기간이며 0이상의 정수 2자입니다.';
comment on column abc_loan.salary is '연봉을 나타내며 0이상의 정수 7자입니다.';
comment on column abc_loan.loan_type is '대출구분이며 개인신용대출로 고정입니다.';
comment on column abc_loan.repay_type is '상황방법이며 원리금균등상환으로 고정입니다.';
comment on column abc_loan.interest_rate is '금리이며 8로 고정입니다.';
comment on column abc_loan.loan_money is '대출신청금액이며 0이상의 정수 8자, 최대 5000만입니다.';
comment on column abc_loan.loan_date is '대출기간이며 정수 2자리, 1~12,18,24 중 하나를 가집니다.';
comment on column abc_loan.repay is '상환일이며 정수 2자리, 1,5,10,15,20,25 중 하나를 가집니다.';
comment on column abc_loan.title is '상품명(제목)이며 한글로 50자까지입니다.';
comment on column abc_loan.fname is '이미지 경로이며 4000byte까지 가능합니다.';
comment on column abc_loan.intro is '소개글이며 4000byte까지 가능합니다.';
comment on column abc_loan.expiry_date is '펀딩기간이며 펀딩 중 모집할 수 있는 최대 기간을 의미합니다. 1~7까지의 정수, 최대 일주일입니다.';
