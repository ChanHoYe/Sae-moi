package com.three_eung.saemoi.infos;

public class BudgetInfo {
    private String category;
    private long value;

    public BudgetInfo() {}
    public BudgetInfo(String category) {
        this.category = category;
        this.value = 0;
    }
    public BudgetInfo(String category, long value) {
        this.category = category;
        this.value = value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getValue() {
        return value;
    }

    public String getCategory() {
        return category;
    }
}
