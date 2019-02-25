--create table user (id bigint not null auto_increment, age integer, first_name varchar(255), last_name varchar(255), password varchar(255), salary bigint, username varchar(255), primary key (id)) engine=MyISAM;
--INSERT INTO user (age, first_name, last_name,password,salary,username) values (23, 'admin', 'admin','$2a$04$EZzbSqieYfe/nFWfBWt2KeCdyq0UuDEM1ycFF8HzmlVR6sbsOnw7u',12345,'admin');

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `account_non_expired`, `account_non_locked`, `credentials_non_expired`, `enabled`, `password`, `type`, `username`, `deleted`) VALUES
(unhex(replace('d87c8a06-37ec-4d01-b149-8e66f2aa4611','-','')), b'1', b'1', b'1', b'1', '$2a$10$1SPU2i7AcX6m5yyoTTMQwu1hmEdnu0URl//hspiSMhQDYJTQKTpYq', 'OWNER', 'admin@inventoryme.app', false);

-- --------------------------------------------------------


--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `name`, `deleted`) VALUES
(unhex(replace('a21f6182-0d26-11e9-ab14-d663bd873d93','-','')), 'USER', false),
(unhex(replace('a21f642a-0d26-11e9-ab14-d663bd873d93','-','')), 'ADMIN', false),
(unhex(replace('a21f657e-0d26-11e9-ab14-d663bd873d93','-','')), 'MANAGER', false),
(unhex(replace('a21f68da-0d26-11e9-ab14-d663bd873d93','-','')), 'SUPPERVISOR', false),
(unhex(replace('a21f6a7e-0d26-11e9-ab14-d663bd873d93','-','')), 'CLIENT', false),
(unhex(replace('a21f6bb4-0d26-11e9-ab14-d663bd873d93','-','')), 'WORKER', false),
(unhex(replace('a21f6f38-0d26-11e9-ab14-d663bd873d93','-','')), 'ANDROID', false),
(unhex(replace('a21f708c-0d26-11e9-ab14-d663bd873d93','-','')), 'IMAWEB', false),
(unhex(replace('25c1eac8-0d27-11e9-ab14-d663bd873d93','-','')), 'IMADMIN', false),
(unhex(replace('25c1f036-0d27-11e9-ab14-d663bd873d93','-','')), 'IOS', false);



-- --------------------------------------------------------

--
-- Dumping data for table `user_roles`
--

INSERT INTO `user_roles` (`user_id`, `roles_id`) VALUES
(unhex(replace('d87c8a06-37ec-4d01-b149-8e66f2aa4611','-','')), unhex(replace('a21f6182-0d26-11e9-ab14-d663bd873d93','-',''))),
(unhex(replace('d87c8a06-37ec-4d01-b149-8e66f2aa4611','-','')), unhex(replace('a21f642a-0d26-11e9-ab14-d663bd873d93','-',''))),
(unhex(replace('d87c8a06-37ec-4d01-b149-8e66f2aa4611','-','')), unhex(replace('a21f657e-0d26-11e9-ab14-d663bd873d93','-',''))),
(unhex(replace('d87c8a06-37ec-4d01-b149-8e66f2aa4611','-','')), unhex(replace('a21f68da-0d26-11e9-ab14-d663bd873d93','-',''))),
(unhex(replace('d87c8a06-37ec-4d01-b149-8e66f2aa4611','-','')), unhex(replace('a21f6a7e-0d26-11e9-ab14-d663bd873d93','-',''))),
(unhex(replace('d87c8a06-37ec-4d01-b149-8e66f2aa4611','-','')), unhex(replace('a21f6bb4-0d26-11e9-ab14-d663bd873d93','-',''))),
(unhex(replace('d87c8a06-37ec-4d01-b149-8e66f2aa4611','-','')), unhex(replace('a21f6f38-0d26-11e9-ab14-d663bd873d93','-',''))),
(unhex(replace('d87c8a06-37ec-4d01-b149-8e66f2aa4611','-','')), unhex(replace('a21f708c-0d26-11e9-ab14-d663bd873d93','-',''))),
(unhex(replace('d87c8a06-37ec-4d01-b149-8e66f2aa4611','-','')), unhex(replace('25c1eac8-0d27-11e9-ab14-d663bd873d93','-',''))),
(unhex(replace('d87c8a06-37ec-4d01-b149-8e66f2aa4611','-','')), unhex(replace('25c1f036-0d27-11e9-ab14-d663bd873d93','-','')));

