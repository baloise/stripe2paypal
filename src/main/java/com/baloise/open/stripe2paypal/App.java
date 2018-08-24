/*
 * 
 */
package com.baloise.open.stripe2paypal;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/** Application */
public class App {
    public static final String STRIPE_API_KEY_ENV = "STRIPE_API_KEY";
    public static final String STRIPE_API_KEY_ARG_LONG = "stripe-api-key";
    public static final String OUTPUT_DIR_ARG_LONG = "output-dir";
    public static final String REPORT_STYLE_ARG_LONG = "report-style";

    public static void main(String[] args) {
        Options options = new Options();

        options.addOption(Option.builder("k")
                    .longOpt(STRIPE_API_KEY_ARG_LONG)
                    .hasArg()
                    .required()
                    .desc("STRIPE API key to use; see: https://stripe.com/docs/keys")
                        .build());

        options.addOption(Option.builder("o")
                    .longOpt(OUTPUT_DIR_ARG_LONG)
                    .hasArg()
                    .required()
                    .desc("a directory to write the output files to")
                        .build());

        options.addOption(Option.builder("r")
                    .longOpt(REPORT_STYLE_ARG_LONG)
                    .hasArg()
                    .required()
                    .desc("the style of the report to create; available reports: monthly-statement")
                        .build());

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);

            String apiKey = cmd.getOptionValue(STRIPE_API_KEY_ARG_LONG);
            System.out.println(apiKey);

        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("stripe2paypal", options);

            System.exit(1);
        }
    }
}
