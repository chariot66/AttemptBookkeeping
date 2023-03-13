package com.example.attemptbookkeeping.DetailPage;

public class notelog {
    private String time;
    private String type;
    private int amount;

    public int getAmount() {
        return amount;
    }

    public String getTime() {
        return time;
    }

    public String getType() {
        return type;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setType(String type) {
        this.type = type;
    }
}