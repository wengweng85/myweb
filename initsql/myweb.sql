--------------------------------------------
-- Export file for user MYWEB@ORCL        --
-- Created by wengsh on 2017/9/5, 1:06:06 --
--------------------------------------------

set define off
spool myweb.log

prompt
prompt Creating table AA01
prompt ===================
prompt
create table AA01
(
  aaa001 VARCHAR2(50) not null,
  aaa002 VARCHAR2(50) not null,
  aaa005 VARCHAR2(500) not null,
  aaa105 VARCHAR2(100),
  aaa104 VARCHAR2(1) not null
)
;
alter table AA01
  add constraint PK_AA01 primary key (AAA001);

prompt
prompt Creating table ACT_EVT_LOG
prompt ==========================
prompt
create table ACT_EVT_LOG
(
  log_nr_       NUMBER(19) not null,
  type_         NVARCHAR2(64),
  proc_def_id_  NVARCHAR2(64),
  proc_inst_id_ NVARCHAR2(64),
  execution_id_ NVARCHAR2(64),
  task_id_      NVARCHAR2(64),
  time_stamp_   TIMESTAMP(6) not null,
  user_id_      NVARCHAR2(255),
  data_         BLOB,
  lock_owner_   NVARCHAR2(255),
  lock_time_    TIMESTAMP(6),
  is_processed_ NUMBER(3) default 0
)
;
alter table ACT_EVT_LOG
  add primary key (LOG_NR_);

prompt
prompt Creating table ACT_RE_DEPLOYMENT
prompt ================================
prompt
create table ACT_RE_DEPLOYMENT
(
  id_          NVARCHAR2(64) not null,
  name_        NVARCHAR2(255),
  category_    NVARCHAR2(255),
  tenant_id_   NVARCHAR2(255) default '',
  deploy_time_ TIMESTAMP(6)
)
;
alter table ACT_RE_DEPLOYMENT
  add primary key (ID_);

prompt
prompt Creating table ACT_GE_BYTEARRAY
prompt ===============================
prompt
create table ACT_GE_BYTEARRAY
(
  id_            NVARCHAR2(64) not null,
  rev_           INTEGER,
  name_          NVARCHAR2(255),
  deployment_id_ NVARCHAR2(64),
  bytes_         BLOB,
  generated_     NUMBER(1)
)
;
create index ACT_IDX_BYTEAR_DEPL on ACT_GE_BYTEARRAY (DEPLOYMENT_ID_);
alter table ACT_GE_BYTEARRAY
  add primary key (ID_);
alter table ACT_GE_BYTEARRAY
  add constraint ACT_FK_BYTEARR_DEPL foreign key (DEPLOYMENT_ID_)
  references ACT_RE_DEPLOYMENT (ID_);
alter table ACT_GE_BYTEARRAY
  add check (GENERATED_ IN (1,0));

prompt
prompt Creating table ACT_GE_PROPERTY
prompt ==============================
prompt
create table ACT_GE_PROPERTY
(
  name_  NVARCHAR2(64) not null,
  value_ NVARCHAR2(300),
  rev_   INTEGER
)
;
alter table ACT_GE_PROPERTY
  add primary key (NAME_);

prompt
prompt Creating table ACT_HI_ACTINST
prompt =============================
prompt
create table ACT_HI_ACTINST
(
  id_                NVARCHAR2(64) not null,
  proc_def_id_       NVARCHAR2(64) not null,
  proc_inst_id_      NVARCHAR2(64) not null,
  execution_id_      NVARCHAR2(64) not null,
  act_id_            NVARCHAR2(255) not null,
  task_id_           NVARCHAR2(64),
  call_proc_inst_id_ NVARCHAR2(64),
  act_name_          NVARCHAR2(255),
  act_type_          NVARCHAR2(255) not null,
  assignee_          NVARCHAR2(255),
  start_time_        TIMESTAMP(6) not null,
  end_time_          TIMESTAMP(6),
  duration_          NUMBER(19),
  tenant_id_         NVARCHAR2(255) default ''
)
;
create index ACT_IDX_HI_ACT_INST_END on ACT_HI_ACTINST (END_TIME_);
create index ACT_IDX_HI_ACT_INST_EXEC on ACT_HI_ACTINST (EXECUTION_ID_, ACT_ID_);
create index ACT_IDX_HI_ACT_INST_PROCINST on ACT_HI_ACTINST (PROC_INST_ID_, ACT_ID_);
create index ACT_IDX_HI_ACT_INST_START on ACT_HI_ACTINST (START_TIME_);
alter table ACT_HI_ACTINST
  add primary key (ID_);

prompt
prompt Creating table ACT_HI_ATTACHMENT
prompt ================================
prompt
create table ACT_HI_ATTACHMENT
(
  id_           NVARCHAR2(64) not null,
  rev_          INTEGER,
  user_id_      NVARCHAR2(255),
  name_         NVARCHAR2(255),
  description_  NVARCHAR2(2000),
  type_         NVARCHAR2(255),
  task_id_      NVARCHAR2(64),
  proc_inst_id_ NVARCHAR2(64),
  url_          NVARCHAR2(2000),
  content_id_   NVARCHAR2(64),
  time_         TIMESTAMP(6)
)
;
alter table ACT_HI_ATTACHMENT
  add primary key (ID_);

prompt
prompt Creating table ACT_HI_COMMENT
prompt =============================
prompt
create table ACT_HI_COMMENT
(
  id_           NVARCHAR2(64) not null,
  type_         NVARCHAR2(255),
  time_         TIMESTAMP(6) not null,
  user_id_      NVARCHAR2(255),
  task_id_      NVARCHAR2(64),
  proc_inst_id_ NVARCHAR2(64),
  action_       NVARCHAR2(255),
  message_      NVARCHAR2(2000),
  full_msg_     BLOB
)
;
alter table ACT_HI_COMMENT
  add primary key (ID_);

prompt
prompt Creating table ACT_HI_DETAIL
prompt ============================
prompt
create table ACT_HI_DETAIL
(
  id_           NVARCHAR2(64) not null,
  type_         NVARCHAR2(255) not null,
  proc_inst_id_ NVARCHAR2(64),
  execution_id_ NVARCHAR2(64),
  task_id_      NVARCHAR2(64),
  act_inst_id_  NVARCHAR2(64),
  name_         NVARCHAR2(255) not null,
  var_type_     NVARCHAR2(64),
  rev_          INTEGER,
  time_         TIMESTAMP(6) not null,
  bytearray_id_ NVARCHAR2(64),
  double_       NUMBER(*,10),
  long_         NUMBER(19),
  text_         NVARCHAR2(2000),
  text2_        NVARCHAR2(2000)
)
;
create index ACT_IDX_HI_DETAIL_ACT_INST on ACT_HI_DETAIL (ACT_INST_ID_);
create index ACT_IDX_HI_DETAIL_NAME on ACT_HI_DETAIL (NAME_);
create index ACT_IDX_HI_DETAIL_PROC_INST on ACT_HI_DETAIL (PROC_INST_ID_);
create index ACT_IDX_HI_DETAIL_TASK_ID on ACT_HI_DETAIL (TASK_ID_);
create index ACT_IDX_HI_DETAIL_TIME on ACT_HI_DETAIL (TIME_);
alter table ACT_HI_DETAIL
  add primary key (ID_);

prompt
prompt Creating table ACT_HI_IDENTITYLINK
prompt ==================================
prompt
create table ACT_HI_IDENTITYLINK
(
  id_           NVARCHAR2(64) not null,
  group_id_     NVARCHAR2(255),
  type_         NVARCHAR2(255),
  user_id_      NVARCHAR2(255),
  task_id_      NVARCHAR2(64),
  proc_inst_id_ NVARCHAR2(64)
)
;
create index ACT_IDX_HI_IDENT_LNK_PROCINST on ACT_HI_IDENTITYLINK (PROC_INST_ID_);
create index ACT_IDX_HI_IDENT_LNK_TASK on ACT_HI_IDENTITYLINK (TASK_ID_);
create index ACT_IDX_HI_IDENT_LNK_USER on ACT_HI_IDENTITYLINK (USER_ID_);
alter table ACT_HI_IDENTITYLINK
  add primary key (ID_);

