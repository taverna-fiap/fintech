public class Transaction {

    private String transictionId;
    private String description;
    private String value;
    private String date;
    private boolean recorrency;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public Transaction(String transictionId, String description, String value, String date, boolean recorrency) {

        this.transictionId = transictionId;
        this.description = description;
        this.value = value;
        this.date = date;
        this.recorrency = recorrency;
    }
}
