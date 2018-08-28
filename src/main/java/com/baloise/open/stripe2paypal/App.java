/*
 * 
 */
package com.baloise.open.stripe2paypal;

import java.io.File;
import java.nio.file.Path;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.baloise.open.stripe2paypal.report.PaypalMonthlyReportFromStripe;
import com.baloise.open.stripe2paypal.report.Report;
import com.stripe.Stripe;

/** Application */
public class App {
    public static final String STRIPE_API_KEY_ENV = "STRIPE_API_KEY";
    
    public static final String STRIPE_API_KEY_ARG_LONG = "stripe-api-key";
    public static final String OUTPUT_DIR_ARG_LONG = "output-dir";
    public static final String START_DATE_TIME_ARG_LONG = "start-date-and-time";
    public static final String DURATION_ARG_LONG = "duration";
    public static final String REPORT_STYLE_ARG_LONG = "report-style";
    
    public static final String REPORT_STYLE_MONTHLY_REPORT = "monthly-statement";

    public static void main(String[] args) {
        Options options = new Options();

        options.addOption(Option.builder("k").longOpt(STRIPE_API_KEY_ARG_LONG).hasArg()
                .required(!System.getenv().containsKey(STRIPE_API_KEY_ENV))
                .desc("the STRIPE API key to use; see: https://stripe.com/docs/keys").build());

        options.addOption(Option.builder("o").longOpt(OUTPUT_DIR_ARG_LONG).hasArg().required()
                .desc("a directory to write the output files to").build());

        options.addOption(Option.builder("r").longOpt(REPORT_STYLE_ARG_LONG).hasArg().required()
                .desc("the style of the report to create; available reports: " + REPORT_STYLE_MONTHLY_REPORT).build());

        options.addOption(Option.builder("s").longOpt(START_DATE_TIME_ARG_LONG).hasArg().required()
                .desc("a local start date and time e. g. '2018-07-01T23:59:59'").build());
        
        options.addOption(Option.builder("d").longOpt(DURATION_ARG_LONG).hasArg().required()
                .desc("a duration (format ISO-8601) given as PnDTnHnMn.nS; e.g. P14D -> 14 days.").build());

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
            String apiKey;
            if (System.getenv().containsKey(STRIPE_API_KEY_ENV)) {
                apiKey = System.getenv().get(STRIPE_API_KEY_ENV);
            } else {
                apiKey = cmd.getOptionValue(STRIPE_API_KEY_ARG_LONG);
            }
            Stripe.apiKey = apiKey;

            String reportStyle = cmd.getOptionValue(REPORT_STYLE_ARG_LONG);
            String outputDir = cmd.getOptionValue(OUTPUT_DIR_ARG_LONG);
            String startDateAndTime = cmd.getOptionValue(START_DATE_TIME_ARG_LONG);
            String duration = cmd.getOptionValue(DURATION_ARG_LONG);

            Path outputPath = new File(outputDir).toPath();

            Report report = null;
            switch (reportStyle) {
            case REPORT_STYLE_MONTHLY_REPORT:
                report = new PaypalMonthlyReportFromStripe(outputPath, startDateAndTime, duration);
                break;
            default:
                break;
            }
            if (report != null) {
                report.create();
            } else {
                throw new ParseException("Unkown report style: " + reportStyle);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            formatter.printHelp("java -jar stripe2paypal-csv.jar", options);

            System.exit(1);
        }
    }
}
