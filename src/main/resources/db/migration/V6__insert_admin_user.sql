insert into users
    (id,name,email,password,created_at,updated_at)
    select '48257d4d-a5a9-4d1e-9a39-32ba6bfa0b2f','admin','admin@gts.co'
        ,'$2a$10$SlFRJ3YusirCUfEpwCxS3O29WPXwd12CFxuNzLLV8BEeGVm1nNOLG',current_timestamp,current_timestamp
    where  not exists(select email from users where email='admin@gts.co');

insert into user_roles
    (user_id,role_id)
    select (select id from users where email='admin@gts.co'),(select id from roles where name=1);
