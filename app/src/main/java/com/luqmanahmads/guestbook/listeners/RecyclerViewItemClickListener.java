package com.luqmanahmads.guestbook.listeners;

import android.view.View;

import com.luqmanahmads.guestbook.data.Guestbook;

public interface RecyclerViewItemClickListener {
    public void onClick(View view, Guestbook gb);
}
