/*
 * 
 */
package com.baloise.open.stripe2paypal;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.baloise.open.stripe2paypal.csv.PayPalMonthlyStatementCSVWriter;
import com.baloise.open.stripe2paypal.model.BalanceTransactionPayPalMonthlyStatementCSVRowMapping;
import com.baloise.open.stripe2paypal.model.PayPalMonthlyStatementCSVRow;
import com.stripe.Stripe;
import com.stripe.model.BalanceTransaction;
import com.stripe.model.BalanceTransactionCollection;

/**
 * @author Markus Tiede
 */
class AppTest {
    /** @throws java.lang.Exception */
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        Stripe.apiKey = System.getenv(App.STRIPE_API_KEY_ENV);
    }

    @Test
    void testBalanceTransactionsNotEmpty() throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("limit", 1);
        BalanceTransactionCollection coll = BalanceTransaction.list(params);
        MatcherAssert.assertThat(coll.getData(), IsCollectionWithSize.hasSize(1));
    }

    @Test
    void testBalanceTransactions() throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("type", "charge");

        Map<String, Object> createdParams = new HashMap<String, Object>();
        createdParams.put("gte", ZonedDateTime.now(ZoneOffset.UTC).minusWeeks(4).toEpochSecond());
        createdParams.put("lte", ZonedDateTime.now(ZoneOffset.UTC).minusWeeks(2).toEpochSecond());
        params.put("created", createdParams);
        
        Iterable<BalanceTransaction> balIt = BalanceTransaction.list(params).autoPagingIterable(params);
        List<PayPalMonthlyStatementCSVRow> balList = new LinkedList<>();
        for (BalanceTransaction t : balIt) {
            balList.add(new BalanceTransactionPayPalMonthlyStatementCSVRowMapping(t)
                    .map().getPaypalMonthlyStatementRow());
        }

        PayPalMonthlyStatementCSVWriter.write(balList, "target/MSR-stripe-yyyymm.csv");
    }
}
