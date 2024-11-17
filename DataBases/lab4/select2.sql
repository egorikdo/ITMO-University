SELECT Н_ЛЮДИ.ОТЧЕСТВО, Н_ВЕДОМОСТИ.ДАТА, Н_СЕССИЯ.ЧЛВК_ИД
FROM Н_ЛЮДИ
LEFT JOIN Н_ВЕДОМОСТИ ON Н_ЛЮДИ.ИД = Н_ВЕДОМОСТИ.ЧЛВК_ИД
LEFT JOIN Н_СЕССИЯ ON Н_ЛЮДИ.ИД = Н_СЕССИЯ.ЧЛВК_ИД
WHERE Н_ЛЮДИ.ФАМИЛИЯ > 'Соколов' AND Н_ВЕДОМОСТИ.ИД = 1250972 AND Н_СЕССИЯ.ДАТА = '2002-01-04';


CREATE INDEX ON Н_ЛЮДИ USING BTREE(ФАМИЛИЯ);
CREATE INDEX ON Н_ВЕДОМОСТИ USING HASH (ИД);
CREATE INDEX ON Н_СЕССИЯ USING HASH (ДАТА);
