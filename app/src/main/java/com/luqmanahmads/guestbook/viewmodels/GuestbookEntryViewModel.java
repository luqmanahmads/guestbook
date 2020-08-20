package com.luqmanahmads.guestbook.viewmodels;

import android.app.Application;

import com.luqmanahmads.guestbook.data.GuestbookEntry;
import com.luqmanahmads.guestbook.data.GuestbookEntryRepository;

import java.util.Calendar;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

public class GuestbookEntryViewModel extends AndroidViewModel {

    private GuestbookEntryRepository repoGuestbookEntry;
    private LiveData<List<GuestbookEntry>> ldGuestbookEntryList;
    private long guestbookEntryId = 0;

    public GuestbookEntryViewModel(@NonNull Application application) {
        super(application);

        this.repoGuestbookEntry = new GuestbookEntryRepository(application);
        this.ldGuestbookEntryList = repoGuestbookEntry.getListGuestbookEntry();
    }

    public void initGuestbookEntryList(long guestbookId){
        this.ldGuestbookEntryList = repoGuestbookEntry.getListGuestbookEntryByGuestbookId(guestbookId);
    }

    public LiveData<List<GuestbookEntry>> getLdGuestbookEntryList(){
        return this.ldGuestbookEntryList;
    }

    public void addGuestbookEntry(long guestbookId, String guestPhotoPath, String guestName, String guestMessage){
        GuestbookEntry guestbookEntry = new GuestbookEntry(
                guestbookId,
                guestName,
                guestMessage,
                guestPhotoPath,
                Calendar.getInstance().getTime(),
                Calendar.getInstance().getTime());
        this.repoGuestbookEntry.addGuestbookEntry(guestbookEntry);
    }

    public void updateGuestbookEntry(long guestbookEntryId, long guestbookId, String guestPhotoPath, String guestName, String guestMessage){
        this.repoGuestbookEntry.updateGuestbookEntry(guestbookEntryId, guestName, guestMessage, guestPhotoPath);
    }

    public void deleteGuestbookEntry(long guestbookEntryId){
        this.repoGuestbookEntry.deleteGuestbookEntry(guestbookEntryId);
    }
}
