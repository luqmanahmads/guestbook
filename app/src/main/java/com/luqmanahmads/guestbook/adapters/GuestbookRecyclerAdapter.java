package com.luqmanahmads.guestbook.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.luqmanahmads.guestbook.R;
import com.luqmanahmads.guestbook.data.Guestbook;
import com.luqmanahmads.guestbook.listeners.RecyclerViewItemClickListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GuestbookRecyclerAdapter extends RecyclerView.Adapter<GuestbookRecyclerAdapter.GuestbookViewHolder> {

    private List<Guestbook> guestbookList = new ArrayList<Guestbook>();
    private RecyclerViewItemClickListener rcvItemListener;


    public GuestbookRecyclerAdapter(List<Guestbook> guestbookList, RecyclerViewItemClickListener rcvItemListener){
        this.guestbookList = guestbookList;
        this.rcvItemListener = rcvItemListener;
    }

    @NonNull
    @Override
    public GuestbookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.guestbook_item_view, parent, false);
        GuestbookViewHolder vh = new GuestbookViewHolder(view, this.rcvItemListener);
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
        return guestbookList.size();
    }

    public List<Guestbook> getDataSet(){
        return this.guestbookList;
    }

    public void setDataSet(List<Guestbook> guestbookList){
        this.guestbookList = guestbookList;
    }


    public void setGuestbookList(List<Guestbook> guestbookList){
        this.guestbookList = guestbookList;
    }

    public static class GuestbookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public Guestbook guestbook = null;
        public TextView txvGuestbookName;
        public TextView txvGuestbookDescription;
        public TextView txvModifiedDate;
        public LinearLayout lyContainer;
        public RecyclerViewItemClickListener rcvItemListener;

        public GuestbookViewHolder(@NonNull View itemView, RecyclerViewItemClickListener rcvItemListener) {
            super(itemView);

            lyContainer = itemView.findViewById(R.id.ly_container);
            txvGuestbookName = itemView.findViewById(R.id.txv_guestbook_name);
            txvGuestbookDescription = itemView.findViewById(R.id.txv_guestbook_description);
            txvModifiedDate = itemView.findViewById(R.id.txv_modified_date);

            itemView.setOnClickListener(this);

            this.rcvItemListener = rcvItemListener;
        }

        @Override
        public void onClick(View view) {
            rcvItemListener.onClick(view, guestbook);
        }
    }
}
