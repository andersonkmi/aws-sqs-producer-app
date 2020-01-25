package org.codecraftlabs.sqs.util;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class CommandLineUtil {
    private CommandLineParser commandLineParser;

    private static final Logger logger = LogManager.getLogger(CommandLineUtil.class);

    public static final String S3_SERVICE_OPT = "s";
    public static final String S3_BUCKET_NAME_OPT = "b";
    public static final String AWS_REGION_OPT = "r";
    public static final String OBJECT_NAME_OPT = "o";
    public static final String KEY_NAME_OPT = "k";
    public static final String CONTENT_TYPE_OPT = "c";

    final private static Options cmdLineOpts = new Options().addRequiredOption(S3_SERVICE_OPT, AppArguments.SERVICE_OPTION, true, "Select which service")
            .addRequiredOption(AWS_REGION_OPT, AppArguments.REGION_OPTION, true, "AWS region to operate")
            .addOption(OBJECT_NAME_OPT, AppArguments.OBJECT_OPTION, true, "Object to upload")
            .addOption(KEY_NAME_OPT, AppArguments.KEY_OPTION, true, "S3 key name")
            .addOption(CONTENT_TYPE_OPT, AppArguments.CONTENT_TYPE, true, "Content type")
            .addOption(S3_BUCKET_NAME_OPT, AppArguments.BUCKET_OPTION,true, "Bucket name");

    public CommandLineUtil() {
        commandLineParser = new DefaultParser();
    }

    public AppArguments parse(String[] args) throws CommandLineException {
        logger.info("Parsing command line arguments");

        final Map<String, String> options = new HashMap<>();

        try {
            var cmdLine = commandLineParser.parse(cmdLineOpts, args);
            options.put(AppArguments.SERVICE_OPTION, cmdLine.getOptionValue(S3_SERVICE_OPT));
            options.put(AppArguments.REGION_OPTION, cmdLine.getOptionValue(AWS_REGION_OPT));

            extractOptionalArgs(cmdLine, options);

            return new AppArguments(options);
        } catch (ParseException exception) {
            logger.error("Command line parse error", exception);
            throw new CommandLineException("Error when parsing command line options", exception);
        }
    }

    private void extractOptionalArgs(@Nonnull CommandLine line, @Nonnull Map<String, String> options) {
        if (line.hasOption(S3_BUCKET_NAME_OPT)) {
            options.put(AppArguments.BUCKET_OPTION, line.getOptionValue(S3_BUCKET_NAME_OPT));
        }

        if (line.hasOption(OBJECT_NAME_OPT)) {
            options.put(AppArguments.OBJECT_OPTION, line.getOptionValue(OBJECT_NAME_OPT));
        }

        if (line.hasOption(KEY_NAME_OPT)) {
            options.put(AppArguments.KEY_OPTION, line.getOptionValue(OBJECT_NAME_OPT));
        }

        if (line.hasOption(CONTENT_TYPE_OPT)) {
            options.put(AppArguments.CONTENT_TYPE, line.getOptionValue(CONTENT_TYPE_OPT));
        }
    }

    public static void help() {
        var header = "\nAWS S3 sandbox app\n";
        var footer = "\nThank you for using\n";
        new HelpFormatter().printHelp("java -jar aws-s3-app.jar", header, cmdLineOpts, footer, true);
    }
}
