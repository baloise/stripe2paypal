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
package com.baloise.open.stripe2paypal.report;

import java.io.File;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.baloise.open.stripe2paypal.csv.PayPalMonthlyStatementCSVWriter;
import com.baloise.open.stripe2paypal.model.BalanceTransactionPayPalMonthlyStatementCSVRowMapping;
import com.baloise.open.stripe2paypal.model.PayPalMonthlyStatementCSVRow;
import com.stripe.model.BalanceTransaction;

/**
 * @author Markus Tiede
 */
public class PaypalMonthlyReportFromStripe implements Report {
    private static final String FILE_NAME_DATE_AND_TIME_FORMAT = "uuuuMMdd_HHmmss";
    
    private LocalDateTime reportStartDateAndTime;
    private Duration reportDuration;
    private Path outputPath;

    public PaypalMonthlyReportFromStripe(Path outputPath, String startTime, String duration) {
        this.outputPath = outputPath;
        this.reportStartDateAndTime = LocalDateTime.parse(startTime);
        this.reportDuration = Duration.parse(duration);
    }

    @Override
    public void create() throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("type", "charge");

        Map<String, Object> createdParams = new HashMap<String, Object>();

        ZonedDateTime start = getReportStartDateAndTime().atZone(ZoneOffset.UTC);
        ZonedDateTime end = getReportStartDateAndTime().plus(getReportDuration()).atZone(ZoneOffset.UTC);

        if (!start.isBefore(end)) {
            ZonedDateTime tmp = start;
            start = end;
            end = tmp;
        }
        
        createdParams.put("gte", start.toEpochSecond());
        createdParams.put("lt", end.toEpochSecond());
        params.put("created", createdParams);

        Iterable<BalanceTransaction> balIt = BalanceTransaction.list(params).autoPagingIterable(params);
        List<PayPalMonthlyStatementCSVRow> balList = new LinkedList<>();
        for (BalanceTransaction t : balIt) {
            balList.add(new BalanceTransactionPayPalMonthlyStatementCSVRowMapping(t)
                        .map().getPaypalMonthlyStatementRow());
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                PaypalMonthlyReportFromStripe.FILE_NAME_DATE_AND_TIME_FORMAT);
        PayPalMonthlyStatementCSVWriter.write(balList, new File(getOutputPath().toFile(),
                "MSR-stripe-s" + start.format(formatter) + "-e" + end.format(formatter) + ".csv"));
    }

    /**
     * @return the reportStartDateAndTime
     */
    public LocalDateTime getReportStartDateAndTime() {
        return reportStartDateAndTime;
    }

    /**
     * @param reportStartDateAndTime the reportStartDateAndTime to set
     */
    public void setReportStartDateAndTime(LocalDateTime reportStartDateAndTime) {
        this.reportStartDateAndTime = reportStartDateAndTime;
    }

    /**
     * @return the reportDuration
     */
    public Duration getReportDuration() {
        return reportDuration;
    }

    /**
     * @param reportDuration the reportDuration to set
     */
    public void setReportDuration(Duration reportDuration) {
        this.reportDuration = reportDuration;
    }

    /**
     * @return the outputPath
     */
    public Path getOutputPath() {
        return outputPath;
    }

    /**
     * @param outputPath the outputPath to set
     */
    public void setOutputPath(Path outputPath) {
        this.outputPath = outputPath;
    }
}
