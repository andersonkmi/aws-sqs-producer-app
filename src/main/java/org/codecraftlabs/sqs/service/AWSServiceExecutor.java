package org.codecraftlabs.sqs.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codecraftlabs.sqs.data.SampleData;
import org.codecraftlabs.sqs.util.AppArguments;
import software.amazon.awssdk.services.sqs.SqsClient;

import static org.codecraftlabs.sqs.util.AppArguments.INTERVAL_SECONDS_OPTION;
import static org.codecraftlabs.sqs.util.AppArguments.SQS_URL_OPTION;

public class AWSServiceExecutor {
    private static final Logger logger = LogManager.getLogger(AWSServiceExecutor.class);
    private SqsClient sqsClient;

    public AWSServiceExecutor() {
        sqsClient = SqsClient.builder().build();
    }
    public void execute(AppArguments args, SampleData data) throws AWSException {
        var sqsUrl = args.option(SQS_URL_OPTION);
        var interval = args.option(INTERVAL_SECONDS_OPTION);

        var gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting().serializeNulls();
        var gson = gsonBuilder.create();
    }
}
