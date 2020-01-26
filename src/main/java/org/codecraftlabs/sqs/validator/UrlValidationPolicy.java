package org.codecraftlabs.sqs.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codecraftlabs.sqs.util.AppArguments;

import javax.annotation.Nonnull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.codecraftlabs.sqs.util.AppArguments.SQS_URL_OPTION;

class UrlValidationPolicy implements AppArgumentsValidationPolicy {
    private static final Logger logger = LogManager.getLogger(UrlValidationPolicy.class);

    @Override
    public void verify(@Nonnull AppArguments args) throws InvalidArgumentException {
        var url = args.option(SQS_URL_OPTION);
        String regex = "<\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]>";

        if (isMatch(url, regex)) {
            logger.info(String.format("URL '%s' is valid", url));
        } else {
            throw new InvalidArgumentException(String.format("URL '%s' is invalid", url));
        }
    }

    private boolean isMatch(String url, String pattern) {
        try {
            Pattern patt = Pattern.compile(pattern);
            Matcher matcher = patt.matcher(url);
            return matcher.matches();
        } catch (RuntimeException e) {
            return false;
        }
    }
}
