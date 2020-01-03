--liquibase formatted sql
--changeset ashimjk:1.0.0

create table corporate_beneficiary
(
	id SERIAL not null
		constraint corporate_beneficiary_pkey
			primary key
);

create table beneficiary
(
	id SERIAL not null
		constraint beneficiary_pkey
			primary key,
	name varchar(255),
	corporate_beneficiary_id bigint
		constraint beneficiary_corporate_beneficiary_fkey
			references corporate_beneficiary
);

create table contact_person
(
	id SERIAL not null
		constraint contact_person_pkey
			primary key,
	cp_name varchar(255),
	corporate_beneficiary_id bigint not null
		constraint contact_person_corporate_beneficiary_fkey
			references corporate_beneficiary
);
