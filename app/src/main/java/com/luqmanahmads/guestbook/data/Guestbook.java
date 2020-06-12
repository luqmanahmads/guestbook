package com.luqmanahmads.guestbook.data;

import java.util.Date;

public class Guestbook {

    private long guestbookId;
    private String guestbookName;
    private String guestbookDescription;
    private Date createDate;
    private Date modifiedDate;

    public Guestbook(){

    }

    public Guestbook(long guestbookId, String guestbookName, String guestbookDescription, Date createDate, Date modifiedDate){
        this.guestbookId = guestbookId;
        this.guestbookName = guestbookName;
        this.guestbookDescription = guestbookDescription;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
    }

    public void setGuestbookId(long guestbookId) {
        this.guestbookId = guestbookId;
    }

    public void setGuestbookName(String guestbookName) {
        this.guestbookName = guestbookName;
    }

    public void setGuestbookDescription(String guestbookDescription) {
        this.guestbookDescription = guestbookDescription;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }


    public long getGuestbookId() {
        return guestbookId;
    }

    public String getGuestbookName() {
        return guestbookName;
    }

    public String getGuestbookDescription() {
        return guestbookDescription;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }
}
