-- Create table
create table PAGE_DESIGN
(
  id              VARCHAR2(32) not null,
  page_name       VARCHAR2(200),
  page_describe   VARCHAR2(500),
  serialized_data CLOB,
  isvalid         VARCHAR2(3),
  designtime      DATE
);
-- Add comments to the table 
comment on table PAGE_DESIGN
  is 'ҳ����Ʊ�';
-- Add comments to the columns 
comment on column PAGE_DESIGN.id
  is '����';
comment on column PAGE_DESIGN.page_name
  is '����';
comment on column PAGE_DESIGN.page_describe
  is '����';
comment on column PAGE_DESIGN.serialized_data
  is '����';
comment on column PAGE_DESIGN.isvalid
  is '�Ƿ���Ч(1�� 0 ��)';
comment on column PAGE_DESIGN.designtime
  is '����ʱ��';
  
  

create table S_USER
(
  userid        VARCHAR2(32) not null,
  username      VARCHAR2(200),
  uuidname      VARCHAR2(200),
  password      VARCHAR2(200),
  cnname        VARCHAR2(200),
  mobile        VARCHAR2(200),
  email         VARCHAR2(200),
  enabled       VARCHAR2(3)
)
;
comment on table S_USER
  is '�˻���Ϣ��';
comment on column S_USER.userid
  is '�û�����ˮ��';
comment on column S_USER.username
  is '�û���';
comment on column S_USER.password
  is '�û�����';
comment on column S_USER.cnname
  is '��ʵ����';
comment on column S_USER.email
  is '�����ʼ�';
comment on column S_USER.mobile
  is '�ֻ���';
alter table S_USER
  add constraint PK_S_USER primary key (USERID);


insert into S_USER (userid, username,uuidname,password, cnname, enabled,mobile,email)
values ('1', 'admin','admin', '0192023a7bbd73250516f069df18b500', '��������Ա', '1',null,null);
  
