package com.luqmanahmads.guestbook.listeners;

import android.view.View;

import com.luqmanahmads.guestbook.data.GuestbookEntry;

public interface EntryItemClickListener {
    public void onClick(View view, GuestbookEntry ge);
}
