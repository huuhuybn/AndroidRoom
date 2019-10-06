package com.dotplays.androidroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // allowMainThreadQueries : cho phép câu lệnh query chạy trực tiếp trên luồng chính
        // nếu truy vấn dữ liệu lớn sẽ gây lag, giật cho ứng dụng
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "user.db").allowMainThreadQueries().build();

        // lấy danh sách User
        List<User> users = db.userDao().getAll();

        // thêm 1 user

        User user = new User();
        user.id = new Random().nextInt();
        user.name = "HuyN Android";
        user.phone = "091.336.0468";
        // kết quả thêm user thành công hay ko trả về qua array long
        long[] result = db.userDao().insertAll(user);



    }
}
