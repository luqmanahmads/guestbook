package com.luqmanahmads.guestbook.viewmodels;

import android.util.Log;

import com.luqmanahmads.guestbook.data.Guestbook;
import com.luqmanahmads.guestbook.data.GuestbookRepository;

import java.util.Calendar;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GuestbookListViewModel extends ViewModel {

    private GuestbookRepository repoGuestbook;
    private MutableLiveData<List<Guestbook>> ldGuestbookList;

    public GuestbookListViewModel (){
        this.repoGuestbook = new GuestbookRepository();
        this.ldGuestbookList = repoGuestbook.getGuestbookList();
    }

    public LiveData<List<Guestbook>> getGuestbookList(){
        return this.ldGuestbookList;
    }

    public void addGuestbook(String guestbookName, String guestbookDescription){
        Guestbook gb = new Guestbook(Calendar.getInstance().getTime().getTime(), guestbookName, guestbookDescription, Calendar.getInstance().getTime(), Calendar.getInstance().getTime());

        Log.d("BEFORE ADD GB", printLdGuestbookList());

        repoGuestbook.addGuestbook(gb);

        Log.d("AFTER ADD GB", printLdGuestbookList());

        //this.ldGuestbookList.setValue(repoGuestbook.getGuestbookList().getValue());
    }

    public void updateGuestbook(long guestbookId, String guestbookName, String guestbookDescription) {
        repoGuestbook.updateGuestbook(guestbookId,  guestbookName, guestbookDescription);
        //this.ldGuestbookList.setValue(repoGuestbook.getGuestbookList().getValue());
    }

    private String printLdGuestbookList(){
        String output = "\n";
        for(Guestbook gb : ldGuestbookList.getValue()){
            output += gb.getGuestbookName()+"\n";
        }
        return output;
    }
}
