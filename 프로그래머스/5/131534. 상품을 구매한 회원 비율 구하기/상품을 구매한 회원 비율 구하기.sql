-- Common Table Expression: 미리 테이블 생성
WITH 
users AS (
    SELECT USER_ID 
    FROM USER_INFO 
    WHERE JOINED >= '2021-01-01' AND JOINED <= '2021-12-31'
),
total AS (
    SELECT COUNT(*) AS total
    FROM users
),
month_by_customer AS (
    SELECT 
        YEAR(SALES_DATE) AS YEAR,
        MONTH(SALES_DATE) AS MONTH,
        COUNT(DISTINCT ONLINE_SALE.USER_ID) PURCHASED_USERS
    FROM ONLINE_SALE
    JOIN users ON users.USER_ID = ONLINE_SALE.USER_ID
    GROUP BY YEAR(SALES_DATE), MONTH(SALES_DATE)
)


SELECT YEAR, 
        MONTH,
        PURCHASED_USERS,
        ROUND(PURCHASED_USERS/total.total, 1) AS PUCHASED_RATIO
FROM month_by_customer, total
ORDER BY YEAR, MONTH
    