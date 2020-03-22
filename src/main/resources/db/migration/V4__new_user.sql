create table user
(
    uid int auto_increment,
    username varchar(20) not null,
    password varchar(20) not null,
    constraint user_pk
        primary key (uid)
);

create unique index user_password_uindex
    on user (username);
