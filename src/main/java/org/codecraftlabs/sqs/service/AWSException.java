package org.codecraftlabs.sqs.service;

import javax.annotation.Nonnull;

public class AWSException extends Exception {
    public AWSException(@Nonnull String message, @Nonnull Throwable exception) {
        super(message, exception);
    }
}
