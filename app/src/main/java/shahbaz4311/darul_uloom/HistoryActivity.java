package shahbaz4311.darul_uloom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

import shahbaz4311.darul_uloom.Adapters.RecordAdapter;
import shahbaz4311.darul_uloom.Models.Record;

public class HistoryActivity extends AppCompatActivity {

    RecyclerView recycleView;
    List<Record> records;
    RecordAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        //initializing
    }
}