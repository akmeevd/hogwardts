SELECT student.id, student.name, student.age
from student
inner join faculty on faculty_id = faculty.id;

select *
from student
right join avatar on student.id = avatar_id;

