package ac.encg.preins;

import com.zaxxer.hikari.HikariDataSource;
import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author Aissam
 */
@Configuration
@ComponentScan
@EnableTransactionManagement
@EnableJpaRepositories

public class SpringConfiguration {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactory
                = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource);
        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
//		jpaProperties.put("hibernate.hbm2ddl.auto", "create-drop");
//              jpaProperties.put("hibernate.hbm2ddl.auto", "create");
        jpaProperties.put("hibernate.hbm2ddl.auto", "update");
        jpaProperties.put("hibernate.show_sql", "true");
        jpaProperties.put("hibernate.format_sql", "true");
        //  jpaProperties.put("generate-ddl", "true");
        jpaProperties.put("hibernate.use-new-id-generator-mappings", "false");
        entityManagerFactory.setJpaProperties(jpaProperties);
        entityManagerFactory.setPackagesToScan("ac.encg.preins.entity");
        entityManagerFactory.setPersistenceUnitName("entity");
        entityManagerFactory
                .setPersistenceProvider(new HibernatePersistenceProvider());

        return entityManagerFactory;
    }

    @Bean
    public JpaTransactionManager transactionManager(DataSource dataSource,
            EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager(
                entityManagerFactory);
        transactionManager.setDataSource(dataSource);

        return transactionManager;
    }

    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

        //local
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/preinsdb?serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("tabit");
        dataSource.setConnectionTestQuery("show tables");

        //Server test
//        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/preinstest?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8");
//        dataSource.setUsername("root");
//        dataSource.setPassword("ENCGdbuser@2019");

        return dataSource;

    }

}
