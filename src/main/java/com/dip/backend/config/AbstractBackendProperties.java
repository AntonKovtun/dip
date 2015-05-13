package com.dip.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public abstract class AbstractBackendProperties implements IBackendProperties {
    @Autowired
    protected Environment env;

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.eharvest.erp.config.IErpBackendCommonProperties#getJdbcDriverClassName
     * ()
     */
    @Override
    public String getJdbcDriverClassName() {
        return env.getProperty("jdbc.driverClassName");
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.eharvest.erp.config.IErpBackendCommonProperties#getJdbcPassword()
     */
    @Override
    public String getJdbcPassword() {
        return env.getProperty("jdbc.password");
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.eharvest.erp.config.IErpBackendCommonProperties#getJdbcUrl()
     */
    @Override
    public String getJdbcUrl() {
        return env.getProperty("jdbc.url");
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.eharvest.erp.config.IErpBackendCommonProperties#getJdbcUsername()
     */
    @Override
    public String getJdbcUsername() {
        return env.getProperty("jdbc.username");
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.eharvest.erp.config.IErpBackendCommonProperties#getHibernateShowSql()
     */
    @Override
    public boolean getHibernateShowSql() {
        return env.getProperty("hibernate.show_sql", Boolean.class, true);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.eharvest.erp.config.IErpBackendCommonProperties#getHibernateDialect()
     */
    @Override
    public String getHibernateDialect() {
        return env.getProperty("hibernate.dialect");
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.eharvest.erp.config.IErpBackendCommonProperties#getHibernateHbm2ddlAuto
     * ()
     */
    @Override
    public String getHibernateHbm2ddlAuto() {
        return env.getProperty("hibernate.hbm2ddl.auto");
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.eharvest.erp.config.IErpBackendCommonProperties#getHibernateJdbcFetchSize
     * ()
     */
    @Override
    public int getHibernateJdbcFetchSize() {
        return env.getProperty("hibernate.jdbc.fetch_size", Integer.class, 1000);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.eharvest.erp.config.IErpBackendCommonProperties#
     * getHibernateCacheUserQueryCache ()
     */
    @Override
    public boolean getHibernateCacheUseQueryCache() {
        return env.getProperty("hibernate.query.cache.enabled", Boolean.class, true);
    }

    @Override
    public boolean getHibernateCacheSecondLevelQueryCache() {
        return env.getProperty("hibernate.second.level.cache.enabled", Boolean.class, false);
    }

    @Override
    public boolean getHibernateSQlCommentEnabled() {
        return env.getProperty("hibernate.sql.comments.enabled", Boolean.class, false);
    }

    @Override
    public boolean getHibernateAutoConnectEnabled() {
        return env.getProperty("hibernate.auto.connect.enabled", Boolean.class, true);
    }

    @Override
    public String getHibernateValidationQuery() {
        return env.getProperty("hibernate.validationQuery", "SELECT 1");
    }

    @Override
    public boolean getHibernateGenerateStatistic() {
        return env.getProperty("hibernate.generate.statistics", Boolean.class, false);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.eharvest.erp.config.IErpBackendCommonProperties#getHibernateJdbcBatchSize
     * ()
     */
    @Override
    public int getHibernateJdbcBatchSize() {
        return env.getProperty("hibernate.jdbc.batch_size", Integer.class, 1000);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.eharvest.erp.config.IErpBackendCommonProperties#getDatabaseHost()
     */
    @Override
    public String getDatabaseHost() {
        return env.getProperty("database.host");
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.eharvest.erp.config.IErpBackendCommonProperties#getDatabaseName()
     */
    @Override
    public String getDatabaseName() {
        return env.getProperty("database.name");
    }

    public String getJndiDbResource() {
        return env.getProperty("jndi.db.url");
    }

    public boolean getJndiDatabaseFlag() {
        return env.getProperty("jndi.database", Boolean.class, false);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.ssc.common.backend.config.IBackendProperties#getDbPoolInitialSize()
     */
    @Override
    public int getDbPoolInitialSize() {
        return env.getProperty("db.pool.initialSize", Integer.class, 10);
    }

    public int getDbPoolMaxSize() {
        return env.getProperty("db.pool.maxSize", Integer.class, 100);
    }

    public int getDbPoolMinSize() {
        return env.getProperty("db.pool.minSize", Integer.class, 10);
    }

    public int getTokenLife() {
        return env.getProperty("dip.auth.token.life", Integer.class, 30);
    }

    public String getQiwiLogin() {
        return env.getProperty("qiwi.login");
    }

    public String getQiwiPassword() {
        return env.getProperty("qiwi.password");
    }

    public String getQiwiPrvId() {
        return env.getProperty("qiwi.prv.id");
    }

    public String getQiwiPrvName() {
        return env.getProperty("qiwi.prv.name");
    }

    public String getQiwiComment() {
        return env.getProperty("qiwi.comment");
    }

    public Double getQiwiAmount() {
        return env.getProperty("qiwi.amount", Double.class);
    }

    public int getQiwiLifeTime() {
        return env.getProperty("qiwi.lifetime", Integer.class);
    }

    public String getAutorefreshTime() {
        return env.getProperty("autorefresh.time");
    }

    public String getIsTest() {
        return env.getProperty("dip.is.test");
    }

}
