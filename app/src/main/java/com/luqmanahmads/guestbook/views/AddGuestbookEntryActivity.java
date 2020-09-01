package com.luqmanahmads.guestbook.views;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.luqmanahmads.guestbook.R;
import com.luqmanahmads.guestbook.data.GuestbookEntry;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class AddGuestbookEntryActivity extends AppCompatActivity {

    private GuestbookEntry guestbookEntry;
    private EditText edtGuestName;
    private EditText edtGuestMessage;
    private Button btnSave;
    private Button btnDelete;
    private Button btnCancel;
    private ImageView imvPhoto;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    ActivityResultLauncher<Intent> arlGetPhoto = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode() == Activity.RESULT_OK){
                Intent intent = result.getData();

                Bundle extras = intent.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");

                guestbookEntry.setGuestPhotoPath(saveBitmapToStorage(imageBitmap));
                imvPhoto.setImageBitmap(imageBitmap);
            }
        }
    });

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
        guestbookEntry.setGuestPhotoPath(intent.getStringExtra("guestPhotoPath"));

        /** Init View **/
        edtGuestName = findViewById(R.id.edt_guest_name);
        edtGuestMessage = findViewById(R.id.edt_guest_message);
        btnSave = findViewById(R.id.btn_add_guestbook_entry);
        btnDelete = findViewById(R.id.btn_delete_entry);
        btnCancel = findViewById(R.id.btn_cancel_entry);
        imvPhoto = findViewById(R.id.imv_photo);

        if(guestbookEntry.getGuestbookEntryId()>0){
            edtGuestName.setText(guestbookEntry.getGuestName());
            edtGuestMessage.setText(guestbookEntry.getGuestMessage());
            imvPhoto.setImageBitmap(getBitmapPhoto(guestbookEntry.getGuestPhotoPath()));
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
        result.putExtra("guestPhotoPath", guestbookEntry.getGuestPhotoPath());

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

    public void getPhotoFromCamera(View view){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePictureIntent.resolveActivity(getPackageManager())!=null){
            arlGetPhoto.launch(takePictureIntent);
        }
    }

    private String saveBitmapToStorage(Bitmap imageBitmap){
        String filename = "guest_photo_"+guestbookEntry.getGuestbookEntryId()+"_"+ Calendar.getInstance().getTimeInMillis();
        File file = new File(this.getFilesDir(), filename);

        FileOutputStream fos = null;
        try{
            fos = new FileOutputStream(file);
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return filename;
    }

    private Bitmap getBitmapPhoto(String guestPhotoPath){
        Bitmap photoBitmap = null;

        try{
            File file = new File(this.getFilesDir(), guestPhotoPath);
            photoBitmap = BitmapFactory.decodeStream(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return photoBitmap;
    }
}