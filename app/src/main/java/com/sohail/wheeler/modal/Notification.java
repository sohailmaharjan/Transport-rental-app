package com.sohail.wheeler.modal;

public class Notification {

    private String title;
    private String postedDate;
    private String endDate;
    private String description;

    public Notification(String title, String postedDate, String endDate, String description) {
        this.title = title;
        this.postedDate = postedDate;
        this.endDate = endDate;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(String postedDate) {
        this.postedDate = postedDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        return "tbl_notification [title = " + title + ", description = " + description + "," +
                " startingdate = " + postedDate + ",  enddate = " + endDate + "]";
    }
}
