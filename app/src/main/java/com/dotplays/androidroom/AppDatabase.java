package com.dotplays.androidroom;

import androidx.room.Database;
import androidx.room.RoomDatabase;

// khởi tạo cơ sở dữ liệu với bảng User
@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDAO userDao();
}