-- 코드를 작성해주세요
select e.DEPT_ID, DEPT_NAME_EN, ROUND(AVG(SAL), 0) as AVG_SAL
from HR_EMPLOYEES e, HR_DEPARTMENT d
where e.DEPT_ID = d.DEPT_ID
group by e.DEPT_ID
order by AVG_SAL DESC;