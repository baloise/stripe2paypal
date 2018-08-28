# stripe2paypal
Conversion of [Stripe BalanceTransactions](https://stripe.com/docs/api#balance) to a [Paypal like "monthly statement"-report](https://www.paypalobjects.com/webstatic/en_US/developer/docs/pdf/PP_GenMonthlyStatementReport.pdf).

[![Build Status](https://travis-ci.org/baloise/stripe2paypal.svg?branch=master)](https://travis-ci.org/baloise/stripe2paypal)

```
mvn clean verify -DskipTests && java -jar target/stripe2paypal-csv.jar --stripe-api-key SECRET --output-dir target -report-style monthly-statement --start-date-and-time 2018-07-01T00:00:00 --duration P14D
```

## the [docs](docs/index.md)

## our [code of conduct](https://baloise.github.io/open-source/docs/md/guides/governance.html#code-of-conduct)