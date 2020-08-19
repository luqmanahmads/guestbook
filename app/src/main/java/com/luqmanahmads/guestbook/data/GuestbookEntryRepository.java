package com.luqmanahmads.guestbook.data;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class GuestbookEntryRepository {

    private GuestbookEntryDao guestbookEntryDao;
    private List<GuestbookEntry> guestbookEntries = new ArrayList<GuestbookEntry>();

    public GuestbookEntryRepository(Application application){
        GuestbookRoomDatabase db = GuestbookRoomDatabase.getDatabase(application);
        guestbookEntryDao = db.guestbookEntryDao();
    }

    public LiveData<List<GuestbookEntry>> getListGuestbookEntryByGuestbookId(long guestbookId){
        return guestbookEntryDao.getGuestbookEntryListByGuestbookId(guestbookId);
    }

    public LiveData<List<GuestbookEntry>> getListGuestbookEntry(){
        return guestbookEntryDao.getGuestbookEntryList();
    }

    public void addGuestbookEntry(final GuestbookEntry guestbookEntry){
        GuestbookRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                guestbookEntryDao.insertGuestbookEntry(guestbookEntry);
            }
        });
    }


}
