
USE `gradebook` ;

DELETE FROM teacher WHERE teacher_id > 0;
# Set data in the role table
ALTER TABLE teacher AUTO_INCREMENT = 1;
INSERT INTO `teacher` (`first_name`, `last_name`, `email`, `phone_number`) VALUES 
	("first_A", "last_A", "aa@gamil.com", "111-111-1111"),
    ("first_B", "last_B", "bb@gamil.com", "222-222-2222"),
    ("first_C", "last_C", "cc@gamil.com", "333-333-3333");