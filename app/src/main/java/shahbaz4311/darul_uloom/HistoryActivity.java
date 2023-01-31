package shahbaz4311.darul_uloom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

import shahbaz4311.darul_uloom.Adapters.RecordAdapter;
import shahbaz4311.darul_uloom.Models.Record;
import shahbaz4311.darul_uloom.Models.Student;
import shahbaz4311.darul_uloom.Utils.DBMS;

public class HistoryActivity extends AppCompatActivity {

    RecyclerView recycleView;
    List<Record> records;
    RecordAdapter adapter;
    Student student;

    DBMS dbms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        dbms = new DBMS(this);

        //getting the student from the intent
        student = (Student) getIntent().getSerializableExtra("student");

        //getting the records from the database
        records = dbms.getStudentRecords(student);

        //initializing
    }
}