package com.mad.lifeapp.dto.request;

public class WeightQueryRequest {
    private int startDay;
    private int endDay;
    private int month;
    private int year;

    // Constructor
    public WeightQueryRequest(int startDay, int endDay, int month, int year) {
        this.startDay = startDay;
        this.endDay = endDay;
        this.month = month;
        this.year = year;
    }
    // Getters and Setters
    public int getStartDay() {
        return startDay;
    }

    public void setStartDay(int startDay) {
        this.startDay = startDay;
    }

    public int getEndDay() {
        return endDay;
    }

    public void setEndDay(int endDay) {
        this.endDay = endDay;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
