package com.luqmanahmads.guestbook.views;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.luqmanahmads.guestbook.R;
import com.luqmanahmads.guestbook.adapters.GuestbookEntryRecyclerAdapter;
import com.luqmanahmads.guestbook.data.Guestbook;
import com.luqmanahmads.guestbook.data.GuestbookEntry;
import com.luqmanahmads.guestbook.viewmodels.GuestbookEntryViewModel;
import com.luqmanahmads.guestbook.viewmodels.GuestbookListViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class GuestbookEntryActivity extends AppCompatActivity {

    private GuestbookEntryViewModel vmGuestbookEntry;
    private Button btnAddGuestbookEntry;
    private GuestbookEntryRecyclerAdapter adapterGuestbookEntry;
    private RecyclerView rcvGuestbookEntry;
    private Long guestbookId;

    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode() == Activity.RESULT_OK){
                Intent intent = result.getData();

                long guestbookEntryId = intent.getLongExtra("guestbookEntryId", 0);
                long guestbookId = intent.getLongExtra("guestbookId", 0);
                String guestName = intent.getStringExtra("guestName");
                String guestMessage = intent.getStringExtra("guestMessage");

                Log.d("guestbook id", String.valueOf(guestbookId));
                Log.d("guestbook entry id", String.valueOf(guestbookEntryId));

                if(guestbookEntryId == 0){
                    Log.d("test", "before add guestbook entry");
                    vmGuestbookEntry.addGuestbookEntry(guestbookId, "", guestName, guestMessage);
                    Log.d("test", "after add guestbook entry");
                } else {

                }

            } else if(result.getResultCode() == 2){

            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guestbook_entries);

        Intent intent = getIntent();
        guestbookId = intent.getLongExtra("guestbookId", 0);
        Toast.makeText(this, "guestbook id : "+guestbookId , Toast.LENGTH_SHORT).show();

        /** Initialize  View Model **/
        vmGuestbookEntry = new ViewModelProvider(this).get(GuestbookEntryViewModel.class);
        vmGuestbookEntry.initGuestbookEntryList(guestbookId.longValue());

        /** Bind view **/
        btnAddGuestbookEntry = findViewById(R.id.btn_add_guestbook_entry);

        /** Bind RecyclerView **/
        rcvGuestbookEntry = findViewById(R.id.rcv_guestbook_entry);
        rcvGuestbookEntry.setNestedScrollingEnabled(false);

        /** Init GuestbookEntry recycler view **/
        initGuestbookEntryRecyclerView();

        /** Observe ViewModel to Adapter **/

        vmGuestbookEntry.getLdGuestbookEntryList().observe(this, new Observer<List<GuestbookEntry>>() {
            @Override
            public void onChanged(List<GuestbookEntry> guestbookEntryList) {
                adapterGuestbookEntry.setDataSet(guestbookEntryList);
                adapterGuestbookEntry.notifyDataSetChanged();
            }
        });

    }

    public void goToAddGuestbookEntry(View view){
        Intent intent = new Intent(this, AddGuestbookEntryActivity.class);
        intent.putExtra("guestbookId", guestbookId);
        intent.putExtra("guestbookEntryId", Long.valueOf(0));
        mStartForResult.launch(intent);
    }

    private void initGuestbookEntryRecyclerView(){
        adapterGuestbookEntry = new GuestbookEntryRecyclerAdapter(vmGuestbookEntry.getLdGuestbookEntryList().getValue());
        RecyclerView.LayoutManager linierLayoutManager = new LinearLayoutManager(this);
        rcvGuestbookEntry.setLayoutManager(linierLayoutManager);
        rcvGuestbookEntry.setAdapter(adapterGuestbookEntry);
    }
}