prompt
prompt Creating table ACT_HI_PROCINST
prompt ==============================
prompt
create table ACT_HI_PROCINST
(
  id_                        NVARCHAR2(64) not null,
  proc_inst_id_              NVARCHAR2(64) not null,
  business_key_              NVARCHAR2(255),
  proc_def_id_               NVARCHAR2(64) not null,
  start_time_                TIMESTAMP(6) not null,
  end_time_                  TIMESTAMP(6),
  duration_                  NUMBER(19),
  start_user_id_             NVARCHAR2(255),
  start_act_id_              NVARCHAR2(255),
  end_act_id_                NVARCHAR2(255),
  super_process_instance_id_ NVARCHAR2(64),
  delete_reason_             NVARCHAR2(2000),
  tenant_id_                 NVARCHAR2(255) default '',
  name_                      NVARCHAR2(255)
)
;
create index ACT_IDX_HI_PRO_INST_END on ACT_HI_PROCINST (END_TIME_);
create index ACT_IDX_HI_PRO_I_BUSKEY on ACT_HI_PROCINST (BUSINESS_KEY_);
alter table ACT_HI_PROCINST
  add primary key (ID_);
alter table ACT_HI_PROCINST
  add unique (PROC_INST_ID_);

prompt
prompt Creating table ACT_HI_TASKINST
prompt ==============================
prompt
create table ACT_HI_TASKINST
(
  id_             NVARCHAR2(64) not null,
  proc_def_id_    NVARCHAR2(64),
  task_def_key_   NVARCHAR2(255),
  proc_inst_id_   NVARCHAR2(64),
  execution_id_   NVARCHAR2(64),
  parent_task_id_ NVARCHAR2(64),
  name_           NVARCHAR2(255),
  description_    NVARCHAR2(2000),
  owner_          NVARCHAR2(255),
  assignee_       NVARCHAR2(255),
  start_time_     TIMESTAMP(6) not null,
  claim_time_     TIMESTAMP(6),
  end_time_       TIMESTAMP(6),
  duration_       NUMBER(19),
  delete_reason_  NVARCHAR2(2000),
  priority_       INTEGER,
  due_date_       TIMESTAMP(6),
  form_key_       NVARCHAR2(255),
  category_       NVARCHAR2(255),
  tenant_id_      NVARCHAR2(255) default ''
)
;
create index ACT_IDX_HI_TASK_INST_PROCINST on ACT_HI_TASKINST (PROC_INST_ID_);
alter table ACT_HI_TASKINST
  add primary key (ID_);

prompt
prompt Creating table ACT_HI_VARINST
prompt =============================
prompt
create table ACT_HI_VARINST
(
  id_                NVARCHAR2(64) not null,
  proc_inst_id_      NVARCHAR2(64),
  execution_id_      NVARCHAR2(64),
  task_id_           NVARCHAR2(64),
  name_              NVARCHAR2(255) not null,
  var_type_          NVARCHAR2(100),
  rev_               INTEGER,
  bytearray_id_      NVARCHAR2(64),
  double_            NUMBER(*,10),
  long_              NUMBER(19),
  text_              NVARCHAR2(2000),
  text2_             NVARCHAR2(2000),
  create_time_       TIMESTAMP(6),
  last_updated_time_ TIMESTAMP(6)
)
;
create index ACT_IDX_HI_PROCVAR_NAME_TYPE on ACT_HI_VARINST (NAME_, VAR_TYPE_);
create index ACT_IDX_HI_PROCVAR_PROC_INST on ACT_HI_VARINST (PROC_INST_ID_);
create index ACT_IDX_HI_PROCVAR_TASK_ID on ACT_HI_VARINST (TASK_ID_);
alter table ACT_HI_VARINST
  add primary key (ID_);

prompt
prompt Creating table ACT_ID_GROUP
prompt ===========================
prompt
create table ACT_ID_GROUP
(
  id_   NVARCHAR2(64) not null,
  rev_  INTEGER,
  name_ NVARCHAR2(255),
  type_ NVARCHAR2(255)
)
;
alter table ACT_ID_GROUP
  add primary key (ID_);

prompt
prompt Creating table ACT_ID_INFO
prompt ==========================
prompt
create table ACT_ID_INFO
(
  id_        NVARCHAR2(64) not null,
  rev_       INTEGER,
  user_id_   NVARCHAR2(64),
  type_      NVARCHAR2(64),
  key_       NVARCHAR2(255),
  value_     NVARCHAR2(255),
  password_  BLOB,
  parent_id_ NVARCHAR2(255)
)
;
alter table ACT_ID_INFO
  add primary key (ID_);

prompt
prompt Creating table ACT_ID_USER
prompt ==========================
prompt
create table ACT_ID_USER
(
  id_         NVARCHAR2(64) not null,
  rev_        INTEGER,
  first_      NVARCHAR2(255),
  last_       NVARCHAR2(255),
  email_      NVARCHAR2(255),
  pwd_        NVARCHAR2(255),
  picture_id_ NVARCHAR2(64)
)
;
alter table ACT_ID_USER
  add primary key (ID_);

prompt
prompt Creating table ACT_ID_MEMBERSHIP
prompt ================================
prompt
create table ACT_ID_MEMBERSHIP
(
  user_id_  NVARCHAR2(64) not null,
  group_id_ NVARCHAR2(64) not null
)
;
create index ACT_IDX_MEMB_GROUP on ACT_ID_MEMBERSHIP (GROUP_ID_);
create index ACT_IDX_MEMB_USER on ACT_ID_MEMBERSHIP (USER_ID_);
alter table ACT_ID_MEMBERSHIP
  add primary key (USER_ID_, GROUP_ID_);
alter table ACT_ID_MEMBERSHIP
  add constraint ACT_FK_MEMB_GROUP foreign key (GROUP_ID_)
  references ACT_ID_GROUP (ID_);
alter table ACT_ID_MEMBERSHIP
  add constraint ACT_FK_MEMB_USER foreign key (USER_ID_)
  references ACT_ID_USER (ID_);

prompt
prompt Creating table ACT_RE_PROCDEF
prompt =============================
prompt
create table ACT_RE_PROCDEF
(
  id_                     NVARCHAR2(64) not null,
  rev_                    INTEGER,
  category_               NVARCHAR2(255),
  name_                   NVARCHAR2(255),
  key_                    NVARCHAR2(255) not null,
  version_                INTEGER not null,
  deployment_id_          NVARCHAR2(64),
  resource_name_          NVARCHAR2(2000),
  dgrm_resource_name_     VARCHAR2(4000),
  description_            NVARCHAR2(2000),
  has_start_form_key_     NUMBER(1),
  has_graphical_notation_ NUMBER(1),
  suspension_state_       INTEGER,
  tenant_id_              NVARCHAR2(255) default ''
)
;
alter table ACT_RE_PROCDEF
  add primary key (ID_);
alter table ACT_RE_PROCDEF
  add constraint ACT_UNIQ_PROCDEF unique (KEY_, VERSION_, TENANT_ID_);
alter table ACT_RE_PROCDEF
  add check (HAS_START_FORM_KEY_ IN (1,0));
alter table ACT_RE_PROCDEF
  add check (HAS_GRAPHICAL_NOTATION_ IN (1,0));

prompt
prompt Creating table ACT_PROCDEF_INFO
prompt ===============================
prompt
create table ACT_PROCDEF_INFO
(
  id_           NVARCHAR2(64) not null,
  proc_def_id_  NVARCHAR2(64) not null,
  rev_          INTEGER,
  info_json_id_ NVARCHAR2(64)
)
;
create index ACT_IDX_PROCDEF_INFO_JSON on ACT_PROCDEF_INFO (INFO_JSON_ID_);
create index ACT_IDX_PROCDEF_INFO_PROC on ACT_PROCDEF_INFO (PROC_DEF_ID_);
alter table ACT_PROCDEF_INFO
  add primary key (ID_);
alter table ACT_PROCDEF_INFO
  add constraint ACT_UNIQ_INFO_PROCDEF unique (PROC_DEF_ID_);
alter table ACT_PROCDEF_INFO
  add constraint ACT_FK_INFO_JSON_BA foreign key (INFO_JSON_ID_)
  references ACT_GE_BYTEARRAY (ID_);
alter table ACT_PROCDEF_INFO
  add constraint ACT_FK_INFO_PROCDEF foreign key (PROC_DEF_ID_)
  references ACT_RE_PROCDEF (ID_);

