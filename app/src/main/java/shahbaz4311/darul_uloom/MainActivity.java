package shahbaz4311.darul_uloom;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    LinearLayout add_student_btn,records_btn,github_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //remove the title bar
        Objects.requireNonNull(getSupportActionBar()).hide();

        //initialize the buttons
        add_student_btn = findViewById(R.id.add_student_btn);
        records_btn = findViewById(R.id.records_btn);
        github_btn = findViewById(R.id.github_btn);


        //add animation to the buttons
        add_student_btn.setAlpha(0);
        records_btn.setAlpha(0);
        github_btn.setAlpha(0);
        add_student_btn.setTranslationY(-50);
        records_btn.setTranslationX(50);
        github_btn.setTranslationY(50);

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
                //do something
                break;
            case R.id.records_btn:
                //do something
                break;
            case R.id.github_btn:
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://github.com/shahbazhassan42000/darul-uloom/commits/main"));
                startActivity(intent);
                break;
        }

    }
}