package org.codecraftlabs.sqs.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codecraftlabs.sqs.util.AppArguments;

import static org.codecraftlabs.sqs.util.AppArguments.INTERVAL_SECONDS_OPTION;
import static org.codecraftlabs.sqs.util.AppArguments.REGION_OPTION;
import static org.codecraftlabs.sqs.util.AppArguments.SQS_URL_OPTION;

public class AWSServiceExecutor {
    private static final Logger logger = LogManager.getLogger(AWSServiceExecutor.class);

    public void execute(AppArguments args) throws AWSException {
        var sqsUrl = args.option(SQS_URL_OPTION);
        var awsRegion = args.option(REGION_OPTION);
        var interval = args.option(INTERVAL_SECONDS_OPTION);
    }
}
