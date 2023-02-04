drop table borrowing_record if exists;

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
