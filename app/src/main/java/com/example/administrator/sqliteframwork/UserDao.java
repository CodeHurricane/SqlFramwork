package com.example.administrator.sqliteframwork;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.administrator.sqliteframwork.db.db_core.BaseDao;
import com.example.administrator.sqliteframwork.db.db_core.PrivateDataBaseEnums;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2017/1/9 0009.
 */

public class UserDao extends BaseDao<User> {
    @Override
    protected String createTable() {
        return "create table if not exists tb_user(user_Id int,name varchar(20),password varchar(10))";
    }

    @Override
    public  Long insert(User entity) {
        boolean isExist=false;
        List<User> list=query(new User());
        User where = null;
        for (User user:list)
        {
            where =new User();
            where.setUser_id(user.getUser_id());
            if(entity.getUser_id()==user.getUser_id()){
                user.setStatus(1);
                update(user,where);
                isExist=true;
                continue;
            }
            user.setStatus(0);
            update(user,where);
        }
        if (isExist){
            return 1l;
        }
        database=SQLiteDatabase.openOrCreateDatabase(PrivateDataBaseEnums.database.getValue(),null);
        entity.setStatus(1);
        return super.insert(entity);
    }


    @Override
    protected Class getTClass() {
        return User.class;
    }
    @Override
    public List query(String sql) {
        return null;
    }

    public User getCurrentUser() {
        User user=new User();
        user.setStatus(1);
        List<User> list=query(user);
        if(list.size()>0)
        {
            return list.get(0);
        }
        return null;
    }
}
