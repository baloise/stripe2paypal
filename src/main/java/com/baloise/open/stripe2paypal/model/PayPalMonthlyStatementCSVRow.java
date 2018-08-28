/*
 * 
 */
package com.baloise.open.stripe2paypal.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Locale;

import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;
import javax.money.format.AmountFormatQueryBuilder;
import javax.money.format.MonetaryAmountFormat;
import javax.money.format.MonetaryFormats;

import org.apache.commons.lang3.StringUtils;
import org.javamoney.moneta.format.CurrencyStyle;

/**
 * @author Markus Tiede
 * 
 * according to {@link https://www.paypalobjects.com/webstatic/en_US/developer/docs/pdf/PP_GenMonthlyStatementReport.pdf}
 */
public class PayPalMonthlyStatementCSVRow {
    public static final String MONEY_FORMAT_PATTERN = "##########0.##";
    public static final MonetaryAmountFormat MONEY_FORMAT = MonetaryFormats.getAmountFormat(AmountFormatQueryBuilder.
            of(Locale.getDefault()).set(CurrencyStyle.NUMERIC_CODE)
                .set("pattern", MONEY_FORMAT_PATTERN).build());
    
    public static final String COMPLETION_DATE_COLUMN_NAME = "Date";
    public static final String COMPLETION_DATE_FORMAT = "MM/dd/uuuu";
    public static final byte COMPLETION_DATE_MAX_LENGTH = 10;
    
    public static final String COMPLETION_TIME_COLUMN_NAME = "Time";
    public static final String COMPLETION_TIME_FORMAT = "HH:mm:ss";
    public static final byte COMPLETION_TIME_MAX_LENGTH = 8;
    
    public static final String TIME_ZONE_COLUMN_NAME = "Time Zone";
    public static final byte TIME_ZONE_MAX_LENGTH = 32;

    public static final String DESCRIPTION_COLUMN_NAME = "Description";
    public static final short DESCRIPTION_MAX_LENGTH = 1024;

    public static final String CURRENCY_COLUMN_NAME = "Currency";
    public static final byte CURRENCY_MAX_LENGTH = 3;

    public static final String GROSS_COLUMN_NAME = "Gross";
    public static final byte GROSS_MAX_LENGTH = 26;
    
    public static final String FEE_COLUMN_NAME = "Fee";
    public static final byte FEE_MAX_LENGTH = 26;
    
    public static final String NET_COLUMN_NAME = "Net";
    public static final byte NET_MAX_LENGTH = 26;
    
    public static final String BALANCE_COLUMN_NAME = "Balance";
    public static final byte BALANCE_MAX_LENGTH = 26;
    
    // SPEC states max length of 24; but STRIPE one is longer!
    public static final String TRANSACTION_ID_COLUMN_NAME = "Transaction ID";
    public static final byte TRANSACTION_ID_MAX_LENGTH = 28;
    
    public static final String FROM_EMAIL_ADDRESS_COLUMN_NAME = "From Email Address";
    public static final short FROM_EMAIL_ADDRESS_MAX_LENGTH = 200;
    
    public static final String NAME_COLUMN_NAME = "Name";
    public static final short NAME_MAX_LENGTH = 200;
    
    public static final String BANK_NAME_COLUMN_NAME = "Bank Name";
    public static final byte BANK_NAME_MAX_LENGTH = 64;
    
    public static final String BANK_ACCOUNT_COLUMN_NAME = "Bank Account";
    public static final byte BANK_ACCOUNT_MAX_LENGTH = 4;
    
    public static final String SHIPPING_AND_HANDLING_AMOUNT_COLUMN_NAME = "Shipping and Handling Amount";
    public static final byte SHIPPING_AND_HANDLING_AMOUNT_MAX_LENGTH = 26;
    
    public static final String SALES_TAX_COLUMN_NAME = "Sales Tax";
    public static final byte SALES_TAX_MAX_LENGTH = 26;
    
    public static final String INVOICE_ID_COLUMN_NAME = "Invoice ID";
    public static final byte INVOICE_ID_MAX_LENGTH = 127;
    
    public static final String REFERENCE_TXN_ID_COLUMN_NAME = "Reference Txn ID";
    public static final byte REFERENCE_TXN_ID_MAX_LENGTH = 64;

    private LocalDate completionDate;
    private LocalTime completionTime;
    private ZoneId timeZone;
    private String description;
    private CurrencyUnit currency;
    private MonetaryAmount gross;
    private MonetaryAmount fee;
    private MonetaryAmount net;
    private MonetaryAmount balance;
    private String transactionID;
    private String fromEmailAddress;
    private String name;
    private String bankName;
    private String bankAccount;
    private MonetaryAmount shippingAndHandlingAmount;
    private MonetaryAmount salesTax;
    private String invoiceId;
    private String referenceTxnId;
    