prompt
prompt Creating table ACT_RE_MODEL
prompt ===========================
prompt
create table ACT_RE_MODEL
(
  id_                           NVARCHAR2(64) not null,
  rev_                          INTEGER,
  name_                         NVARCHAR2(255),
  key_                          NVARCHAR2(255),
  category_                     NVARCHAR2(255),
  create_time_                  TIMESTAMP(6),
  last_update_time_             TIMESTAMP(6),
  version_                      INTEGER,
  meta_info_                    NVARCHAR2(2000),
  deployment_id_                NVARCHAR2(64),
  editor_source_value_id_       NVARCHAR2(64),
  editor_source_extra_value_id_ NVARCHAR2(64),
  tenant_id_                    NVARCHAR2(255) default ''
)
;
create index ACT_IDX_MODEL_DEPLOYMENT on ACT_RE_MODEL (DEPLOYMENT_ID_);
create index ACT_IDX_MODEL_SOURCE on ACT_RE_MODEL (EDITOR_SOURCE_VALUE_ID_);
create index ACT_IDX_MODEL_SOURCE_EXTRA on ACT_RE_MODEL (EDITOR_SOURCE_EXTRA_VALUE_ID_);
alter table ACT_RE_MODEL
  add primary key (ID_);
alter table ACT_RE_MODEL
  add constraint ACT_FK_MODEL_DEPLOYMENT foreign key (DEPLOYMENT_ID_)
  references ACT_RE_DEPLOYMENT (ID_);
alter table ACT_RE_MODEL
  add constraint ACT_FK_MODEL_SOURCE foreign key (EDITOR_SOURCE_VALUE_ID_)
  references ACT_GE_BYTEARRAY (ID_);
alter table ACT_RE_MODEL
  add constraint ACT_FK_MODEL_SOURCE_EXTRA foreign key (EDITOR_SOURCE_EXTRA_VALUE_ID_)
  references ACT_GE_BYTEARRAY (ID_);

prompt
prompt Creating table ACT_RU_EXECUTION
prompt ===============================
prompt
create table ACT_RU_EXECUTION
(
  id_               NVARCHAR2(64) not null,
  rev_              INTEGER,
  proc_inst_id_     NVARCHAR2(64),
  business_key_     NVARCHAR2(255),
  parent_id_        NVARCHAR2(64),
  proc_def_id_      NVARCHAR2(64),
  super_exec_       NVARCHAR2(64),
  act_id_           NVARCHAR2(255),
  is_active_        NUMBER(1),
  is_concurrent_    NUMBER(1),
  is_scope_         NUMBER(1),
  is_event_scope_   NUMBER(1),
  suspension_state_ INTEGER,
  cached_ent_state_ INTEGER,
  tenant_id_        NVARCHAR2(255) default '',
  name_             NVARCHAR2(255),
  lock_time_        TIMESTAMP(6)
)
;
create index ACT_IDX_EXEC_BUSKEY on ACT_RU_EXECUTION (BUSINESS_KEY_);
create index ACT_IDX_EXE_PARENT on ACT_RU_EXECUTION (PARENT_ID_);
create index ACT_IDX_EXE_PROCDEF on ACT_RU_EXECUTION (PROC_DEF_ID_);
create index ACT_IDX_EXE_PROCINST on ACT_RU_EXECUTION (PROC_INST_ID_);
create index ACT_IDX_EXE_SUPER on ACT_RU_EXECUTION (SUPER_EXEC_);
alter table ACT_RU_EXECUTION
  add primary key (ID_);
alter table ACT_RU_EXECUTION
  add constraint ACT_FK_EXE_PARENT foreign key (PARENT_ID_)
  references ACT_RU_EXECUTION (ID_);
alter table ACT_RU_EXECUTION
  add constraint ACT_FK_EXE_PROCDEF foreign key (PROC_DEF_ID_)
  references ACT_RE_PROCDEF (ID_);
alter table ACT_RU_EXECUTION
  add constraint ACT_FK_EXE_PROCINST foreign key (PROC_INST_ID_)
  references ACT_RU_EXECUTION (ID_);
alter table ACT_RU_EXECUTION
  add constraint ACT_FK_EXE_SUPER foreign key (SUPER_EXEC_)
  references ACT_RU_EXECUTION (ID_);
alter table ACT_RU_EXECUTION
  add check (IS_ACTIVE_ IN (1,0));
alter table ACT_RU_EXECUTION
  add check (IS_CONCURRENT_ IN (1,0));
alter table ACT_RU_EXECUTION
  add check (IS_SCOPE_ IN (1,0));
alter table ACT_RU_EXECUTION
  add check (IS_EVENT_SCOPE_ IN (1,0));

prompt
prompt Creating table ACT_RU_EVENT_SUBSCR
prompt ==================================
prompt
create table ACT_RU_EVENT_SUBSCR
(
  id_            NVARCHAR2(64) not null,
  rev_           INTEGER,
  event_type_    NVARCHAR2(255) not null,
  event_name_    NVARCHAR2(255),
  execution_id_  NVARCHAR2(64),
  proc_inst_id_  NVARCHAR2(64),
  activity_id_   NVARCHAR2(64),
  configuration_ NVARCHAR2(255),
  created_       TIMESTAMP(6) not null,
  proc_def_id_   NVARCHAR2(64),
  tenant_id_     NVARCHAR2(255) default ''
)
;
create index ACT_IDX_EVENT_SUBSCR on ACT_RU_EVENT_SUBSCR (EXECUTION_ID_);
create index ACT_IDX_EVENT_SUBSCR_CONFIG_ on ACT_RU_EVENT_SUBSCR (CONFIGURATION_);
alter table ACT_RU_EVENT_SUBSCR
  add primary key (ID_);
alter table ACT_RU_EVENT_SUBSCR
  add constraint ACT_FK_EVENT_EXEC foreign key (EXECUTION_ID_)
  references ACT_RU_EXECUTION (ID_);

prompt
prompt Creating table ACT_RU_TASK
prompt ==========================
prompt
create table ACT_RU_TASK
(
  id_               NVARCHAR2(64) not null,
  rev_              INTEGER,
  execution_id_     NVARCHAR2(64),
  proc_inst_id_     NVARCHAR2(64),
  proc_def_id_      NVARCHAR2(64),
  name_             NVARCHAR2(255),
  parent_task_id_   NVARCHAR2(64),
  description_      NVARCHAR2(2000),
  task_def_key_     NVARCHAR2(255),
  owner_            NVARCHAR2(255),
  assignee_         NVARCHAR2(255),
  delegation_       NVARCHAR2(64),
  priority_         INTEGER,
  create_time_      TIMESTAMP(6),
  due_date_         TIMESTAMP(6),
  category_         NVARCHAR2(255),
  suspension_state_ INTEGER,
  tenant_id_        NVARCHAR2(255) default '',
  form_key_         NVARCHAR2(255)
)
;
create index ACT_IDX_TASK_CREATE on ACT_RU_TASK (CREATE_TIME_);
create index ACT_IDX_TASK_EXEC on ACT_RU_TASK (EXECUTION_ID_);
create index ACT_IDX_TASK_PROCDEF on ACT_RU_TASK (PROC_DEF_ID_);
create index ACT_IDX_TASK_PROCINST on ACT_RU_TASK (PROC_INST_ID_);
alter table ACT_RU_TASK
  add primary key (ID_);
alter table ACT_RU_TASK
  add constraint ACT_FK_TASK_EXE foreign key (EXECUTION_ID_)
  references ACT_RU_EXECUTION (ID_);
alter table ACT_RU_TASK
  add constraint ACT_FK_TASK_PROCDEF foreign key (PROC_DEF_ID_)
  references ACT_RE_PROCDEF (ID_);
alter table ACT_RU_TASK
  add constraint ACT_FK_TASK_PROCINST foreign key (PROC_INST_ID_)
  references ACT_RU_EXECUTION (ID_);

prompt
prompt Creating table ACT_RU_IDENTITYLINK
prompt ==================================
prompt
create table ACT_RU_IDENTITYLINK
(
  id_           NVARCHAR2(64) not null,
  rev_          INTEGER,
  group_id_     NVARCHAR2(255),
  type_         NVARCHAR2(255),
  user_id_      NVARCHAR2(255),
  task_id_      NVARCHAR2(64),
  proc_inst_id_ NVARCHAR2(64),
  proc_def_id_  NVARCHAR2(64)
)
;
create index ACT_IDX_ATHRZ_PROCEDEF on ACT_RU_IDENTITYLINK (PROC_DEF_ID_);
create index ACT_IDX_IDENT_LNK_GROUP on ACT_RU_IDENTITYLINK (GROUP_ID_);
create index ACT_IDX_IDENT_LNK_USER on ACT_RU_IDENTITYLINK (USER_ID_);
create index ACT_IDX_IDL_PROCINST on ACT_RU_IDENTITYLINK (PROC_INST_ID_);
create index ACT_IDX_TSKASS_TASK on ACT_RU_IDENTITYLINK (TASK_ID_);
alter table ACT_RU_IDENTITYLINK
  add primary key (ID_);
