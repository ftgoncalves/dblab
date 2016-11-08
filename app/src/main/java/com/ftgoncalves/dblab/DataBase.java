package com.ftgoncalves.dblab;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {

    private static DataBase dataBase;

    public DataBase() {
        super(MyApplication.getInstance(), "database", null, 1);
    }

    public static DataBase getInstance() {
        if (dataBase == null) {
            dataBase = new DataBase();
        }

        return dataBase;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE `Foo` (" +
                "  integerFoo INTEGER PRIMARY KEY, " +
                "  stringFoo VARCHAR(200) DEFAULT NULL " +
                ")";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
