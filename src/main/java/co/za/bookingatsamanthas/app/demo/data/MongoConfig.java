package co.za.bookingatsamanthas.app.demo.data;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;



@Configuration
public class MongoConfig {


    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(
                "mongodb://localhost:27017/application-database"
        );
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), "application-database");
    }

}