alter table ACT_RU_IDENTITYLINK
  add constraint ACT_FK_ATHRZ_PROCEDEF foreign key (PROC_DEF_ID_)
  references ACT_RE_PROCDEF (ID_);
alter table ACT_RU_IDENTITYLINK
  add constraint ACT_FK_IDL_PROCINST foreign key (PROC_INST_ID_)
  references ACT_RU_EXECUTION (ID_);
alter table ACT_RU_IDENTITYLINK
  add constraint ACT_FK_TSKASS_TASK foreign key (TASK_ID_)
  references ACT_RU_TASK (ID_);

prompt
prompt Creating table ACT_RU_JOB
prompt =========================
prompt
create table ACT_RU_JOB
(
  id_                  NVARCHAR2(64) not null,
  rev_                 INTEGER,
  type_                NVARCHAR2(255) not null,
  lock_exp_time_       TIMESTAMP(6),
  lock_owner_          NVARCHAR2(255),
  exclusive_           NUMBER(1),
  execution_id_        NVARCHAR2(64),
  process_instance_id_ NVARCHAR2(64),
  proc_def_id_         NVARCHAR2(64),
  retries_             INTEGER,
  exception_stack_id_  NVARCHAR2(64),
  exception_msg_       NVARCHAR2(2000),
  duedate_             TIMESTAMP(6),
  repeat_              NVARCHAR2(255),
  handler_type_        NVARCHAR2(255),
  handler_cfg_         NVARCHAR2(2000),
  tenant_id_           NVARCHAR2(255) default ''
)
;
create index ACT_IDX_JOB_EXCEPTION on ACT_RU_JOB (EXCEPTION_STACK_ID_);
alter table ACT_RU_JOB
  add primary key (ID_);
alter table ACT_RU_JOB
  add constraint ACT_FK_JOB_EXCEPTION foreign key (EXCEPTION_STACK_ID_)
  references ACT_GE_BYTEARRAY (ID_);
alter table ACT_RU_JOB
  add check (EXCLUSIVE_ IN (1,0));

prompt
prompt Creating table ACT_RU_VARIABLE
prompt ==============================
prompt
create table ACT_RU_VARIABLE
(
  id_           NVARCHAR2(64) not null,
  rev_          INTEGER,
  type_         NVARCHAR2(255) not null,
  name_         NVARCHAR2(255) not null,
  execution_id_ NVARCHAR2(64),
  proc_inst_id_ NVARCHAR2(64),
  task_id_      NVARCHAR2(64),
  bytearray_id_ NVARCHAR2(64),
  double_       NUMBER(*,10),
  long_         NUMBER(19),
  text_         NVARCHAR2(2000),
  text2_        NVARCHAR2(2000)
)
;
create index ACT_IDX_VARIABLE_TASK_ID on ACT_RU_VARIABLE (TASK_ID_);
create index ACT_IDX_VAR_BYTEARRAY on ACT_RU_VARIABLE (BYTEARRAY_ID_);
create index ACT_IDX_VAR_EXE on ACT_RU_VARIABLE (EXECUTION_ID_);
create index ACT_IDX_VAR_PROCINST on ACT_RU_VARIABLE (PROC_INST_ID_);
alter table ACT_RU_VARIABLE
  add primary key (ID_);
alter table ACT_RU_VARIABLE
  add constraint ACT_FK_VAR_BYTEARRAY foreign key (BYTEARRAY_ID_)
  references ACT_GE_BYTEARRAY (ID_);
alter table ACT_RU_VARIABLE
  add constraint ACT_FK_VAR_EXE foreign key (EXECUTION_ID_)
  references ACT_RU_EXECUTION (ID_);
alter table ACT_RU_VARIABLE
  add constraint ACT_FK_VAR_PROCINST foreign key (PROC_INST_ID_)
  references ACT_RU_EXECUTION (ID_);

prompt
prompt Creating table CODE_TYPE
prompt ========================
prompt
create table CODE_TYPE
(
  code_type        VARCHAR2(20) not null,
  type_name        VARCHAR2(200),
  code_group       VARCHAR2(200),
  code_description VARCHAR2(200),
  code_root_value  VARCHAR2(20)
)
;
comment on table CODE_TYPE
  is '[0078]业务代码表';
comment on column CODE_TYPE.code_type
  is '代码类别编码';
comment on column CODE_TYPE.type_name
  is '代码类别的名称';
comment on column CODE_TYPE.code_group
  is '代码类别的分组';
comment on column CODE_TYPE.code_description
  is '代码类别的描述';
comment on column CODE_TYPE.code_root_value
  is '代码类别根目录值如果存在';
alter table CODE_TYPE
  add constraint PK_CODE_TYPE primary key (CODE_TYPE);

prompt
prompt Creating table CODE_VALUE
prompt =========================
prompt
create table CODE_VALUE
(
  code_seq       NUMBER(6) not null,
  code_type      VARCHAR2(200) not null,
  code_level     VARCHAR2(10),
  code_value     VARCHAR2(100),
  par_code_value VARCHAR2(10),
  code_name      VARCHAR2(200),
  code_spelling  VARCHAR2(100),
  code_status    CHAR(1),
  code_describe  VARCHAR2(500),
  code_sort      VARCHAR2(100)
)
;
comment on table CODE_VALUE
  is '[0079]业务代码明细表';
comment on column CODE_VALUE.code_seq
  is '代码项序号';
comment on column CODE_VALUE.code_type
  is '代码类别编码';
comment on column CODE_VALUE.code_level
  is '代码等级';
comment on column CODE_VALUE.code_value
  is '代码值';
comment on column CODE_VALUE.par_code_value
  is '父代码值';
comment on column CODE_VALUE.code_name
  is '代码名称';
comment on column CODE_VALUE.code_spelling
  is '代码拼音';
comment on column CODE_VALUE.code_status
  is '代码状态（0：不显示，1：显示）';
comment on column CODE_VALUE.code_describe
  is '代码全称';
comment on column CODE_VALUE.code_sort
  is '代码分类';
create index CODE_VALUE_SEQ on CODE_VALUE (CODE_TYPE, PAR_CODE_VALUE);
alter table CODE_VALUE
  add primary key (CODE_SEQ);
alter table CODE_VALUE
  add constraint UK_CODE_VALUE unique (CODE_TYPE, CODE_VALUE);

prompt
prompt Creating table DEMO_AC01
prompt ========================
prompt
create table DEMO_AC01
(
  aac001   VARCHAR2(20) not null,
  aac003   VARCHAR2(50) not null,
  aac002   VARCHAR2(20),
  aac004   VARCHAR2(3) not null,
  aac005   VARCHAR2(3) not null,
  aac006   DATE not null,
  aac033   VARCHAR2(3),
  aac017   VARCHAR2(3),
  aac024   VARCHAR2(3),
  aac011   VARCHAR2(3),
  aae006   VARCHAR2(20),
  aac067   VARCHAR2(20),
  aae015   VARCHAR2(50),
  aac007   VARCHAR2(200),
  aac027   VARCHAR2(3),
  adc100   VARCHAR2(3),
  aaf011   VARCHAR2(100),
  aae011   VARCHAR2(32),
  aae036   DATE,
  aaz002   NUMBER(16),
  aae100   VARCHAR2(3),
  eae052   VARCHAR2(3),
  aae200   VARCHAR2(32),
  aae202   DATE,
  aae201   VARCHAR2(100),
  aae203   VARCHAR2(100),
  aaa148   VARCHAR2(50),
  adc300   VARCHAR2(20),
  aac127   VARCHAR2(3),
  aac032   VARCHAR2(3),
  aac034   NUMBER(5),
  aac035   NUMBER(5),
  aac036   VARCHAR2(5),
  aae013   VARCHAR2(200),
  aab301   VARCHAR2(12),
  aae009   VARCHAR2(100),
  aae010   VARCHAR2(80),
  aae198   VARCHAR2(100),
  aae199   VARCHAR2(80),
  aae132   VARCHAR2(100),
  aae133   VARCHAR2(80),
  aae134   VARCHAR2(32),
  aae135   VARCHAR2(100),
  aae102   DATE,
  id       VARCHAR2(32),
  aac128   VARCHAR2(3),
  aab800   VARCHAR2(50),
  aab801   VARCHAR2(50),
  aab802   VARCHAR2(50),
  bus_uuid VARCHAR2(32)
)
;
comment on table DEMO_AC01
  is '劳动者基本情况表（个人基本信息）';
