alter table audit_logs
add column old_value character varying(485760);
alter table audit_logs
add column new_value character varying(485760);