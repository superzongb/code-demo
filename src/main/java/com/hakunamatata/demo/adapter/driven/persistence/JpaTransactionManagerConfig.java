package com.hakunamatata.demo.adapter.driven.persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;


/**
 * @authoer: Hakuna Matata
 * @createDate: 2023/3/22
 * @description:
 */
@Configuration
public class JpaTransactionManagerConfig {
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }
}
