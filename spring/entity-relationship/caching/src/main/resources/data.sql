insert into course(id, course_name, created_date, modified_date, is_deleted)
values(10001,'JPA Hibernate', sysdate(), sysdate(), false);
insert into course(id, course_name, created_date, modified_date, is_deleted)
values(10002,'Spring Boot', sysdate(), sysdate(), false);
insert into course(id, course_name, created_date, modified_date, is_deleted)
values(10003,'Angular', sysdate(), sysdate(), false);

insert into review(id,rating,description,course_id)
values(50001,'FIVE', 'Great Course',10001);
insert into review(id,rating,description,course_id)
values(50002,'FOUR', 'Wonderful Course',10002);
insert into review(id,rating,description,course_id)
values(50003,'FIVE', 'Awesome Course',10003);
