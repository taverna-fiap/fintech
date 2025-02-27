package Models;

public class Tag {

    private String tagId;
    private String tagName;

    public String getTypeId() {
        return tagId;
    }

    public void setTypeId(String typeId) {
        this.tagId = typeId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Tag(String typeId, String tagName) {
        this.tagId = typeId;
        this.tagName = tagName;
    }
}

