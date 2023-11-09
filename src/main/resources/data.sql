INSERT INTO product_category (id, name, created_at, updated_at, deleted_at, created_by, updated_by)
VALUES
    (1,'Books', localtimestamp, localtimestamp, null, 1,null ),
    (2,'Music, Movies & Games', localtimestamp, localtimestamp, null, 1, null),
    (3,'Computer & Electronics', localtimestamp, localtimestamp, null, 1, null),
    (4,'Toys, Hobby & Party', localtimestamp, localtimestamp, null, 1, null),
    (5,'Clothing, Shoes and Accessories', localtimestamp, localtimestamp, null, 1, null),
    (6,'Sports, Outdoor and Travel', localtimestamp, localtimestamp, null, 1, null),
    (7,'Beautiful & Healthy', localtimestamp, localtimestamp, null, 1, null),
    (8,'Eat & Drink', localtimestamp, localtimestamp, null, 1, null),
    (9,'Office & School', localtimestamp, localtimestamp, null, 1, null),
    (10,'Car, Motorcycle & Bicycle', localtimestamp, localtimestamp, null, 1, null);


ALTER SEQUENCE category_id_seq RESTART WITH 11;


INSERT INTO product_sub_category(id, name, category_id, created_at, updated_at, deleted_at, created_by, updated_by)
VALUES
    (1, 'Books', 1, localtimestamp, localtimestamp, null, 1,null ),
    (2, 'Ebooks & Audiobooks', 1, localtimestamp, localtimestamp, null, 1,null ),
    (3, 'Magazine', 1, localtimestamp, localtimestamp, null, 1,null ),
    (4, 'Puzzle books', 1, localtimestamp, localtimestamp, null, 1,null ),

    (5, 'Movies & Series', 2, localtimestamp, localtimestamp, null, 1,null ),
    (6, 'Music', 2, localtimestamp, localtimestamp, null, 1,null ),
    (7, 'Videogames', 2, localtimestamp, localtimestamp, null, 1,null ),
    (8, 'Game-consoles & Accessories', 2, localtimestamp, localtimestamp, null, 1,null ),
    (9, 'Digital games & Gaming credit', 2, localtimestamp, localtimestamp, null, 1,null ),
    (10,'PC Gaming', 2, localtimestamp, localtimestamp, null, 1,null ),
    (11,'Merchandise', 2, localtimestamp, localtimestamp, null, 1,null ),

    (12, 'Computer & Accessories', 3, localtimestamp, localtimestamp, null, 1,null ),
    (13, 'Sound and Vision', 3, localtimestamp, localtimestamp, null, 1,null ),
    (14, 'Photo & Videocameras', 3, localtimestamp, localtimestamp, null, 1,null ),
    (15, 'Telephony & Tables', 3, localtimestamp, localtimestamp, null, 1,null ),
    (16, 'Smartwatches & Accessories', 3, localtimestamp, localtimestamp, null, 1,null ),
    (17, 'Appliances', 3, localtimestamp, localtimestamp, null, 1,null ),
    (18, 'Kitchen appliances', 3, localtimestamp, localtimestamp, null, 1,null ),
    (19, 'Personal care devices', 3, localtimestamp, localtimestamp, null, 1,null ),

    (20, 'Toys', 4, localtimestamp, localtimestamp, null, 1,null ),
    (21, 'Indoortoys', 4, localtimestamp, localtimestamp, null, 1,null ),
    (22, 'Hobby & Creatively', 4, localtimestamp, localtimestamp, null, 1,null ),
    (23, 'Party and Dress up', 4, localtimestamp, localtimestamp, null, 1,null ),

    (24, 'Women', 5, '2023-10-25 18:05:30', '2023-10-25 18:05:30', null, 1,null ),
    (25, 'Men', 5, '2023-10-25 18:05:30', '2023-10-25 18:05:30', null, 1,null ),
    (26, 'Girls', 5, '2023-10-25 18:05:30', '2023-10-25 18:05:30', null, 1,null ),
    (27, 'Boys', 5, '2023-10-25 18:05:30', '2023-10-25 18:05:30', null, 1,null ),
    (28, 'Babies', 5, '2023-10-25 18:05:30', '2023-10-25 18:05:30', null, 1,null ),

    (29, 'Sportswear', 6, '2023-10-25 18:05:30', '2023-10-25 18:05:30', null, 1,null ),
    (30, 'Sports', 6, '2023-10-25 18:05:30', '2023-10-25 18:05:30', null, 1,null ),
    (31, 'Camping & Outdoor', 6, '2023-10-25 18:05:30', '2023-10-25 18:05:30', null, 1,null ),
    (32, 'Travel', 6, '2023-10-25 18:05:30', '2023-10-25 18:05:30', null, 1,null ),
    (33, 'Cycling', 6, '2023-10-25 18:05:30', '2023-10-25 18:05:30', null, 1,null ),

    (34, 'Daily Care', 7, '2023-10-25 18:05:30', '2023-10-25 18:05:30', null, 1,null ),
    (35, 'Perfume', 7, '2023-10-25 18:05:30', '2023-10-25 18:05:30', null, 1,null ),
    (36, 'Professional Care', 7, '2023-10-25 18:05:30', '2023-10-25 18:05:30', null, 1,null ),
    (37, 'Health', 7, '2023-10-25 18:05:30', '2023-10-25 18:05:30', null, 1,null ),
    (38, 'Erotic', 7, '2023-10-25 18:05:30', '2023-10-25 18:05:30', null, 1,null ),

    (39, 'Breakfast, Spread & Snack', 8, '2023-10-25 18:05:30', '2023-10-25 18:05:30', null, 1,null ),
    (40, 'Soups, Canned & Sauces', 8, '2023-10-25 18:05:30', '2023-10-25 18:05:30', null, 1,null ),
    (41, 'Pasta, Rice & World Cuisine', 8, '2023-10-25 18:05:30', '2023-10-25 18:05:30', null, 1,null ),
    (42, 'Candy, Cookie, Chocolate and Chips', 8, '2023-10-25 18:05:30', '2023-10-25 18:05:30', null, 1,null ),
    (43, 'Coffee & Tea', 8, '2023-10-25 18:05:30', '2023-10-25 18:05:30', null, 1,null ),
    (44, 'Soft Drinks, Juice and Dairy', 8, '2023-10-25 18:05:30', '2023-10-25 18:05:30', null, 1,null ),

    (45, 'Officesupplies', 9, '2023-10-25 18:05:30', '2023-10-25 18:05:30', null, 1,null ),
    (46, 'Schoolsupplies', 9, '2023-10-25 18:05:30', '2023-10-25 18:05:30', null, 1,null ),
    (47, 'Agendas and Calendars', 9, '2023-10-25 18:05:30', '2023-10-25 18:05:30', null, 1,null ),
    (48, 'Diaries & Friendsbooks', 9, '2023-10-25 18:05:30', '2023-10-25 18:05:30', null, 1,null ),
    (49, 'Greetingcards', 9, '2023-10-25 18:05:30', '2023-10-25 18:05:30', null, 1,null ),
    (50, 'Schoolsupplies', 9, '2023-10-25 18:05:30', '2023-10-25 18:05:30', null, 1,null ),

    (51, 'Car-accessories', 10, '2023-10-25 18:05:30', '2023-10-25 18:05:30', null, 1,null ),
    (52, 'Car-maintenance', 10, '2023-10-25 18:05:30', '2023-10-25 18:05:30', null, 1,null ),
    (53, 'Car-parts', 10, '2023-10-25 18:05:30', '2023-10-25 18:05:30', null, 1,null ),
    (54, 'Motorcycle-accessories', 10, '2023-10-25 18:05:30', '2023-10-25 18:05:30', null, 1,null ),
    (55, 'Scooter', 10, '2023-10-25 18:05:30', '2023-10-25 18:05:30', null, 1,null ),
    (56, 'Bicycles', 10, '2023-10-25 18:05:30', '2023-10-25 18:05:30', null, 1,null );

