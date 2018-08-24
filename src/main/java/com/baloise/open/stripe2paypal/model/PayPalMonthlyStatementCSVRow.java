/*
 * 
 */
package com.baloise.open.stripe2paypal.model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Markus Tiede
 * 
 * according to {@link https://www.paypalobjects.com/webstatic/en_US/developer/docs/pdf/PP_GenMonthlyStatementReport.pdf}
 */
public class PayPalMonthlyStatementCSVRow {
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
    
    public static final String TRANSACTION_ID_COLUMN_NAME = "Transaction ID";
    public static final byte TRANSACTION_ID_MAX_LENGTH = 24;
    
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
    private String timeZone;
    private String description;
    private String currency;
    private long gross;
    private long fee;
    private long net;
    private long balance;
    private String transactionID;
    private String fromEmailAddress;
    private String name;
    private String bankName;
    private String bankAccount;
    private long shippingAndHandlingAmount;
    private long salesTax;
    private String invoiceId;
    private String referenceTxnId;
    
    public static final String[] FIELD_MAPPING = new String[] { 
            "completionDate", // 
            "completionTime", // 
            "timeZone", // 
            "description", // 
            "currency", // 
            "gross", // 
            "fee", // 
            "net", // 
            "balance", // 
            "transactionID", // 
            "fromEmailAddress", // 
            "name", // 
            "bankName", // 
            "bankAccount", // 
            "shippingAndHandlingAmount", // 
            "salesTax", // 
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
    public PayPalMonthlyStatementCSVRow(LocalDate completionDate, LocalTime completionTime, String timeZone,
            String description, String currency, long gross, long fee, long net, long balance, String transactionID,
            String fromEmailAddress, String name, String bankName, String bankAccount, long shippingAndHandlingAmount,
            long salesTax, String invoiceId, String referenceTxnId) {
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
    public String getTimeZone() {
        return timeZone;
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
    public String getCurrency() {
        return currency;
    }

    /**
     * @return the gross
     */
    public long getGross() {
        return gross;
    }

    /**
     * @return the fee
     */
    public long getFee() {
        return fee;
    }

    /**
     * @return the net
     */
    public long getNet() {
        return net;
    }

    /**
     * @return the balance
     */
    public long getBalance() {
        return balance;
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
    public long getShippingAndHandlingAmount() {
        return shippingAndHandlingAmount;
    }

    /**
     * @return the salesTax
     */
    public long getSalesTax() {
        return salesTax;
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

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (balance ^ (balance >>> 32));
        result = prime * result + ((bankAccount == null) ? 0 : bankAccount.hashCode());
        result = prime * result + ((bankName == null) ? 0 : bankName.hashCode());
        result = prime * result + ((completionDate == null) ? 0 : completionDate.hashCode());
        result = prime * result + ((completionTime == null) ? 0 : completionTime.hashCode());
        result = prime * result + ((currency == null) ? 0 : currency.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + (int) (fee ^ (fee >>> 32));
        result = prime * result + ((fromEmailAddress == null) ? 0 : fromEmailAddress.hashCode());
        result = prime * result + (int) (gross ^ (gross >>> 32));
        result = prime * result + ((invoiceId == null) ? 0 : invoiceId.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + (int) (net ^ (net >>> 32));
        result = prime * result + ((referenceTxnId == null) ? 0 : referenceTxnId.hashCode());
        result = prime * result + (int) (salesTax ^ (salesTax >>> 32));
        result = prime * result + (int) (shippingAndHandlingAmount ^ (shippingAndHandlingAmount >>> 32));
        result = prime * result + ((timeZone == null) ? 0 : timeZone.hashCode());
        result = prime * result + ((transactionID == null) ? 0 : transactionID.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
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
        if (balance != other.balance)
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
        if (fee != other.fee)
            return false;
        if (fromEmailAddress == null) {
            if (other.fromEmailAddress != null)
                return false;
        } else if (!fromEmailAddress.equals(other.fromEmailAddress))
            return false;
        if (gross != other.gross)
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
        if (net != other.net)
            return false;
        if (referenceTxnId == null) {
            if (other.referenceTxnId != null)
                return false;
        } else if (!referenceTxnId.equals(other.referenceTxnId))
            return false;
        if (salesTax != other.salesTax)
            return false;
        if (shippingAndHandlingAmount != other.shippingAndHandlingAmount)
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
