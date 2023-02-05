drop table borrowing_record if exists;

CREATE TABLE borrowing_record_status (
  id int(11) NOT NULL AUTO_INCREMENT,
  status varchar(20) NOT NULL check (status in ('OPEN', 'CLOSED', 'CANCELLED')),
  PRIMARY KEY (id)
);

INSERT INTO borrowing_record_status
VALUES
(1,'OPEN'),
(2,'CLOSED'),
(3,'CANCELLED');

CREATE TABLE borrowing_record (
  id int(11) NOT NULL AUTO_INCREMENT,
  borrower_id int(11) DEFAULT NULL,
  book_id int(11) DEFAULT NULL,
  due_date date DEFAULT NULL,
  borrowing_record_status_id int(11) NOT NULL,
  PRIMARY KEY (id),
  foreign key (borrowing_record_status_id) references borrowing_record_status(id)
);

INSERT INTO borrowing_record
VALUES
(1,5,1,'2023-01-10',1),
(2,8,2,'2022-12-13',1),
(3,5,3,'2023-01-10',1);
