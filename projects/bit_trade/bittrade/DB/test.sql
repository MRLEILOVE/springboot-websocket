/*
TRUNCATE TABLE t_entrust;TRUNCATE TABLE t_entrust_record;TRUNCATE TABLE t_kline;
SELECT * FROM t_entrust;SELECT * FROM t_entrust_record;SELECT * FROM t_kline;
*/
SELECT * FROM t_entrust WHERE status IN (1, 2) ORDER BY entrust_direction ASC, price ASC;

SELECT '0, (1, 2)' AS 'entrust_direction', COUNT(1), SUM(count), SUM(amount) FROM t_entrust WHERE currency_trade_ID = 1 AND entrust_direction = 0 AND status IN (1, 2) UNION ALL
SELECT '1, (1, 2)', COUNT(1), SUM(count), SUM(amount) FROM t_entrust WHERE currency_trade_ID = 1 AND entrust_direction = 1 AND status IN (1, 2) UNION ALL
SELECT '0, (3)', COUNT(1), SUM(count), SUM(amount) FROM t_entrust WHERE currency_trade_ID = 1 AND entrust_direction = 0 AND status = 3 UNION ALL
SELECT '1, (3)', COUNT(1), SUM(count), SUM(amount) FROM t_entrust WHERE currency_trade_ID = 1 AND entrust_direction = 1 AND status = 3 UNION ALL
SELECT 0, COUNT(1), SUM(count), SUM(amount) FROM t_entrust_record WHERE entrust_direction = 0 UNION ALL
SELECT 1, COUNT(1), SUM(count), SUM(amount) FROM t_entrust_record WHERE entrust_direction = 1;

SELECT SUBSTRING(HOST, 1, LOCATE(':', HOST) - 1), t.*
FROM information_schema.processlist t
WHERE user = 'root' AND db = 'bit_trade_dev' AND host LIKE '119.123.124.144%';

SELECT COUNT(0) FROM t_entrust
WHERE status IN (1, 2)