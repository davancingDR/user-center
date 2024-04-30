-- 新建数据库表
CREATE TABLE USER (
    user_id BIGINT ( 20 ) AUTO_INCREMENT NOT NULL,
    username VARCHAR ( 64 ) NOT NULL COMMENT '用户名/昵称',
    account VARCHAR ( 64 ) NOT NULL COMMENT '账号',
    PASSWORD VARCHAR ( 100 ) NOT NULL COMMENT '密码',
    avatar VARCHAR ( 255 ) DEFAULT '' COMMENT '用户头像',
    gender TINYINT DEFAULT '2' COMMENT '性别 0-女 1-男 2-保密',
    phone VARCHAR ( 11 ) DEFAULT NULL COMMENT '联系方式(手机号)',
    email VARCHAR ( 128 ) DEFAULT NULL COMMENT '邮箱地址',
    user_role TINYINT NOT NULL DEFAULT '1' COMMENT '用户角色 0-管理员 1-普通用户 2-vip',
    STATUS TINYINT NOT NULL DEFAULT '1' COMMENT '账号状态 0-禁用 1-正常',
    deleted TINYINT NOT NULL DEFAULT '0' COMMENT '删除标识 0-未删除 1-已删除',
    create_time datetime DEFAULT NULL COMMENT '创建时间',
    update_time datetime DEFAULT NULL COMMENT '更新时间',
    last_login_time datetime DEFAULT NULL COMMENT '最近登录时间',
    PRIMARY KEY ( user_id ) USING BTREE,
    UNIQUE KEY login_key ( account ) USING BTREE
) ENGINE = INNODB DEFAULT charset = utf8 COMMENT = '用户信息表';

-- 插入测试数据
INSERT INTO USER (username, account, PASSWORD, avatar, gender, phone, email, user_role, STATUS, deleted, create_time, update_time) VALUES
('admin', 'admin', 'admin', '', 1, '12345678901', 'admin@example.com', 0, 1, 0, now(), now()),
('Alice', 'alice123', 'alice123', '', 1, '12345678901', 'alice@example.com', 1, 1, 0, '2024-05-01 10:00:00', '2024-05-01 10:00:00'),
('Bob', 'bob123', 'bob123', '', 0, '98765432109', 'bob@example.com', 1, 1, 0, '2024-06-01 11:00:00', '2024-06-01 11:00:00'),
('Charlie', 'charlie123', 'password', '', 2, NULL, 'charlie@example.com', 1, 1, 0, '2024-05-12 18:00:00', '2024-05-12 18:00:00'),
('Henry', 'henry123', 'password123', '', 0, '98765432107', 'henry@example.com', 1, 1, 0, '2024-07-09 19:54:23', '2024-07-09 19:54:23');
