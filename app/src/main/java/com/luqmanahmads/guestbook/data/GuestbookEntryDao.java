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
    void updateGuestbook(Guestbook guestbook);

    @Query("DELETE FROM guestbook_entry WHERE guestbook_entry_id = :guestbookEntryId")
    void deleteGuestbook(long guestbookEntryId);

    @Query("SELECT * FROM guestbook_entry ORDER BY create_date DESC")
    LiveData<List<GuestbookEntry>> getGuestbookEntryList();

    @Query("SELECT * FROM guestbook_entry WHERE guestbook_id = :guestbookId ORDER BY create_date DESC")
    LiveData<List<GuestbookEntry>> getGuestbookEntryListByGuestbookId(long guestbookId);
}
