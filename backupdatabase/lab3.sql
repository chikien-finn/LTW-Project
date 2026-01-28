CREATE database Lab3
GO
use Lab3
GO

Create TABLE Student(
	ID INT PRIMARY KEY,
	name Nvarchar(100),
	age INT,
	email NVARCHAR(100)
);

INSERT INTO Student Values 
(1, N'Võ Chí Kiên', 20, 'kien@gmail.com','Nam'),
(2,N'Trần Nguyễn Thanh Thùy', 20, 'thuy@gmail.com','Nữ'),
(3,N'Trần Duy Huy Hoàng', 20, 'hoang@gmail.com','Nam');

ALTER TABLE Student ADD gender NVARCHAR(10);

UPDATE Student SET gender = N'Nam' WHERE ID = 1;
UPDATE Student SET gender = N'Nữ'  WHERE ID = 2;
UPDATE Student SET gender = N'Nam' WHERE ID = 3;
GO

select * 
from Student
