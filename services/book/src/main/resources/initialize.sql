drop table book if exists;

CREATE TABLE book (
  id int(11) NOT NULL AUTO_INCREMENT,
  call_number varchar(15) NOT NULL,
  name varchar(256) NOT NULL,
  author varchar(50) NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO book
VALUES
(1, '658.4012 BET', 'Your next five moves : master the art of business strategy', 'Patrick Bet-David, with Greg Dinkin'),
(2, '658.4 LEN', 'The five dysfunctions of a team : a leadership fable', 'Patrick Lencioni');
