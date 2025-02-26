package br.com.moneysense.Category;

public class Category {

    private String categoryId;
    private String name;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category (String categoryId, String name) {

        this.categoryId = categoryId;
        this.name = name;
    }
}
