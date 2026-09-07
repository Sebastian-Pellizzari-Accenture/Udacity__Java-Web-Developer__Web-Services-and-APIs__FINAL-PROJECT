INSERT INTO price (currency, price, vehicle_id)
SELECT
    'USD',
    CAST(5000 + (RAND() * 20000) AS DECIMAL(10, 2)),
    X
FROM SYSTEM_RANGE(1, 20);