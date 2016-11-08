package com.ftgoncalves.dblab;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Date;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getCanonicalName();
    private DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataBase = DataBase.getInstance();

        Button runInserts = (Button) findViewById(R.id.run);
        runInserts.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(Subscriber<? super Void> subscriber) {

                Log.d(TAG, "START: " + new Date().getTime());
                for (int i = 0; i < 48623; i++) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("integerFoo", i);
                    contentValues.put("stringFoo", "foo=" + i);
                    dataBase.getWritableDatabase().insert("Foo", null, contentValues);
                }
                Log.d(TAG, "END: " + new Date().getTime());
                subscriber.onNext(null);
            }
        })
        .observeOn(Schedulers.io())
        .subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {

            }
        });
    }
}
