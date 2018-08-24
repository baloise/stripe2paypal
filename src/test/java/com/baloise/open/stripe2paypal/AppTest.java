/*
 * 
 */
package com.baloise.open.stripe2paypal;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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
        Stripe.apiKey = System.getenv(App.STRIPE_API_KEY);
    }

    /** @throws java.lang.Exception */
    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    /** @throws java.lang.Exception */
    @BeforeEach
    void setUp() throws Exception {
    }

    /** @throws java.lang.Exception */
    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void testBalanceTransactionsNotEmpty() throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("type", "charge");
        params.put("limit", 1);
        BalanceTransactionCollection coll = BalanceTransaction.list(params);
        MatcherAssert.assertThat(coll.getData(), IsCollectionWithSize.hasSize(1));
    }

    @Test
    void testBalanceTransactions() throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("type", "charge");
        Iterable<BalanceTransaction> balIt = BalanceTransaction.list(params).autoPagingIterable(params);
        List<PayPalMonthlyStatementCSVRow> balList = new LinkedList<>();
        int i = 0;
        for (BalanceTransaction t : balIt) {
            balList.add(new BalanceTransactionPayPalMonthlyStatementCSVRowMapping(t)
                    .map().getPaypalMonthlyStatementRow());

            i++;
            if (i == 100) {
                break;
            }
        }

        PayPalMonthlyStatementCSVWriter.write(balList, "target/MSR-stripe-yyyymm.csv");
    }
}
