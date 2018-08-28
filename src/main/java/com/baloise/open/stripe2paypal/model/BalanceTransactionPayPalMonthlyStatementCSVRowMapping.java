/*
 * 
 */
package com.baloise.open.stripe2paypal.model;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.MonetaryAmountFactory;

import org.apache.commons.lang3.StringUtils;
import org.javamoney.moneta.Money;

import com.stripe.exception.StripeException;
import com.stripe.model.BalanceTransaction;
import com.stripe.model.Charge;

/**
 * @author Markus Tiede
 */
public class BalanceTransactionPayPalMonthlyStatementCSVRowMapping {
    public static final int UNKNOWN_NUMERIC_VALUE = -1;

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
        ZonedDateTime created = Instant.ofEpochSecond(stripeBalanceTransaction.getCreated())
                                        .atZone(ZoneOffset.systemDefault());
        
        String currencyCode = StringUtils.upperCase(stripeBalanceTransaction.getCurrency());
        CurrencyUnit currency = Monetary.getCurrency(currencyCode);
        
        MonetaryAmountFactory<?> amountFactory = Monetary.getDefaultAmountFactory();
        MonetaryAmount chfAmount = amountFactory
                .setCurrency(currency).setNumber(stripeBalanceTransaction.getAmount() / 100d)
                .create();
        MonetaryAmount chfFee = amountFactory
                .setCurrency(currency).setNumber(stripeBalanceTransaction.getFee() / 100d)
                .create();
        MonetaryAmount chfNet = amountFactory
                .setCurrency(currency).setNumber(stripeBalanceTransaction.getNet() / 100d)
                .create();
        
        paypalMonthlyStatementRow = new PayPalMonthlyStatementCSVRow(
                created.toLocalDate(),
                created.toLocalTime(),
                created.getZone(),
                "description", // description
                currency,
                chfAmount,
                chfFee,
                chfNet,
                Money.of(UNKNOWN_NUMERIC_VALUE, currency),
                stripeBalanceTransaction.getId(),
                "", // fromEmailAddress
                "", // name
                "", // bankName
                "", // bankAccount
                Money.of(0, currency), // shippingAndHandlingAmount
                Money.of(UNKNOWN_NUMERIC_VALUE, currency),
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