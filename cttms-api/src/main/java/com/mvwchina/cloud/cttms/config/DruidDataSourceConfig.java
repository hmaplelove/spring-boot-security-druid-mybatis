package com.mvwchina.cloud.cttms.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
public class DruidDataSourceConfig {
	@Primary
	@Bean(destroyMethod = "close",initMethod = "init")
	public DataSource dataSource(Environment environment) {
        return DruidDataSourceBuilder
                .create()
                .build();
	}	
	
}
