---
-- #%L
-- JSQLParser library
-- %%
-- Copyright (C) 2004 - 2019 JSQLParser
-- %%
-- Dual licensed under GNU LGPL 2.1 or Apache License 2.0
-- #L%
---
with 
x1 as (select max(y1) from klm1),
x2 as (select max(y2) from klm2),
x3 as (select max(y3) from klm3),
x4 as (select max(y4) from klm4)
select
 distinct
 -1,
 +1,
 a + b * (a * d) as aaa,
 t1.region_name,
 t2.division_name,
 t1.region_name as a,
 t2.division_name as aaaa,
 a.*,
 sum(t3.amount),
 sum(count(1)) + count(*)
 , sum(1) + (select count(1) from ddd) a
from dual, fff
where a is null 
or b is not null 
and ( a like 'd')
and 1 = 0
and a.b is a set
union
select a from dual


--@FAILURE: Encountered unexpected token: "is" "IS" recorded first on Aug 3, 2021, 7:20:08 AM
--@FAILURE: Encountered: <K_IS> / "is", at line 33, column 9, in lexical state DEFAULT. recorded first on 15 May 2025, 16:24:08