drop table borrower if exists;

create table borrower_type (
  id int(11) NOT NULL AUTO_INCREMENT,
  borrower_type varchar(50) DEFAULT NULL check (borrower_type in ('STAFF', 'GENERAL', 'STUDENT')),
  PRIMARY KEY (id)
);

INSERT INTO borrower_type
VALUES
(1, 'STAFF'),
(2, 'GENERAL'),
(3, 'STUDENT');

create table borrower (
  id int(11) NOT NULL AUTO_INCREMENT,
  borrower_card_number varchar(20) NOT NULL,
  user_name varchar(50) DEFAULT NULL,
  password varchar(50) DEFAULT NULL,
  borrower_full_name varchar(50) DEFAULT NULL,
  borrower_type_id int(11) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (borrower_type_id) REFERENCES borrower_type(id)
);

INSERT INTO borrower
VALUES
(1,'28888467000000','admin','admin','DJ Ridiculands',1),
(2,'28888467010001', 'christian','christian','Christian Bale',2),
(3,'28888467010002', 'hugh','hugh','Hugh Jackman',2),
(4,'28888467010003', 'ross','ross','Ross Geller',3),
(5,'28888467010004', 'chandler','chandler','Chandler Bing',3),
(6,'28888467010005', 'monica','monica','Monica Geller',2),
(7,'28888467010006', 'rachel','rachel','Rachel Greene',3),
(8,'28888467010007', 'phoebe','phoebe','Phoebe Buffay',2);
