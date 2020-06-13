package com.luqmanahmads.guestbook.data;

import android.content.Context;

import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Guestbook.class}, version = 1, exportSchema = true)
public abstract class GuestbookRoomDatabase extends RoomDatabase {

    public abstract GuestbookDao guestbookDao();

    private static volatile GuestbookRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    // Populate the database in the background.
                    // If you want to start with more words, just add them.
                    GuestbookDao dao = INSTANCE.guestbookDao();
                    dao.deleteAll();

                }
            });
        }
    };

    public static GuestbookRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (GuestbookRoomDatabase.class){
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            GuestbookRoomDatabase.class, "guestbook_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
