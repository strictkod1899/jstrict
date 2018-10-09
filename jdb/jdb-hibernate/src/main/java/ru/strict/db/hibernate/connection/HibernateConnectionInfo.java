package ru.strict.db.hibernate.connection;



import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Необходимая информация для создания соединения с базой данных, при использовании Hibernate
 * <p><b>Пример использования:</b></p>
 * <code><pre style="background-color: white; font-family: consolas">
 *     ...
 *     HibernateConnectionInfo connectionInfo = new HibernateConnectionInfo("org.hibernate.dialect.MySQLDialect", "com.mysql.jdbc.Driver", "jdbc:mysql://mydb", "mysqluser", "mysqlpassword");
 *     Session connection = connectionCreater.createConnection();
 * </pre></code>
 */
public class HibernateConnectionInfo {

    private String dialect;

    /**
     * Строка драйвера подключаемой базы данных
     */
    private String driver;

    /**
     * Строка url подключаемой базы данных
     */
    private String url;

    /**
     * Пользователь базы данных
     */
    private String username;

    /**
     * Пароль для подключения к базе данных
     */
    private String password;

    private int poolSize;

    private boolean autoCommit;

    private String providerClass;

    private boolean useSecondLevelCache;

    private boolean useQueryCache;

    private boolean showSql;

    private String currentSessionContextClass;

    private List<String> packages;

    private List<Class> entityClasses;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public HibernateConnectionInfo(String dialect, String driver, String url, String username, String password) {
        this.dialect = dialect;
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
        this.poolSize = 150;
        this.autoCommit = true;
        this.providerClass = "org.hibernate.cache.NoCacheProvider";
        this.useSecondLevelCache = false;
        this.useQueryCache = false;
        this.showSql = true;
        this.currentSessionContextClass = "thread";
        packages = new LinkedList<>();
        entityClasses = new LinkedList<>();
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public String getDialect() {
        return dialect;
    }

    public String getDriver() {
        return driver;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getPoolSize() {
        return poolSize;
    }

    public void setPoolSize(int poolSize) {
        this.poolSize = poolSize;
    }

    public boolean isAutoCommit() {
        return autoCommit;
    }

    public void setAutoCommit(boolean autoCommit) {
        this.autoCommit = autoCommit;
    }

    public String getProviderClass() {
        return providerClass;
    }

    public void setProviderClass(String providerClass) {
        this.providerClass = providerClass;
    }

    public boolean isUseSecondLevelCache() {
        return useSecondLevelCache;
    }

    public void setUseSecondLevelCache(boolean useSecondLevelCache) {
        this.useSecondLevelCache = useSecondLevelCache;
    }

    public boolean isUseQueryCache() {
        return useQueryCache;
    }

    public void setUseQueryCache(boolean useQueryCache) {
        this.useQueryCache = useQueryCache;
    }

    public boolean isShowSql() {
        return showSql;
    }

    public void setShowSql(boolean showSql) {
        this.showSql = showSql;
    }

    public String getCurrentSessionContextClass() {
        return currentSessionContextClass;
    }

    public void setCurrentSessionContextClass(String currentSessionContextClass) {
        this.currentSessionContextClass = currentSessionContextClass;
    }

    public List<String> getPackages() {
        return packages;
    }

    public void setPackages(List<String> packages) {
        this.packages = packages;
    }

    public void addPackage(String packagePath){
        if(packages != null && packagePath !=null){
            packages.add(packagePath);
        }
    }

    public List<Class> getEntityClasses() {
        return entityClasses;
    }

    public void setEntityClasses(List<Class> entityClasses) {
        this.entityClasses = entityClasses;
    }

    public void addEntityClass(Class entityClass){
        if(entityClasses != null && entityClass !=null){
            entityClasses.add(entityClass);
        }
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("%s [%s]. User: %s/%s", driver, url, username, password);
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof HibernateConnectionInfo) {
            HibernateConnectionInfo object = (HibernateConnectionInfo) obj;
            return Objects.equals(dialect, object.getDialect())
                    && Objects.equals(password, object.getPassword())
                    && Objects.equals(username, object.getUsername())
                    && Objects.equals(driver, object.getDriver())
                    && Objects.equals(url, object.getUrl())
                    && Objects.equals(poolSize, object.getPoolSize())
                    && Objects.equals(autoCommit, object.isAutoCommit())
                    && Objects.equals(providerClass, object.getProviderClass())
                    && Objects.equals(useSecondLevelCache, object.isUseSecondLevelCache())
                    && Objects.equals(useQueryCache, object.isUseQueryCache())
                    && Objects.equals(showSql, object.isShowSql())
                    && Objects.equals(currentSessionContextClass, object.getCurrentSessionContextClass())
                    && Objects.equals(packages, object.getPackages())
                    && Objects.equals(entityClasses, object.getEntityClasses());
        }else {
            return false;
        }
    }

    @Override
    public int hashCode(){
        return Objects.hash(dialect, password, username, driver, url, poolSize, autoCommit, providerClass,
                useSecondLevelCache, useQueryCache, showSql, currentSessionContextClass, packages, entityClasses);
    }
    //</editor-fold>
}