ALTER SEQUENCE sub_category_id_seq RESTART WITH 57;

INSERT INTO product_inventory(id, quantity, created_at,updated_at, deleted_at, created_by, updated_by)
VALUES
    (1, 501, localtimestamp, localtimestamp, null, 1,null),
    (2, 54, localtimestamp, localtimestamp, null, 1,null),
    (3, 520, localtimestamp, localtimestamp, null, 1,null),
    (4, 58, localtimestamp, localtimestamp, null, 1,null),
    (5, 205, localtimestamp, localtimestamp, null, 1,null),
    (6, 150, localtimestamp, localtimestamp, null, 1,null),
    (7, 25, localtimestamp, localtimestamp, null, 1,null),
    (8, 368, localtimestamp, localtimestamp, null, 1,null),
    (9, 521, localtimestamp, localtimestamp, null, 1,null),
    (10, 214, localtimestamp, localtimestamp, null, 1,null);
--     (11, 253, localtimestamp, localtimestamp, null),
--     (12, 20, localtimestamp, localtimestamp, null),
--     (13, 365, localtimestamp, localtimestamp, null),
--     (14, 78, localtimestamp, localtimestamp, null),
--     (15, 59, localtimestamp, localtimestamp, null),
--     (16, 36, localtimestamp, localtimestamp, null),
--     (17, 25, localtimestamp, localtimestamp, null),
--     (18, 254, localtimestamp, localtimestamp, null),
--     (19, 365, localtimestamp, localtimestamp, null),
--     (20, 258, localtimestamp, localtimestamp, null),
--     (21, 369, localtimestamp, localtimestamp, null),
--     (22, 147, localtimestamp, localtimestamp, null),
--     (23, 254, localtimestamp, localtimestamp, null),
--     (24, 354, localtimestamp, localtimestamp, null),
--     (25, 245, localtimestamp, localtimestamp, null),
--     (26, 256, localtimestamp, localtimestamp, null),
--     (27, 365, localtimestamp, localtimestamp, null),
--     (28, 784, localtimestamp, localtimestamp, null),
--     (29, 147, localtimestamp, localtimestamp, null),
--     (30, 125, localtimestamp, localtimestamp, null),
--     (32, 236, localtimestamp, localtimestamp, null),
--     (33, 244, localtimestamp, localtimestamp, null),
--     (34, 277, localtimestamp, localtimestamp, null),
--     (35, 247, localtimestamp, localtimestamp, null),
--     (36, 258, localtimestamp, localtimestamp, null),
--     (37, 256, localtimestamp, localtimestamp, null),
--     (38, 236, localtimestamp, localtimestamp, null),
--     (39, 256, localtimestamp, localtimestamp, null),
--     (40, 369, localtimestamp, localtimestamp, null),
--     (41, 784, localtimestamp, localtimestamp, null),
--     (42, 245, localtimestamp, localtimestamp, null),
--     (43, 145, localtimestamp, localtimestamp, null),
--     (44, 245, localtimestamp, localtimestamp, null),
--     (45, 256, localtimestamp, localtimestamp, null),
--     (46, 236, localtimestamp, localtimestamp, null),
--     (47, 256, localtimestamp, localtimestamp, null),
--     (48, 254, localtimestamp, localtimestamp, null),
--     (49, 788, localtimestamp, localtimestamp, null),
--     (50, 147, localtimestamp, localtimestamp, null);

