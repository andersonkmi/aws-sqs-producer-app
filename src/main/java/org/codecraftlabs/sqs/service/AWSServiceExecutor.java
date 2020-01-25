package org.codecraftlabs.sqs.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.codecraftlabs.sqs.data.AWSRegion;
import org.codecraftlabs.sqs.data.S3Object;
import org.codecraftlabs.sqs.util.AppArguments;

import javax.annotation.Nonnull;
import java.util.Map;

import static org.codecraftlabs.sqs.util.AppArguments.BUCKET_OPTION;
import static org.codecraftlabs.sqs.util.AppArguments.KEY_OPTION;
import static org.codecraftlabs.sqs.util.AppArguments.OBJECT_OPTION;
import static org.codecraftlabs.sqs.util.AppArguments.REGION_OPTION;
import static org.codecraftlabs.sqs.util.AppArguments.SERVICE_OPTION;

public class AWSServiceExecutor {
    private static final Logger logger = LogManager.getLogger(AWSServiceExecutor.class);

    public void execute(AppArguments args) throws AWSException {
        logger.info(String.format("Executing AWS service: %s", args.option(SERVICE_OPTION)));
        var serviceName = args.option(SERVICE_OPTION);
        var awsRegion = args.option(REGION_OPTION);
        var bucketName = args.option(BUCKET_OPTION);
        var fileName = args.option(OBJECT_OPTION);
        var keyName = args.option(KEY_OPTION);
    }

    private void runListBucketService(@Nonnull String awsRegion) throws AWSException {
        var region = AWSRegion.findByCode(awsRegion);
        var service = new S3BucketListService();
        var buckets = service.buckets(region.orElseThrow());
        buckets.forEach(logger::info);
        buckets.forEach(System.out::println);
    }

    private void runCreateBucketService(@Nonnull String awsRegion, @Nonnull String bucketName) throws AWSException {
        var region = AWSRegion.findByCode(awsRegion);
        var bucket = new S3Bucket(bucketName, region.orElseThrow());
        var service = new S3BucketCreateService();
        service.create(bucket);
    }

    private void runDeleteBucketService(@Nonnull String awsRegion, @Nonnull String bucketName) throws AWSException {
        var region = AWSRegion.findByCode(awsRegion);
        var bucket = new S3Bucket(bucketName, region.orElseThrow());
        var service = new S3BucketDeleteService();
        service.remove(bucket);
    }

    private void runListObjectsService(@Nonnull String awsRegion, @Nonnull String bucketName) throws AWSException {
        var region = AWSRegion.findByCode(awsRegion);
        var service = new S3ObjectListService();
        var bucket = new S3Bucket(bucketName, region.orElseThrow());
        var results = service.list(bucket);
        results.forEach(logger::info);
        results.forEach(System.out::println);
    }

    private void runUploadObjectsService(@Nonnull String awsRegion, @Nonnull String bucketName, @Nonnull String fileName, @Nonnull String keyName) throws AWSException {
        var region = AWSRegion.findByCode(awsRegion);
        var bucket = new S3Bucket(bucketName, region.orElseThrow());
        var s3Objects = Map.of(fileName, new S3Object(keyName));
        var service = new S3UploadObjectsService();
        service.uploadObjects(bucket, s3Objects);
    }

    private void runDeleteObjectsService(@Nonnull String awsRegion, @Nonnull String bucketName, @NonNull String keyName) throws AWSException {
        var region = AWSRegion.findByCode(awsRegion);
        var bucket = new S3Bucket(bucketName, region.orElseThrow());
    }
}
