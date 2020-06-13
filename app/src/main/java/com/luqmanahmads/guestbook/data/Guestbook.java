package com.luqmanahmads.guestbook.data;

import java.util.Calendar;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "guestbook")
public class Guestbook {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="guestbook_id")
    private long guestbookId;

    @ColumnInfo(name="guestbook_name")
    private String guestbookName;

    @ColumnInfo(name="guestbook_description")
    private String guestbookDescription;

    @ColumnInfo(name="create_date")
    private long createDateMilis;

    @ColumnInfo(name="modified_date")
    private long modifiedDateMilis;

    @Ignore
    private Date createDate;

    @Ignore
    private Date modifiedDate;

    public Guestbook(){
        this.createDate = new Date(this.createDateMilis);
        this.modifiedDate = new Date(this.modifiedDateMilis);
    }

    public Guestbook(String guestbookName, String guestbookDescription, Date createDate, Date modifiedDate){
        this.guestbookName = guestbookName;
        this.guestbookDescription = guestbookDescription;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
        this.createDateMilis = this.createDate.getTime();
        this.modifiedDateMilis = this.modifiedDate.getTime();
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

    public void setCreateDateMilis(long createDateMilis) {
        this.createDate.setTime(createDateMilis);
        this.createDateMilis = createDateMilis;
    }

    public void setModifiedDateMilis(long modifiedDateMilis) {
        this.modifiedDate.setTime(modifiedDateMilis);
        this.modifiedDateMilis = modifiedDateMilis;
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
        createDate.setTime(this.createDateMilis);
        return createDate;
    }

    public Date getModifiedDate() {
        modifiedDate.setTime(this.modifiedDateMilis);
        return modifiedDate;
    }

    public long getCreateDateMilis() {
        return createDateMilis;
    }

    public long getModifiedDateMilis() {
        return modifiedDateMilis;
    }
}
