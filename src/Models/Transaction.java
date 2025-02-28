package Models;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Transaction {

    private String transictionId;
    private String description;
    private String value;
    private String timestamp;
    private boolean recorrency;

    public Transaction(String transictionId, String description, String value, boolean recorrency) {
        this.transictionId = transictionId;
        this.description = description;
        this.value = value;
        this.timestamp = new SimpleDateFormat("dd/MM/yyyy-HHmms").format(Calendar.getInstance().getTime());
        this.recorrency = recorrency;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}
