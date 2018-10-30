/*
 * Copyright 2018 Baloise Group
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.baloise.open.stripe2paypal;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.baloise.open.stripe2paypal.report.PaypalMonthlyReportFromStripe;
import com.baloise.open.stripe2paypal.report.Report;
import com.stripe.Stripe;
import com.stripe.model.BalanceTransaction;
import com.stripe.model.BalanceTransactionCollection;

/**
 * @author Markus Tiede
 */
class StripeTest {
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
    void testBalanceTransactionReportCreation() throws Exception {
        Report r = new PaypalMonthlyReportFromStripe(
                new File("target/").toPath(), 
                "2018-07-01T00:00:00", 
                "P31D");
        r.create();
    }
}
