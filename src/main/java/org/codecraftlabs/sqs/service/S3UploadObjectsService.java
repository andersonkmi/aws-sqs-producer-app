package org.codecraftlabs.sqs.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codecraftlabs.sqs.data.S3Object;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import javax.annotation.Nonnull;
import java.io.File;
import java.util.Map;

import static org.codecraftlabs.sqs.util.AWSRegionMapper.awsRegion;
import static software.amazon.awssdk.core.sync.RequestBody.fromFile;

class S3UploadObjectsService {
    private static final Logger logger = LogManager.getLogger(S3UploadObjectsService.class);

    public void uploadObjects(@Nonnull S3Bucket bucket, @Nonnull Map<String, S3Object> s3Objects) throws AWSException {
        try {
            var s3Client = S3Client.builder().region(awsRegion(bucket.region())).build();
            s3Objects.forEach((key, value) -> uploadFile(s3Client, bucket.name(), key, value.key()));
        } catch (AwsServiceException | SdkClientException exception) {
            String errorMessage = "Error when listing objects";
            logger.warn(errorMessage, exception);
            throw new AWSException(errorMessage, exception);
        }
    }

    private void uploadFile(@Nonnull S3Client s3Client, @Nonnull String bucket, @Nonnull String fileName, @Nonnull String key) {
        s3Client.putObject(PutObjectRequest.builder().bucket(bucket).key(key).build(), fromFile(new File(fileName)));
    }
}
