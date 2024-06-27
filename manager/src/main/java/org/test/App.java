package org.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication(scanBasePackages = {"org.test"})
public class App
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class);
    }
}
