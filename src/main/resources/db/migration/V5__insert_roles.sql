INSERT INTO roles
    ( name)
SELECT 1
WHERE
    NOT EXISTS (
        SELECT name FROM roles WHERE name = 1
    );


INSERT INTO roles
    ( name)
SELECT 0
WHERE
    NOT EXISTS (
        SELECT name FROM roles WHERE name = 0
    );