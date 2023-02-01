package shahbaz4311.darul_uloom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;

import shahbaz4311.darul_uloom.Adapters.RecordAdapter;
import shahbaz4311.darul_uloom.Models.Record;
import shahbaz4311.darul_uloom.Models.Student;
import shahbaz4311.darul_uloom.Utils.Add_record_dialog;
import shahbaz4311.darul_uloom.Utils.DBMS;

public class HistoryActivity extends AppCompatActivity {

    Add_record_dialog add_record_dialog;
    RecyclerView recycleView2;
    List<Record> records;
    RecordAdapter adapter;
    Student student;
    TextView studentNameInp;
    View add_record_btn;
    DBMS dbms;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        //remove the title bar
        Objects.requireNonNull(getSupportActionBar()).hide();

        dbms = new DBMS(this);

        //initializing
        studentNameInp = findViewById(R.id.studentNameInp);
        add_record_btn = findViewById(R.id.add_record_btn);

        //getting the student from the intent
        student = (Student) getIntent().getSerializableExtra("student");

        //setting the student name
        studentNameInp.setText(student.getName());

        //getting the records from the database
        records = dbms.getStudentRecords(student);

        //initializing
        recycleView2 = findViewById(R.id.recycleView2);
        recycleView2.setLayoutManager(new LinearLayoutManager(this));
        recycleView2.setHasFixedSize(true);

        //initializing the adapter
        adapter = new RecordAdapter(records);
        adapter.notifyDataSetChanged();

        //setting the adapter
        recycleView2.setAdapter(adapter);

        //setting the click listener
        add_record_btn.setOnClickListener(v -> {
            //creating the add record activity
            add_record_dialog = new Add_record_dialog(student,records);
            add_record_dialog.setCancelable(false);
            add_record_dialog.show(getSupportFragmentManager(), String.valueOf(R.string.today_record));
        });
    }
}