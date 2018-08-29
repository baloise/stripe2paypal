/*
 * 
 */
package com.baloise.open.stripe2paypal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;

/**
 * @author Markus Tiede
 */
class PaypalTest {
    private static final String CLIENT_ID = System.getenv(
            App.PAYPAL_CLIENT_ID_KEY_ENV);
    
    private static final String CLIENT_SECRET = System.getenv(
            App.PAYPAL_CLIENT_SECRET_KEY_ENV);

    private static PayPalEnvironment env;

    /** @throws java.lang.Exception */
    @BeforeEach
    void setUpBeforeClass() throws Exception {
        // env = new PayPalEnvironment.Sandbox(CLIENT_ID, CLIENT_SECRET);
    }

    @Test
    @Disabled
    void testBalanceTransactionsNotEmpty() throws Exception {
        PayPalHttpClient client = new PayPalHttpClient(env);
    }

    @Test
    @Disabled
    void testBalanceTransactionReportCreation() throws Exception {
    }
}