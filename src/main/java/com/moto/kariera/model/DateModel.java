package com.moto.kariera.model;

public class DateModel {
    private String dateTo;
    private String dateFrom;

    public DateModel(String dateTo, String dateFrom) {
        this.dateTo = dateTo;
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    @Override
    public String toString() {
        return "DateModel{" +
                "dateTo='" + dateTo + '\'' +
                ", dateFrom='" + dateFrom + '\'' +
                '}';
    }
}
