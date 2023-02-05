drop table book if exists;

CREATE TABLE book_status (
  id int(11) NOT NULL AUTO_INCREMENT,
  status varchar(20) NOT NULL check (status in ('CHECKED_OUT', 'ON_SHELF', 'IN_TRANSFER', 'ARCHIVED', 'MISSING')),
  PRIMARY KEY (id)
);

INSERT INTO book_status
VALUES
(1, 'CHECKED_OUT'),
(2, 'ON_SHELF'),
(3, 'IN_TRANSFER'),
(4, 'ARCHIVED'),
(5, 'MISSING');

CREATE TABLE book (
  id int(11) NOT NULL AUTO_INCREMENT,
  call_number varchar(15) NOT NULL,
  name varchar(256) NOT NULL,
  author varchar(50) NOT NULL,
  status_id int(11) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (status_id) REFERENCES book_status(id)
);

INSERT INTO book
VALUES
(1, '658.4012 BET', 'Your next five moves : master the art of business strategy', 'Patrick Bet-David, with Greg Dinkin', 1),
(2, '658.4 LEN', 'The five dysfunctions of a team : a leadership fable', 'Patrick Lencioni', 1),
(3, '005.133 IND', 'gRPC : up and running : building cloud native applications with Go and Java for Docker and Kubernetes', 2),
(4, '137.3 CAI', 'Quiet : the power of introverts in a world that can''t stop talking', 2),
(5, '796.334092 MOU', 'Mourinho : further anatomy of a winner', 2);
