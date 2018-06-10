# WAAMidtermProject
The goal of this project is to give hands on practical experience with building a Spring Boot Web Application and Web Services using the technologies we have been discussing during in class.

#IMPORTANT

After you use the application and before you use it
insert these line SQL in the data base

# To create the role
INSERT INTO `role` (`role_id`, `role`) VALUES(1, 'ADMIN'),(2, 'USER'); 

# personal info about admin
INSERT INTO `person` (`id`, `email`, `first_name`, `last_name`, `phone`, `account_user_id`, `address_id`) VALUES
(1, 'noah@test.ed', 'Jean Joseph', 'Toussaint', '3126623805', 29, 30);

#user
INSERT INTO `user` (`user_id`, `email`, `active`, `password`) VALUES
(1, 'noah@test.ed', b'1', '$2a$10$mpuz056IgdMBnUb80bkCBeKmJiJUlRJ2CeneOsWIM9UxkZ693BIti');

#user role
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES(1, 1);

The ADMIN ACCOUNT WILL BE

User : noah@test.ed
password : 12345
Role : ADMIN