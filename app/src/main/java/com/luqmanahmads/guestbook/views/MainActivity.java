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
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.luqmanahmads.guestbook.R;
import com.luqmanahmads.guestbook.adapters.GuestbookRecyclerAdapter;
import com.luqmanahmads.guestbook.data.Guestbook;
import com.luqmanahmads.guestbook.listeners.RecyclerViewItemClickListener;
import com.luqmanahmads.guestbook.viewmodels.GuestbookListViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rcvGuestbook;
    private RecyclerViewItemClickListener rcvItemListener;
    private GuestbookRecyclerAdapter adapterGuestbook;
    private Button btnAddGuestbook;
    private GuestbookListViewModel vmGuestbookList;

    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode() == Activity.RESULT_OK){
                Intent intent = result.getData();

                long guestbookId = intent.getLongExtra("guestbookId", 0);
                String guestbookName = intent.getStringExtra("guestbookName");
                String guestbookDescription = intent.getStringExtra("guestbookDescription");

                if(guestbookId == 0){
                    vmGuestbookList.addGuestbook(guestbookName, guestbookDescription);
                } else {
                    vmGuestbookList.updateGuestbook(guestbookId, guestbookName, guestbookDescription);
                }

            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** Initialize ViewModel **/
        vmGuestbookList = new ViewModelProvider(this).get(GuestbookListViewModel.class);

        /** Bind Add Button **/
        btnAddGuestbook = findViewById(R.id.btn_add_guestbook);

        /** Bind RecyclerView **/
        rcvGuestbook = findViewById(R.id.rcv_guestbook);
        rcvGuestbook.setNestedScrollingEnabled(false);

        /** Set onClick RecylcerView Item **/
        rcvItemListener = new RecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, Guestbook guestbook) {

                Intent intent = new Intent(view.getContext(), AddGuestbookActivity.class);
                intent.putExtra("guestbookId", Long.valueOf(guestbook.getGuestbookId()));
                intent.putExtra("guestbookName", guestbook.getGuestbookName());
                intent.putExtra("guestbookDescription", guestbook.getGuestbookDescription());

                mStartForResult.launch(intent);
            }
        };

        /** Initialize RecyclerView : set Adapter and LayoutManager **/
        initGuestbookRecyclerView();

        /** Observe ViewModel to Adapter **/
        vmGuestbookList.getGuestbookList().observe(this, new Observer<List<Guestbook>>() {
            @Override
            public void onChanged(List<Guestbook> guestbooks) {
                adapterGuestbook.setDataSet(vmGuestbookList.getGuestbookList().getValue());
                adapterGuestbook.notifyDataSetChanged();
            }
        });
    }

    public void goToAddGuestbook(View view){
        Intent intent = new Intent(this, AddGuestbookActivity.class);
        intent.putExtra("guestbookId", Long.valueOf(0));
        mStartForResult.launch(intent);
    }

    private void initGuestbookRecyclerView(){
        adapterGuestbook = new GuestbookRecyclerAdapter(vmGuestbookList.getGuestbookList().getValue(), rcvItemListener);
        RecyclerView.LayoutManager linierLayoutManager = new LinearLayoutManager(this);
        rcvGuestbook.setLayoutManager(linierLayoutManager);
        rcvGuestbook.setAdapter(adapterGuestbook);
    }
}
