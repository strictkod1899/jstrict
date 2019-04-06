package ru.strict.db.core.requests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.UUID;

@RunWith(JUnit4.class)
public class TestDbParametrizedRequests {

    @Test
    public void testEmpty(){
        DbRequests requests = new DbRequests();
        Assert.assertEquals("", requests.getParametrizedSql());
    }

    @Test
    public void testWhere1(){
        String sql = "SELECT * FROM table1";
        String sqlExpected = sql + " WHERE (table1.column1 = ?)";
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereEquals("table1", "column1", "value1"));
        Assert.assertEquals(sqlExpected, sql + " " + requests.getParametrizedSql());
    }

    @Test
    public void testWhere2(){
        String sql = "SELECT * FROM table1";
        String sqlExpected = sql + " WHERE (table1.column1 = ?)";
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereEquals("table1", "column1", 123));
        Assert.assertEquals(sqlExpected, sql + " " + requests.getParametrizedSql());
    }

    @Test
    public void testWhere3(){
        UUID uuid = UUID.randomUUID();
        String sql = "SELECT * FROM table1";
        String sqlExpected = sql + " WHERE (table1.column1 = ?)";
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereEquals("table1", "column1", uuid));
        Assert.assertEquals(sqlExpected, sql + " " + requests.getParametrizedSql());
    }

    @Test
    public void testWhere4(){
        String sql = "SELECT * FROM table1";
        String sqlExpected = sql + " WHERE (table1.column1 = ?) AND (table1.column2 = ?)";
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereEquals("table1", "column1", 123));
        requests.addWhere(new DbWhereEquals("table1", "column2", "value1"));
        Assert.assertEquals(sqlExpected, sql + " " + requests.getParametrizedSql());
    }

    @Test
    public void testWhere5(){
        String sql = "SELECT * FROM table1";
        String sqlExpected = sql + " WHERE (table1.column1 = ?) AND ((table1.column2 = ?) OR (table1.column3 = ?))";


        DbWhere where2 = new DbWhere(WhereType.OR);
        where2.add(new DbWhereEquals("table1", "column2", "value2"));
        where2.add(new DbWhereEquals("table1", "column3", "value3"));

        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereEquals("table1", "column1", 123));
        requests.addWhere(where2);
        Assert.assertEquals(sqlExpected, sql + " " + requests.getParametrizedSql());
    }

    @Test
    public void testJoin1(){
        String sql = "SELECT * FROM table1";
        String sqlExpected = sql + " INNER JOIN table2 ON table2.column1 = table1.column2";
        DbRequests requests = new DbRequests();
        requests.addJoin(new DbJoin(JoinType.INNER, "table2", "column1", "table1", "column2"));
        Assert.assertEquals(sqlExpected, sql + " " + requests.getParametrizedSql());
    }

    @Test
    public void testJoin2(){
        String sql = "SELECT * FROM table1";
        String sqlExpected = sql + " INNER JOIN table2 ON table2.column1 = table1.column2 " +
                "LEFT OUTER JOIN table3 ON table3.column1 = table2.column2";
        DbRequests requests = new DbRequests();
        requests.addJoin(new DbJoin(JoinType.INNER, "table2", "column1", "table1", "column2"));
        requests.addJoin(new DbJoin(JoinType.LEFT, "table3", "column1", "table2", "column2"));
        Assert.assertEquals(sqlExpected, sql + " " + requests.getParametrizedSql());
    }

    @Test
    public void testJoinAndWhere(){
        String sql = "SELECT * FROM table1";
        String sqlExpected = sql + " LEFT OUTER JOIN table2 ON table2.column1 = table1.column2 WHERE (table1.column1 = ?)";
        DbRequests requests = new DbRequests();
        requests.addJoin(new DbJoin(JoinType.LEFT, "table2", "column1", "table1", "column2"));
        requests.addWhere(new DbWhereEquals("table1", "column1", 123));
        Assert.assertEquals(sqlExpected, sql + " " + requests.getParametrizedSql());
    }

    @Test
    public void testLimitOffset(){
        String sql = "SELECT * FROM table1";
        String sqlExpected = sql + " WHERE (table1.column1 = ?) LIMIT 10 OFFSET 5";
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereEquals("table1", "column1", 123));
        requests.setLimit(new DbLimit(10));
        requests.setOffset(new DbOffset(5));
        Assert.assertEquals(sqlExpected, sql + " " + requests.getParametrizedSql());
    }

    @Test
    public void testWhereAndSort(){
        String sql = "SELECT * FROM table1";
        String sqlExpected = sql + " WHERE (table1.column1 = ?) ORDER BY table1.column1 DESC";
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereEquals("table1", "column1", 123));
        requests.setSort(new DbSort("table1", "column1", SortType.DESC));
        Assert.assertEquals(sqlExpected, sql + " " + requests.getParametrizedSql());
    }
}
