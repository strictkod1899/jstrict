package ru.strict.db.core.requests;

import edu.emory.mathcs.backport.java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.strict.db.core.common.SqlParameter;
import ru.strict.db.core.common.SqlParameters;

import java.util.UUID;

@RunWith(JUnit4.class)
public class TestDbParametrizedRequests {

    @Test
    public void testParametersSequential(){
        DbTable table = new DbTable("table1");
        DbWhere where2 = new DbWhere(WhereType.OR);
        where2.add(new DbWhereEquals(table, "column2", "value2"));
        where2.add(new DbWhereEquals(table, "column3", "value3"));

        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereEquals(table, "column1", 123));
        requests.addWhere(where2);

        SqlParameters parameters = requests.getParameters();

        Assert.assertEquals(parameters.getByIndex(0), new SqlParameter(0, "where0", 123));
        Assert.assertEquals(parameters.getByIndex(1), new SqlParameter(1, "where1", "value2"));
        Assert.assertEquals(parameters.getByIndex(2), new SqlParameter(2, "where2", "value3"));
    }


    @Test
    public void testEmpty(){
        DbRequests requests = new DbRequests();
        Assert.assertEquals("", requests.getParametrizedSql());
    }

    @Test
    public void testWhere1(){
        String sqlExpected = "SELECT * FROM table1 WHERE (table1.column1 = ?)";

        DbTable table = new DbTable("table1");
        DbSelect select = new DbSelect(table);

        select.getRequests().addWhere(new DbWhereEquals(table, "column1", "value1"));
        Assert.assertEquals(sqlExpected, select.getParametrizedSql());
        Assert.assertEquals(select.getParameters().size(), 1);
    }

    @Test
    public void testWhere2(){
        String sqlExpected = "SELECT * FROM table1 as t1 WHERE (t1.column1 = ?)";

        DbTable table = new DbTable("table1", "t1");
        DbSelect select = new DbSelect(table);

        select.getRequests().addWhere(new DbWhereEquals(table, "column1", 123));
        Assert.assertEquals(sqlExpected, select.getParametrizedSql());
    }

    @Test
    public void testWhere3(){
        UUID uuid = UUID.randomUUID();
        String sqlExpected = "SELECT * FROM table1 WHERE (table1.column1 = ?)";

        DbTable table = new DbTable("table1");
        DbSelect select = new DbSelect(table);

        select.getRequests().addWhere(new DbWhereEquals(table, "column1", uuid));
        Assert.assertEquals(sqlExpected, select.getParametrizedSql());
    }

    @Test
    public void testWhere4(){
        String sqlExpected = "SELECT * FROM table1 WHERE (table1.column1 = ?) AND (table1.column2 = ?)";

        DbTable table = new DbTable("table1");
        DbSelect select = new DbSelect(table);

        select.getRequests().addWhere(new DbWhereEquals(table, "column1", 123));
        select.getRequests().addWhere(new DbWhereEquals(table, "column2", "value1"));
        Assert.assertEquals(sqlExpected, select.getParametrizedSql());
    }

    @Test
    public void testWhere5(){
        String sqlExpected = "SELECT * FROM table1 WHERE (table1.column1 = ?) AND ((table1.column2 = ?) OR (table1.column3 = ?))";

        DbTable table = new DbTable("table1");
        DbSelect select = new DbSelect(table);

        DbWhere where2 = new DbWhere(WhereType.OR);
        where2.add(new DbWhereEquals(table, "column2", "value2"));
        where2.add(new DbWhereEquals(table, "column3", "value3"));

        select.getRequests().addWhere(new DbWhereEquals(table, "column1", 123));
        select.getRequests().addWhere(where2);
        Assert.assertEquals(sqlExpected, select.getParametrizedSql());
    }

    @Test
    public void testWhere6(){
        String sqlExpected = "SELECT * FROM table1 WHERE (table1.column1 IN (?, ?, ?))";

        DbTable table = new DbTable("table1");
        DbSelect select = new DbSelect(table);
        select.getRequests().addWhere(new DbWhereIn(table, "column1", new DbIn(Arrays.asList(new Object[]{1, 2, "test"}))));
        Assert.assertEquals(sqlExpected, select.getParametrizedSql());
        Assert.assertEquals(3, select.getParameters().size());
    }

    @Test
    public void testWhere7(){
        String sqlExpected = "SELECT * FROM table1 WHERE (table1.column1 IN (SELECT table2.column1 FROM table2))";

        DbTable table1 = new DbTable("table1");
        DbSelect select = new DbSelect(table1);

        DbTable table2 = new DbTable("table2");
        DbSelect innerSelect = new DbSelect(table2);
        innerSelect.addSelectItem(table2, "column1");

        select.getRequests().addWhere(new DbWhereIn(table1, "column1", new DbIn(innerSelect)));
        Assert.assertEquals(sqlExpected, select.getSql());
        Assert.assertEquals(0, select.getParameters().size());
    }

    @Test
    public void testGroup(){
        String sqlExpected = "SELECT * FROM table1 WHERE (table1.column1 = ?) GROUP BY table1.column3 HAVING (COUNT(1) > ?)";

        DbTable table = new DbTable("table1");
        DbSelect select = new DbSelect(table);
        select.getRequests().addWhere(new DbWhereEquals(table, "column1", 1));
        select.getRequests().setGroup(new DbGroup(table, "column3", new DbWhereItem(null, "COUNT(1)", 1, ">")));
        Assert.assertEquals(sqlExpected, select.getParametrizedSql());
        Assert.assertEquals(2, select.getParameters().size());
    }

    @Test
    public void testJoin1(){
        String sqlExpected = "SELECT * FROM table1 INNER JOIN table2 ON table2.column1 = table1.column2";

        DbTable table = new DbTable("table1");
        DbSelect select = new DbSelect(table);

        DbTable table2 = new DbTable("table2");

        select.getRequests().addJoin(new DbJoin(JoinType.INNER, table2, "column1", table, "column2"));
        Assert.assertEquals(sqlExpected, select.getParametrizedSql());
    }

    @Test
    public void testJoin2(){
        String sqlExpected = "SELECT * FROM table1 INNER JOIN table2 ON table2.column1 = table1.column2 " +
                "LEFT OUTER JOIN table3 ON table3.column1 = table2.column2";

        DbTable table = new DbTable("table1");
        DbSelect select = new DbSelect(table);

        DbTable table2 = new DbTable("table2");
        DbTable table3 = new DbTable("table3");

        select.getRequests().addJoin(new DbJoin(JoinType.INNER, table2, "column1", table, "column2"));
        select.getRequests().addJoin(new DbJoin(JoinType.LEFT, table3, "column1", table2, "column2"));
        Assert.assertEquals(sqlExpected, select.getParametrizedSql());
    }

    @Test
    public void testJoinAndWhere(){
        String sqlExpected = "SELECT * FROM table1 LEFT OUTER JOIN table2 ON table2.column1 = table1.column2 WHERE (table1.column1 = ?)";

        DbTable table = new DbTable("table1");
        DbSelect select = new DbSelect(table);

        DbTable table2 = new DbTable("table2");

        select.getRequests().addJoin(new DbJoin(JoinType.LEFT, table2, "column1", table, "column2"));
        select.getRequests().addWhere(new DbWhereEquals(table, "column1", 123));
        Assert.assertEquals(sqlExpected, select.getParametrizedSql());
    }

    @Test
    public void testLimitOffset(){
        String sqlExpected = "SELECT * FROM table1 WHERE (table1.column1 = ?) LIMIT 10 OFFSET 5";

        DbTable table = new DbTable("table1");
        DbSelect select = new DbSelect(table);

        select.getRequests().addWhere(new DbWhereEquals(table, "column1", 123));
        select.getRequests().setLimit(new DbLimit(10));
        select.getRequests().setOffset(new DbOffset(5));
        Assert.assertEquals(sqlExpected, select.getParametrizedSql());
    }

    @Test
    public void testWhereAndSort(){
        String sqlExpected = "SELECT * FROM table1 WHERE (table1.column1 = ?) ORDER BY table1.column1 DESC";

        DbTable table = new DbTable("table1");
        DbSelect select = new DbSelect(table);

        select.getRequests().addWhere(new DbWhereEquals(table, "column1", 123));
        select.getRequests().setSort(new DbSort(table, "column1", SortType.DESC));
        Assert.assertEquals(sqlExpected, select.getParametrizedSql());
    }

    @Test
    public void testWhereInnerSql(){
        String sqlExpected = "SELECT * FROM table1 as t1 WHERE (t1.column1 = ?) AND " +
                "((t1.column2) in (SELECT t2.column1 FROM table2 as t2 WHERE (t2.column2 like ?))) " +
                "ORDER BY t1.column1 DESC";

        DbTable table = new DbTable("table1", "t1");
        DbSelect select = new DbSelect(table);

        DbTable table2 = new DbTable("table2", "t2");

        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereEquals(table, "column1", 123));

        DbSelect innerSelect = new DbSelect(table2, new DbSelectItem(table2, "column1"));
        innerSelect.getRequests().addWhere(new DbWhereItem(table2, "column2", "Hello World", "like"));
        requests.addWhere(new DbWhereInnerSql(
                new DbSelectItem(table, "column2"),
                "in",
                innerSelect));
        requests.setSort(new DbSort(table, "column1", SortType.DESC));

        select.setRequests(requests);
        Assert.assertEquals(sqlExpected, select.getParametrizedSql());
        Assert.assertEquals(select.getParameters().size(), 2);
        Assert.assertEquals(select.getParameters().getByIndex(0).getValue(), 123);
        Assert.assertEquals(select.getParameters().getByIndex(1).getValue(), "Hello World");
    }
}
