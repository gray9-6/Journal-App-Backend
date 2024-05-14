package com.ajay.journalApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class TransactionConfig {
    @Bean
    public PlatformTransactionManager platformTransactionManager(MongoDatabaseFactory databaseFactory){
        return new MongoTransactionManager(databaseFactory);
    }
}

//PlatformTransactionManager :- ye ek interface hai , jiski help se hum Transactional ko maintain karte hai
//MongoTransactionManager :- ye implementation class hai PlatformTransactionManager
//MongoDatabaseFactory :- MongoTransactionManager, MongoDatabaseFactory ko use karta hai db se communicate karne ke liye
//SimpleMongoClientDatabaseFactory :- ye implementation class hai ,MongoDatabaseFactory ki