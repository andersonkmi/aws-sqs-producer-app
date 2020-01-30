package org.codecraftlabs.sqs.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.codecraftlabs.sqs.util.AppArguments;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;

import static org.codecraftlabs.sqs.util.AppArguments.SQS_URL_OPTION;

public class SQSConsumerService {
    private static final Logger logger = LogManager.getLogger(SQSConsumerService.class);
    private SqsClient sqsClient;

    public SQSConsumerService() {
        sqsClient = SqsClient.builder().build();
    }

    public void execute(@NonNull AppArguments arguments) throws AWSException {
        try {
            var receiveMessageRequest = ReceiveMessageRequest.builder()
                    .queueUrl(arguments.option(SQS_URL_OPTION))
                    .maxNumberOfMessages(5)
                    .build();

            var messages= sqsClient.receiveMessage(receiveMessageRequest).messages();

            messages.forEach(item -> {
                var body = item.body();
                logger.info(String.format("Message: '%s'", body));
            });

            messages.forEach(message -> {
                var deleteRequest = DeleteMessageRequest.builder().queueUrl(arguments.option(SQS_URL_OPTION)).receiptHandle(message.receiptHandle()).build();
                sqsClient.deleteMessage(deleteRequest);
            });
        } catch (Exception exception) {
            logger.error("SQS operation failed", exception);
            throw new AWSException("SQS operation failed", exception);
        }
    }
}
