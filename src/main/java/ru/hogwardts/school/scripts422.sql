CREATE TABLE humans (
    id bigint primary key ,
    name varchar,
    age integer,
    is_driver_license boolean,
    car_id bigint references cars(id)
);

CREATE TABLE cars(
    id bigint primary key ,
    brand varchar,
    model varchar,
    cost integer
);