package org.codecraftlabs.sqs.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.codecraftlabs.sqs.data.SampleData;
import org.codecraftlabs.sqs.util.AppArguments;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

import static org.codecraftlabs.sqs.util.AppArguments.SQS_URL_OPTION;

public class SQSProducerService {
    private static final Logger logger = LogManager.getLogger(SQSProducerService.class);
    private SqsClient sqsClient;

    public SQSProducerService() {
        sqsClient = SqsClient.builder().build();
    }

    public void execute(@NonNull AppArguments args, @NonNull SampleData data) throws AWSException {
        var sqsUrl = args.option(SQS_URL_OPTION);

        var request = SendMessageRequest.builder()
                .queueUrl(sqsUrl)
                .messageBody(data.toString())
                .build();

        try {
            var response = sqsClient.sendMessage(request);
            logger.info(String.format("Message successfully posted: '%s'", response.messageId()));
        } catch (Exception exception) {
            logger.error("SQS operation failed", exception);
            throw new AWSException("SQS operation failed", exception);
        }
    }

}
