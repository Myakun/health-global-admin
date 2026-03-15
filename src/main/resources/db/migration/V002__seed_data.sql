-- Languages
INSERT INTO languages (id, code, name, position)
VALUES ('aaaaaaaa-0000-0000-0000-000000000001', 'en', 'English', 1),
       ('aaaaaaaa-0000-0000-0000-000000000002', 'de', 'Deutsch', 2),
       ('aaaaaaaa-0000-0000-0000-000000000003', 'ru', 'Русский', 3);

-- Language translations (how each language is named in other languages)
INSERT INTO language_translations (id, source_language_id, translation_language_id, name)
VALUES
    -- English named in EN / DE / RU
    ('bbbbbbbb-0000-0000-0000-000000000001', 'aaaaaaaa-0000-0000-0000-000000000001', 'aaaaaaaa-0000-0000-0000-000000000001', 'English'),
    ('bbbbbbbb-0000-0000-0000-000000000002', 'aaaaaaaa-0000-0000-0000-000000000001', 'aaaaaaaa-0000-0000-0000-000000000002', 'Englisch'),
    ('bbbbbbbb-0000-0000-0000-000000000003', 'aaaaaaaa-0000-0000-0000-000000000001', 'aaaaaaaa-0000-0000-0000-000000000003', 'Английский'),
    -- German named in EN / DE / RU
    ('bbbbbbbb-0000-0000-0000-000000000004', 'aaaaaaaa-0000-0000-0000-000000000002', 'aaaaaaaa-0000-0000-0000-000000000001', 'German'),
    ('bbbbbbbb-0000-0000-0000-000000000005', 'aaaaaaaa-0000-0000-0000-000000000002', 'aaaaaaaa-0000-0000-0000-000000000002', 'Deutsch'),
    ('bbbbbbbb-0000-0000-0000-000000000006', 'aaaaaaaa-0000-0000-0000-000000000002', 'aaaaaaaa-0000-0000-0000-000000000003', 'Немецкий'),
    -- Russian named in EN / DE / RU
    ('bbbbbbbb-0000-0000-0000-000000000007', 'aaaaaaaa-0000-0000-0000-000000000003', 'aaaaaaaa-0000-0000-0000-000000000001', 'Russian'),
    ('bbbbbbbb-0000-0000-0000-000000000008', 'aaaaaaaa-0000-0000-0000-000000000003', 'aaaaaaaa-0000-0000-0000-000000000002', 'Russisch'),
    ('bbbbbbbb-0000-0000-0000-000000000009', 'aaaaaaaa-0000-0000-0000-000000000003', 'aaaaaaaa-0000-0000-0000-000000000003', 'Русский');

-- Questions
INSERT INTO questions (id)
VALUES ('cccccccc-0000-0000-0000-000000000001'),
       ('cccccccc-0000-0000-0000-000000000002'),
       ('cccccccc-0000-0000-0000-000000000003'),
       ('cccccccc-0000-0000-0000-000000000004'),
       ('cccccccc-0000-0000-0000-000000000005'),
       ('cccccccc-0000-0000-0000-000000000006'),
       ('cccccccc-0000-0000-0000-000000000007'),
       ('cccccccc-0000-0000-0000-000000000008'),
       ('cccccccc-0000-0000-0000-000000000009'),
       ('cccccccc-0000-0000-0000-000000000010');