INSERT INTO app_user (email, password, pseudo, admin) VALUES
        ('a@a', '$2a$10$Wt8piqncC156t0XbZjwjvu59xy1e8kkM05r6UOCAekHcSigJttyWS', 'User A', true),
        ('b@b', '$2a$10$Wt8piqncC156t0XbZjwjvu59xy1e8kkM05r6UOCAekHcSigJttyWS', 'User B', false),
        ('c@c', '$2a$10$Wt8piqncC156t0XbZjwjvu59xy1e8kkM05r6UOCAekHcSigJttyWS', 'User C', false);

INSERT INTO recipe (name, creator_id) VALUES
        ('tarte aux pommes', 1),
        ('paella', 2),
        ('croissant', 1);

INSERT INTO tag (name) VALUES
        ('sud'),
        ('dessert'),
        ('arachide');

INSERT INTO recipe_tags (recipe_id, tags_id) VALUES
        (1, 2),
        (2, 1),
        (3, 2),
        (3, 3);

INSERT INTO question (content, answer, create, user_id) VALUES
                                                            ('Je veux quelque chose de rapide', 'Je vous recommande le Buddha Bowl !', NOW(), 2),
                                                            ('Une recette végétarienne épicée ?', 'Le Curry de lentilles est parfait pour vous.', NOW(), 3);

-- Question <-> includedTags
INSERT INTO question_included_tags (question_id, included_tags_id) VALUES
                                                                       (1, 4),
                                                                       (2, 1), (2, 5);