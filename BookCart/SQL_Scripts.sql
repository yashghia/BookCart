drop table if exists user;

CREATE TABLE `user` (
  `EmailId` varchar(45) NOT NULL,
  `FirstName` varchar(45) DEFAULT NULL,
  `MiddleName` varchar(45) DEFAULT NULL,
  `LastName` varchar(45) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `salt` varchar(100) DEFAULT NULL,
  Admin boolean default false,
  PRIMARY KEY (`EmailId`)
);

drop table if exists books;


Create table books(
bookid int not null auto_increment,
bookname varchar(100) not null,
bookauthor varchar(100) not null,
bookgenre varchar(20) not null,
primary key (bookid)
);

Insert into books(bookname,bookauthor,bookgenre)
values('book1','author1','genre1'),
('book2','author2','genre2'),
('book3','author3','genre3');

drop table if exists books_review;
Create table books_review(
bookid int,
bookname varchar(100) not null,
emailId varchar(100) not null,
review varchar(5000) not null,
primary key (bookid,EmailId)
);

select * from books_review 