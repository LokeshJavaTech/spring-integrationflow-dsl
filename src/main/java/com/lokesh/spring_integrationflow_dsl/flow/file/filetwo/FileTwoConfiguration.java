package com.lokesh.spring_integrationflow_dsl.flow.file.filetwo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.file.outbound.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;

import java.io.File;

@Configuration
@Profile("fileTwo")
public class FileTwoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(FileTwoConfiguration.class);

    private static final Integer POLLER_DELAY_MILLISECONDS = 2000;
    private static final String INPUT_DIR = "C:\\Users\\lokes\\Downloads\\input-files";
    private static final String OUTPUT_DIR = "C:\\Users\\lokes\\Downloads\\output-files";
    private static final String FILE_PATTERN = "*.csv";

    @Bean
    public IntegrationFlow inputFlow() {
        MessageSource<File> messageSource = Files
                .inboundAdapter(new File(INPUT_DIR))
                .autoCreateDirectory(true)
                .patternFilter(FILE_PATTERN)
                .getObject();
        FileWritingMessageHandler messageHandler = Files
                .outboundAdapter(new File(OUTPUT_DIR))
                .autoCreateDirectory(true)
                .fileExistsMode(FileExistsMode.REPLACE)
                .deleteSourceFiles(true)
                .getObject();
        return IntegrationFlow
                .from(
                        messageSource,
                        pollingSpec -> pollingSpec.poller(Pollers.fixedDelay(POLLER_DELAY_MILLISECONDS))           // Optional polling specification
                )
                .handle(messageHandler)
                .get();
    }
}
