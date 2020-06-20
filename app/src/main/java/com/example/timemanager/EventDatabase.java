package com.example.timemanager;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Event.class}, version = 1)
public abstract class EventDatabase extends RoomDatabase {
    private static EventDatabase instance;

    public abstract EventDao eventDao();

    public static synchronized EventDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    EventDatabase.class, "event_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }
private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
        super.onCreate(db);
        new PopulateDbAsyncTask(instance).execute();
    }
};

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
private EventDao eventDao;

private PopulateDbAsyncTask(EventDatabase db ){
eventDao = db.eventDao();

}
        @Override
        protected Void doInBackground(Void... voids) {
    eventDao.insert(new Event("title 1", "description 1", "06/12/2020", "Minutes", "30", 10));
    eventDao.insert(new Event("title 2", "description 2", "06/13/2020", "Hours", "40", 9));
    eventDao.insert(new Event("title 3", "description 3", "06/14/2020", "Minutes", "50", 8));

            return null;
        }
    }
}
