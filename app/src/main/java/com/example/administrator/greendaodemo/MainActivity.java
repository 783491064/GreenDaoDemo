package com.example.administrator.greendaodemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button add;
    Button delete;
    Button updata;
    Button check;
    Button deleteAll;
    Button checkId;
    private Context mContext;
    private Student student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        add=(Button) findViewById(R.id.add);
        delete=(Button) findViewById(R.id.delete);
        updata=(Button) findViewById(R.id.updata);
        check=(Button) findViewById(R.id.check);
        deleteAll=(Button) findViewById(R.id.deleteAll);
        checkId=(Button) findViewById(R.id.check_id);
        initData();
        initListener();
        DbManager.getInstance(this,"password");

    }

    private List<Student> studentList = new ArrayList<>();

    /**
     * 初始化数据
     */
    private void initData() {
        for (int i = 0; i < 100; i++) {
            student = new Student((long) i, "huang" + i, 25);
            studentList.add(student);
        }

    }

    private void initListener() {
        /**
         *增
         */
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentDaoOpe.insertData(mContext, studentList);
            }
        });
        /**
         * 删
         */
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = new Student((long) 5, "haung" + 5, 25);
                /**
                 * 根据特定的对象删除
                 */
                StudentDaoOpe.deleteData(mContext, student);
                /**
                 * 根据主键删除
                 */
                StudentDaoOpe.deleteByKeyData(mContext, 7);
            }
        });
        /**
         *删除所有
         */
        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentDaoOpe.deleteAllData(mContext);
            }
        });
        /**
         * 更新
         */
        updata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = new Student((long) 2, "haungxiaoguo", 16516);
                StudentDaoOpe.updateData(mContext, student);
            }
        });
        /**
         * 查询全部
         */
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Student> students = StudentDaoOpe.queryAll(mContext);
                for (int i = 0; i < students.size(); i++) {
                    Log.i("Log", students.get(i).getName());
                }
            }
        });
        /**
         * 根据id查询
         */
        checkId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Student> students = StudentDaoOpe.queryForId(mContext, 50);
                for (int i = 0; i < students.size(); i++) {
                    Log.i("Log", students.get(i).getName()+student.getNum());
                }
            }
        });
    }
}
