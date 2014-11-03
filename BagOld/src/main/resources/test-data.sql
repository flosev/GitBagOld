--Fashions
INSERT INTO Fashions (id, caption) VALUES (1, 'Сумки');
INSERT INTO Fashions (id, caption) VALUES (2, 'Кейсы');
INSERT INTO Fashions (id, caption) VALUES (3, 'Suitcases');
INSERT INTO Fashions (id, caption) VALUES (4, 'Рюкзаки');


-- Users
INSERT INTO Users (login, password, email) VALUES ('Mike', '123', 'Mike@example.com');
INSERT INTO Users (login, password, email) VALUES ('Sara', '123', 'Sara@example.com');



INSERT INTO Commodities (id, article, length, width, heigth, price, fashion_id) VALUES (1, 'C27', 234,345,546, 34, 1);
INSERT INTO Commodities (id, article, length, width, heigth, price, fashion_id) VALUES (2, 'C47', 246,567,890, 45, 1);
INSERT INTO Commodities (id, article, length, width, heigth, price, fashion_id) VALUES (3, 'C37', 439,670,213, 45, 2);
INSERT INTO Commodities (id, article, length, width, heigth, price, fashion_id) VALUES (4, 'C67', 45,34,78,  67,  3);
INSERT INTO Commodities (id, article, length, width, heigth, price, fashion_id) VALUES (5, 'C57', 421,678,214, 58, 2);
INSERT INTO Commodities (id, article, length, width, heigth, price, fashion_id) VALUES (6, 'C97', 430,620,24, 56, 3);
