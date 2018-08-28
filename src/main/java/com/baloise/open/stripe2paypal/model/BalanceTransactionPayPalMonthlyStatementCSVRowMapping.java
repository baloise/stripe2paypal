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

    private BalanceTransaction stripeTransaction;
    private PayPalMonthlyStatementCSVRow paypalMonthlyStatementRow;

    /**
     * @param stripeTransaction
     */
    public BalanceTransactionPayPalMonthlyStatementCSVRowMapping(BalanceTransaction stripeTransaction) {
        super();
        this.stripeTransaction = stripeTransaction;
    }

    public BalanceTransactionPayPalMonthlyStatementCSVRowMapping map() throws StripeException {
        String currencyCode = StringUtils.upperCase(stripeTransaction.getCurrency());
        CurrencyUnit currency = Monetary.getCurrency(currencyCode);

        MonetaryAmountFactory<?> amountFac = Monetary.getDefaultAmountFactory();
        
        MonetaryAmount amount = amountFac.setCurrency(currency)
                .setNumber(stripeTransaction.getAmount() / 100d).create();
        MonetaryAmount fee = amountFac.setCurrency(currency)
                .setNumber(stripeTransaction.getFee() / 100d).create();
        MonetaryAmount net = amountFac.setCurrency(currency)
                .setNumber(stripeTransaction.getNet() / 100d).create();

        ZonedDateTime created = Instant.ofEpochSecond(stripeTransaction.getCreated())
                .atZone(ZoneOffset.systemDefault());
        paypalMonthlyStatementRow = new PayPalMonthlyStatementCSVRow(created.toLocalDate(), created.toLocalTime(),
                created.getZone(), "description", // description
                currency, 
                amount, 
                fee, 
                net, 
                Money.of(UNKNOWN_NUMERIC_VALUE, currency), 
                stripeTransaction.getId(), 
                "", // fromEmailAddress
                "", // name
                "", // bankName
                "", // bankAccount
                Money.of(0, currency), // shippingAndHandlingAmount
                Money.of(UNKNOWN_NUMERIC_VALUE, currency),
                Charge.retrieve(stripeTransaction.getSource()).getMetadata().get("Policy"),
                stripeTransaction.getSource());

        return this;
    }

    /**
     * @return the stripeBalanceTransaction
     */
    public BalanceTransaction getStripeBalanceTransaction() {
        return stripeTransaction;
    }

    /**
     * @return the paypalMonthlyStatementRow
     */
    public PayPalMonthlyStatementCSVRow getPaypalMonthlyStatementRow() {
        return paypalMonthlyStatementRow;
    }
}