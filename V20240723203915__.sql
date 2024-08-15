CREATE TABLE product.user
(
    user_id UUID primary key not null,
    first_name varchar(45) not null,
    last_name varchar(45) not null,
    email varchar(45) not null unique,
    password varchar(45),
    account_id  UUID,
    address_id  UUID
);
CREATE TABLE product.address
(
    address_id UUID primary key not null,
    country varchar(45) not null,
    city varchar(45) not null,
    street varchar(45) not null
);

CREATE TABLE product.account
(
    account_id   UUID primary key not null,
    account_name varchar(45)      not null unique ,
    account_type varchar(45)      not null
);