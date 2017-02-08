INSERT INTO ADMIN VALUES ('admin', 'admin');
INSERT INTO USERS VALUES
('user1', '1234', 'minjee9108@hotmail.com', 'jo', 'joseph', 
'smith', '1990-11-8', '24', 'kent st', 'sydney', 2000, 'NSW',
123456789123, 'FALSE', 'CUSTOMER', 'TRUE');
INSERT INTO USERS VALUES
('seller1', 'seller1', 'minjee9108@hotmail.com', 'jo', 'joseph', 
'smith', '1990-11-8', '24', 'kent st', 'sydney', 2000, 'NSW',
123456789123, 'FALSE', 'SELLER', 'TRUE');

INSERT INTO USERS VALUES
('seller2', 'seller2', 'minjee9108@hotmail.com', 'jo', 'joseph', 
'smith', '1990-11-8', '24', 'kent st', 'sydney', 2000, 'NSW',
123456789123, 'TRUE', 'SELLER', 'TRUE');

INSERT INTO USERS VALUES
('seller3', 'seller3', 'minjee9108@hotmail.com', 'jo', 'joseph', 
'smith', '1990-11-8', '24', 'kent st', 'sydney', 2000, 'NSW',
123456789123, 'FALSE', 'SELLER', 'TRUE');

INSERT INTO USERS VALUES
('seller4', 'seller4', 'minjee9108@hotmail.com', 'jo', 'joseph', 
'smith', '1990-11-8', '24', 'kent st', 'sydney', 2000, 'NSW',
123456789123, 'TRUE', 'SELLER', 'TRUE');

INSERT INTO USERS VALUES
('seller5', 'seller5', 'minjee9108@hotmail.com', 'jo', 'joseph', 
'smith', '1990-11-8', '24', 'kent st', 'sydney', 2000, 'NSW',
123456789123, 'FALSE', 'SELLER', 'TRUE');

INSERT INTO USERS VALUES
('lee', 'lee', 'lirenxn@gmail.com', 'lee', 'liren', 
'wang', '1992-1-8', '24', 'anzac st', 'sydney', 2035, 'NSW',
123456789123, 'FALSE', 'CUSTOMER', 'TRUE');

INSERT INTO USERS VALUES
('lee001', 'lee001', 'lirenxn@gmail.com', 'lee', 'liren', 
'wang', '1992-1-8', '24', 'anzac st', 'sydney', 2035, 'NSW',
123456789123, 'FALSE', 'CUSTOMER', 'TRUE');

INSERT INTO USERS VALUES
('lee002', 'lee002', 'lirenxn@gmail.com', 'lee', 'liren', 
'wang', '1992-1-8', '24', 'anzac st', 'sydney', 2035, 'NSW',
123456789123, 'TRUE', 'CUSTOMER', 'TRUE');

INSERT INTO USERS VALUES
('lee003', 'lee003', 'lirenxn@gmail.com', 'lee', 'liren', 
'wang', '1992-1-8', '24', 'anzac st', 'sydney', 2035, 'NSW',
123456789123, 'FALSE', 'CUSTOMER', 'TRUE');

INSERT INTO USERS VALUES
('lee004', 'lee004', 'lirenxn@gmail.com', 'lee', 'liren', 
'wang', '1992-1-8', '24', 'anzac st', 'sydney', 2035, 'NSW',
123456789123, 'TRUE', 'CUSTOMER', 'TRUE');

INSERT INTO USERS VALUES
('lee005', 'lee005', 'lirenxn@gmail.com', 'lee', 'liren', 
'wang', '1992-1-8', '24', 'anzac st', 'sydney', 2035, 'NSW',
123456789123, 'FALSE', 'CUSTOMER', 'TRUE');

INSERT INTO USERS VALUES
('lee006', 'lee006', 'lirenxn@gmail.com', 'lee', 'liren', 
'wang', '1992-1-8', '24', 'anzac st', 'sydney', 2035, 'NSW',
123456789123, 'TRUE', 'CUSTOMER', 'TRUE');

