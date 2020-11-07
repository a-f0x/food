INSERT INTO users(login, password)
VALUES ('admin', '$2a$10$rR1XjxsUvfverlEeSb09beAGyRNpHlMKVJtzl6auU3d6EXoiGYVVS');
INSERT INTO users_roles(user_id, role_id)
VALUES ((SELECT id FROM users WHERE login = 'admin'),
        (SELECT id FROM roles WHERE role = 'ADMIN'));