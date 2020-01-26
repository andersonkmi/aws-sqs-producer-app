package org.codecraftlabs.sqs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codecraftlabs.sqs.data.SampleData;
import org.codecraftlabs.sqs.service.AWSException;
import org.codecraftlabs.sqs.service.AWSServiceExecutor;
import org.codecraftlabs.sqs.util.CommandLineException;
import org.codecraftlabs.sqs.util.CommandLineUtil;
import org.codecraftlabs.sqs.validator.InvalidArgumentException;

import java.time.Instant;

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

            var sampleData = new SampleData();
            sampleData.setCreationDate(Instant.now());
            sampleData.setId("123456");
            sampleData.setName("sqs-test-app");
            var serviceExecutor = new AWSServiceExecutor();
            serviceExecutor.execute(arguments, sampleData);

            logger.info("Finishing app");
        } catch (AWSException exception) {
            logger.error(exception.getMessage(), exception);
        } catch (InvalidArgumentException | IllegalArgumentException | CommandLineException exception) {
            logger.error("Failed to parse command line options", exception);
            help();
        }
    }
}
