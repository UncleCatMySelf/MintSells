package com.myself.sbdiancan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching //缓存
//@MapperScan(basePackages = "com.myself.sbdiancan.dataobject.mapper") //启动MyBatis
public class SbdiancanApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbdiancanApplication.class, args);
	}

}
