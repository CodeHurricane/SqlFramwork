package com.example.administrator.sqliteframwork.db;

import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

/**
 * Created by Administrator on 2017/1/9 0009.
 */

public class DbFactory {

    private String sqliteDatabasePath;

    private SQLiteDatabase sqLiteDatabase;
    private static final String DB_NAME="/temp.db";
    private static DbFactory instance=new DbFactory();
    public DbFactory()
    {   sqliteDatabasePath= Environment.getExternalStorageDirectory().getAbsolutePath()+DB_NAME;
        openDatabase();
    }

    public  synchronized  <T extends  BaseDao> T getDataHelper(Class<T> clazz){
            BaseDao baseDao=null;
            try {
                baseDao=clazz.newInstance();
                baseDao.init(baseDao.getTClass(),sqLiteDatabase);
            } catch(InstantiationException e){
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return (T) baseDao;
        }

    private void openDatabase(){
        this.sqLiteDatabase=SQLiteDatabase.openOrCreateDatabase(sqliteDatabasePath,null);
    }

    public  static DbFactory getInstance()
    {
        return instance;
    }
}
