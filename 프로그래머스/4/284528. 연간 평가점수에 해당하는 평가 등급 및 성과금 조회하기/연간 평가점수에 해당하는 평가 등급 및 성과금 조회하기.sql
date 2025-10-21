-- 사원별 성과금 정보
WITH EMP_GRADE AS (
    SELECT 
        e.EMP_NO,
        e.EMP_NAME,
        e.SAL,
        CASE
            WHEN AVG(g.SCORE) >= 96 THEN 'S'
            WHEN AVG(g.SCORE) >= 90 THEN 'A'
            WHEN AVG(g.SCORE) >= 80 THEN 'B'
            ELSE 'C'
        END AS GRADE
    FROM HR_EMPLOYEES e
    JOIN HR_GRADE g ON e.EMP_NO = g.EMP_NO
    GROUP BY e.EMP_NO
)

SELECT gr.EMP_NO, 
    gr.EMP_NAME,
    gr.GRADE,
    CASE
        WHEN GRADE = 'S' THEN gr.SAL * 0.2
        WHEN GRADE = 'A' THEN gr.SAL * 0.15
        WHEN GRADE = 'B' THEN  gr.SAL * 0.1
        ELSE 0
    END AS BONUS
FROM EMP_GRADE AS gr
JOIN HR_EMPLOYEES e ON e.EMP_NO = gr.EMP_NO
JOIN HR_DEPARTMENT d ON d.DEPT_ID = e.DEPT_ID
ORDER BY gr.EMP_NO