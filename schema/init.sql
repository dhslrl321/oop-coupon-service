create table coupon
(
    id          bigint       not null
        primary key,
    assigned_at datetime(6)  null,
    assignee    bigint       null,
    code        varchar(255) null,
    state       varchar(255) null,
    used_at     datetime(6)  null
)ENGINE=InnoDB default charset=utf8 collate utf8_general_ci;

create table coupon_stock
(
    id             bigint       not null
        primary key,
    amount         bigint       null,
    code           varchar(255) null,
    left_quantity  bigint       null,
    name           varchar(255) null,
    total_quantity bigint       null
)ENGINE=InnoDB default charset=utf8 collate utf8_general_ci;

insert into coupon_stock values (333, 50000, 'C0001', 1000, 'Clothing Discount Coupon 50,000', 1000);
insert into coupon_stock values (777, 100000, 'E0001', 300, 'Electronics Discount Coupon 100,000', 300);
