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
  is '页面设计表';
-- Add comments to the columns 
comment on column PAGE_DESIGN.id
  is '主键';
comment on column PAGE_DESIGN.page_name
  is '名称';
comment on column PAGE_DESIGN.page_describe
  is '描述';
comment on column PAGE_DESIGN.serialized_data
  is '数据';
comment on column PAGE_DESIGN.isvalid
  is '是否有效(1是 0 否)';
comment on column PAGE_DESIGN.designtime
  is '保存时间';
  
  

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
  is '账户信息表';
comment on column S_USER.userid
  is '用户表流水号';
comment on column S_USER.username
  is '用户名';
comment on column S_USER.password
  is '用户密码';
comment on column S_USER.cnname
  is '真实姓名';
comment on column S_USER.email
  is '电子邮件';
comment on column S_USER.mobile
  is '手机号';
alter table S_USER
  add constraint PK_S_USER primary key (USERID);


insert into S_USER (userid, username,uuidname,password, cnname, enabled,mobile,email)
values ('1', 'admin','admin', '0192023a7bbd73250516f069df18b500', '超级管理员', '1',null,null);
  
