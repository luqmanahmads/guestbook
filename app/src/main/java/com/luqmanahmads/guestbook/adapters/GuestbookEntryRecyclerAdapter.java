package com.luqmanahmads.guestbook.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.luqmanahmads.guestbook.R;
import com.luqmanahmads.guestbook.data.GuestbookEntry;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GuestbookEntryRecyclerAdapter extends RecyclerView.Adapter<GuestbookEntryRecyclerAdapter.GuestbookEntryViewHolder> {

    private List<GuestbookEntry> guestbookEntryList = new ArrayList<GuestbookEntry>();

    public GuestbookEntryRecyclerAdapter(List<GuestbookEntry> guestbookEntryList){
        this.guestbookEntryList = guestbookEntryList;
    }

    @NonNull
    @Override
    public GuestbookEntryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.guestbook_entry_item_view, parent, false);
        GuestbookEntryRecyclerAdapter.GuestbookEntryViewHolder vh = new GuestbookEntryRecyclerAdapter.GuestbookEntryViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull GuestbookEntryViewHolder holder, int position) {
        GuestbookEntry guestbookEntry = guestbookEntryList.get(position);
        holder.txvGuestName.setText(guestbookEntry.getGuestName());
        holder.txvGuestMessage.setText(guestbookEntry.getGuestMessage());
        holder.txvCreateDate.setText(guestbookEntry.getCreateDate().toString());
        holder.txvModifiedDate.setText(guestbookEntry.getModifiedDate().toString());
    }

    @Override
    public int getItemCount() {
        if (guestbookEntryList != null)
            return guestbookEntryList.size();
        else return 0;
    }

    public List<GuestbookEntry> getDataSet(){
        return this.guestbookEntryList;
    }

    public void setDataSet(List<GuestbookEntry> guestbookEntries){
        this.guestbookEntryList = guestbookEntries;
    }

    public static class GuestbookEntryViewHolder extends RecyclerView.ViewHolder {

        public TextView txvGuestName;
        public TextView txvGuestMessage;
        public TextView txvCreateDate;
        public TextView txvModifiedDate;

        public GuestbookEntryViewHolder(@NonNull View itemView) {
            super(itemView);

            txvGuestName = itemView.findViewById(R.id.txv_guest_name);
            txvGuestMessage = itemView.findViewById(R.id.txv_guest_message);
            txvCreateDate = itemView.findViewById(R.id.txv_create_date);
            txvModifiedDate = itemView.findViewById(R.id.txv_modified_date);
        }
    }

}
