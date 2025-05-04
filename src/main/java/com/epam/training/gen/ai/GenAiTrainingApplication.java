package com.epam.training.gen.ai;

import com.epam.training.gen.ai.controller.RAGController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:/config/application.properties")
public class GenAiTrainingApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(GenAiTrainingApplication.class, args);

		// Retrieve the RAGController bean from the ApplicationContext
		// loads Vector DB with Hotel Embeddings
		RAGController ragController = context.getBean(RAGController.class);
		ragController.loadEmbedding();
	}

}