    public static final String[] FIELD_MAPPING = new String[] { 
            "completionDate", // 
            "completionTime", // 
            "timeZoneFmt", // 
            "description", // 
            "currencyFmt", // 
            "grossFmt", // 
            "feeFmt", // 
            "netFmt", // 
            "balanceFmt", // 
            "transactionID", // 
            "fromEmailAddress", // 
            "name", // 
            "bankName", // 
            "bankAccount", // 
            "shippingAndHandlingAmountFmt", // 
            "salesTaxFmt", // 
            "invoiceId", // 
            "referenceTxnId"};// 

    /**
     * @param completionDate
     * @param completionTime
     * @param timeZone
     * @param description
     * @param currency
     * @param gross
     * @param fee
     * @param net
     * @param balance
     * @param transactionID
     * @param fromEmailAddress
     * @param name
     * @param bankName
     * @param bankAccount
     * @param shippingAndHandlingAmount
     * @param salesTax
     * @param invoiceId
     * @param referenceTxnId
     */
    public PayPalMonthlyStatementCSVRow(LocalDate completionDate, LocalTime completionTime, ZoneId timeZone,
            String description, CurrencyUnit currency, MonetaryAmount gross, MonetaryAmount fee, MonetaryAmount net, MonetaryAmount balance, String transactionID,
            String fromEmailAddress, String name, String bankName, String bankAccount, MonetaryAmount shippingAndHandlingAmount,
            MonetaryAmount salesTax, String invoiceId, String referenceTxnId) {
        super();
        this.completionDate = completionDate;
        this.completionTime = completionTime;
        this.timeZone = timeZone;
        this.description = description;
        this.currency = currency;
        this.gross = gross;
        this.fee = fee;
        this.net = net;
        this.balance = balance;
        this.transactionID = transactionID;
        this.fromEmailAddress = fromEmailAddress;
        this.name = name;
        this.bankName = bankName;
        this.bankAccount = bankAccount;
        this.shippingAndHandlingAmount = shippingAndHandlingAmount;
        this.salesTax = salesTax;
        this.invoiceId = invoiceId;
        this.referenceTxnId = referenceTxnId;
    }

    /**
     * @return the completionDate
     */
    public LocalDate getCompletionDate() {
        return completionDate;
    }

    /**
     * @return the completionTime
     */
    public LocalTime getCompletionTime() {
        return completionTime;
    }

    /**
     * @return the timeZone
     */
    public ZoneId getTimeZone() {
        return timeZone;
    }

