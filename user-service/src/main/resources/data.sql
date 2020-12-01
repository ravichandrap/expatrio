insert into role (id, name)
    values ( 1001, 'Admin' );
insert into role (id, name)
    values ( 1002, 'User' );

insert into user (id, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, PHONE_NUMBER)
    values(90001,'REDDY', 'JOHN', 'ravi@gmail.com', 'papapapapap', '2020202020');

insert into user (id, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, PHONE_NUMBER)
    values(90002,'KRISHNA', 'VAMSI', 'rc.reddy@gmail.com', 'papapapapap', '2020202020');

-- insert into USER_ROLE(user_id, role_id)
--     values ( 9001, 1001 );
--
-- insert into USER_ROLE(user_id, role_id)
--     values ( 9002, 1002 );