package org.codecraftlabs.sqs.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.codecraftlabs.sqs.util.AppArguments;
import software.amazon.awssdk.services.sqs.SqsClient;

public class SQSConsumerService {
    private static final Logger logger = LogManager.getLogger(SQSConsumerService.class);
    private SqsClient sqsClient;

    public SQSConsumerService() {
        sqsClient = SqsClient.builder().build();
    }

    public void execute(@NonNull AppArguments arguments) throws AWSException {

    }
}
