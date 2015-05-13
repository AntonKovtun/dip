package com.sulin.backend.config;

/**
 * Created with IntelliJ IDEA. User: alexander Date: 10/27/13 Time: 7:04 PM To
 * change this template use File | Settings | File Templates.
 */
public interface IBackendProperties {
    public String getJdbcDriverClassName();

    public String getJdbcPassword();

    public String getJdbcUrl();

    public String getJdbcUsername();

    public boolean getHibernateShowSql();

    public String getHibernateDialect();

    public String getHibernateHbm2ddlAuto();

    public int getHibernateJdbcFetchSize();

    public boolean getHibernateCacheUseQueryCache();

    public boolean getHibernateCacheSecondLevelQueryCache();

    public boolean getHibernateSQlCommentEnabled();

    public boolean getHibernateAutoConnectEnabled();

    public String getHibernateValidationQuery();

    public boolean getHibernateGenerateStatistic();

    public int getHibernateJdbcBatchSize();

    public String getDatabaseHost();

    public String getDatabaseName();

    public String getJndiDbResource();

    public boolean getJndiDatabaseFlag();

    public int getDbPoolInitialSize();

    public int getDbPoolMaxSize();

    public int getDbPoolMinSize();

    public int getTokenLife();

    public String getQiwiLogin();

    public String getQiwiPassword();

    public String getQiwiPrvId();

    public String getQiwiPrvName();

    public String getQiwiComment();

    public Double getQiwiAmount();

    public int getQiwiLifeTime();

    public String getAutorefreshTime();

}
