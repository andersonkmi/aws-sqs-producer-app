package org.codecraftlabs.sqs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codecraftlabs.sqs.data.SampleData;
import org.codecraftlabs.sqs.service.AWSException;
import org.codecraftlabs.sqs.service.AWSServiceExecutor;
import org.codecraftlabs.sqs.util.AppArguments;
import org.codecraftlabs.sqs.util.CommandLineException;
import org.codecraftlabs.sqs.util.CommandLineUtil;
import org.codecraftlabs.sqs.validator.InvalidArgumentException;

import java.time.Instant;

import static org.codecraftlabs.sqs.util.AppArguments.INTERVAL_SECONDS_OPTION;
import static org.codecraftlabs.sqs.util.CommandLineUtil.help;
import static org.codecraftlabs.sqs.validator.AppArgsValidator.build;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Starting app");
        try {
            var commandLineUtil = new CommandLineUtil();
            var arguments = commandLineUtil.parse(args);

            var cliValidator = build();
            cliValidator.validate(arguments);

            while (true) {
                var sampleData = new SampleData();
                sampleData.setCreationDate(Instant.now());
                sampleData.setId("123456");
                sampleData.setName("sqs-test-app");
                sampleData.setProgrammingLanguage("java");
                var serviceExecutor = new AWSServiceExecutor();
                serviceExecutor.execute(arguments, sampleData);

                var interval = arguments.option(INTERVAL_SECONDS_OPTION);
                var intervalValue = Long.valueOf(interval);
                Thread.sleep(intervalValue * 1000);
            }

            //logger.info("Finishing app");
        } catch (AWSException exception) {
            logger.error(exception.getMessage(), exception);
        } catch (InvalidArgumentException | IllegalArgumentException | CommandLineException | InterruptedException exception) {
            logger.error("Failed to parse command line options", exception);
            help();
        }
    }
}
