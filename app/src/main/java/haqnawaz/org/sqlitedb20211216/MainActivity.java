package haqnawaz.org.sqlitedb20211216;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    static  int id = 0;
    Button buttonAdd, buttonViewAll;
    EditText editName, editAge;
    Switch switchIsActive;
    ListView listViewStudent;

    //<==== For Recycler view ===>
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // <=== For recycler view only ====>
        recyclerView = (RecyclerView) findViewById(R.id.studentRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));

        // <=== Other than recycler view ==>
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonViewAll = findViewById(R.id.buttonViewAll);
        editName = findViewById(R.id.editTextName);
        editAge = findViewById(R.id.editTextAge);
        switchIsActive = findViewById(R.id.switchStudent);

        // <===== Add Button on click lister ===>
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            StudentModel studentModel;
            @Override
            public void onClick(View v) {
                try {
                    studentModel = new StudentModel(editName.getText().toString(), Integer.parseInt(editAge.getText().toString()), switchIsActive.isChecked());
                    Toast.makeText(MainActivity.this, studentModel.toString(), Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
                DbHelper dbHelper = new DbHelper(MainActivity.this);
                dbHelper.addStudent(studentModel);
                editAge.setText("");
                editName.setText("");
                updateRecyclerView();
            }
        });


        //<=== View All ===>
        buttonViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateRecyclerView();
            }
        });



    }

//    public void editStudentfun(View view) {
//        EditText editName = findViewById(R.id.editTextName);
//        EditText editAge = findViewById(R.id.editTextAge);
//        Switch switchIsActive = findViewById(R.id.switchStudent);
//        StudentModel studentModelEdit = new StudentModel(editName.getText().toString(), Integer.parseInt(editAge.getText().toString()), switchIsActive.isChecked());
//
//        DbHelper dbHelper = new DbHelper(MainActivity.this);
//        dbHelper.editStudent(studentModelEdit);
//        List<StudentModel> list = dbHelper.getAllStudents();
//        ArrayAdapter arrayAdapter = new ArrayAdapter<StudentModel>(MainActivity.this, android.R.layout.simple_list_item_1,list);
//        listViewStudent.setAdapter(arrayAdapter);
//        editAge.setText("");
//        editName.setText("");
//    }


    public void updateRecyclerView(){
        DbHelper dbHelper = new DbHelper(MainActivity.this);
        List<StudentModel> list = dbHelper.getAllStudents();
        adapter = new MyAdapter(list, MainActivity.this);
        recyclerView.setAdapter(adapter);
    }
}
