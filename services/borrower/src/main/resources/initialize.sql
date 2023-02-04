drop table borrower if exists;

create table borrower (
  id int(11) NOT NULL AUTO_INCREMENT,
  borrower_card_number varchar(20) NOT NULL,
  user_name varchar(50) DEFAULT NULL,
  password varchar(50) DEFAULT NULL,
  borrower_full_name varchar(50) DEFAULT NULL,
  borrower_type varchar(50) DEFAULT NULL,
  PRIMARY KEY (id)
);

INSERT INTO borrower
VALUES
(1,'28888467000000','admin','admin','DJ Ridiculands','STAFF'),
(2,'28888467010001', 'christian','christian','Christian Bale','GENERAL'),
(3,'28888467010002', 'hugh','hugh','Hugh Jackman','GENERAL'),
(4,'28888467010003', 'ross','ross','Ross Geller','STUDENT'),
(5,'28888467010004', 'chandler','chandler','Chandler Bing','STUDENT'),
(6,'28888467010005', 'monica','monica','Monica Geller','GENERAL'),
(7,'28888467010006', 'rachel','rachel','Rachel Greene','STUDENT'),
(8,'28888467010007', 'phoebe','phoebe','Phoebe Buffay','GENERAL');
