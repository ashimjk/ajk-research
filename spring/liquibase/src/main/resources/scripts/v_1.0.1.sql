--liquibase formatted sql
--changeset ashimjk:1.0.1

create sequence corporate_beneficiary_id_sequence start with 1 increment by 1;
create sequence beneficiary_id_sequence start with 1 increment by 1;
create sequence contact_person_id_sequence start with 1 increment by 1;

insert into corporate_beneficiary(id)
values (1);

insert into beneficiary(id, name, corporate_beneficiary_id)
values (1, 'ashim', 1);

insert into contact_person(id, cp_name, corporate_beneficiary_id)
values (1, 'pabitra', 1);
