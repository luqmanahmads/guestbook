package com.luqmanahmads.guestbook.data;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface GuestbookDao {

    @Insert
    void insertGuestbook(Guestbook guestbook);

    @Update
    void updateGuestbook(Guestbook guestbook);

    @Query("UPDATE guestbook SET guestbook_name = :guestbookName, guestbook_description = :guestbookDescription, modified_date = :modifiedDateMilis WHERE guestbook_id = :guestbookId")
    void updateGuestbook(long guestbookId, String guestbookName, String guestbookDescription, long modifiedDateMilis);

    @Query("SELECT * FROM guestbook ORDER BY create_date DESC")
    LiveData<List<Guestbook>> getGuestbookList();

    @Query("DELETE FROM guestbook")
    void deleteAll();

    @Query("DELETE FROM guestbook WHERE guestbook_id = :guestbookId")
    void deleteGuestbook(long guestbookId);

    @Query("SELECT * FROM guestbook WHERE guestbook_id = :guestbookId")
    LiveData<Guestbook> getGuestbook(long guestbookId);

}