comment on column DEMO_AC01.aac001
  is '劳动者编号';
comment on column DEMO_AC01.aac003
  is '姓名';
comment on column DEMO_AC01.aac002
  is '身份证号';
comment on column DEMO_AC01.aac004
  is '性别';
comment on column DEMO_AC01.aac005
  is '民族';
comment on column DEMO_AC01.aac006
  is '出生日期';
comment on column DEMO_AC01.aac033
  is '健康状况';
comment on column DEMO_AC01.aac017
  is '婚姻状况';
comment on column DEMO_AC01.aac024
  is '政治面貌';
comment on column DEMO_AC01.aac011
  is '学历';
comment on column DEMO_AC01.aae006
  is '联系电话';
comment on column DEMO_AC01.aac067
  is '移动电话';
comment on column DEMO_AC01.aae015
  is '电子邮件';
comment on column DEMO_AC01.aac007
  is '照片路径';
comment on column DEMO_AC01.aac027
  is '人员身份';
comment on column DEMO_AC01.adc100
  is '就业状态(就业失业状态)';
comment on column DEMO_AC01.aaf011
  is '经办机构(ID)';
comment on column DEMO_AC01.aae011
  is '经办人(ID)';
comment on column DEMO_AC01.aae036
  is '经办日期';
comment on column DEMO_AC01.aaz002
  is '日志ID';
comment on column DEMO_AC01.aae100
  is '是否有效';
comment on column DEMO_AC01.eae052
  is '审核状态';
comment on column DEMO_AC01.aae200
  is '审核人(ID)';
comment on column DEMO_AC01.aae202
  is '审核时间';
comment on column DEMO_AC01.aae201
  is '审核机构(ID)';
comment on column DEMO_AC01.aae203
  is '审核原因';
comment on column DEMO_AC01.aaa148
  is '备注信息，同统筹区编号';
comment on column DEMO_AC01.adc300
  is '就失业状态(登记证状态)';
comment on column DEMO_AC01.aac127
  is '人员类别 1正常人员,2高校毕业生,3劳动力';
comment on column DEMO_AC01.aac032
  is '血型';
comment on column DEMO_AC01.aac034
  is '身高(CM)';
comment on column DEMO_AC01.aac035
  is '体重(KG)';
comment on column DEMO_AC01.aac036
  is '视力';
comment on column DEMO_AC01.aae013
  is '备注';
comment on column DEMO_AC01.aab301
  is '统筹区编号(数据行政区划)';
comment on column DEMO_AC01.aae009
  is '经办机构名';
comment on column DEMO_AC01.aae010
  is '经办人姓名';
comment on column DEMO_AC01.aae198
  is '审核机构名';
comment on column DEMO_AC01.aae199
  is '审核人姓名';
comment on column DEMO_AC01.aae132
  is '修改机构名';
comment on column DEMO_AC01.aae133
  is '修改人姓名';
comment on column DEMO_AC01.aae134
  is '修改人(ID)';
comment on column DEMO_AC01.aae135
  is '修改机构(ID)';
comment on column DEMO_AC01.aae102
  is '修改时间';
comment on column DEMO_AC01.id
  is '匹配浪潮数据';
comment on column DEMO_AC01.aac128
  is '历史人员类别 1正常人员,2高校毕业生,3劳动力';
comment on column DEMO_AC01.aab800
  is '省';
comment on column DEMO_AC01.aab801
  is '市';
comment on column DEMO_AC01.aab802
  is '县';
comment on column DEMO_AC01.bus_uuid
  is '关联文件业务记录表';

prompt
prompt Creating table LOGININF
prompt =======================
prompt
create table LOGININF
(
  loginhash VARCHAR2(32),
  logintime DATE,
  ip        VARCHAR2(100),
  usergent  VARCHAR2(1000),
  sessionid VARCHAR2(100)
)
;
comment on column LOGININF.loginhash
  is '登录信息hash信息sessionid+ip+agent';
comment on column LOGININF.logintime
  is '登录时间';
comment on column LOGININF.ip
  is '用户ip';
comment on column LOGININF.usergent
  is 'usergent';
comment on column LOGININF.sessionid
  is 'sessionid';

prompt
prompt Creating table QRTZ_JOB_DETAILS
prompt ===============================
prompt
create table QRTZ_JOB_DETAILS
(
  sched_name        VARCHAR2(120) not null,
  job_name          VARCHAR2(200) not null,
  job_group         VARCHAR2(200) not null,
  description       VARCHAR2(250),
  job_class_name    VARCHAR2(250) not null,
  is_durable        VARCHAR2(1) not null,
  is_nonconcurrent  VARCHAR2(1) not null,
  is_update_data    VARCHAR2(1) not null,
  requests_recovery VARCHAR2(1) not null,
  job_data          BLOB
)
;
create index IDX_QRTZ_J_GRP on QRTZ_JOB_DETAILS (SCHED_NAME, JOB_GROUP);
create index IDX_QRTZ_J_REQ_RECOVERY on QRTZ_JOB_DETAILS (SCHED_NAME, REQUESTS_RECOVERY);
alter table QRTZ_JOB_DETAILS
  add constraint QRTZ_JOB_DETAILS_PK primary key (SCHED_NAME, JOB_NAME, JOB_GROUP);

prompt
prompt Creating table QRTZ_TRIGGERS
prompt ============================
prompt
create table QRTZ_TRIGGERS
(
  sched_name     VARCHAR2(120) not null,
  trigger_name   VARCHAR2(200) not null,
  trigger_group  VARCHAR2(200) not null,
  job_name       VARCHAR2(200) not null,
  job_group      VARCHAR2(200) not null,
  description    VARCHAR2(250),
  next_fire_time NUMBER(13),
  prev_fire_time NUMBER(13),
  priority       NUMBER(13),
  trigger_state  VARCHAR2(16) not null,
  trigger_type   VARCHAR2(8) not null,
  start_time     NUMBER(13) not null,
  end_time       NUMBER(13),
  calendar_name  VARCHAR2(200),
  misfire_instr  NUMBER(2),
  job_data       BLOB
)
;
create index IDX_QRTZ_T_C on QRTZ_TRIGGERS (SCHED_NAME, CALENDAR_NAME);
create index IDX_QRTZ_T_G on QRTZ_TRIGGERS (SCHED_NAME, TRIGGER_GROUP);
create index IDX_QRTZ_T_J on QRTZ_TRIGGERS (SCHED_NAME, JOB_NAME, JOB_GROUP);
create index IDX_QRTZ_T_JG on QRTZ_TRIGGERS (SCHED_NAME, JOB_GROUP);
create index IDX_QRTZ_T_NEXT_FIRE_TIME on QRTZ_TRIGGERS (SCHED_NAME, NEXT_FIRE_TIME);
create index IDX_QRTZ_T_NFT_MISFIRE on QRTZ_TRIGGERS (SCHED_NAME, MISFIRE_INSTR, NEXT_FIRE_TIME);
create index IDX_QRTZ_T_NFT_ST on QRTZ_TRIGGERS (SCHED_NAME, TRIGGER_STATE, NEXT_FIRE_TIME);
create index IDX_QRTZ_T_NFT_ST_MISFIRE on QRTZ_TRIGGERS (SCHED_NAME, MISFIRE_INSTR, NEXT_FIRE_TIME, TRIGGER_STATE);
create index IDX_QRTZ_T_NFT_ST_MISFIRE_GRP on QRTZ_TRIGGERS (SCHED_NAME, MISFIRE_INSTR, NEXT_FIRE_TIME, TRIGGER_GROUP, TRIGGER_STATE);
create index IDX_QRTZ_T_N_G_STATE on QRTZ_TRIGGERS (SCHED_NAME, TRIGGER_GROUP, TRIGGER_STATE);
create index IDX_QRTZ_T_N_STATE on QRTZ_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP, TRIGGER_STATE);
create index IDX_QRTZ_T_STATE on QRTZ_TRIGGERS (SCHED_NAME, TRIGGER_STATE);
alter table QRTZ_TRIGGERS
  add constraint QRTZ_TRIGGERS_PK primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP);
