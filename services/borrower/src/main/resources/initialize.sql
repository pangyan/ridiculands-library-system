drop table book if exists;
drop table borrowing_record if exists;
drop table borrower if exists;

create table borrower (
  id int(11) NOT NULL AUTO_INCREMENT,
  user_name varchar(50) DEFAULT NULL,
  password varchar(50) DEFAULT NULL,
  borrower_full_name varchar(50) DEFAULT NULL,
  borrower_type varchar(50) DEFAULT NULL,
  PRIMARY KEY (id)
);

INSERT INTO borrower
VALUES
(1,'admin','admin','DJ Ridiculands','STAFF'),
(2,'christian','christian','Christian Bale','GENERAL'),
(3,'hugh','hugh','Hugh Jackman','GENERAL'),
(4,'ross','ross','Ross Geller','STUDENT'),
(5,'chandler','chandler','Chandler Bing','STUDENT'),
(6,'monica','monica','Monica Geller','GENERAL'),
(7,'rachel','rachel','Rachel Greene','STUDENT'),
(8,'phoebe','phoebe','Phoebe Buffay','GENERAL');

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
  borrower_id int(11) DEFAULT NULL,
  book_id int(11) DEFAULT NULL,
  due_date date DEFAULT NULL,
  PRIMARY KEY (borrowing_record_id),
  foreign key (borrower_id) references borrower(id),
  foreign key (book_id) references book(id)
);

INSERT INTO borrowing_record VALUES (1,5,1,'2023-01-10'),(2,8,2,'2022-12-13');
