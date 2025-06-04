package org.jingouzhui;

import org.jingouzhui.observer.spring.RegisterEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DesignModeOfStrategyApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(DesignModeOfStrategyApplication.class, args);
	}

}
