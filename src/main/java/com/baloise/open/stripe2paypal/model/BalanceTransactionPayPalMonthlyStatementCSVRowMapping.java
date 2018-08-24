/*
 * 
 */
package com.baloise.open.stripe2paypal.model;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import com.stripe.model.BalanceTransaction;

/**
 * @author Markus Tiede
 */
public class BalanceTransactionPayPalMonthlyStatementCSVRowMapping {
    public static final int UNDEFINED_NUMERIC_VALUE = -1;

    private BalanceTransaction stripeBalanceTransaction;
    private PayPalMonthlyStatementCSVRow paypalMonthlyStatementRow;

    /**
     * @param stripeBalanceTransaction
     */
    public BalanceTransactionPayPalMonthlyStatementCSVRowMapping(BalanceTransaction stripeBalanceTransaction) {
        super();
        this.stripeBalanceTransaction = stripeBalanceTransaction;
    }

    public BalanceTransactionPayPalMonthlyStatementCSVRowMapping map() {
        ZonedDateTime created = Instant.ofEpochSecond(
                                    stripeBalanceTransaction.getCreated())
                                        .atZone(ZoneOffset.UTC);
        
        paypalMonthlyStatementRow = new PayPalMonthlyStatementCSVRow(
                created.toLocalDate(),
                created.toLocalTime(),
                created.getZone().getId(),
                stripeBalanceTransaction.getDescription(),
                stripeBalanceTransaction.getCurrency().toUpperCase(),
                stripeBalanceTransaction.getAmount(),
                -1 * stripeBalanceTransaction.getFee(),
                stripeBalanceTransaction.getNet(),
                UNDEFINED_NUMERIC_VALUE,
                stripeBalanceTransaction.getId().substring(5, stripeBalanceTransaction.getId().length() - 1),
                "",
                "",
                "",
                "",
                UNDEFINED_NUMERIC_VALUE,
                UNDEFINED_NUMERIC_VALUE,
                "",
                "");

        return this;
    }

    /**
     * @return the stripeBalanceTransaction
     */
    public BalanceTransaction getStripeBalanceTransaction() {
        return stripeBalanceTransaction;
    }

    /**
     * @return the paypalMonthlyStatementRow
     */
    public PayPalMonthlyStatementCSVRow getPaypalMonthlyStatementRow() {
        return paypalMonthlyStatementRow;
    }
}