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
package com.baloise.open.stripe2paypal.csv;

import java.io.File;
import java.io.FileWriter;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.constraint.StrMinMax;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.cellprocessor.time.FmtLocalDate;
import org.supercsv.cellprocessor.time.FmtLocalTime;
import org.supercsv.io.dozer.CsvDozerBeanWriter;
import org.supercsv.io.dozer.ICsvDozerBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.baloise.open.stripe2paypal.model.PayPalMonthlyStatementCSVRow;

/**
 * @author Markus Tiede
 */
public class PayPalMonthlyStatementCSVWriter {
    public static final int MIN_STRING_LENGTH = 0;

    /**
     * writeToCSV
     */
    public static void write(List<PayPalMonthlyStatementCSVRow> rows, File file) throws Exception {
        final CellProcessor[] processors = new CellProcessor[] {
                new FmtLocalDate(DateTimeFormatter.ofPattern(
                        PayPalMonthlyStatementCSVRow.COMPLETION_DATE_FORMAT),
                            new StrMinMax(MIN_STRING_LENGTH, PayPalMonthlyStatementCSVRow.COMPLETION_DATE_MAX_LENGTH)),
                new FmtLocalTime(DateTimeFormatter.ofPattern(
                        PayPalMonthlyStatementCSVRow.COMPLETION_TIME_FORMAT),
                            new StrMinMax(MIN_STRING_LENGTH, PayPalMonthlyStatementCSVRow.COMPLETION_TIME_MAX_LENGTH)),

                new StrMinMax(MIN_STRING_LENGTH, PayPalMonthlyStatementCSVRow.TIME_ZONE_MAX_LENGTH),
                new Optional(new StrMinMax(MIN_STRING_LENGTH, PayPalMonthlyStatementCSVRow.DESCRIPTION_MAX_LENGTH)),
                new StrMinMax(MIN_STRING_LENGTH, PayPalMonthlyStatementCSVRow.CURRENCY_MAX_LENGTH),
                new StrMinMax(MIN_STRING_LENGTH, PayPalMonthlyStatementCSVRow.GROSS_MAX_LENGTH),
                new StrMinMax(MIN_STRING_LENGTH, PayPalMonthlyStatementCSVRow.FEE_MAX_LENGTH),
                new StrMinMax(MIN_STRING_LENGTH, PayPalMonthlyStatementCSVRow.NET_MAX_LENGTH),
                new StrMinMax(MIN_STRING_LENGTH, PayPalMonthlyStatementCSVRow.BALANCE_MAX_LENGTH),
                new StrMinMax(MIN_STRING_LENGTH, PayPalMonthlyStatementCSVRow.TRANSACTION_ID_MAX_LENGTH),
                new StrMinMax(MIN_STRING_LENGTH, PayPalMonthlyStatementCSVRow.FROM_EMAIL_ADDRESS_MAX_LENGTH),
                new StrMinMax(MIN_STRING_LENGTH, PayPalMonthlyStatementCSVRow.NAME_MAX_LENGTH),
                new StrMinMax(MIN_STRING_LENGTH, PayPalMonthlyStatementCSVRow.BANK_NAME_MAX_LENGTH),
                new StrMinMax(MIN_STRING_LENGTH, PayPalMonthlyStatementCSVRow.BANK_ACCOUNT_MAX_LENGTH),
                new StrMinMax(MIN_STRING_LENGTH, PayPalMonthlyStatementCSVRow.SHIPPING_AND_HANDLING_AMOUNT_MAX_LENGTH),
                new StrMinMax(MIN_STRING_LENGTH, PayPalMonthlyStatementCSVRow.SALES_TAX_MAX_LENGTH),
                new StrMinMax(MIN_STRING_LENGTH, PayPalMonthlyStatementCSVRow.INVOICE_ID_MAX_LENGTH),
                new StrMinMax(MIN_STRING_LENGTH, PayPalMonthlyStatementCSVRow.REFERENCE_TXN_ID_MAX_LENGTH) };

        try (ICsvDozerBeanWriter beanWriter = new CsvDozerBeanWriter(new FileWriter(file),
                CsvPreference.STANDARD_PREFERENCE)) {

            // configure the mapping from the fields to the CSV columns
            beanWriter.configureBeanMapping(PayPalMonthlyStatementCSVRow.class,
                    PayPalMonthlyStatementCSVRow.FIELD_MAPPING);

            // write the header
            beanWriter.writeHeader(
                    PayPalMonthlyStatementCSVRow.COMPLETION_DATE_COLUMN_NAME,
                    PayPalMonthlyStatementCSVRow.COMPLETION_TIME_COLUMN_NAME,
                    PayPalMonthlyStatementCSVRow.TIME_ZONE_COLUMN_NAME,
                    PayPalMonthlyStatementCSVRow.DESCRIPTION_COLUMN_NAME,
                    PayPalMonthlyStatementCSVRow.CURRENCY_COLUMN_NAME, 
                    PayPalMonthlyStatementCSVRow.GROSS_COLUMN_NAME,
                    PayPalMonthlyStatementCSVRow.FEE_COLUMN_NAME, 
                    PayPalMonthlyStatementCSVRow.NET_COLUMN_NAME,
                    PayPalMonthlyStatementCSVRow.BALANCE_COLUMN_NAME,
                    PayPalMonthlyStatementCSVRow.TRANSACTION_ID_COLUMN_NAME,
                    PayPalMonthlyStatementCSVRow.FROM_EMAIL_ADDRESS_COLUMN_NAME,
                    PayPalMonthlyStatementCSVRow.NAME_COLUMN_NAME, 
                    PayPalMonthlyStatementCSVRow.BANK_NAME_COLUMN_NAME,
                    PayPalMonthlyStatementCSVRow.BANK_ACCOUNT_COLUMN_NAME,
                    PayPalMonthlyStatementCSVRow.SHIPPING_AND_HANDLING_AMOUNT_COLUMN_NAME,
                    PayPalMonthlyStatementCSVRow.SALES_TAX_COLUMN_NAME,
                    PayPalMonthlyStatementCSVRow.INVOICE_ID_COLUMN_NAME,
                    PayPalMonthlyStatementCSVRow.REFERENCE_TXN_ID_COLUMN_NAME);

            // write the beans
            for (final PayPalMonthlyStatementCSVRow row : rows) {
                beanWriter.write(row, processors);
            }
        }
    }
}
