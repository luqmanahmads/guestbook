package com.luqmanahmads.guestbook.data;

import java.util.Date;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "guestbook_entry", foreignKeys = @ForeignKey(entity=Guestbook.class, parentColumns = "guestbook_id", childColumns = "guestbook_id", onDelete = ForeignKey.CASCADE))
public class GuestbookEntry {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="guestbook_entry_id")
    private long guestbookEntryId;

    @ColumnInfo(name="guestbook_id")
    private long guestbookId;

    @ColumnInfo(name="guest_name")
    private String guestName;

    @ColumnInfo(name="guest_message")
    private String guestMessage;

    @ColumnInfo(name="guest_photo_path")
    private String guestPhotoPath;

    @ColumnInfo(name="create_date")
    private long createDateMilis;

    @ColumnInfo(name="modified_date")
    private long modifiedDateMilis;

    @Ignore
    private Date createDate;

    @Ignore
    private Date modifiedDate;

    public GuestbookEntry(){
        this.createDate = new Date(this.createDateMilis);
        this.modifiedDate = new Date(this.modifiedDateMilis);
    }

    public GuestbookEntry(long guestbookId, String guestName, String guestMessage, String guestPhotoPath, Date createDate, Date modifiedDate){
        this.guestbookId = guestbookId;
        this.guestName = guestName;
        this.guestMessage = guestMessage;
        this.guestPhotoPath = guestPhotoPath;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
        this.createDateMilis = this.createDate.getTime();
        this.modifiedDateMilis = this.modifiedDate.getTime();
    }

    public long getGuestbookEntryId() {
        return guestbookEntryId;
    }

    public void setGuestbookEntryId(long guestbookEntryId) {
        this.guestbookEntryId = guestbookEntryId;
    }

    public long getGuestbookId() {
        return guestbookId;
    }

    public void setGuestbookId(long guestbookId) {
        this.guestbookId = guestbookId;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getGuestMessage() {
        return guestMessage;
    }

    public void setGuestMessage(String guestMessage) {
        this.guestMessage = guestMessage;
    }

    public String getGuestPhotoPath() {
        return guestPhotoPath;
    }

    public void setGuestPhotoPath(String guestPhotoPath) {
        this.guestPhotoPath = guestPhotoPath;
    }

    public long getCreateDateMilis() {
        return createDateMilis;
    }

    public void setCreateDateMilis(long createDateMilis) {
        this.createDate.setTime(createDateMilis);
        this.createDateMilis = createDateMilis;
    }

    public long getModifiedDateMilis() {
        return modifiedDateMilis;
    }

    public void setModifiedDateMilis(long modifiedDateMilis) {
        this.modifiedDate.setTime(modifiedDateMilis);
        this.modifiedDateMilis = modifiedDateMilis;
    }

    public Date getCreateDate() {
        createDate.setTime(this.createDateMilis);
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifiedDate() {
        modifiedDate.setTime(this.modifiedDateMilis);
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
