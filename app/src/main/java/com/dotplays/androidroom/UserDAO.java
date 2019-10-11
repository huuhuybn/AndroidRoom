package com.dotplays.androidroom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDAO {

    // câu lệnh lấy danh sách User từ bảng user (tên bảng trùng với tên class User)
    @Query("SELECT * FROM user")
    List<User> getAll();

    // thêm 1 hoặc nhiều User
    @Insert
    long[] insertAll(User... users);

    // xóa 1 User
    @Delete
    int delete(User user);
}
