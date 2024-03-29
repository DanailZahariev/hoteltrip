insert into towns(town_name)
values ('Sofia');

insert into hotels(description, hotel_name, town_id)
values ('Set a 5-minute walk from the Serdika underground railway station, the 5-star Grand Hotel Sofia is located right in the heart of Sofia, overlooking the City Garden. It offers large rooms, free Wi-Fi and free indoor parking.  The National Theater, the Bulgarian National bank and all government institutions are just a few steps away from the Grand Hotel Sofia. The closest metro station is a 5-minute walk away.',
        'Hotel Sofia', 1);

insert into pictures(public_id, url)
values ('crydsjwirlkodu2krcmk',
        'https://res.cloudinary.com/dio3s4oo1/image/upload/v1659789639/crydsjwirlkodu2krcmk.jpg'),
       ('yvz4tvlnwsb0ogujmrrl',
        'https://res.cloudinary.com/dio3s4oo1/image/upload/v1659789641/yvz4tvlnwsb0ogujmrrl.jpg'),
       ('f9heswg6xou9kasrzxju',
        'https://res.cloudinary.com/dio3s4oo1/image/upload/v1659789642/f9heswg6xou9kasrzxju.jpg'),
       ('h6ywyz137iew9c8s53vg',
        'https://res.cloudinary.com/dio3s4oo1/image/upload/v1659789643/h6ywyz137iew9c8s53vg.jpg'),
       ('h3fnjq9kq8fnlhjofo1d',
        'https://res.cloudinary.com/dio3s4oo1/image/upload/v1659789644/h3fnjq9kq8fnlhjofo1d.jpg');

insert into hotels_pictures(hotel_id, picture_id)
values (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (1, 5);


insert into rooms(price, room_type, hotel_id)
values (99.00, 'STUDIO', 1),
       (99.00, 'STUDIO', 1),
       (129.00, 'DOUBLE_ROOM', 1),
       (129.00, 'DOUBLE_ROOM', 1),
       (199.00, 'APARTMENT', 1),
       (199.00, 'APARTMENT', 1);


insert into hotels_rooms(hotel_entity_id, rooms_id)
values (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (1, 5),
       (1, 6);


insert into reservation(start_date, end_date, is_active, user_id, hotel_id, rooms_id)
values ('2022-08-06', '2022-08-10', 1, 1, 1, 1),
       ('2022-08-06', '2022-08-10', 1, 1, 1, 3);


insert into hotels_reservation(hotel_entity_id, reservation_id)
values (1, 1),
       (1, 2);