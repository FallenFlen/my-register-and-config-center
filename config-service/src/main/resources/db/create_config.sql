CREATE TABLE config
(
    id                           VARCHAR(32) PRIMARY KEY,
    content                      text NULL COMMENT '配置内容',
    `type`                       VARCHAR(100) NOT NULL COMMENT '配置类型',
    `status`                     VARCHAR(100) NOT NULL COMMENT '状态',
    `belonging_application_name` VARCHAR(200) NOT NULL COMMENT '归属应用名称',
    `file_name` VARCHAR(200) NOT NULL COMMENT '配置文件名称',
    description                  text NULL COMMENT '描述',
    md5                          VARCHAR(32)  NOT NULL COMMENT 'md5',
    create_by                    VARCHAR(100) NULL COMMENT '创建人',
    create_time                  datetime(3) NOT NULL COMMENT '创建时间',
    update_by                    VARCHAR(100) NULL COMMENT '更新人',
    update_time                  datetime(3) NOT NULL COMMENT '更新时间',
    version                      int(11) NOT NULL DEFAULT 0 COMMENT '版本号',
    KEY                          `idx_belonging_application_name`(`belonging_application_name`)
);