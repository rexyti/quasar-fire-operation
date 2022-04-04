package com.quasar.operation.quasarmessagesplitprocessor;


import com.quasar.operation.quasarmessagesplitprocessor.domain.shared.command.InitializeProcessorCommand;
import com.quasar.operation.quasarmessagesplitprocessor.config.QuasarSplitProcessorConfig;
import org.axonframework.commandhandling.gateway.CommandGateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class QuasarMessageSplitProcessorApplication implements CommandLineRunner{

	@Autowired
	private QuasarSplitProcessorConfig config;

	@Autowired
	private CommandGateway commandGateway;


	public void run(String... args) throws Exception {
		config.setProcessorName(UUID.randomUUID().toString());
		InitializeProcessorCommand command = new InitializeProcessorCommand(config.getProcessorName(), config.getSatellitesNames());
		commandGateway.send(command);
	}

	public static void main(String[] args) {

		SpringApplication.run(QuasarMessageSplitProcessorApplication.class, args);
	}

}
