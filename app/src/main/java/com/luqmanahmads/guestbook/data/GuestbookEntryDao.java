package com.luqmanahmads.guestbook.data;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface GuestbookEntryDao {

    @Insert
    void insertGuestbookEntry(GuestbookEntry guestbookEntry);

    @Update
    void updateGuestbookEntry(GuestbookEntry guestbookEntry);

    @Query("DELETE FROM guestbook_entry WHERE guestbook_entry_id = :guestbookEntryId")
    void deleteGuestbookEntry(long guestbookEntryId);

    @Query("SELECT * FROM guestbook_entry ORDER BY create_date DESC")
    LiveData<List<GuestbookEntry>> getGuestbookEntryList();

    @Query("SELECT * FROM guestbook_entry WHERE guestbook_id = :guestbookId ORDER BY create_date DESC")
    LiveData<List<GuestbookEntry>> getGuestbookEntryListByGuestbookId(long guestbookId);

    @Query("UPDATE guestbook_entry SET guest_name=:guestName, guest_message=:guestMessage, guest_photo_path=:guestPhotoPath, modified_date=:modifiedDateMilis WHERE guestbook_entry_id =:guestbookEntryId")
    void updateGuestbookEntry(long guestbookEntryId, String guestName, String guestMessage, String guestPhotoPath, long modifiedDateMilis);
}
