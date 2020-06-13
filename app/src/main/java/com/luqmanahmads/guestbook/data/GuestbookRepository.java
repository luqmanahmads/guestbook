package com.luqmanahmads.guestbook.data;

import android.app.Application;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class GuestbookRepository {

    private GuestbookDao guestbookDao;
    private LiveData<List<Guestbook>> guestbookList;

    public GuestbookRepository(Application application){
        GuestbookRoomDatabase db = GuestbookRoomDatabase.getDatabase(application);
        guestbookDao = db.guestbookDao();
        guestbookList = guestbookDao.getGuestbookList();
    }

    public LiveData<List<Guestbook>> getGuestbookList(){
        return guestbookList;
    }

    public LiveData<Guestbook> getGuestbook(long guestbookId){
        return guestbookDao.getGuestbook(guestbookId);
    }

    public void addGuestbook(final Guestbook guestbook){
        GuestbookRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                guestbookDao.insertGuestbook(guestbook);
            }
        });
    }

    public void updateGuestbook(final Guestbook guestbook) {
        GuestbookRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                guestbookDao.updateGuestbook(guestbook);
            }
        });
    }

    public void updateGuestbook(final long guestbookId, final String guestbookName, final String guestbookDescription) {
        GuestbookRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                guestbookDao.updateGuestbook(guestbookId, guestbookName, guestbookDescription, Calendar.getInstance().getTimeInMillis());
            }
        });
    }
}
