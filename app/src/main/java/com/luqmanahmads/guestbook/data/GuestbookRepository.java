package com.luqmanahmads.guestbook.data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import androidx.lifecycle.MutableLiveData;

public class GuestbookRepository {

    private static GuestbookRepository instance;

    // Pretend as data source
    private List<Guestbook> guestbookList = new ArrayList<Guestbook>(getDefaultGuestbookList());

    public static GuestbookRepository getInstance(){

        if(instance == null){
            instance = new GuestbookRepository();
        }
        return instance;
    }

    public MutableLiveData<List<Guestbook>> getGuestbookList(){
        MutableLiveData<List<Guestbook>> data = new MutableLiveData<List<Guestbook>>();
        data.setValue(this.guestbookList);
        return data;
    }

    public void addGuestbook(Guestbook guestbook){
        this.guestbookList.add(guestbook);
    }


    private List<Guestbook> getDefaultGuestbookList(){
        List<Guestbook> gbList = new ArrayList<Guestbook>();

        Guestbook gb = new Guestbook(
                Calendar.getInstance().getTime().getTime(),
                "Happy Family Hospital Guestbook",
                "This is the default guestbook for logging the guest name of our famous Happy Hospital Family",
                Calendar.getInstance().getTime(),
                Calendar.getInstance().getTime());

        gbList.add(gb);

        gb = new Guestbook(
                Calendar.getInstance().getTime().getTime()+1,
                "Delisioso Restaurant Guestbook",
                "This is the default guestbook for Delisioso Restaurant",
                Calendar.getInstance().getTime(),
                Calendar.getInstance().getTime());

        gbList.add(gb);

        return gbList;
    }

    public void updateGuestbook(long guestbookId, String guestbookName, String guestbookDescription) {
        for(Guestbook gb : guestbookList){
            if(gb.getGuestbookId() == guestbookId){
                gb.setGuestbookName(guestbookName);
                gb.setGuestbookDescription(guestbookDescription);
                break;
            }
        }
    }
}
