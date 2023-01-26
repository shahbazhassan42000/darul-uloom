package shahbaz4311.darul_uloom;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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
                //do something
                break;
            case R.id.github_btn:
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://github.com/shahbazhassan42000/darul-uloom/commits/main"));
                startActivity(intent);
                break;
        }

    }

    private void add_student() {
        //dialog box for adding student
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        //modified title for the dialog box
        TextView title = new TextView(MainActivity.this);
        title.setText(R.string.add_student);
        title.setTextSize(24);
        title.setGravity(Gravity.START);
        title.setPadding(60, 60, 30, 20);
        title.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.add_student_icon,0);
        title.setCompoundDrawablePadding(20);
        //setting the title for the dialog box
        builder.setCustomTitle(title);

        //creating edit text for the dialog box
        final EditText name = new EditText(MainActivity.this);
        name.setHint(R.string.student_name);
        name.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(name);

        //setting on click listener for the dialog box to register student
        builder.setPositiveButton(R.string.register, (dialog, which) -> {
            //check if the name is empty display error
            if (name.getText().toString().isEmpty()){
                name.setError("abc");
            }else{
                //if not empty then register the student
                //do something

            }
        });

        //setting on click listener for the dialog box to cancel the dialog box
        builder.setNegativeButton(R.string.cancel, (dialog, which) -> dialog.dismiss());

        //make it non cancelable
        builder.setCancelable(false);


        //show the dialog box
        builder.show();
    }
}