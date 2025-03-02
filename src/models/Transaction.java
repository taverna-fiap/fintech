package models;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Transaction {

    private String transictionId;
    private String description;
    private Integer amount;
    private final String timestamp;
    private boolean recorrency;
    private boolean isProcessed;

    public Transaction(String transictionId, String description, Integer amount, boolean recorrency) {
        this.transictionId = transictionId;
        this.description = description;
        this.amount = amount;
        this.timestamp = new SimpleDateFormat("dd/MM/yyyy-HHmms").format(Calendar.getInstance().getTime());
        this.recorrency = recorrency;
        this.isProcessed = false;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isRecorrency() {
        return recorrency;
    }

    public void setRecorrency(boolean recorrency) {
        this.recorrency = recorrency;
    }

    public String getTransictionId() {
        return transictionId;
    }

    public void setTransictionId(String transictionId) {
        this.transictionId = transictionId;
    }

    public Integer getAmount() {
        return amount;
    }



}
