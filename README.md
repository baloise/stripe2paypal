# stripe2paypal

## idea
<img src="http://yuml.me/diagram/nofunky/usecase/[User]-(Paypal),[User]-(STRIPE),(Paypal)>(monthly statement report),(STRIPE)-(stripe2paypal), (stripe2paypal)>(monthly statement report),(monthly statement report)<(unified post processing).svg"/>

Conversion of [Stripe BalanceTransactions](https://stripe.com/docs/api#balance) to a [Paypal like "monthly statement"-report](https://www.paypalobjects.com/webstatic/en_US/developer/docs/pdf/PP_GenMonthlyStatementReport.pdf).

[![Build Status](https://travis-ci.org/baloise/stripe2paypal.svg?branch=master)](https://travis-ci.org/baloise/stripe2paypal)

```
mvn clean verify -DskipTests && java -jar target/stripe2paypal-csv.jar \
   --stripe-api-key SECRET \
   --output-dir target \
   --report-style monthly-statement \
   --start-date-and-time 2018-07-01T00:00:00 \
   --duration P14D
```

## Commandline usage

```
usage: java -jar stripe2paypal-csv.jar
 -d,--duration <arg>              a duration (format ISO-8601) given as
                                  PnDTnHnMn.nS; e.g. P14D -> 14 days.
 -k,--stripe-api-key <arg>        the STRIPE API key to use; see:
                                  https://stripe.com/docs/keys
 -o,--output-dir <arg>            a directory to write the output files to
 -r,--report-style <arg>          the style of the report to create;
                                  available reports: monthly-statement
 -s,--start-date-and-time <arg>   a local start date and time e. g.
                                  '2018-07-01T23:59:59'
```

## setup [docs](docs/index.md)

## our [code of conduct](https://baloise.github.io/open-source/docs/md/guides/governance.html#code-of-conduct)
