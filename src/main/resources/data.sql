INSERT INTO roles (role)
values ('ADMIN'),
       ('USER');

INSERT INTO towns (town_name)
VALUES ('Sofia'),
       ('Nesebar'),
       ('Varna');

INSERT INTO rooms (room_type, price)
VALUES ('STUDIO', 99.00),
       ('DOUBLE_ROOM', 129.00),
       ('APARTMENT', 199.00);

INSERT INTO hotels (hotel_name, description, total_rooms, hotel_pictures_id, town_id)
VALUES ('Nesebar Sunny Resort',
        'Rooms at Nesebar Sunny Resort are bright and spacious and they are equipped with cable TV.
On the rooftop, guests can benefit from an outdoor swimming pool and a terrace bar. There is also a restaurant where breakfast, lunch and dinner are available.
Nesebar Sunny Resort also features a massage center where guests can choose from several therapies and facilities such as sauna and steam room.',
           50, null, 2);
INSERT INTO hotels (hotel_name, description, total_rooms, hotel_pictures_id, town_id)
VALUES ('GrandHotel Sofia', 'Set a 5-minute walk from the Serdika underground railway station, the 5-star Grand Hotel Sofia is located right in the heart of Sofia, overlooking the City Garden. It offers large rooms, free Wi-Fi and free indoor parking.

The National Theater, the Bulgarian National bank and all government institutions are just a few steps away from the Grand Hotel Sofia.
                            The closest metro station is a 5-minute walk away.', 50, null, 1);
INSERT INTO hotels (hotel_name, description, total_rooms, hotel_pictures_id, town_id)
VALUES ('Хотел Чайка',
        'Located in Varna City, 2.1 km from Asparuhovo Beach, Hotel Vanilla, Varna - Free parking has accommodations with a restaurant, free private parking, a seasonal outdoor swimming pool and a bar. Offering a garden, the property is located within 5.3 km of Varna Opera House. The property provides a playground, a 24-hour front desk, and free WiFi is available throughout the property.',
        50, null, 3);

INSERT INTO hotels_rooms(hotel_entity_id, rooms_id)
VALUES (1, 2),
       (1, 2),
       (1, 2),
       (1, 2),
       (1, 1),
       (1, 1),
       (1, 1),
       (1, 1),
       (1, 1),
       (1, 3),
       (1, 3),
       (1, 3),
       (1, 3),
       (1, 3);

INSERT INTO hotels_rooms(hotel_entity_id, rooms_id)
VALUES (2, 2),
       (2, 2),
       (2, 2),
       (2, 2),
       (2, 1),
       (2, 1),
       (2, 1),
       (2, 1),
       (2, 1),
       (2, 3),
       (2, 3),
       (2, 3),
       (2, 3),
       (2, 3);

INSERT INTO hotels_rooms(hotel_entity_id, rooms_id)
VALUES (3, 2),
       (3, 2),
       (3, 2),
       (3, 2),
       (3, 1),
       (3, 1),
       (3, 1),
       (3, 1),
       (3, 1),
       (3, 3),
       (3, 3),
       (3, 3),
       (3, 3),
       (3, 3);