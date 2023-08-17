package com.example.errorhandling.model;

public class CategoryDTO {
    private String categoryName;

    public CategoryDTO() {
    }

    public String getCategoryName() {
        return categoryName;
    }

    public CategoryDTO setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }
}