ALTER SEQUENCE product_inventory_id_seq RESTART WITH 11;


INSERT INTO discount(ID, DISCOUNT_PERCENT, NAME, DESCR, STATUS, CREATED_AT, UPDATED_AT, DELETED_AT, created_by, updated_by)
VALUES
    (1, 10, 'Back2School', 'Get 10% reduction on school articles', '0',
     localtimestamp, localtimestamp, null, 1,null),
    (2, 5, 'LoverLover', 'Get 5% reduction on perfumes at Valentine day', '0',
     localtimestamp, localtimestamp, null, 1,null),
    (3, 20, 'Halloweenie', 'Get 20% reduction on all party articles 5 days before Halloween', '1',
     localtimestamp, localtimestamp, null, 1,null);

ALTER SEQUENCE discount_id_seq RESTART WITH 4;

INSERT INTO product(id, name, descr, purchase_price, selling_price, vat, category_id, sub_category_id, discount_id, inventory_id,
                    created_at, updated_at, deleted_at, created_by,updated_by)
VALUES
    (1, 'Fairy Tale', E'A new masterpiece from Stephen King.\n\nCharlie Reade looks like an average high school student. ' ||
                      'He is good at baseball and American football, and is an excellent student. ' ||
                      'But he carries a heavy burden. ' ||
                      'His mother died in a car accident when he was ten, ' ||
                      'and the grief caused his father to turn to the bottle. ' ||
                      'Charlie learned at an early age how to take care of himself and his father. ' ||
                      E'\nThen, when Charlie is seventeen, he meets a dog named Radar and his owner, Howard Bowditch, ' ||
                      'a hermit in a large house at the top of a high hill, with a locked shed in the backyard. ' ||
                      'Sometimes strange noises come from that shed. ' ||
                      E'\nCharlie starts doing odd jobs for Mr. Bowditch and falls in love with Radar. ' ||
                      'When Bowditch dies, ' ||
                      'he leaves Charlie a cassette tape with a story so absurd that no one would ever believe it. ' ||
                      'What he has kept secret all his life is that a gate to another world is hidden in the shed.',
     15.55, 24.99, 3, 1, 1, null, 1,localtimestamp, localtimestamp, null, 1,null),
    (2,'Elon Musk', E'ELON MUSK BIOGRAPHY OF BESTSELLER AUTHOR WALTER ISAACSON\n\nIn this biography, bestselling ' ||
                    E'author Walter Isaacson tells the surprisingly intimate story ' ||
                    E'of one of the ' ||
                    E'most fascinating and controversial entrepreneurs of our time: Elon Musk. A groundbreaking ' ||
                    E'pioneer who led ' ||
                    E'the world into the era of electric vehicles, private space travel and artificial intelligence. ' ||
                    E'Oh, and he took over Twitter. ' ||
                    E'When Elon Musk lived in South Africa as a child, he was regularly beaten up by bullies. ' ||
                    E'One day he was pushed down a concrete staircase by a group and then kicked until his face was ' ||
                    E'bulging and swollen. He was in the hospital for a week. ' ||
                    E'But the physical scars were nothing compared to the emotional damage inflicted by his father, ' ||
                    E'an engineer, scumbag and charismatic fantasist.\n\nHis father''s influence on his mental state ' ||
                    E'continued. He turned into a tough but vulnerable, ' ||
                    E'sometimes even childish adult, prone to Jekyll-and-Hyde-like mood swings and with an extremely ' ||
                    E'high tolerance for risk, a craving for drama, disproportionate goals and a manic intensity ' ||
                    E'that is sometimes harsh and destructive.\n\nIn early 2022 — a year after his company ' ||
                    E'SpaceX completed 31 successful rocket launches, ' ||
                    E'Tesla sold nearly a million cars and became the richest man on Earth — Musk spoke ruefully ' ||
                    E'about his need to create drama. “I have to get my mindset out of the crisis mode that it ' ||
                    E'has been in for the last 14 years, if not my entire life,” he said.\n\nIt was a melancholy ' ||
                    E'statement, not a good intention. And in the meantime, he secretly bought ' ||
                    E'shares of Twitter, the world''s largest playground. When he was in deep trouble, it always ' ||
                    E'brought him back to the bullying in the playground. Now he had the chance to be the boss ' ||
                    E'of the playground himself.\n\nIsaacson followed Musk for two years. He attended his ' ||
                    E'meetings, walked with him through his ' ||
                    E'factories and spent hours interviewing him, his family, friends, colleagues and enemies. ' ||
                    E'The result is a revealing inside story, full of fantastic anecdotes of triumph and turmoil, ' ||
                    E'that raises just one question: are the demons that drive Musk also what is needed for ' ||
                    E'innovation and progress?',
     17.85, 29.99, 3, 1, 1, null, 2, localtimestamp, localtimestamp, null, 1,null),
    (3,'The Women in Me', E'Together with producer Max Martin, Britney Spears was responsible for the return of ' ||
                          E'teen pop in the second half of the 1990s.\n\nSpears started her career at the age of ' ||
                          E'eleven with the New Mickey Mouse Club (which also included Justin Timberlake and ' ||
                          E'Christina Aguilera). She had already auditioned for the program when she was eight, ' ||
                          E'but the producers thought she was still too young. Instead, she attended Professional ' ||
                          E'Performing Arts School and appeared in a number of Broadway shows and commercials.\n\nSpears ' ||
                          E'returned to New York at the age of fifteen after the New Mickey Mouse Club was canceled. ' ||
                          E'There she auditioned for pop bands and made a number of demos. ' ||
                          E'Her big breakthrough came in 1998 with the song ''...Baby One More Time''.\n\nSpears image ' ||
                          E'was innocent and sexy at the same time. This sparked debate about the impact this had on ' ||
                          E'young girls.\n\nSpears also acted in the film ''Crossroads'' and had a reality soap, ' ||
                          E'''Chaotic'' (2005), with her then husband Kevin Federline. \n\nShe divorced Federline ' ||
                          E'in 2006, shortly after the birth of her second child. ' ||
                          E'The divorce and custody case were widely reported in the tabloids, ' ||
                          E'as was Spears'' party behavior after her divorce.',
     5.85, 9.99, 3, 1, 2, null, 3, localtimestamp, localtimestamp, null, 1,null),
    (4,'Elon Musk', E'BIOGRAPHY ELON MUSK BY BESTSELLER AUTHOR WALTER ISAACSON In this biography, ' ||
                    E'bestselling author Walter Isaacson tells the surprisingly intimate story of ' ||
                    E'one of the most fascinating and controversial entrepreneurs of our time: Elon Musk. ' ||
                    E'A groundbreaking pioneer who led the world into the era of electric vehicles, ' ||
                    E'private space travel and artificial intelligence. Oh, and he took over Twitter. ' ||
                    E'When Elon Musk lived in South Africa as a child, he was regularly beaten up by bullies. ' ||
                    E'One day he was pushed down a concrete staircase by a group and then kicked until his ' ||
                    E'face was bulging and swollen. He was in the hospital for a week. But the physical scars ' ||
                    E'were nothing compared to the emotional damage inflicted by his father, an engineer, ' ||
                    E'scumbag and charismatic fantasist. His fathers influence on his mental state continued. ' ||
                    E'He turned into a tough but vulnerable, sometimes even childish adult, prone to ' ||
                    E'Jekyll-and-Hyde-like mood swings and with an extremely high tolerance for risk, a ' ||
                    E'craving for drama, disproportionate goals and a manic intensity that is sometimes ' ||
                    E'harsh and destructive. In early 2022 — a year after his company SpaceX completed 31 ' ||
                    E'successful rocket launches, Tesla sold nearly a million cars and became the richest ' ||
                    E'man on Earth — Musk spoke ruefully about his need to create drama. “I have to get my ' ||
                    E'mindset out of the crisis mode it''s been in for the last 14 years, if not my entire ' ||
                    E'life,” he said. It was a melancholy statement, not a good intention. ' ||
                    E'And in the meantime, he secretly bought shares of Twitter, the world''s largest ' ||
                    E'playground. When he was in deep trouble, it always brought him back to the bullying in ' ||
                    E'the playground. Now he had the chance to be the boss of the playground himself. ' ||
                    E'Isaacson followed Musk for two years. He attended his meetings, walked with him ' ||
                    E'through his factories and spent hours interviewing him, his family, friends, ' ||
                    E'colleagues and enemies. The result is a revealing inside story, full of fantastic ' ||
                    E'anecdotes of triumph and turmoil, that raises just one question: are the demons that ' ||
                    E'drive Musk also what is needed for innovation and progress? Walter Isaacson is a professor ' ||
                    E'of history at Tulane University and known as the author of several critically acclaimed ' ||
                    E'biographies. He wrote biographies about Steve Jobs, Albert Einstein and Leonardo da Vinci, ' ||
                    E'among others. He also served as CEO of the Aspen Institute, ' ||
                    E'chairman of CNN and editor-in-chief of Time.',
     12.85, 17.99, 3, 1, 2, null, 4, localtimestamp, localtimestamp, null, 1,null),
    (5, 'ChatGPT 02 2023', E'About this magazine:\nGet started with ChatGPT yourself. Learn how to ' ||
                           E'write texts on any subject ' ||
                           E'in no time and how to make your own ' ||
                           'texts better and more beautiful. In 132 pages we give you explanations, ' ||
                           'tips and workshops about ChatGPT and you discover other possibilities of AI.',
    11.95, 16.99, 3, 1, 3, null, 5, localtimestamp, localtimestamp, null, 1,null),
    (6, 'Puzzlesport - Puzzle book', E'Popular sudokus at an intermediate level\n\nThe sudoku 2-4* champion is ' ||
                                     E'full of intermediate level puzzles. This popular puzzle with 9 blocks of ' ||
                                     E'3x3 compartments is a real brainteaser. The numbers 1 to 9 appear only once ' ||
                                     E'in each row, column and block!',
     4.95, 8.99, 3, 1, 4, null, 6, localtimestamp, localtimestamp, null, 1,null),
    (7, 'Transformers - Rise Of The Beasts(Blu-ray)', 'Transformers: Rise of the Beasts returns to the action ' ||
                                                      'and spectacle that has impressed moviegoers worldwide. ' ||
                                                      'Set in the 1990s, the film introduces a whole new breed of ' ||
                                                      'Transformers - the Maximals - to the battle raging on Earth ' ||
                                                      'between the Autobots and the Decepticons. The film is directed ' ||
                                                      'by Steven Caple Jr. and has leading roles for Anthony Ramos ' ||
                                                      'and Dominique Fishback, as well as voice roles for ' ||
                                                      'Pete Davidson and Michelle Yeoh, among others.',
     14.95, 19.95, 3, 2, 5, null, 7, localtimestamp, localtimestamp, null, 1,null),
    (8, 'TMission: Impossible - Dead Reckoning Part One (Blu-ray)', 'In Mission: Impossible - ' ||
                                                                    'Dead Reckoning Part One, Ethan Hunt ' ||
                                                      '(Tom Cruise) and his IMF team embark on their most dangerous ' ||
                                                      'mission yet: track down a terrifying new weapon that threatens ' ||
                                                      'all of humanity before it falls into the wrong hands. ' ||
                                                      'A deadly race around the world ensues where control over the ' ||
                                                      'future and the fate of the world are at stake and Ethan is ' ||
                                                      'confronted with dark forces from his past. In his showdown ' ||
                                                      'against a mysterious and powerful enemy, Ethan is forced to ' ||
                                                      'consider the thought that nothing could be more important ' ||
                                                      'than his mission, not even the lives of those closest to him. ' ||
                                                      'Temporary synopsis',
     14.95, 19.95, 3, 2, 5, null, 8, localtimestamp, localtimestamp, null, 1,null),
    (9, 'The Rolling Stones - Hackney Diamonds(CD)', 'Hackney Diamonds is the 24th studio album by The Rolling Stones ' ||
                                                     'and includes the single Angry. ' ||
                                                     'This is the CD jewel case version of the album.',
     14.95, 21.95, 3, 2, 6, null, 9, localtimestamp, localtimestamp, null, 1,null),
    (10, 'Taylor Swift - 1989 (Taylor''s Version) (CD)', 'After Fearless, Red, and Speak Now, 1989 (Taylor''s Version) ' ||
                                                         'is the fourth album that Taylor Swift re-records, ' ||
                                                         'and the successor to the previously released Speak Now ' ||
                                                         '(Taylor''s Verion) in 2023, which entered the ' ||
                                                         'album charts at #1. Taylor-mania reached a peak in the ' ||
                                                         'summer of 2023: she was the first artist ever with no ' ||
                                                         'fewer than 5 albums in the Top 10 of the official album ' ||
                                                         'Top 100, and the first female artist with no fewer than ' ||
                                                         'nine albums in total in the Top 100. In addition, ' ||
                                                         'she immediately sold out no fewer than three shows ' ||
                                                         'in the Amsterdam Johan Cruijff Arena, with enough ' ||
                                                         'demand for tickets for many more shows. The original ' ||
                                                         'album ''1989'' from 2014 containing the hits ''Blank Space'', ' ||
                                                         '''Shake It Off'', and ''Bad Blood'' meant the definitive ' ||
                                                         'breakthrough for Taylor in the Netherlands and the album ' ||
                                                         '''Midnights'' released in 2022 is with hits '' Anti-Hero'' ' ||
                                                         'and ''Karma'' are still one of ' ||
                                                         'the biggest releases this year.',
     16.95, 23.95, 3, 2, 6, null, 10, localtimestamp, localtimestamp, null, 1,null);

ALTER SEQUENCE product_id_seq RESTART WITH 11;











