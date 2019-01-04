DROP TABLE IF EXISTS `teacher` ;

CREATE TABLE IF NOT EXISTS `teacher` (
  `teacher_id` INT NOT NULL,
  `first_name` VARCHAR(50) NOT NULL,
  `last_name` VARCHAR(50) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `phone_number` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`teacher_id`),
  UNIQUE INDEX (`email`) 
  );
