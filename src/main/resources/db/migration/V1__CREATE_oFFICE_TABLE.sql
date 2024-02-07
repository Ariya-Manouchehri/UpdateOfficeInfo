create table office
(
    office_code           varchar(255)                       not null
        primary key,
    manager_mobile_number varchar(255)                       null,
    manager_name          varchar(255)                       null,
    manager_national_id   varchar(255)                       null,
    office_address        varchar(255)                       null,
    office_level          varchar(255)                       null,
    office_level_type     varchar(255)                       null,
    office_mobile_number  varchar(255)                       null,
    office_name           varchar(255)                       null,
    office_postal_code    varchar(255)                       null,
    office_status         varchar(255)                       null,
    office_type           enum ('INDIVIDUAL', 'CORPORATION') null,
    provider              varchar(255)                       null,
    province_id           varchar(255)                       null,
    province_name         varchar(255)                       null,
    region_id             varchar(255)                       null,
    region_name           varchar(255)                       null
);