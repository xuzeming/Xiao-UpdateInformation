package com.zl.SecuritiesSystem;

import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.zl.mapper")
@ComponentScan(basePackages="com.zl")
public class SecuritiesSystemApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(SecuritiesSystemApplication.class, args);
	}
	/**
	 * 重写启动类
	 */
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SecuritiesSystemApplication.class);
    }
}