    /**
     * @return the timeZone formatted for CSV
     */
    public String getTimeZoneFmt() {
        return StringUtils.abbreviate(getTimeZone().toString(),
                PayPalMonthlyStatementCSVRow.TIME_ZONE_MAX_LENGTH);
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the currency
     */
    public CurrencyUnit getCurrency() {
        return currency;
    }

    /**
     * @return the currency formatted for CSV
     */
    public String getCurrencyFmt() {
        return StringUtils.upperCase(getCurrency().getCurrencyCode());
    }

    /**
     * @return the gross
     */
    public MonetaryAmount getGross() {
        return gross;
    }

    /**
     * @return the gross formatted for CSV
     */
    public String getGrossFmt() {
        return MONEY_FORMAT.format(getGross());
    }

    /**
     * @return the fee
     */
    public MonetaryAmount getFee() {
        return fee;
    }

    /**
     * @return the fee formatted for CSV
     */
    public String getFeeFmt() {
        return MONEY_FORMAT.format(getFee().negate());
    }

    /**
     * @return the net
     */
    public MonetaryAmount getNet() {
        return net;
    }

    /**
     * @return the net formatted for CSV
     */
    public String getNetFmt() {
        return MONEY_FORMAT.format(getNet());
    }

    /**
     * @return the balance
     */
    public MonetaryAmount getBalance() {
        return balance;
    }

    /**
     * @return the balance formatted for CSV
     */
    public String getBalanceFmt() {
        return MONEY_FORMAT.format(getBalance());
    }

    /**
     * @return the transactionID
     */
    public String getTransactionID() {
        return transactionID;
    }

    /**
     * @return the fromEmailAddress
     */
    public String getFromEmailAddress() {
        return fromEmailAddress;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the bankName
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * @return the bankAccount
     */
    public String getBankAccount() {
        return bankAccount;
    }

    /**
     * @return the shippingAndHandlingAmount
     */
    public MonetaryAmount getShippingAndHandlingAmount() {
        return shippingAndHandlingAmount;
    }

    /**
     * @return the shippingAndHandlingAmount formatted for CSV
     */
    public String getShippingAndHandlingAmountFmt() {
        return MONEY_FORMAT.format(getShippingAndHandlingAmount());
    }

    /**
     * @return the salesTax
     */
    public MonetaryAmount getSalesTax() {
        return salesTax;
    }

    /**
     * @return the salesTax formatted for CSV
     */
    public String getSalesTaxFmt() {
        return MONEY_FORMAT.format(getSalesTax());
    }

    /**
     * @return the invoiceId
     */
    public String getInvoiceId() {
        return invoiceId;
    }

    /**
     * @return the referenceTxnId
     */
    public String getReferenceTxnId() {
        return referenceTxnId;
    }

    
    
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((balance == null) ? 0 : balance.hashCode());
        result = prime * result + ((bankAccount == null) ? 0 : bankAccount.hashCode());
        result = prime * result + ((bankName == null) ? 0 : bankName.hashCode());
        result = prime * result + ((completionDate == null) ? 0 : completionDate.hashCode());
        result = prime * result + ((completionTime == null) ? 0 : completionTime.hashCode());
        result = prime * result + ((currency == null) ? 0 : currency.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((fee == null) ? 0 : fee.hashCode());
        result = prime * result + ((fromEmailAddress == null) ? 0 : fromEmailAddress.hashCode());
        result = prime * result + ((gross == null) ? 0 : gross.hashCode());
        result = prime * result + ((invoiceId == null) ? 0 : invoiceId.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((net == null) ? 0 : net.hashCode());
        result = prime * result + ((referenceTxnId == null) ? 0 : referenceTxnId.hashCode());
        result = prime * result + ((salesTax == null) ? 0 : salesTax.hashCode());
        result = prime * result + ((shippingAndHandlingAmount == null) ? 0 : shippingAndHandlingAmount.hashCode());
        result = prime * result + ((timeZone == null) ? 0 : timeZone.hashCode());
        result = prime * result + ((transactionID == null) ? 0 : transactionID.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PayPalMonthlyStatementCSVRow other = (PayPalMonthlyStatementCSVRow) obj;
        if (balance == null) {
            if (other.balance != null)
                return false;
        } else if (!balance.equals(other.balance))
            return false;
        if (bankAccount == null) {
            if (other.bankAccount != null)
                return false;
        } else if (!bankAccount.equals(other.bankAccount))
            return false;
        if (bankName == null) {
            if (other.bankName != null)
                return false;
        } else if (!bankName.equals(other.bankName))
            return false;
        if (completionDate == null) {
            if (other.completionDate != null)
                return false;
        } else if (!completionDate.equals(other.completionDate))
            return false;
        if (completionTime == null) {
            if (other.completionTime != null)
                return false;
        } else if (!completionTime.equals(other.completionTime))
            return false;
        if (currency == null) {
            if (other.currency != null)
                return false;
        } else if (!currency.equals(other.currency))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (fee == null) {
            if (other.fee != null)
                return false;
        } else if (!fee.equals(other.fee))
            return false;
        if (fromEmailAddress == null) {
            if (other.fromEmailAddress != null)
                return false;
        } else if (!fromEmailAddress.equals(other.fromEmailAddress))
            return false;
        if (gross == null) {
            if (other.gross != null)
                return false;
        } else if (!gross.equals(other.gross))
            return false;
        if (invoiceId == null) {
            if (other.invoiceId != null)
                return false;
        } else if (!invoiceId.equals(other.invoiceId))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (net == null) {
            if (other.net != null)
                return false;
        } else if (!net.equals(other.net))
            return false;
        if (referenceTxnId == null) {
            if (other.referenceTxnId != null)
                return false;
        } else if (!referenceTxnId.equals(other.referenceTxnId))
            return false;
        if (salesTax == null) {
            if (other.salesTax != null)
                return false;
        } else if (!salesTax.equals(other.salesTax))
            return false;
        if (shippingAndHandlingAmount == null) {
            if (other.shippingAndHandlingAmount != null)
                return false;
        } else if (!shippingAndHandlingAmount.equals(other.shippingAndHandlingAmount))
            return false;
        if (timeZone == null) {
            if (other.timeZone != null)
                return false;
        } else if (!timeZone.equals(other.timeZone))
            return false;
        if (transactionID == null) {
            if (other.transactionID != null)
                return false;
        } else if (!transactionID.equals(other.transactionID))
            return false;
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "PayPalMonthlyStatementCSVRow [completionDate=" + completionDate + ", completionTime=" + completionTime
                + ", timeZone=" + timeZone + ", description=" + description + ", currency=" + currency + ", gross="
                + gross + ", fee=" + fee + ", net=" + net + ", balance=" + balance + ", transactionID=" + transactionID
                + ", fromEmailAddress=" + fromEmailAddress + ", name=" + name + ", bankName=" + bankName
                + ", bankAccount=" + bankAccount + ", shippingAndHandlingAmount=" + shippingAndHandlingAmount
                + ", salesTax=" + salesTax + ", invoiceId=" + invoiceId + ", referenceTxnId=" + referenceTxnId + "]";
    }
}
