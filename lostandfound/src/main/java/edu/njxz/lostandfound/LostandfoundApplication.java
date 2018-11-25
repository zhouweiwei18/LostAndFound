package edu.njxz.lostandfound;

import javax.servlet.MultipartConfigElement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan("edu.njxz.lostandfound.mapper")
public class LostandfoundApplication {

	public static void main(String[] args) {
		SpringApplication.run(LostandfoundApplication.class, args);
	}

	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		// 单个文件最大 KB,MB
		factory.setMaxFileSize("10MB");
		/// 设置总上传数据总大小
		factory.setMaxRequestSize("100MB");
		return factory.createMultipartConfig();
	}

}
