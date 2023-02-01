package shahbaz4311.darul_uloom.Utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import shahbaz4311.darul_uloom.R;

public class Add_record_dialog extends DialogFragment {
    DBMS dbms;
    EditText name;

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
        //getting the edit text for the name
        //name = view.findViewById(R.id.student_name_input);


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
            //enable both buttons
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setEnabled(true);
            //dismiss the dialog box
            dialog.dismiss();
        });

    }
}
