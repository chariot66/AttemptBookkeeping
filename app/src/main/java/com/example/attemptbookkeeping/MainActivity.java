package com.example.attemptbookkeeping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import com.example.attemptbookkeeping.Database.DBTableHelper;
import com.example.attemptbookkeeping.MainPage.CreateTableDialog;
import com.example.attemptbookkeeping.MainPage.NoteAdapter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView noteRecyclerView;
    NoteAdapter noteAdapter;

    CreateTableDialog createTableD;

    DBTableHelper DBtable;

    ArrayList<com.example.attemptbookkeeping.notebook> notebooks_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 数据库
        DBtable = new DBTableHelper(this);
        this.notebooks_list = viewAllRecords();

        // 列表显示
        noteRecyclerView = findViewById(R.id.noteRecyclerView);
        noteRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        noteAdapter = new NoteAdapter(this, this.notebooks_list, DBtable);
        noteRecyclerView.setAdapter(noteAdapter);
    }

    //表示当activity获取焦点时会调用的方法, 不知道为啥没有用
//    @Override
//    protected void onResume() {
//        super.onResume();
//        notebooks_list.clear();
//        notebooks_list.addAll(viewAllRecords());
//        noteAdapter.setNewData(viewAllRecords());
//        noteAdapter.notifyDataSetChanged();
//    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_save:

                    String table_name = createTableD.table_name.getText().toString().trim();
                    String table_info = createTableD.table_info.getText().toString().trim();

                    boolean isInserted = DBtable.insertData(table_name, table_info);
                    if (!isInserted){
                        AlertDialog.Builder builder =
                                new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("错误");
                        builder.setMessage("存在同名");
                        builder.setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which)
                                    { }
                                });
                        builder.create();
                        builder.show();
                        return;
                    }

                    // 更新notebook的显示
                    noteAdapter.setNewData(viewAllRecords());
                    noteAdapter.notifyDataSetChanged();
                    createTableD.dismiss();
                    break;
                case R.id.btn_cancel:
                    createTableD.dismiss();
                    break;
            }
        }
    };

    private ArrayList<com.example.attemptbookkeeping.notebook> getNotebooks() {
        final String[] names =
                getResources().getStringArray(R.array.noteName);
        final String[] descriptions =
                getResources().getStringArray(R.array.noteDescription);
        ArrayList<com.example.attemptbookkeeping.notebook> models = new ArrayList<>();
        com.example.attemptbookkeeping.notebook p = new com.example.attemptbookkeeping.notebook();
        for (int i = 0; i < names.length; i++) {
            if (i !=0) { p = new com.example.attemptbookkeeping.notebook(); }
            p.setName(names[i]);
            p.setDescription(descriptions[i]);
            p.setImg(R.drawable.in_xinzi);
            models.add(p);
        }
        return models;
    }

    /**
     * 读取数据库
     */
    public ArrayList<com.example.attemptbookkeeping.notebook> viewAllRecords() {
        Cursor res = DBtable.getAllData();
        ArrayList<com.example.attemptbookkeeping.notebook> models = new ArrayList<>();
        if (res.getCount() == 0) {
            return models;
        }
        while (res.moveToNext()) {
            com.example.attemptbookkeeping.notebook p = new com.example.attemptbookkeeping.notebook();
            p.setName(res.getString(1));
            p.setDescription(res.getString(2));
            p.setImg(R.drawable.in_xinzi);
            models.add(p);
        }
        return models;
    }

    public void showEditDialog(View view) {
        createTableD = new CreateTableDialog(this, androidx.appcompat.R.style.Base_Theme_AppCompat_Dialog,onClickListener);
        createTableD.show();
    }
}

//this is for testing