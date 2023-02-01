package shahbaz4311.darul_uloom.Utils;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import shahbaz4311.darul_uloom.Models.Record;
import shahbaz4311.darul_uloom.Models.Student;
import shahbaz4311.darul_uloom.R;

public class Add_record_dialog extends DialogFragment implements TextWatcher {
    private final Student student;
    private  List<Record> records;
    DBMS dbms;
    EditText sabaq, start, end, manzil;
    CheckBox sabqi;

    public Add_record_dialog(Student student, List<Record> records) {
        this.student = student;
        this.records=records;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dbms = new DBMS(getActivity());
        //dialog box for adding student
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AlertDialogTheme);
        //modified title for the dialog box
        TextView title = new TextView(getActivity());
        title.setText(R.string.today_record);
        title.setTextSize(30);
        title.setGravity(Gravity.START);
        title.setPadding(60, 60, 65, 20);
        //setting title icon
        Drawable icon = ContextCompat.getDrawable(getActivity(), R.drawable.add_record_icon_v2);
        icon.setBounds(0, 0, 100, 100);
        title.setCompoundDrawables(null, null, icon, null);
        title.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
        title.setCompoundDrawablePadding(20);
        //setting the title for the dialog box
        builder.setCustomTitle(title);
        LayoutInflater inflater = getLayoutInflater();
        final View view = inflater.inflate(R.layout.add_record, null);
        builder.setView(view);

        //initializing
        sabaq = view.findViewById(R.id.sabaq);
        start = view.findViewById(R.id.start);
        end = view.findViewById(R.id.end);
        sabqi = view.findViewById(R.id.sabqi);
        manzil = view.findViewById(R.id.manzil);

        //setting on text change listener
        sabaq.addTextChangedListener(this);
        start.addTextChangedListener(this);
        end.addTextChangedListener(this);
        manzil.addTextChangedListener(this);


        //setting on click listener for the dialog box to register student
        builder.setPositiveButton(R.string.register, (dialog, which) -> {
        });
        builder.setOnDismissListener(dialog -> {
        });
        //setting on click listener for the dialog box to cancel the dialog box
        builder.setNegativeButton(R.string.cancel, (dialog, which) -> dialog.dismiss());

        //make it non cancelable
        builder.setCancelable(false);
        return builder.create();
    }

    @SuppressLint("SimpleDateFormat")
    @Override
    public void onResume() {
        super.onResume();
        // disable positive button by default
        AlertDialog dialog = (AlertDialog) getDialog();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);

        //setting on click listener for the dialog box to register student
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {
            //disable both buttons
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setEnabled(false);
            //add record to the database
            //check if the required fields are not empty
            if (validateInputs()) {
                //create record object
                Record record = new Record();
                record.setDate(new SimpleDateFormat("MMMM dd, yyyy").format(new Date()));
                record.setSabaq(Integer.parseInt(sabaq.getText().toString().trim()));
                record.setStart(start.getText().toString().trim());
                record.setEnd(end.getText().toString().trim());
                record.setSabqi(sabqi.isChecked());
                record.setManzil(Integer.parseInt(manzil.getText().toString().trim()));
                record.setStudent_id(student.getId());

                //add record to the database
                int id = dbms.addRecord(record);
                Toast.makeText(getActivity(), id != -1 ? "ریکارڈ شامل کر دیا گیا یے":"ریکارڈ کا اندراج ناکام", Toast.LENGTH_SHORT).show();
                if(id!=-1){
                    record.setId(id);
                    records.add(record);
                }
            }
            //enable both buttons
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setEnabled(true);
            //dismiss the dialog box
            dialog.dismiss();
        });

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        AlertDialog dialog = (AlertDialog) getDialog();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(validateInputs());
    }

    private boolean validateInputs() {
        if (sabaq.getText().toString().trim().isEmpty()) {
            sabaq.setError(getString(R.string.sabaq_error));
            return false;
        }
        if (start.getText().toString().trim().isEmpty()) {
            start.setError(getString(R.string.start_error));
            return false;
        }
        if (end.getText().toString().trim().isEmpty()) {
            end.setError(getString(R.string.end_error));
            return false;
        }
        if (manzil.getText().toString().trim().isEmpty()) {
            manzil.setError(getString(R.string.manzil_error));
            return false;
        }
        return true;
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
