SELECT cake.cake_id, cake.cake_name, cake_style.style_name, cake_size.size_name, 
cake_flavor.flavor_name, cake_frosting.frosting_name, cake.cake_type, cake.has_writing, 
cake.custom_text, cake.amount_available
FROM cake
INNER JOIN cake_style ON cake.cake_style = cake_style.cake_style_id
INNER JOIN cake_size ON cake.cake_size = cake_size.cake_size_id
INNER JOIN cake_flavor ON cake.cake_flavor = cake_flavor.cake_flavor_id
INNER JOIN cake_frosting ON cake.cake_frosting = cake_frosting.cake_frosting_id
WHERE cake.cake_type = 'Standard';

_____________________

WITH style AS (
    SELECT cake_style_id FROM cake_style WHERE style_name = 'Sheet'
),
size AS (
    SELECT cake_size_id FROM cake_size WHERE size_name = 'Large'
),
flavor AS (
    SELECT cake_flavor_id FROM cake_flavor WHERE flavor_name = 'Lemon'
),
frosting AS (
    SELECT cake_frosting_id FROM cake_frosting WHERE frosting_name IS NULL
)
UPDATE cake (cake_name, cake_style, cake_size, cake_flavor, cake_filling, 
cake_frosting, cake_type, 
has_writing, custom_text, amount_available, price)
VALUES (
    'Pound Cake', 
    (SELECT cake_style_id FROM style),
    (SELECT cake_size_id FROM size),
    (SELECT cake_flavor_id FROM flavor),
    NULL,
    NULL,
    'Standard',
    false,
    NULL,
    3
);