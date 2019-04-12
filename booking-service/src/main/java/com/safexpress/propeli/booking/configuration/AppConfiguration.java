package com.safexpress.propeli.booking.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "com.safexpress.propeli" })
@EnableAutoConfiguration
public class AppConfiguration {

}
