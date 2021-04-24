package ej.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = {
                "ej.idempotent.repository",
                "ej.domain.route.repository",
                "ej.domain.seatReservation.projection.repository"},
        entityManagerFactoryRef = "projectionEntityManagerFactory",
        transactionManagerRef = "projectionTransactionManager"
)
@EntityScan(basePackages = {
        "ej.domain.route.entity",
        "ej.domain.seatReservation.projection.entity",
        "ej.idempotent.entity"
})
public class PersistenceBusServiceConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "spring.bms-bus-service.datasource")
    public DataSource projectionDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean projectionEntityManagerFactory(
            final EntityManagerFactoryBuilder builder,
            final DataSource projectionDataSource) {
        return builder
                .dataSource(projectionDataSource)
                .packages(
                        "ej.domain.route.entity",
                        "ej.domain.seatReservation.projection.entity",
                        "ej.idempotent.entity"
                )
                .persistenceUnit("projectionPersistenceUnit")
                .build();
    }

    @Bean
    public PlatformTransactionManager projectionTransactionManager(final EntityManagerFactory factory) {
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                factory);
        return transactionManager;
    }

}
