package com.luqmanahmads.guestbook.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.luqmanahmads.guestbook.R;
import com.luqmanahmads.guestbook.data.Guestbook;

public class AddGuestbookActivity extends AppCompatActivity {
    private Guestbook guestbook;
    private EditText edtGuestbookName;
    private EditText edtGuestbookDescription;
    private Button btnAdd;
    private Button btnCancel;
    private Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_guestbook);

        Intent intent = getIntent();
        guestbook = new Guestbook();
        guestbook.setGuestbookId(intent.getLongExtra("guestbookId", 0));
        guestbook.setGuestbookName(intent.getStringExtra("guestbookName"));
        guestbook.setGuestbookDescription(intent.getStringExtra("guestbookDescription"));

        Toast.makeText(this, "id : "+guestbook.getGuestbookId() , Toast.LENGTH_SHORT).show();

        edtGuestbookName = findViewById(R.id.edt_guestbook_name);
        edtGuestbookDescription = findViewById(R.id.edt_guestbook_description);

        btnAdd = findViewById(R.id.btn_add);
        btnDelete = findViewById(R.id.btn_delete);
        btnCancel = findViewById(R.id.btn_cancel);

        if(guestbook.getGuestbookId()>0){
            edtGuestbookName.setText(guestbook.getGuestbookName());
            edtGuestbookDescription.setText(guestbook.getGuestbookDescription());
        } else{
            btnDelete.setVisibility(View.GONE);
        }
    }

    public void addGuestbook(View view){
        Intent result = new Intent();
        result.putExtra("guestbookId", guestbook.getGuestbookId());
        result.putExtra("guestbookName", edtGuestbookName.getText().toString());
        result.putExtra("guestbookDescription", edtGuestbookDescription.getText().toString());
        setResult(Activity.RESULT_OK, result);
        finish();
    }

    public void deleteGuestbook(View view){
        Intent result = new Intent();
        result.putExtra("guestbookId", guestbook.getGuestbookId());
        setResult(2 , result);
        finish();
    }

    public void cancel(View view){
        Intent result = new Intent();
        setResult(Activity.RESULT_CANCELED , result);
        finish();
    }
}
