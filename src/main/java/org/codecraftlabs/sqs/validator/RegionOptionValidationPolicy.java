package org.codecraftlabs.sqs.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codecraftlabs.sqs.util.AppArguments;

import javax.annotation.Nonnull;

import static java.lang.String.format;
import static org.codecraftlabs.sqs.data.AWSRegion.findByCode;
import static org.codecraftlabs.sqs.util.AppArguments.REGION_OPTION;

class RegionOptionValidationPolicy implements AppArgumentsValidationPolicy {
    private static final Logger logger = LogManager.getLogger(RegionOptionValidationPolicy.class);

    @Override
    public void verify(@Nonnull AppArguments args) throws InvalidArgumentException {
        logger.info("Applying region option validation policy");
        var region = findByCode(args.option(REGION_OPTION));
        if (region.isEmpty()) {
            throw new InvalidArgumentException(format("Invalid region: '%s'", args.option(REGION_OPTION)));
        }
    }
}
