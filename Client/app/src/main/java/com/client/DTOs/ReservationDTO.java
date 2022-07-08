package com.client.DTOs;

public class ReservationDTO {
    private int doorNum;
    private int reservationID;
    private int guestCount;
    private int daysToStay;
    private float pricePerDay;
    private boolean hasPaid;
    private String imageURL;
    private String dateFrom;
    private String dateTo;

    public boolean isHasPaid() {
        return hasPaid;
    }

    public void setHasPaid(boolean hasPaid) {
        this.hasPaid = hasPaid;
    }

    public int getDoorNum() {
        return doorNum;
    }

    public void setDoorNum(int doorNum) {
        this.doorNum = doorNum;
    }

    public int getReservationId() {
        return reservationID;
    }

    public void setReservationId(int reservationId) {
        this.reservationID = reservationId;
    }

    public int getGuestCount() {
        return guestCount;
    }

    public void setGuestCount(int guestCount) {
        this.guestCount = guestCount;
    }

    public int getDaysToStay() {
        return daysToStay;
    }

    public void setDaysToStay(int daysToStay) {
        this.daysToStay = daysToStay;
    }

    public float getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(float pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }
}


