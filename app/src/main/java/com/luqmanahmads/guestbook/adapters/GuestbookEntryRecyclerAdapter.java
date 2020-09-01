package com.luqmanahmads.guestbook.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.luqmanahmads.guestbook.R;
import com.luqmanahmads.guestbook.data.GuestbookEntry;
import com.luqmanahmads.guestbook.listeners.EntryItemClickListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GuestbookEntryRecyclerAdapter extends RecyclerView.Adapter<GuestbookEntryRecyclerAdapter.GuestbookEntryViewHolder> {

    private List<GuestbookEntry> guestbookEntryList = new ArrayList<GuestbookEntry>();
    private EntryItemClickListener entryItemClickListener;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm | dd MMM YYYY");

    public GuestbookEntryRecyclerAdapter(List<GuestbookEntry> guestbookEntryList, EntryItemClickListener entryItemClickListener){
        this.guestbookEntryList = guestbookEntryList;
        this.entryItemClickListener = entryItemClickListener;
    }

    @NonNull
    @Override
    public GuestbookEntryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.guestbook_entry_item_view, parent, false);
        GuestbookEntryRecyclerAdapter.GuestbookEntryViewHolder vh = new GuestbookEntryRecyclerAdapter.GuestbookEntryViewHolder(view, this.entryItemClickListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull GuestbookEntryViewHolder holder, int position) {
        GuestbookEntry guestbookEntry = guestbookEntryList.get(position);
        holder.guestbookEntry = guestbookEntry;
        holder.txvGuestName.setText(guestbookEntry.getGuestName());
        holder.txvGuestMessage.setText(guestbookEntry.getGuestMessage());
        holder.txvModifiedDate.setText(simpleDateFormat.format(guestbookEntry.getModifiedDate()));

        File imageFile = new File(holder.imvGuestPhoto.getContext().getFilesDir(), guestbookEntry.getGuestPhotoPath());
        Uri imageUri = Uri.fromFile(imageFile);

        Glide.with(holder.imvGuestPhoto.getContext())
                .load(imageUri)
                .placeholder(new ColorDrawable(Color.BLACK))
                .error(R.drawable.ic_launcher_background)
                .into(holder.imvGuestPhoto);
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
        this.notifyDataSetChanged();
    }

    public static class GuestbookEntryViewHolder extends RecyclerView.ViewHolder {

        public GuestbookEntry guestbookEntry = null;
        public LinearLayout lyContainer;
        public TextView txvGuestName;
        public TextView txvGuestMessage;
        public TextView txvModifiedDate;
        public ImageView imvGuestPhoto;


        public GuestbookEntryViewHolder(@NonNull View itemView, final EntryItemClickListener entryItemClickListener) {
            super(itemView);

            lyContainer = itemView.findViewById(R.id.ly_container);
            txvGuestName = itemView.findViewById(R.id.txv_guest_name);
            txvGuestMessage = itemView.findViewById(R.id.txv_guest_message);
            txvModifiedDate = itemView.findViewById(R.id.txv_modified_date);
            imvGuestPhoto = itemView.findViewById(R.id.imv_guest_photo);

            lyContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    entryItemClickListener.onClick(view, guestbookEntry);
                }
            });
        }
    }
}
