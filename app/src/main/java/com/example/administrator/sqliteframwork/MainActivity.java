package com.example.administrator.sqliteframwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.administrator.sqliteframwork.db.DbFactory;
import com.example.administrator.sqliteframwork.db.IBaseDao;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    IBaseDao<User> baseDao;
    IBaseDao<DownFile> fileDao;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        baseDao=DbFactory.getInstance().getDataHelper(UserDao.class);
        fileDao=DbFactory.getInstance().getDataHelper(DownDao.class);
    }
    public void save(View view)
    {
        for (int i=0;i<20;i++)
        {
            User user=new User(i,"teacher","123456");
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
        User where=new User();
        where.setName("teacher");
        User user=new User(1,"David","123456789");
        baseDao.update(user,where);
    }
    public void queryList(View view)
    {
        User where=new User();
        where.setName("teacher");
        where.setUser_Id(5);
        List<User> list=baseDao.query(where);
        Log.d("kjjhsdasda",list.size()+"");


    }


}
