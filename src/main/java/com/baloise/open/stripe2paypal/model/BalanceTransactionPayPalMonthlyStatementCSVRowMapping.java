/*
 * 
 */
package com.baloise.open.stripe2paypal.model;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import com.stripe.exception.StripeException;
import com.stripe.model.BalanceTransaction;
import com.stripe.model.Charge;

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

    public BalanceTransactionPayPalMonthlyStatementCSVRowMapping map() throws StripeException {
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
                Math.negateExact(stripeBalanceTransaction.getFee()),
                stripeBalanceTransaction.getNet(),
                UNDEFINED_NUMERIC_VALUE,
                stripeBalanceTransaction.getId(),
                "",
                "",
                "",
                "",
                UNDEFINED_NUMERIC_VALUE,
                UNDEFINED_NUMERIC_VALUE,
                Charge.retrieve(stripeBalanceTransaction.getSource()).getMetadata().get("Policy"),
                stripeBalanceTransaction.getSource());

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