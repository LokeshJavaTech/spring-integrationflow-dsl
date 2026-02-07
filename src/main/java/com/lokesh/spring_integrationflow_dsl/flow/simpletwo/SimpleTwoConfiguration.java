package com.lokesh.spring_integrationflow_dsl.flow.simpletwo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.Pollers;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.GenericMessage;

@Configuration
@Profile("simpleTwo")
public class SimpleTwoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(SimpleTwoConfiguration.class);

    private boolean triggerEnabled = true;
    private static final Integer POLLER_PERIOD_MILLISECONDS = 2000;

    @Bean
    public IntegrationFlow inputAndOutputFlow() {
        return IntegrationFlow
            .from(
                new MessageSource<Object>() {
                    @Override
                    public Message<Object> receive() {
                        log.info("Checking simple inbound trigger");
                        return triggerEnabled ? new GenericMessage<>("Hello world from simple-two configuration") : null;
                    }
                },
                pollingSpec -> pollingSpec.poller(Pollers.fixedDelay(POLLER_PERIOD_MILLISECONDS))           // Optional polling specification
            )
            .handle(new MessageHandler() {
                @Override
                public void handleMessage(Message<?> message) throws MessagingException {
                    log.info("Got message: "+message.getPayload());
                }
            })
            .get();
    }

}
