package com.luqmanahmads.guestbook.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.luqmanahmads.guestbook.R;
import com.luqmanahmads.guestbook.data.Guestbook;
import com.luqmanahmads.guestbook.listeners.GuestbookItemClickListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GuestbookRecyclerAdapter extends RecyclerView.Adapter<GuestbookRecyclerAdapter.GuestbookViewHolder> {

    private List<Guestbook> guestbookList = new ArrayList<Guestbook>();
    private GuestbookItemClickListener rcvItemListener;
    private GuestbookItemClickListener entryListener;

    public GuestbookRecyclerAdapter(List<Guestbook> guestbookList, GuestbookItemClickListener rcvItemListener, GuestbookItemClickListener entryListener){
        this.guestbookList = guestbookList;
        this.rcvItemListener = rcvItemListener;
        this.entryListener = entryListener;
    }

    @NonNull
    @Override
    public GuestbookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.guestbook_item_view, parent, false);
        GuestbookViewHolder vh = new GuestbookViewHolder(view, this.rcvItemListener, this.entryListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull GuestbookViewHolder holder, int position) {
        Guestbook gb = guestbookList.get(position);
        holder.guestbook = gb;
        holder.txvGuestbookName.setText(gb.getGuestbookName());
        holder.txvGuestbookDescription.setText(gb.getGuestbookDescription());
        holder.txvModifiedDate.setText("Last modified : "+gb.getModifiedDate().toString());
    }

    @Override
    public int getItemCount() {
        if (guestbookList != null)
            return guestbookList.size();
        else return 0;
    }

    public List<Guestbook> getDataSet(){
        return this.guestbookList;
    }

    public void setDataSet(List<Guestbook> guestbookList){
        this.guestbookList = guestbookList;
        this.notifyDataSetChanged();
    }


    public void setGuestbookList(List<Guestbook> guestbookList){
        this.guestbookList = guestbookList;
    }

    public static class GuestbookViewHolder extends RecyclerView.ViewHolder {

        public Guestbook guestbook = null;
        public TextView txvGuestbookName;
        public TextView txvGuestbookDescription;
        public TextView txvModifiedDate;
        public LinearLayout lyContainer;
        public LinearLayout lyGuestbook;
        public LinearLayout lyEnries;
        public GuestbookItemClickListener rcvItemListener;
        public GuestbookItemClickListener entryListener;

        public GuestbookViewHolder(@NonNull View itemView, final GuestbookItemClickListener rcvItemListener, final GuestbookItemClickListener entryListener) {
            super(itemView);

            lyGuestbook = itemView.findViewById(R.id.ly_guestbook);
            lyEnries = itemView.findViewById(R.id.ly_entries);
            lyContainer = itemView.findViewById(R.id.ly_container);
            txvGuestbookName = itemView.findViewById(R.id.txv_label_guestbook_name);
            txvGuestbookDescription = itemView.findViewById(R.id.txv_guestbook_description);
            txvModifiedDate = itemView.findViewById(R.id.txv_modified_date);

            lyGuestbook.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    rcvItemListener.onClick(lyGuestbook, guestbook);
                }

            });

            lyEnries.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    entryListener.onClick(view, guestbook);
                }

            });

            this.rcvItemListener = rcvItemListener;
        }
    }
}
