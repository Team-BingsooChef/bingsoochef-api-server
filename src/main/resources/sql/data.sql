INSERT INTO topping_type(topping_type_id, topping_type_name, frozen_img, defrosted_img)
VALUES
    (1, 'banana', 'temp.jpeg', 'temp.jpeg'),
    (2, 'cherry', 'temp.jpeg', 'temp.jpeg'),
    (3, 'kiwi', 'temp.jpeg', 'temp.jpeg'),
    (4, 'mango', 'temp.jpeg', 'temp.jpeg'),
    (5, 'shine', 'temp.jpeg', 'temp.jpeg'),
    (6, 'strawberry', 'temp.jpeg', 'temp.jpeg'),
    (7, 'chex', 'temp.jpeg', 'temp.jpeg'),
    (8, 'maltesers', 'temp.jpeg', 'temp.jpeg'),
    (9, 'oreo', 'temp.jpeg', 'temp.jpeg'),
    (10, 'stick', 'temp.jpeg', 'temp.jpeg'),
    (11, 'injeolmi', 'temp.jpeg', 'temp.jpeg'),
    (12, 'mochi', 'temp.jpeg', 'temp.jpeg');

INSERT INTO bingsoo(bingsoo_id, taste)
VALUES
    (1, 'STRAWBERRY'),
    (2, 'CHOCO'),
    (3, 'CONDENSED_MILK'),
    (4, 'MATCHA'),
    (5, 'MANGO'),
    (6, 'MINT_CHOCO');

INSERT INTO users(user_id, username, password, bingsoo_id)
VALUES
    (1, 'admin1@domain.com', '$2a$10$MpKs/V4u.RGLbe6bnrDNyewvPcs5fL9pHrtpj55XNq9txhYkgk0c6', 1),
    (2, 'admin2@domain.com', '$2a$10$go4FpdBoRZh5LIIrVNvgB.oMHKrI9hcTo446Abj61IhAJs5bvr9uW', 2),
    (3, 'test3@domain.com', 'test', 3),
    (4, 'test4@domain.com', 'test', 4),
    (5, 'test5@doamin.com', 'test', 5),
    (6, 'test6@domain.com', 'test', 6),
    (7, 'test7@domain.com', 'test', null),
    (8, 'test8@domain.com', 'test', null),
    (9, 'test9@domain.com', 'test', null),
    (10, 'test10@domain.com', 'test', null),
    (11, 'test11@domain.com', 'test', null),
    (12, 'test12@domain.com', 'test', null),
    (13, 'test13@domain.com', 'test', null);


INSERT INTO
    topping(topping_id, bingsoo_id, chef_id, topping_type_id, comment_id, chef_name, topping_title, topping_content, topping_position, topping_created_time, is_hidden)
VALUES 
    (1, 1, 3, 1, null, '익명1', '제목1', '본문1', 1, now(), true),
    (2, 1, 4, 1, null, '익명2', '제목2', '본문2', 2, now(), true),
    (3, 1, 5, 2, null, '익명3', '제목3', '본문3', 3, now(), false),
    (4, 1, 6, 3, null, '익명4', '제목4', '본문4', 4, now(), false),
    (5, 1, 7, 4, null, '익명5', '제목5', '본문5', 5, now(), false),
    (6, 1, 8, 5, null, '익명6', '제목6', '본문6', 6, now(), false),
    (7, 1, 9, 3, null, '익명7', '제목7', '본문7', 7, now(), false),
    (8, 1, 10, 3, null, '익명8', '제목8', '본문8', 8, now(), false),
    (9, 1, 11, 3, null, '익명9', '제목9', '본문9', 9, now(), false),
    (10, 1, 12, 3, null, '익명10', '제목10', '본문10', 10, now(), false),
    (11, 1, 13, 3, null, '익명11', '제목11', '본문11', 11, now(), false),
    (12, 2, 3, 7, null, '셰프1', '제목1', '본문1', 1, now(), false),
    (13, 2, 4, 12, null, '셰프2', '제목2', '본문2', 2, now(), false),
    (14, 2, 5, 10, null, '셰프3', '제목3', '본문3', 3, now(), false);

INSERT INTO
    quiz(quiz_id, quiz_type, quiz_title, topping_id, wrong_count)
VALUES 
    (1, 'OX', '오엑스 퀴즈입니다', 1, 0),
    (2, 'MULTIPLE_CHOICE', '다지선다 퀴즈입니다', 2, 0);

INSERT INTO
    question(question_id, quiz_id, question_content, is_answer)
VALUES
    (1, 1, 'O', true),
    (2, 1, 'X', false),
    (3, 2, '선지 1번', false),
    (4, 2, '선지 2번', false),
    (5, 2, '선지 3번', true),
    (6, 2, '선지 4번', false);

