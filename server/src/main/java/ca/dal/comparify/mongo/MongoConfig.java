package ca.dal.comparify.mongo;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Configuration
@Service
public class MongoConfig {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public MongoClient mongoClient(@Value("${spring.data.mongodb.uri}") String connectionString) {

        ConnectionString connString = new ConnectionString(connectionString);

        WriteConcern writeConcern = new WriteConcern(2);
        writeConcern = writeConcern.withWTimeout(2500, TimeUnit.MILLISECONDS);

        MongoClientSettings options = MongoClientSettings
                .builder()
                .applyConnectionString(connString)
                .writeConcern(writeConcern)
                .build();

        MongoClient mongoClient = MongoClients.create(options);

        return mongoClient;
    }
}

