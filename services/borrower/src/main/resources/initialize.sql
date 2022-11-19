drop table book if exists;
drop table borrowing_record if exists;
drop table customer if exists;

create table customer (
  id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(50) DEFAULT NULL,
  password varchar(50) DEFAULT NULL,
  name varchar(50) DEFAULT NULL,
  membertype varchar(50) DEFAULT NULL,
  PRIMARY KEY (id)
);

INSERT INTO customer
VALUES
(1,'admin','admin','DJ Ridiculands','GOLD'),
(2,'christian','christian','Christian Bale','GOLD'),
(3,'hugh','hugh','Hugh Jackman','GOLD'),
(4,'ross','ross','Ross Geller','GOLD'),
(5,'chandler','chandler','Chandler Bing','GOLD'),
(6,'monica','monica','Monica Geller','GOLD'),
(7,'rachel','rachel','Rachel Greene','GOLD'),
(8,'phoebe','phoebe','Phoebe Buffay','GOLD');

CREATE TABLE book (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(50) NOT NULL,
  author varchar(50) NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO book
VALUES
(1, '', ''),
(2, '', '');

CREATE TABLE borrowing_record (
  borrowing_record_id int(11) NOT NULL AUTO_INCREMENT,
  customer_id int(11) DEFAULT NULL,
  book_id int(11) DEFAULT NULL,
  due_date date DEFAULT NULL,
  PRIMARY KEY (borrowing_record_id),
  foreign key (customer_id) references customer(id),
  foreign key (book_id) references book(id)
);

INSERT INTO borrowing_record VALUES (1,5,1,'2023-01-10'),(2,8,2,'2022-12-13');
