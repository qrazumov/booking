INSERT INTO public.roles (id, name) VALUES (1, 'ROLE_USER');
INSERT INTO public.roles (id, name) VALUES (2, 'ROLE_MODERATOR');
INSERT INTO public.roles (id, name) VALUES (3, 'ROLE_ADMIN');

INSERT INTO public.users (id, email, password, username) VALUES (1, 'qrazumov@gmail.com', '$2a$10$g9lRa3vJ0OJ24k4/WA7mKe/Z.8dhX7iTDkQrq/mlns2.DH8mFciuu', 'qrazumov');

INSERT INTO public.user_roles (user_id, role_id) VALUES (1, 3);