alter table QRTZ_TRIGGERS
  add constraint QRTZ_TRIGGER_TO_JOBS_FK foreign key (SCHED_NAME, JOB_NAME, JOB_GROUP)
  references QRTZ_JOB_DETAILS (SCHED_NAME, JOB_NAME, JOB_GROUP);

prompt
prompt Creating table QRTZ_BLOB_TRIGGERS
prompt =================================
prompt
create table QRTZ_BLOB_TRIGGERS
(
  sched_name    VARCHAR2(120) not null,
  trigger_name  VARCHAR2(200) not null,
  trigger_group VARCHAR2(200) not null,
  blob_data     BLOB
)
;
alter table QRTZ_BLOB_TRIGGERS
  add constraint QRTZ_BLOB_TRIG_PK primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP);
alter table QRTZ_BLOB_TRIGGERS
  add constraint QRTZ_BLOB_TRIG_TO_TRIG_FK foreign key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
  references QRTZ_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP);

prompt
prompt Creating table QRTZ_CALENDARS
prompt =============================
prompt
create table QRTZ_CALENDARS
(
  sched_name    VARCHAR2(120) not null,
  calendar_name VARCHAR2(200) not null,
  calendar      BLOB not null
)
;
alter table QRTZ_CALENDARS
  add constraint QRTZ_CALENDARS_PK primary key (SCHED_NAME, CALENDAR_NAME);

prompt
prompt Creating table QRTZ_CRON_TRIGGERS
prompt =================================
prompt
create table QRTZ_CRON_TRIGGERS
(
  sched_name      VARCHAR2(120) not null,
  trigger_name    VARCHAR2(200) not null,
  trigger_group   VARCHAR2(200) not null,
  cron_expression VARCHAR2(120) not null,
  time_zone_id    VARCHAR2(80)
)
;
alter table QRTZ_CRON_TRIGGERS
  add constraint QRTZ_CRON_TRIG_PK primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP);
alter table QRTZ_CRON_TRIGGERS
  add constraint QRTZ_CRON_TRIG_TO_TRIG_FK foreign key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
  references QRTZ_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP);

prompt
prompt Creating table QRTZ_FIRED_TRIGGERS
prompt ==================================
prompt
create table QRTZ_FIRED_TRIGGERS
(
  sched_name        VARCHAR2(120) not null,
  entry_id          VARCHAR2(95) not null,
  trigger_name      VARCHAR2(200) not null,
  trigger_group     VARCHAR2(200) not null,
  instance_name     VARCHAR2(200) not null,
  fired_time        NUMBER(13) not null,
  sched_time        NUMBER(13) not null,
  priority          NUMBER(13) not null,
  state             VARCHAR2(16) not null,
  job_name          VARCHAR2(200),
  job_group         VARCHAR2(200),
  is_nonconcurrent  VARCHAR2(1),
  requests_recovery VARCHAR2(1)
)
;
create index IDX_QRTZ_FT_INST_JOB_REQ_RCVRY on QRTZ_FIRED_TRIGGERS (SCHED_NAME, INSTANCE_NAME, REQUESTS_RECOVERY);
create index IDX_QRTZ_FT_JG on QRTZ_FIRED_TRIGGERS (SCHED_NAME, JOB_GROUP);
create index IDX_QRTZ_FT_J_G on QRTZ_FIRED_TRIGGERS (SCHED_NAME, JOB_NAME, JOB_GROUP);
create index IDX_QRTZ_FT_TG on QRTZ_FIRED_TRIGGERS (SCHED_NAME, TRIGGER_GROUP);
create index IDX_QRTZ_FT_TRIG_INST_NAME on QRTZ_FIRED_TRIGGERS (SCHED_NAME, INSTANCE_NAME);
create index IDX_QRTZ_FT_T_G on QRTZ_FIRED_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP);
alter table QRTZ_FIRED_TRIGGERS
  add constraint QRTZ_FIRED_TRIGGER_PK primary key (SCHED_NAME, ENTRY_ID);

prompt
prompt Creating table QRTZ_LOCKS
prompt =========================
prompt
create table QRTZ_LOCKS
(
  sched_name VARCHAR2(120) not null,
  lock_name  VARCHAR2(40) not null
)
;
alter table QRTZ_LOCKS
  add constraint QRTZ_LOCKS_PK primary key (SCHED_NAME, LOCK_NAME);

prompt
prompt Creating table QRTZ_PAUSED_TRIGGER_GRPS
prompt =======================================
prompt
create table QRTZ_PAUSED_TRIGGER_GRPS
(
  sched_name    VARCHAR2(120) not null,
  trigger_group VARCHAR2(200) not null
)
;
alter table QRTZ_PAUSED_TRIGGER_GRPS
  add constraint QRTZ_PAUSED_TRIG_GRPS_PK primary key (SCHED_NAME, TRIGGER_GROUP);

prompt
prompt Creating table QRTZ_SCHEDULER_STATE
prompt ===================================
prompt
create table QRTZ_SCHEDULER_STATE
(
  sched_name        VARCHAR2(120) not null,
  instance_name     VARCHAR2(200) not null,
  last_checkin_time NUMBER(13) not null,
  checkin_interval  NUMBER(13) not null
)
;
alter table QRTZ_SCHEDULER_STATE
  add constraint QRTZ_SCHEDULER_STATE_PK primary key (SCHED_NAME, INSTANCE_NAME);

prompt
prompt Creating table QRTZ_SIMPLE_TRIGGERS
prompt ===================================
prompt
create table QRTZ_SIMPLE_TRIGGERS
(
  sched_name      VARCHAR2(120) not null,
  trigger_name    VARCHAR2(200) not null,
  trigger_group   VARCHAR2(200) not null,
  repeat_count    NUMBER(7) not null,
  repeat_interval NUMBER(12) not null,
  times_triggered NUMBER(10) not null
)
;
alter table QRTZ_SIMPLE_TRIGGERS
  add constraint QRTZ_SIMPLE_TRIG_PK primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP);
alter table QRTZ_SIMPLE_TRIGGERS
  add constraint QRTZ_SIMPLE_TRIG_TO_TRIG_FK foreign key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
  references QRTZ_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP);

prompt
prompt Creating table QRTZ_SIMPROP_TRIGGERS
prompt ====================================
prompt
create table QRTZ_SIMPROP_TRIGGERS
(
  sched_name    VARCHAR2(120) not null,
  trigger_name  VARCHAR2(200) not null,
  trigger_group VARCHAR2(200) not null,
  str_prop_1    VARCHAR2(512),
  str_prop_2    VARCHAR2(512),
  str_prop_3    VARCHAR2(512),
  int_prop_1    NUMBER(10),
  int_prop_2    NUMBER(10),
  long_prop_1   NUMBER(13),
  long_prop_2   NUMBER(13),
  dec_prop_1    NUMBER(13,4),
  dec_prop_2    NUMBER(13,4),
  bool_prop_1   VARCHAR2(1),
  bool_prop_2   VARCHAR2(1)
)
;
alter table QRTZ_SIMPROP_TRIGGERS
  add constraint QRTZ_SIMPROP_TRIG_PK primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP);
alter table QRTZ_SIMPROP_TRIGGERS
  add constraint QRTZ_SIMPROP_TRIG_TO_TRIG_FK foreign key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
  references QRTZ_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP);

prompt
prompt Creating table S_BUS_FILE_RECORD
prompt ================================
prompt
create table S_BUS_FILE_RECORD
(
  bus_uuid      VARCHAR2(36) not null,
  file_uuid     VARCHAR2(36) not null,
  file_bus_id   VARCHAR2(36),
  file_bus_type VARCHAR2(36) not null,
  bus_status    VARCHAR2(36),
  bus_addtime   DATE
)
;
comment on table S_BUS_FILE_RECORD
  is '业务文件记录表';
comment on column S_BUS_FILE_RECORD.bus_uuid
  is '业务文件记录表编号';
comment on column S_BUS_FILE_RECORD.file_uuid
  is '文件上传记录表编号';
comment on column S_BUS_FILE_RECORD.file_bus_id
  is '文件所属业务编号';
comment on column S_BUS_FILE_RECORD.file_bus_type
  is '文件所属业务业务类型';
