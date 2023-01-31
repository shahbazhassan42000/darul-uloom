package shahbaz4311.darul_uloom;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import java.util.Objects;

import shahbaz4311.darul_uloom.Utils.DBMS;
import shahbaz4311.darul_uloom.Utils.MyCustomDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    LinearLayout add_student_btn,records_btn,github_btn;
    DBMS dbms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbms = new DBMS(this);

        //remove the title bar
        Objects.requireNonNull(getSupportActionBar()).hide();

        //initialize the buttons
        add_student_btn = findViewById(R.id.add_student_btn);
        records_btn = findViewById(R.id.records_btn);
        github_btn = findViewById(R.id.github_btn);


        //setting basis for the animation
        add_student_btn.setAlpha(0);
        records_btn.setAlpha(0);
        github_btn.setAlpha(0);
        add_student_btn.setTranslationY(-50);
        records_btn.setTranslationX(50);
        github_btn.setTranslationY(50);

        //adding animations
        add_student_btn.animate().alpha(1).translationYBy(50).setDuration(1500).setStartDelay(400).start();
        records_btn.animate().alpha(1).translationXBy(-50).setDuration(1500).setStartDelay(400).start();
        github_btn.animate().alpha(1).translationYBy(-50).setDuration(1500).setStartDelay(400).start();






        //set the onclick listener
        add_student_btn.setOnClickListener(this);
        records_btn.setOnClickListener(this);
        github_btn.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_student_btn:
                add_student();
                break;
            case R.id.records_btn:
                startActivity(new Intent(MainActivity.this,RecordActivity.class));
                break;
            case R.id.github_btn:
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://github.com/shahbazhassan42000/darul-uloom/commits/main"));
                startActivity(intent);
                break;
        }

    }


    //closing the activity on back pressed
    @Override
    public void onBackPressed() {
        finish();
    }

    private void add_student() {
        MyCustomDialog dialog = new MyCustomDialog();
        dialog.setCancelable(false);
        dialog.show(getSupportFragmentManager(),getString(R.string.add_student));
    }
}