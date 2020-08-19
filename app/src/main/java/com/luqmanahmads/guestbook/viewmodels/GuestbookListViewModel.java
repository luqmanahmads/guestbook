package com.luqmanahmads.guestbook.viewmodels;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.luqmanahmads.guestbook.data.Guestbook;
import com.luqmanahmads.guestbook.data.GuestbookRepository;

import java.util.Calendar;
import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GuestbookListViewModel extends AndroidViewModel {

    private GuestbookRepository repoGuestbook;
    private LiveData<List<Guestbook>> ldGuestbookList;

    public GuestbookListViewModel (Application application){
        super(application);
        this.repoGuestbook = new GuestbookRepository(application);
        this.ldGuestbookList = repoGuestbook.getGuestbookList();
    }

    public LiveData<List<Guestbook>> getGuestbookList(){
        return this.ldGuestbookList;
    }

    public void addGuestbook(String guestbookName, String guestbookDescription){
        Guestbook gb = new Guestbook(guestbookName, guestbookDescription, Calendar.getInstance().getTime(), Calendar.getInstance().getTime());
        repoGuestbook.addGuestbook(gb);
    }

    public void updateGuestbook(long guestbookId, String guestbookName, String guestbookDescription) {
        repoGuestbook.updateGuestbook(guestbookId, guestbookName, guestbookDescription);
    }

    public void deleteGuestbook(final long guestbookId){
        repoGuestbook.deleteGuestbook(guestbookId);
    }

}
