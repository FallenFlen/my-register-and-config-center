create table `service_info`
(
    id          VARCHAR(32) PRIMARY KEY,
    `name`      varchar(256) not null comment '服务名称',
    `status`    VARCHAR(100) NOT NULL COMMENT '状态',
    create_by   VARCHAR(100) NULL COMMENT '创建人',
    create_time datetime(3) NOT NULL COMMENT '创建时间',
    update_by   VARCHAR(100) NULL COMMENT '更新人',
    update_time datetime(3) NOT NULL COMMENT '更新时间',
    version     int(11) NOT NULL DEFAULT 0 COMMENT '版本号'
);

create table `service_instance`
(
    id           VARCHAR(32) PRIMARY KEY,
    `service_id` varchar(32)  not null comment '关联服务id',
    `status`     VARCHAR(100) NOT NULL COMMENT '状态',
    `host`       text         not null comment '域名',
    `port`       int(11) not null comment '端口',
    create_by    VARCHAR(100) NULL COMMENT '创建人',
    create_time  datetime(3) NOT NULL COMMENT '创建时间',
    update_by    VARCHAR(100) NULL COMMENT '更新人',
    update_time  datetime(3) NOT NULL COMMENT '更新时间',
    version      int(11) NOT NULL DEFAULT 0 COMMENT '版本号'
);