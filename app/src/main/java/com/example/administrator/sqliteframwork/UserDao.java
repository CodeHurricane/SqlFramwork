package com.example.administrator.sqliteframwork;

import com.example.administrator.sqliteframwork.db.BaseDao;

import java.util.List;

/**
 * Created by Administrator on 2017/1/9 0009.
 */

public class UserDao extends BaseDao {
    @Override
    protected String createTable() {

        return "create table if not exists tb_user(user_Id int,name varchar(20),password varchar(10))";
    }

    @Override
    protected Class getTClass() {
        return User.class;
    }
    @Override
    public List query(String sql) {


        return null;
    }
}
