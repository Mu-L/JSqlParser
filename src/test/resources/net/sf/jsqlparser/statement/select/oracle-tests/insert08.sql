---
-- #%L
-- JSQLParser library
-- %%
-- Copyright (C) 2004 - 2019 JSQLParser
-- %%
-- Dual licensed under GNU LGPL 2.1 or Apache License 2.0
-- #L%
---
-- without check option
insert into (
select deptno, dname, loc
from dept
where deptno < 30)
values (98, 'travel', 'seattle')

--@FAILURE: Encountered unexpected token: "(" "(" recorded first on Aug 3, 2021, 7:20:08 AM
--@FAILURE: Encountered unexpected token: "insert" "INSERT" recorded first on Mar 26, 2023, 6:59:20 PM
--@FAILURE: Encountered: <K_INSERT> / "insert", at line 11, column 1, in lexical state DEFAULT. recorded first on 15 May 2025, 16:24:08