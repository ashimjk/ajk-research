insert into course(id, course_name, created_date, modified_date)
values(10001,'JPA Hibernate', sysdate(), sysdate());
insert into course(id, course_name, created_date, modified_date)
values(10002,'Spring Boot', sysdate(), sysdate());
insert into course(id, course_name, created_date, modified_date)
values(10003,'Angular', sysdate(), sysdate());

insert into review(id,rating,description,course_id)
values(50001,'FIVE', 'Great Course',10001);
insert into review(id,rating,description,course_id)
values(50002,'FOUR', 'Wonderful Course',10002);
insert into review(id,rating,description,course_id)
values(50003,'FIVE', 'Awesome Course',10003);
