package shahbaz4311.darul_uloom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import shahbaz4311.darul_uloom.Adapters.StudentAdapter;
import shahbaz4311.darul_uloom.Models.Student;
import shahbaz4311.darul_uloom.Utils.DBMS;

public class RecordActivity extends AppCompatActivity {

    DBMS dbms;
    RecyclerView recycleView;
    List<Student> students;
    StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        //remove the title bar
        Objects.requireNonNull(getSupportActionBar()).hide();

        //initializing
        dbms = new DBMS(this);
        recycleView = findViewById(R.id.recycleView);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        recycleView.setHasFixedSize(true);

        //getting all the students from the database
        students = dbms.getAllStudents();

        //initializing the adapter
        adapter = new StudentAdapter(students);
        //setting the adapter
        recycleView.setAdapter(adapter);
    }
}