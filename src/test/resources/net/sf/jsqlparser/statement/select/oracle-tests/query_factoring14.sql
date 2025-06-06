---
-- #%L
-- JSQLParser library
-- %%
-- Copyright (C) 2004 - 2019 JSQLParser
-- %%
-- Dual licensed under GNU LGPL 2.1 or Apache License 2.0
-- #L%
---
with emp_count (eid, emp_last, mgr_id, mgrlevel, salary, cnt_employees) as (
  select employee_id, last_name, manager_id, 0 mgrlevel, salary, 0 cnt_employees
  from employees
  union all
  select e.employee_id, e.last_name, e.manager_id, r.mgrlevel+1 mgrlevel, e.salary, 1
  cnt_employees
  from emp_count r, employees e
  where e.employee_id = r.mgr_id)
search depth first by emp_last set order1
select emp_last, eid, mgr_id, salary, sum(cnt_employees), max(mgrlevel) mgrlevel
from emp_count
group by emp_last, eid, mgr_id, salary
having max(mgrlevel) > 0
order by mgr_id nulls first, emp_last

--@FAILURE: Encountered unexpected token: "search" <S_IDENTIFIER> recorded first on Aug 3, 2021, 7:20:08 AM
--@FAILURE: Encountered unexpected token: "union" "UNION" recorded first on Feb 13, 2025, 10:16:06 AM
--@FAILURE: Encountered: <S_IDENTIFIER> / "search", at line 18, column 1, in lexical state DEFAULT. recorded first on 15 May 2025, 16:24:09