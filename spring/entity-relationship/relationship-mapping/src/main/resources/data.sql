insert into course(id, course_name, created_date, modified_date)
values(10001,'JPA Hibernate', sysdate(), sysdate());
insert into course(id, course_name, created_date, modified_date)
values(10002,'Spring Boot', sysdate(), sysdate());
insert into course(id, course_name, created_date, modified_date)
values(10003,'Angular', sysdate(), sysdate());

insert into passport(id,passport_number)
values(40001,'E123456');
insert into passport(id,passport_number)
values(40002,'N123457');
insert into passport(id,passport_number)
values(40003,'L123890');

insert into student(id,name,passport_id)
values(20001,'Ashim',40001);
insert into student(id,name,passport_id)
values(20002,'Ashik',40002);
insert into student(id,name,passport_id)
values(20003,'Yaas',40003);

insert into review(id,rating,description,course_id)
values(50001,'FIVE', 'Great Course',10001);
insert into review(id,rating,description,course_id)
values(50002,'FOUR', 'Wonderful Course',10001);
insert into review(id,rating,description,course_id)
values(50003,'FIVE', 'Awesome Course',10003);

insert into student_course(student_id,course_id)
values(20001,10001);
insert into student_course(student_id,course_id)
values(20002,10001);
insert into student_course(student_id,course_id)
values(20003,10001);
insert into student_course(student_id,course_id)
values(20001,10003);