INSERT INTO topping_type(topping_type_id, defrosted_img, frozen_img, topping_type_name)
VALUES
    (50, 'defrosted_img', 'frozen_img', 'FRUIT'),
    (51, 'defrosted_img', 'frozen_img', 'NUT'),
    (52, 'defrosted_img', 'frozen_img', 'CEREAL'),
    (53, 'defrosted_img', 'frozen_img', 'JELLY'),
    (54, 'defrosted_img', 'frozen_img', 'COOKIE'),
    (55, 'defrosted_img', 'frozen_img', 'ETC');

INSERT INTO bingsoo(BINGSOO_ID, TASTE) VALUES (50, 'STRAWBERRY');

INSERT INTO topping(chef_name, topping_title, topping_content, is_hidden, bingsoo_id, topping_position, topping_type_id)
VALUES
    ('chef01', 'top01', 'content of topping 01', false, 50, 0, 50),
    ('chef02', 'top02', 'content of topping 02', false, 50, 1, 51),
    ('chef03', 'top03', 'content of topping 03', false, 50, 2, 52),
    ('chef04', 'top04', 'content of topping 04', true, 50, 3, 53),
    ('chef05', 'top05', 'content of topping 05', false, 50, 4, 54),
    ('chef06', 'top06', 'content of topping 06', false, 50, 5, 55),
    ('chef07', 'top07', 'content of topping 07', false, 50, 6, 50),
    ('chef08', 'top08', 'content of topping 08', false, 50, 7, 51),
    ('chef09', 'top09', 'content of topping 09', false, 50, 8, 52),
    ('chef10', 'top10', 'content of topping 10', true, 50, 9, 53),
    ('chef11', 'top11', 'content of topping 11', false, 50, 10, 54),
    ('chef12', 'top12', 'content of topping 12', false, 50, 11, 55),
    ('chef13', 'top13', 'content of topping 13', false, 50, 12, 50),
    ('chef14', 'top14', 'content of topping 14', false, 50, 13, 51),
    ('chef15', 'top15', 'content of topping 15', false, 50, 14, 52),
    ('chef16', 'top16', 'content of topping 16', false, 50, 15, 53),
    ('chef17', 'top17', 'content of topping 17', false, 50, 16, 54),
    ('chef18', 'top18', 'content of topping 18', true, 50, 17, 55),
    ('chef19', 'top19', 'content of topping 19', false, 50, 18, 50),
    ('chef20', 'top20', 'content of topping 20', false, 50, 19, 51);

