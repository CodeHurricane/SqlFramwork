package com.example.administrator.sqliteframwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.administrator.sqliteframwork.db.db_core.BaseDbFactory;
import com.example.administrator.sqliteframwork.db.db_core.IBaseDao;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    IBaseDao<User> baseDao;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        baseDao=BaseDbFactory.getInstance().getDataHelper(UserDao.class);
    }
    public void save(View view)
    {
        for(int i=0;i<20;i++){
            User user=new User();
            user.setName("V00"+(i++));
            user.setPassword("123456");
            user.setName("张三"+i);
            user.setUser_id("N000"+i);
            baseDao.insert(user);
        }
    }
    public void deleteUser(View view)
    {
        User user=new User();
        user.setName("David");
        baseDao.delete(user);
    }
    public  void  update(View view)
    {
//        User where=new User();
//        where.setName("teacher");
//        User user=new User(1,"David","123456789");
//        baseDao.update(user,where);
    }
    public void queryList(View view)
    {
//        User where=new User();
//        where.setName("teacher");
//        where.setUser_Id(5);
//        List<User> list=baseDao.query(where);
//        Log.d("kjjhsdasda",list.size()+"");
    }
}
