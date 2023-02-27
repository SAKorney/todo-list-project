create table if not exists task (
    task_id serial primary key,
    task_description varchar(100) not null,
    task_completed boolean not null
);

DO $$
BEGIN
    IF (SELECT COUNT(*) FROM task) = 0
    THEN
        INSERT INTO task (task_description, task_completed) VALUES
            ('Test task', TRUE),
            ('First task', TRUE),
            ('Second task', FALSE),
            ('Third task', FALSE),
            ('Fourth task', FALSE);
    END IF;
END$$;
