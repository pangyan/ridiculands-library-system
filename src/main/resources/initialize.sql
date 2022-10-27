drop table orders if exists;
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
(1,'admin','admin','John Doe','GOLD'),
(2,'christian','christian','Christian Bale','GOLD'),
(3,'hugh','hugh','Hugh Jackman','GOLD'),
(4,'ross','ross','Ross Geller','GOLD'),
(5,'chandler','chandler','Chandler Bing','GOLD'),
(6,'monica','monica','Monica Geller','GOLD'),
(7,'rachel','rachel','Rachel Greene','GOLD'),
(8,'pheobe','pheobe','Pheobe Buffay','GOLD');


CREATE TABLE orders (
  order_id int(11) NOT NULL AUTO_INCREMENT,
  customer_id int(11) DEFAULT NULL,
  no_of_items int(11) DEFAULT NULL,
  total_amount double DEFAULT NULL,
  order_date date DEFAULT NULL,
  PRIMARY KEY (order_id),
  foreign key (customer_id) references customer(id)
 );


INSERT INTO orders VALUES (1,5,3,635,'2020-01-10'),(2,8,1,1045,'2020-10-13');
