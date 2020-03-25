CREATE TABLE attendees
(
    attendee_id  integer PRIMARY KEY,
    first_name   varchar(30) NOT NULL,
    last_name    varchar(30) NOT NULL,
    email        varchar(80) NOT NULL UNIQUE,
    phone_number varchar(20) NULL,
    title        varchar(40) NULL,
    company      varchar(50) NULL
);

CREATE SEQUENCE attendees_seq;

CREATE TABLE ticket_types
(
    ticket_type_code  varchar(1) PRIMARY KEY,
    ticket_type_name  varchar(30)  NOT NULL,
    description       varchar(100) NOT NULL,
    includes_workshop boolean      NOT NULL
);

CREATE TABLE pricing_categories
(
    pricing_category_code varchar(1) PRIMARY KEY,
    pricing_category_name varchar(20) NOT NULL,
    pricing_start_date    date        NOT NULL,
    pricing_end_date      date        NOT NULL
);

CREATE TABLE ticket_prices
(
    ticket_price_id       integer PRIMARY KEY,
    ticket_type_code      varchar(1)    NOT NULL REFERENCES ticket_types (ticket_type_code),
    pricing_category_code varchar(1)    NOT NULL REFERENCES pricing_categories (pricing_category_code),
    base_price            numeric(8, 2) NOT NULL
);

CREATE SEQUENCE ticket_prices_seq;

CREATE TABLE discount_codes
(
    discount_code_id integer PRIMARY KEY,
    discount_code    varchar(20)   NOT NULL,
    discount_name    varchar(30)   NOT NULL,
    discount_type    varchar(1)    NOT NULL,
    discount_amount  numeric(8, 2) NOT NULL
);

CREATE SEQUENCE discount_codes_seq;

CREATE TABLE attendee_tickets
(
    attendee_ticket_id integer PRIMARY KEY,
    ticket_code        varchar(40)   NOT NULL,
    attendee_id        integer       NOT NULL REFERENCES attendees (attendee_id),
    ticket_price_id    integer       NOT NULL REFERENCES ticket_prices (ticket_price_id),
    discount_code_id   integer       NULL REFERENCES discount_codes (discount_code_id),
    net_price          numeric(8, 2) NOT NULL
);

CREATE SEQUENCE attendee_tickets_seq;