comment on column S_BUS_FILE_RECORD.bus_status
  is '文件上传状态';
comment on column S_BUS_FILE_RECORD.bus_addtime
  is '业务发生时间';

prompt
prompt Creating table S_FILE_RECORD
prompt ============================
prompt
create table S_FILE_RECORD
(
  file_uuid    VARCHAR2(36) not null,
  file_name    VARCHAR2(200),
  file_length  VARCHAR2(32),
  file_addtime DATE,
  file_path    VARCHAR2(200),
  file_status  VARCHAR2(32),
  file_md5     VARCHAR2(32),
  file_type    VARCHAR2(32)
)
;
comment on table S_FILE_RECORD
  is '系统之文件上传记录表';
comment on column S_FILE_RECORD.file_uuid
  is '文件上传记录表编号(uuid)';
comment on column S_FILE_RECORD.file_name
  is '文件名称';
comment on column S_FILE_RECORD.file_length
  is '文件大小单位(byte)';
comment on column S_FILE_RECORD.file_addtime
  is '文件上传时间';
comment on column S_FILE_RECORD.file_path
  is '文件上传角色路径';
comment on column S_FILE_RECORD.file_status
  is '文件状态(0无效 1有效审核通过)';
comment on column S_FILE_RECORD.file_md5
  is '文件md5,用于判断是否重复上传';
comment on column S_FILE_RECORD.file_type
  is '文件类型';
alter table S_FILE_RECORD
  add constraint PK_S_FILE_RECORD primary key (FILE_UUID);

prompt
prompt Creating table S_GROUP
prompt ======================
prompt
create table S_GROUP
(
  groupid      VARCHAR2(32) not null,
  description  VARCHAR2(500),
  parentid     VARCHAR2(32),
  org          VARCHAR2(8),
  districtcode VARCHAR2(32),
  license      VARCHAR2(20),
  name         VARCHAR2(100) not null,
  principal    VARCHAR2(32),
  shortname    VARCHAR2(50),
  address      VARCHAR2(150),
  tel          VARCHAR2(30),
  linkman      VARCHAR2(30),
  type         VARCHAR2(3),
  chargedept   VARCHAR2(50),
  otherinfo    VARCHAR2(2000),
  owner        VARCHAR2(32),
  status       CHAR(1),
  createdate   DATE
)
;
comment on column S_GROUP.groupid
  is '机构ID';
comment on column S_GROUP.description
  is '用户组描述';
comment on column S_GROUP.parentid
  is '上级结构ID';
comment on column S_GROUP.org
  is '系统机构编码';
comment on column S_GROUP.districtcode
  is '地区代码';
comment on column S_GROUP.name
  is '用户组名称';
comment on column S_GROUP.principal
  is '机构负责人';
comment on column S_GROUP.shortname
  is '简称';
comment on column S_GROUP.address
  is '地址';
comment on column S_GROUP.tel
  is '电话';
comment on column S_GROUP.linkman
  is '联系人';
comment on column S_GROUP.chargedept
  is '主管部门';
comment on column S_GROUP.otherinfo
  is '其他信息';
comment on column S_GROUP.owner
  is '创建者';
comment on column S_GROUP.status
  is '状态';
comment on column S_GROUP.createdate
  is '创建时间';

prompt
prompt Creating table S_LOG
prompt ====================
prompt
create table S_LOG
(
  logid         VARCHAR2(36) not null,
  logtype       VARCHAR2(10),
  message       VARCHAR2(2000),
  logtime       DATE,
  cost          VARCHAR2(100),
  stackmsg      CLOB,
  exceptiontype VARCHAR2(100),
  usergent      VARCHAR2(1000),
  ipaddr        VARCHAR2(200),
  referer       VARCHAR2(1000),
  url           VARCHAR2(1000),
  userid        VARCHAR2(32),
  cookie        VARCHAR2(1000),
  appkey        VARCHAR2(1000),
  queryparam    VARCHAR2(1000),
  method        VARCHAR2(1000),
  success       VARCHAR2(1000),
  responsemsg   VARCHAR2(2000)
)
;
comment on table S_LOG
  is '网站日志';
comment on column S_LOG.logid
  is '日志id';
comment on column S_LOG.logtype
  is '日志类型';
comment on column S_LOG.message
  is '日志标题';
comment on column S_LOG.logtime
  is '发生时间';
comment on column S_LOG.cost
  is '请求耗费时间';
comment on column S_LOG.stackmsg
  is '异常栈信息';
comment on column S_LOG.exceptiontype
  is '异常类型';
comment on column S_LOG.usergent
  is 'user-agent';
comment on column S_LOG.ipaddr
  is '客户端ip地址';
comment on column S_LOG.referer
  is 'refer';
comment on column S_LOG.url
  is '请求的地址';
comment on column S_LOG.userid
  is '当前操作人员id';
comment on column S_LOG.cookie
  is 'cookie';
comment on column S_LOG.appkey
  is 'appkey';
comment on column S_LOG.queryparam
  is '请求参数信息';
comment on column S_LOG.method
  is '请求方法类型';
comment on column S_LOG.success
  is '请求是否成功';
comment on column S_LOG.responsemsg
  is '返回信息';
alter table S_LOG
  add constraint PK_S_ERRORLOG primary key (LOGID);

prompt
prompt Creating table S_PERMISSION
prompt ===========================
prompt
create table S_PERMISSION
(
  permissionid VARCHAR2(32) not null,
  name         VARCHAR2(32),
  type         VARCHAR2(100),
  url          VARCHAR2(100),
  parentid     VARCHAR2(32),
  enabled      VARCHAR2(3),
  sortnum      VARCHAR2(32),
  open         VARCHAR2(10),
  describe     VARCHAR2(200),
  code         VARCHAR2(200),
  updatetime   DATE,
  iconcss      VARCHAR2(100)
)
;
comment on table S_PERMISSION
  is '网上办事系统功能模块表';
comment on column S_PERMISSION.permissionid
  is '权限表流水号 uuid ';
comment on column S_PERMISSION.name
  is '权限名称';
comment on column S_PERMISSION.type
  is '权限类型(1 节点 2叶子 3 按钮)';
comment on column S_PERMISSION.url
  is '叶子结点对应功能url(相对地址)';
comment on column S_PERMISSION.parentid
  is '父权限编号';
comment on column S_PERMISSION.enabled
  is '是否有效标志';
comment on column S_PERMISSION.sortnum
  is '排序页面';
comment on column S_PERMISSION.open
  is '打开状态';
comment on column S_PERMISSION.describe
  is '权限描述';
comment on column S_PERMISSION.code
  is '权限编码';
comment on column S_PERMISSION.updatetime
  is '权限最后更新时间';
comment on column S_PERMISSION.iconcss
  is '图标样式';
alter table S_PERMISSION
  add constraint PK_S_PERMISSION_K primary key (PERMISSIONID);

prompt
prompt Creating table S_ROLE
prompt =====================
prompt
create table S_ROLE
(
  roleid     VARCHAR2(32) not null,
  name       VARCHAR2(200),
  describe   VARCHAR2(200),
  enabled    VARCHAR2(20),
  code       VARCHAR2(200),
  updatetime DATE
)
;
comment on table S_ROLE
  is '网上办事用户角色信息表 ';
comment on column S_ROLE.roleid
  is '角色id uuid';
comment on column S_ROLE.name
  is '角色名称';
comment on column S_ROLE.describe
  is '角色描述';
comment on column S_ROLE.enabled
  is '是否有效标志';
comment on column S_ROLE.code
  is '角色编码';
comment on column S_ROLE.updatetime
  is '最后更新时间';
alter table S_ROLE
  add constraint PK_S_ROLE_K primary key (ROLEID);

prompt
prompt Creating table S_ROLE_PERMISSION
prompt ================================
prompt
create table S_ROLE_PERMISSION
(
  id           VARCHAR2(32) not null,
  roleid       VARCHAR2(32),
  permissionid VARCHAR2(32)
)
;
comment on table S_ROLE_PERMISSION
  is '网上办事角色与权限关联表';
comment on column S_ROLE_PERMISSION.id
  is '流水号 uuid ';
comment on column S_ROLE_PERMISSION.roleid
  is '角色id';
comment on column S_ROLE_PERMISSION.permissionid
  is '权限编号';
alter table S_ROLE_PERMISSION
  add constraint PK_S_ROLE_PERMISSION primary key (ID);

