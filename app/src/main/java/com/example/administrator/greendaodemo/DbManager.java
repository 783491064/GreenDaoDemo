package com.example.administrator.greendaodemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.greendaodemo.greendao.DaoMaster;
import com.example.administrator.greendaodemo.greendao.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by bijingcun
 * on 2019/5/2.
 */
public class DbManager {
    private Context mContext;
    // 是否加密
    private static final String DB_NAME = "test.db";
    private static DbManager mDbManager;
    private static DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;
    private static MyOpenHelper helper;

    private DbManager(Context context,String password) {
        this.mContext = context;
        // 初始化数据库信息
        helper = new MyOpenHelper(context,DB_NAME,null);
        getDaoMaster(context,password);
        getDaoSession(context,password);
    }

    public static DbManager getInstance(Context context,String password) {
        if (null == mDbManager) {
            synchronized (DbManager.class) {
                if (null == mDbManager) {
                    mDbManager = new DbManager(context,password);
                }
            }
        }
        return mDbManager;
    }
    /**
     * 获取DaoMaster
     */
    public static DaoMaster getDaoMaster(Context context,String password) {
        if (null == mDaoMaster) {
            synchronized (DbManager.class) {
                if (null == mDaoMaster) {
                    mDaoMaster = new DaoMaster(getWritableDatabase(context,password));
                }
            }
        }
        return mDaoMaster;
    }
    public static Database getWritableDatabase(Context context, String passwprd) {
        if (null == helper) {
            getInstance(context, passwprd);
        }
            return helper.getEncryptedWritableDb(passwprd);
    }

    /**
     * 获取DaoSession
     */
    public static DaoSession getDaoSession(Context context,String password) {
        if (null == mDaoSession) {
            synchronized (DbManager.class) {
                mDaoSession = getDaoMaster(context,password).newDevSession(context,DB_NAME);
            }
        }

        return mDaoSession;
    }

}
