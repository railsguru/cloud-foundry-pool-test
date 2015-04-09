package org.cloudfoundry.samples.music.config.data;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.cloud.service.PooledServiceConnectorConfig;
import org.springframework.cloud.service.relational.DataSourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile({"mysql-cloud", "postgres-cloud", "oracle-cloud", "cloud"})
public class RelationalCloudDataSourceConfig extends AbstractCloudConfig {
    private static final Log logger = LogFactory.getLog(RelationalCloudDataSourceConfig.class);

    @Bean
    public DataSource dataSource() {
        PooledServiceConnectorConfig.PoolConfig poolConfig = new PooledServiceConnectorConfig.PoolConfig(40, 30000);
        DataSourceConfig.ConnectionConfig connectionConfig =
                new DataSourceConfig.ConnectionConfig("characterEncoding=UTF-8");
        DataSourceConfig serviceConfig =
                new DataSourceConfig(poolConfig, connectionConfig);

        DataSource source = connectionFactory().dataSource(serviceConfig);

        logger.info("Returning configured DataSource object");

        return source;
    }

}
