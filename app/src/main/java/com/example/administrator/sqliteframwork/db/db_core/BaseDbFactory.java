package com.example.administrator.sqliteframwork.db.db_core;

import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/9 0009.
 */

public  class BaseDbFactory {

    private String sqliteDatabasePath;
    private Map<String,BaseDao> map= Collections.synchronizedMap(new HashMap<String, BaseDao>());
    private SQLiteDatabase sqLiteDatabase;
    private static final String DB_USER="user.db";
    private static BaseDbFactory instance;
    private BaseDbFactory()
    {
        File file=new File(Environment.getExternalStorageDirectory(),"soliteFramworl");
        if(!file.exists())
        {
            file.mkdirs();
        }
        sqliteDatabasePath= file.getAbsolutePath()+DB_USER;
        openDatabase(sqliteDatabasePath);
    }
    public  synchronized  <T extends  BaseDao> T changeUser(Class<T> clazz){
              openDatabase(PrivateDataBaseEnums.database.getValue());
              return getDataHelper(clazz);
    }
    public  synchronized  <T extends  BaseDao> T getDataHelper(Class<T> clazz){
            BaseDao baseDao=null;
           if(map.get(clazz.getSimpleName())!=null)
            {
            return (T) map.get(clazz.getSimpleName());
            }
            try {
                baseDao=clazz.newInstance();
                baseDao.init(baseDao.getTClass(),sqLiteDatabase);
                map.put(clazz.getSimpleName(),baseDao);
            } catch(InstantiationException e){
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return (T) baseDao;
        }

    private void openDatabase(String sqliteDatabasePath){
         this.sqLiteDatabase=SQLiteDatabase.openOrCreateDatabase(sqliteDatabasePath,null);
    }

    public  static BaseDbFactory getInstance()
    {
        synchronized (BaseDbFactory.class){
            if (instance==null){
                synchronized (BaseDbFactory.class){
                    instance=new BaseDbFactory();
                }
            }
        }
        return instance;
    }
}
