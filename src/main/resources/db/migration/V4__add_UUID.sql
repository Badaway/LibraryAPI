drop table user_roles;

alter table book
    drop CONSTRAINT book_member;
alter table book
    drop CONSTRAINT book_author;

truncate table users ;
truncate table book ;
truncate table authors ;
truncate table members ;
truncate table roles ;



alter table authors
    drop column id;
alter table authors
    add column id UUID PRIMARY KEY;
alter table members
    drop column id;
alter table members
    add column id UUID PRIMARY KEY;
alter table book
     drop column id;
alter table book
      add column id UUID PRIMARY KEY;
alter table book
     drop column member_id;
alter table book
      add column member_id UUID ;
alter table book
     drop column author_id;
alter table book
      add column author_id UUID ;
alter table users
      drop column id;
alter table users
      add column id UUID PRIMARY KEY;

alter table book
    add CONSTRAINT book_member FOREIGN KEY (member_id)
                REFERENCES members (id) MATCH SIMPLE
                ON UPDATE NO ACTION
                ON DELETE NO ACTION;
alter table book
    add CONSTRAINT book_author FOREIGN KEY (author_id)
                REFERENCES authors (id) MATCH SIMPLE
                ON UPDATE NO ACTION
                ON DELETE NO ACTION;


CREATE TABLE user_roles
(
    user_id UUID NOT NULL,
    role_id bigint NOT NULL,
    CONSTRAINT user_roles_pkey PRIMARY KEY (user_id, role_id),
    CONSTRAINT role_user FOREIGN KEY (role_id)
        REFERENCES roles (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT user_role FOREIGN KEY (user_id)
        REFERENCES users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION

);


