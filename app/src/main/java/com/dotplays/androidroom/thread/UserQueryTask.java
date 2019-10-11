package com.dotplays.androidroom.thread;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;

import com.dotplays.androidroom.AppDatabase;
import com.dotplays.androidroom.User;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class UserQueryTask {

    public AppDatabase appDatabase;


    public UserQueryTask(Context context) {
        appDatabase = Room.databaseBuilder(context,
                AppDatabase.class, "user.db").build();
    }

    public interface OnQuery<T> {
        void onResult(T t);
    }


    public void getAllUsers(OnQuery<List<User>> onQuery) {
        new GetUsersAsyncTask(onQuery).execute();
    }

    public void insertUsers(OnQuery<long[]> onQuery, User... users) {
        new InsertUsersAsyncTask(onQuery).execute();
    }

    private class GetUsersAsyncTask extends AsyncTask<Void, Void, List<User>> {
        OnQuery onQuery;

        public GetUsersAsyncTask(OnQuery onQuery) {
            this.onQuery = onQuery;
        }

        @Override
        protected List<User> doInBackground(Void... voids) {
            return appDatabase.userDao().getAll();
        }

        @Override
        protected void onPostExecute(List<User> users) {
            super.onPostExecute(users);
            onQuery.onResult(users);
        }
    }

    private class InsertUsersAsyncTask extends AsyncTask<User, Void, long[]> {
        OnQuery onQuery;

        public InsertUsersAsyncTask(OnQuery onQuery) {
            this.onQuery = onQuery;
        }

        @Override
        protected long[] doInBackground(User... users) {
            return appDatabase.userDao().insertAll(users);
        }

        @Override
        protected void onPostExecute(long[] longs) {
            super.onPostExecute(longs);
            this.onQuery.onResult(longs);
        }
    }


}
