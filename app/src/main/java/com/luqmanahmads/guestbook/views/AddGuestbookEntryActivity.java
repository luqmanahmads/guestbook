package com.luqmanahmads.guestbook.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.luqmanahmads.guestbook.R;
import com.luqmanahmads.guestbook.data.GuestbookEntry;

public class AddGuestbookEntryActivity extends AppCompatActivity {

    private GuestbookEntry guestbookEntry;
    private EditText edtGuestName;
    private EditText edtGuestMessage;
    private Button btnSave;
    private Button btnDelete;
    private Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_guestbook_entry);

        /** Init Guestbook **/
        Intent intent = getIntent();
        guestbookEntry = new GuestbookEntry();
        guestbookEntry.setGuestbookId(intent.getLongExtra("guestbookId", 0));
        guestbookEntry.setGuestbookEntryId(intent.getLongExtra("guestbookEntryId", 0));
        guestbookEntry.setGuestName(intent.getStringExtra("guestName"));
        guestbookEntry.setGuestMessage(intent.getStringExtra("guestMessage"));

        /** Init View **/
        edtGuestName = findViewById(R.id.edt_guest_name);
        edtGuestMessage = findViewById(R.id.edt_guest_message);
        btnSave = findViewById(R.id.btn_add_guestbook_entry);
        btnDelete = findViewById(R.id.btn_delete_entry);
        btnCancel = findViewById(R.id.btn_cancel_entry);

        if(guestbookEntry.getGuestbookEntryId()>0){
            edtGuestName.setText(guestbookEntry.getGuestName());
            edtGuestMessage.setText(guestbookEntry.getGuestMessage());
        } else{
            btnDelete.setVisibility(View.GONE);
        }
    }

    public void addGuestbookEntry(View view){
        Intent result = new Intent();
        result.putExtra("guestbookId", guestbookEntry.getGuestbookId());
        result.putExtra("guestbookEntryId", guestbookEntry.getGuestbookEntryId());
        result.putExtra("guestName", edtGuestName.getText().toString());
        result.putExtra("guestMessage", edtGuestMessage.getText().toString());
        setResult(Activity.RESULT_OK, result);
        finish();
    }

    public void delete(View view){
        Intent result = new Intent();
        result.putExtra("guestbookEntryId", guestbookEntry.getGuestbookEntryId());
        setResult(2 , result);
        finish();
    }

    public void cancel(View view){
        Intent result = new Intent();
        setResult(Activity.RESULT_CANCELED , result);
        finish();
    }
}