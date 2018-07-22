package ru.strict.db.hibernate.common;

public enum HibernateDialects {

    DB2("org.hibernate.dialect.DB2Dialect"),
    POSTGRESQL("org.hibernate.dialect.PostgreSQLDialect"),
    MYSQL("org.hibernate.dialect.MySQLDialect"),
    OREACLE("org.hibernate.dialect.OracleDialect"),
    MS_SQL("org.hibernate.dialect.SQLServerDialect"),
    SQLITE("org.hibernate.dialect.SQLiteDialect");

    private String dialect;

    HibernateDialects(String dialect) {
        this.dialect = dialect;
    }

    public String getDialect() {
        return dialect;
    }
}
