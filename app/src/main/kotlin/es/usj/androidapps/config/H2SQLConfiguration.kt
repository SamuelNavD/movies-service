package es.usj.androidapps.config

import org.h2.tools.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.sql.SQLException
import java.util.*
import javax.sql.DataSource


@Configuration
@EnableJpaRepositories(basePackages = ["es.usj.androidapps", "es.usj.androidapps"])
@EnableTransactionManagement
class H2SQLConfiguration {
    @Bean
    fun dataSource(): DataSource {
        val builder = EmbeddedDatabaseBuilder()
        return builder.setType(EmbeddedDatabaseType.H2)
            //.addScript("db/sql/create-db.sql")
            //.addScript("db/sql/insert-data.sql")
        .build()
    }

    // Start WebServer, access http://localhost:8082
    @Bean(initMethod = "start", destroyMethod = "stop")
    @Throws(SQLException::class)
    fun startDBManager(): Server {
        return Server.createWebServer()
    }
}