INSERT INTO USERS VALUES
('lee007', 'lee007', 'lirenxn@gmail.com', 'lee', 'liren', 
'wang', '1992-1-8', '24', 'anzac st', 'sydney', 2035, 'NSW',
123456789123, 'FALSE', 'CUSTOMER', 'TRUE');

INSERT INTO USERS VALUES
('lee008', 'lee008', 'lirenxn@gmail.com', 'lee', 'liren', 
'wang', '1992-1-8', '24', 'anzac st', 'sydney', 2035, 'NSW',
123456789123, 'TRUE', 'CUSTOMER', 'TRUE');

INSERT INTO USERS VALUES
('lee009', 'lee009', 'lirenxn@gmail.com', 'lee', 'liren', 
'wang', '1992-1-8', '24', 'anzac st', 'sydney', 2035, 'NSW',
123456789123, 'FALSE', 'CUSTOMER', 'TRUE');

INSERT INTO USERS VALUES
('lee010', 'lee010', 'lirenxn@gmail.com', 'lee', 'liren', 
'wang', '1992-1-8', '24', 'anzac st', 'sydney', 2035, 'NSW',
123456789123, 'TRUE', 'CUSTOMER', 'TRUE');

INSERT INTO ENTRIES (ID, SELLER, TITLE)
VALUES (0, 'seller1', 'Sample');
INSERT INTO BOOKS VALUES (0);

INSERT INTO ENTRIES (SELLER, TITLE, AUTHORS)
VALUES ('seller1', 'wind up bird chronicle', 'haruki murakami');

INSERT INTO ENTRIES (SELLER, TITLE, AUTHORS)
VALUES ('seller1', 'gone with wind', 'some lady');

INSERT INTO ENTRIES (SELLER, TITLE, AUTHORS)
VALUES ('seller1', 'harry potter', 'some lady');

INSERT INTO ENTRIES (SELLER, TITLE, AUTHORS)
VALUES ('seller1', 'Math', 'some smart guy');

INSERT INTO ENTRIES (SELLER, TITLE, AUTHORS)
VALUES ('seller2', 'Physics', 'physician1');

INSERT INTO ENTRIES (SELLER, TITLE, AUTHORS)
VALUES ('seller2', 'Cosmos Physics', 'physician1 ; physician2');

INSERT INTO ENTRIES (SELLER, TITLE, AUTHORS)
VALUES ('seller3', 'Quatum Physics', 'physician3');

INSERT INTO ENTRIES (SELLER, TITLE, AUTHORS)
VALUES ('seller3', 'C++', 'coder1');

INSERT INTO ENTRIES (SELLER, TITLE, AUTHORS)
VALUES ('seller3', 'Java', 'coder2');

INSERT INTO ENTRIES (SELLER, TITLE, AUTHORS)
VALUES ('seller3', 'Html', 'coder3');

INSERT INTO ENTRIES (SELLER, TITLE, AUTHORS)
VALUES ('seller5', 'Water', 'author1');

INSERT INTO ENTRIES (SELLER, TITLE, AUTHORS)
VALUES ('seller5', 'Oil', 'author2');

INSERT INTO ENTRIES (SELLER, TITLE, AUTHORS)
VALUES ('seller5', 'Mass', 'author3');

INSERT INTO ENTRIES (SELLER, TITLE, AUTHORS)
VALUES ('seller5', 'English', 'a native speaker');

INSERT INTO ENTRIES (SELLER, TITLE, AUTHORS)
VALUES ('seller5', 'Chinese', 'a Chinese');

insert into books 
select id from entries 
where types = 'BOOK' or types = 'INCOLLECTION';

insert into journals
select id from entries
where types = 'article';

insert into conferences
select id from entries
where types = 'inproceedings' or types = 'proceedings';

select count(*) from entries;
select count(*) from books;
select count(*) from journals;

truncate table entries;
truncate table books;
truncate table journals;

select * from cart_history;