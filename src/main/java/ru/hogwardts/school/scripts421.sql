ALTER TABLE student add constraint age_constraint check (age >=16);

ALTER TABLE student alter column name set not null, add constraint name_constraint unique (name);

ALTER TABLE faculty
    add constraint name_color_constraint unique (name, color);

ALTER TABLE student
    alter column age set default age = 20;


