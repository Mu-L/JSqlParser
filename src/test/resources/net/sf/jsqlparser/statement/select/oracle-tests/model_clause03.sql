---
-- #%L
-- JSQLParser library
-- %%
-- Copyright (C) 2004 - 2019 JSQLParser
-- %%
-- Dual licensed under GNU LGPL 2.1 or Apache License 2.0
-- #L%
---
select country,prod,year,s
  from sales_view_ref
  model
    partition by (country)
    dimension by (prod, year)
    measures (sale s)
    ignore nav
    unique dimension
    rules upsert sequential order
    (
      s[prod='mouse pad'] = 1,
      s['standard mouse'] = 2
    )
  order by country, prod, year


--@FAILURE: Encountered unexpected token: "partition" "PARTITION" recorded first on Aug 3, 2021, 7:20:08 AM
--@FAILURE: Encountered: <K_PARTITION> / "partition", at line 13, column 5, in lexical state DEFAULT. recorded first on 15 May 2025, 16:24:08