prompt
prompt Creating table S_USER
prompt =====================
prompt
create table S_USER
(
  userid     VARCHAR2(32) not null,
  password   VARCHAR2(64),
  username   VARCHAR2(50),
  enabled    VARCHAR2(1) not null,
  isleader   VARCHAR2(1),
  cnname     VARCHAR2(32),
  owner      VARCHAR2(32),
  macaddr    VARCHAR2(300),
  usertype   VARCHAR2(2),
  otherinfo  VARCHAR2(2000),
  createdate DATE
)
;
comment on table S_USER
  is '系统用户表';
comment on column S_USER.userid
  is '用户ID';
comment on column S_USER.password
  is '密码';
comment on column S_USER.username
  is '登录名';
comment on column S_USER.enabled
  is '用户是否有效 1有效，0无效';
comment on column S_USER.isleader
  is '是否领导 1是，0不是';
comment on column S_USER.cnname
  is '用户名称';
comment on column S_USER.owner
  is '创建者';
comment on column S_USER.macaddr
  is '网卡地址';
comment on column S_USER.usertype
  is '用户类别 0超级管理员，1管理员，2普通用户';
comment on column S_USER.otherinfo
  is '其它信息';
comment on column S_USER.createdate
  is '创建时间';

prompt
prompt Creating table S_USERGROUPREF
prompt =============================
prompt
create table S_USERGROUPREF
(
  usergroupid VARCHAR2(32) not null,
  userid      VARCHAR2(32),
  groupid     VARCHAR2(32)
)
;
comment on table S_USERGROUPREF
  is '用户用户组关联表';
comment on column S_USERGROUPREF.usergroupid
  is 'ID';
comment on column S_USERGROUPREF.userid
  is '用户ID';
comment on column S_USERGROUPREF.groupid
  is '组ID';

prompt
prompt Creating table S_USER_PERMISSION
prompt ================================
prompt
create table S_USER_PERMISSION
(
  id           VARCHAR2(32) not null,
  userid       VARCHAR2(32) not null,
  permissionid VARCHAR2(32) not null
)
;
comment on table S_USER_PERMISSION
  is '用户与权限关联表';
comment on column S_USER_PERMISSION.id
  is '用户与权限关联表编号';
comment on column S_USER_PERMISSION.userid
  is '用户表流水号';
comment on column S_USER_PERMISSION.permissionid
  is '权限编号';
alter table S_USER_PERMISSION
  add constraint PK_S_USER_PERMISSION primary key (ID);

prompt
prompt Creating table S_USER_ROLE
prompt ==========================
prompt
create table S_USER_ROLE
(
  id     VARCHAR2(32) not null,
  userid VARCHAR2(32) not null,
  roleid VARCHAR2(32) not null
)
;
comment on table S_USER_ROLE
  is '网上办事用户与角色关联表';
comment on column S_USER_ROLE.id
  is '流水号';
comment on column S_USER_ROLE.userid
  is '用户id';
comment on column S_USER_ROLE.roleid
  is '角色id';
alter table S_USER_ROLE
  add constraint PK_S_USER_ROLE primary key (ID);

prompt
prompt Creating sequence ACT_EVT_LOG_SEQ
prompt =================================
prompt
create sequence ACT_EVT_LOG_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence CODE_VALUE_SEQ
prompt ================================
prompt
create sequence CODE_VALUE_SEQ
minvalue 1
maxvalue 999999999
start with 24185
increment by 1
cache 20;

prompt
prompt Creating sequence SQ_AAC001
prompt ===========================
prompt
create sequence SQ_AAC001
minvalue 1
maxvalue 999999999999999999999999999
start with 17950720
increment by 1
cache 20;

prompt
prompt Creating view AA26
prompt ==================
prompt
create or replace force view aa26 as
select
  GROUPID AAB301,
  DESCRIPTION AAA146,
  Case When (substr(GROUPID,3,4)='0000' And Length(GROUPID) = 6) Then '1'
   When (substr(GROUPID,5,2)='00' And Length(GROUPID) = 6) Then '2'
   When (substr(GROUPID,5,2)<>'00' And Length(GROUPID) = 6) Then '3'
   When (Length(GROUPID) > 6 And Length(GROUPID)<10) Then '4'
   When (Length(GROUPID) > 10) Then '5'
    End aaa147,
  PARENTID AAA148,
  Null EAE037

from s_group t;

prompt
prompt Creating view V_AA10
prompt ====================
prompt
create or replace force view v_aa10 as
select code_type aaa100, code_value aaa102, code_name aaa103, '1' aaa105 ,nvl(code_describe,code_name)  aaa106 from code_value t -- where t.code_type not in ('AAB301','AAC183_1','AAC200','AAC180')
UNION ALL
select 'AAB800' aaa100, code_value aaa102, code_name aaa103, PAR_CODE_VALUE aaa105, nvl(code_describe,code_name)  aaa106 from code_value t where t.code_type='AAB301' AND T.CODE_LEVEL='1'
union all
select 'AAB801' aaa100, code_value aaa102, code_name aaa103, PAR_CODE_VALUE aaa105, nvl(code_describe,code_name)  aaa106 from code_value t where t.code_type='AAB301' AND T.CODE_LEVEL='2'
union all
select 'AAB802' aaa100, code_value aaa102, code_name aaa103, PAR_CODE_VALUE aaa105, nvl(code_describe,code_name)  aaa106 from code_value t where t.code_type='AAB301' AND T.CODE_LEVEL='3'
union all
select 'AAS001',aab301,aaa146,aaa148,aaa146 aaa106 from AA26 WHERE aaa147='1'
union all
select 'AAS002',aab301,aaa146,aaa148,aaa146 aaa106 from AA26 WHERE aaa147='2'
union all
select 'AAS003',aab301,aaa146,aaa148,aaa146 aaa106 from AA26 WHERE aaa147='3'
union all
select 'AAS004',aab301,aaa146,aaa148,aaa146 aaa106 from AA26 WHERE aaa147='4'
union all
select 'AAS005',aab301,aaa146,aaa148,aaa146 aaa106 from AA26 WHERE aaa147='5'
union all
select 'FILENAME',file_uuid,file_name,'','' aaa106 from S_FILE_RECORD
;

prompt
prompt Creating view V_CODE_TYPE
prompt =========================
prompt
create or replace force view v_code_type as
select aaa100 from v_aa10 t where t.aaa100 in ('FILE_BUS_TYPE','APPKEY') group by aaa100;

prompt
prompt Creating view V_SUGGEST_DATA
prompt ============================
prompt
create or replace force view v_suggest_data as
select aac001 id,aac002 key, aac003 name, aac003||'('||aac002||')' showname  ,'AC01' keytype  from demo_ac01;

prompt
prompt Creating view V_S_GROUP
prompt =======================
prompt
create or replace force view v_s_group as
select
     "GROUPID","DESCRIPTION","PARENTID","ORG","DISTRICTCODE","LICENSE","NAME","PRINCIPAL","SHORTNAME","ADDRESS","TEL","LINKMAN","TYPE","CHARGEDEPT","OTHERINFO","OWNER","STATUS","CREATEDATE","HASHCODE","RATE","ORGAN_ID","PARENT_ORGAN_ID","DATAAREA" from sxjyuc.smt_group;

prompt
prompt Creating view V_S_USER
prompt ======================
prompt
create or replace force view v_s_user as
select
     t.loginname username,
     t.passwd password,
     t.username cnname,
     t.useful  enabled from sxjyuc.smt_user t;

prompt
prompt Creating view V_S_USERGROUREF
prompt =============================
prompt
create or replace force view v_s_usergrouref as
select
     "USERGROUPID","USERID","GROUPID","ISLEADER" from sxjyuc.smt_usergroupref;

prompt
prompt Creating function ORACLE_TO_UNIX
prompt ================================
prompt
create or replace function oracle_to_unix(in_date IN DATE) return number is
begin
  return( (in_date -TO_DATE('19700101','yyyymmdd'))*86400 - TO_NUMBER(SUBSTR(TZ_OFFSET(sessiontimezone),1,3))*3600);
end oracle_to_unix;
/

prompt
prompt Creating function UNIX_TO_ORACLE
prompt ================================
prompt
create or replace function unix_to_oracle(in_number NUMBER) return date is
begin
  return(TO_DATE('19700101','yyyymmdd') + in_number/86400 +TO_NUMBER(SUBSTR(TZ_OFFSET(sessiontimezone),1,3))/24);
end unix_to_oracle;
/


spool off
