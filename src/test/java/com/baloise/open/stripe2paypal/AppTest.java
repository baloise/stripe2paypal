/*
 * 
 */
package com.baloise.open.stripe2paypal;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.stripe.Stripe;
import com.stripe.model.BalanceTransaction;
import com.stripe.model.BalanceTransactionCollection;

/**
 * @author Markus Tiede
 */
class AppTest {
    /**
     * @throws java.lang.Exception
     */
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        Stripe.apiKey = System.getenv(App.STRIPE_API_KEY);
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @BeforeEach
    void setUp() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
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
        Iterable<BalanceTransaction> list = BalanceTransaction.list(params).autoPagingIterable(params);

        int i = 0;
        for (BalanceTransaction t : list) {
            ZonedDateTime created = Instant.ofEpochSecond(t.getCreated()).atZone(ZoneOffset.UTC);
            System.out.println(created.toLocalDate() + " " + created.toLocalTime());
            System.out.println(t.getDescription());
            System.out.println(t.getCurrency().toUpperCase());
            System.out.println(t.getAmount());
            System.out.println(-1 * t.getFee());
            System.out.println(t.getNet());
            System.out.println(t.getId());

            System.out.println();
            i++;
            if (i == 1) {
                break;
            }
        }
    }
}
