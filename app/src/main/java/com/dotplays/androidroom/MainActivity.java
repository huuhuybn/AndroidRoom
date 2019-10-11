package com.dotplays.androidroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.dotplays.androidroom.thread.UserQueryTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private AppDatabase db;
    private RecyclerView recyclerView;
    private List<User> users;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.lvList);
        users = new ArrayList<>();
        userAdapter = new UserAdapter(this, users);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(userAdapter);

        // allowMainThreadQueries : cho phép câu lệnh query chạy trực tiếp trên luồng chính
        // nếu truy vấn dữ liệu lớn sẽ gây lag, giật cho ứng dụng
        // vì vậy chúng ta nên mở luông riêng bằng AsyncTask để đảm bảo ứng dụng chay mượt
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "user.db").allowMainThreadQueries().build();

        // lấy danh sách User

        UserQueryTask userQueryTask = new UserQueryTask(this);

        userQueryTask.getAllUsers(new UserQueryTask.OnQuery<List<User>>() {
            @Override
            public void onResult(List<User> users) {
                MainActivity.this.users.addAll(users);
                Log.e("ABC", users.size() + "");
                userAdapter.notifyDataSetChanged();
            }
        });

        User user = new User();
        user.id = new Random().nextInt();
        user.name = "Huy Nguyen";
        user.phone = "0913456789";

        userQueryTask.insertUsers(new UserQueryTask.OnQuery<long[]>() {
            @Override
            public void onResult(long[] longs) {
                // kết quả trả về là 1 array long, nếu thành công là 1 giá trị > 0
            }
        },user);


    }

    public void addUser(View view) {
        // thêm 1 user
        User user = new User();
        user.id = new Random().nextInt();
        user.name = "HuyN Android";
        user.phone = "091.336.0468";
        // kết quả thêm user thành công hay ko trả về qua array long
        long[] result = db.userDao().insertAll(user);
        if (result[0] > 0) {
            Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
            this.users.add(user);
            userAdapter.notifyDataSetChanged();
        }

    }
}
