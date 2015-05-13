package com.sulin.backend.config;

import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * Created with IntelliJ IDEA. User: alexander Date: 10/24/13 Time: 12:49 AM To
 * change this template use File | Settings | File Templates.
 */
@Configuration
@EnableTransactionManagement
@EnableAsync
@EnableScheduling
@ComponentScan(basePackages = "com.sulin.backend")
public class BackendConfig implements SchedulingConfigurer {
    @Autowired
    private IBackendProperties backendProperties;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(schedulerTaskExecutor());
        addCronTasks(taskRegistrar);
    }

    @Bean
    public Executor schedulerTaskExecutor() {
        return Executors.newScheduledThreadPool(100);
    }

    /**
     * @param taskRegistrar
     */
    protected void addCronTasks(ScheduledTaskRegistrar taskRegistrar) {
        // add your schedule task here
        // try {
        // taskRegistrar.setScheduler(cronService());
        // } catch (Exception e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }

       // taskRegistrar.addCronTask(qiwiService(), "0 * * * * *");

    }

//    @Bean
//    public QiwiTask qiwiService() {
//        return new QiwiTask();
//    }

    // @Bea
    // public MessageTest messageService()
    // return new MessageTest()
    // }

    // @Bean
    // public CronScheluder cronService() throws Exception {
    // return new CronScheluder();
    // }
    //
    // @Bean
    // public TestTask taskServise() {
    // return new TestTask();
    // }

    // @Bean
    // public Main mainService() {
    // return new Main();
    // }

    // @Bean
    // public JavaMail mailService() {
    // return new JavaMail();
    // }

    @Bean
    public DataSource dataSource() throws Exception {
        if (backendProperties.getJndiDatabaseFlag()) {
            Context ctx = new InitialContext();
            return (DataSource) ctx.lookup(backendProperties.getJndiDbResource());
        } else {
            ComboPooledDataSource dataSource = new ComboPooledDataSource();

            dataSource.setDriverClass(backendProperties.getJdbcDriverClassName());
            dataSource.setJdbcUrl(backendProperties.getJdbcUrl());
            dataSource.setUser(backendProperties.getJdbcUsername());
            dataSource.setPassword(backendProperties.getJdbcPassword());

            dataSource.setInitialPoolSize(backendProperties.getDbPoolInitialSize());
            dataSource.setMaxPoolSize(backendProperties.getDbPoolMaxSize());
            dataSource.setMinPoolSize(backendProperties.getDbPoolMinSize());
            return dataSource;
        }
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() throws Exception {
        LocalSessionFactoryBean bean = new LocalSessionFactoryBean();
        bean.setDataSource(dataSource());
        bean.setHibernateProperties(getHibernateProperties());
        bean.setPackagesToScan("com.sulin.backend.domain");

        return bean;
    }

    private Properties getHibernateProperties() {
        Properties hibernateProperties = new Properties();

        hibernateProperties.put("hibernate.dialect", backendProperties.getHibernateDialect());
        hibernateProperties.put("hibernate.hbm2ddl.auto", backendProperties.getHibernateHbm2ddlAuto());
        hibernateProperties.put("hibernate.show_sql", backendProperties.getHibernateShowSql());
        hibernateProperties.put("hibernate.format_sql", backendProperties.getHibernateShowSql());
        hibernateProperties.put("hibernate.generate_statistics", backendProperties.getHibernateGenerateStatistic());
        hibernateProperties.put("hibernate.cache.use_second_level_cache",
                backendProperties.getHibernateCacheSecondLevelQueryCache());
        hibernateProperties.put("hibernate.cache.use_query_cache", backendProperties.getHibernateCacheUseQueryCache());
        hibernateProperties.put("hibernate.use_sql_comments", backendProperties.getHibernateSQlCommentEnabled());
        hibernateProperties.put("hibernate.connection.autoReconnect",
                backendProperties.getHibernateAutoConnectEnabled());

        hibernateProperties.put("hibernate.connection.CharSet", "UTF-8");
        hibernateProperties.put("hibernate.connection.characterEncoding", "UTF-8");
        hibernateProperties.put("hibernate.connection.useUnicode", true);

        hibernateProperties.put("hibernate.jdbc.fetch_size", backendProperties.getHibernateJdbcFetchSize());
        hibernateProperties.put("hibernate.jdbc.batch_size", backendProperties.getHibernateJdbcBatchSize());

        return hibernateProperties;
    }

    @Bean
    @Autowired
    public PlatformTransactionManager transationManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

    @Bean
    @Autowired
    public TransactionTemplate transactionTemplate(PlatformTransactionManager transationManager) {
        TransactionTemplate template = new TransactionTemplate();
        template.setTransactionManager(transationManager);
        return template;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver bean = new CommonsMultipartResolver();
        bean.setMaxUploadSize(900000000000l);
        return bean;
    }

}
