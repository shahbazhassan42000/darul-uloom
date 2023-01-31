package shahbaz4311.darul_uloom.Adapters;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import shahbaz4311.darul_uloom.Models.Student;
import shahbaz4311.darul_uloom.R;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

        List<Student> students;
        public StudentAdapter(List<Student> students){
                this.students = students;
        }

        @NonNull
        @Override
        public StudentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_record, parent, false);
                return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull StudentAdapter.ViewHolder holder, int position) {
                holder.student = students.get(position);
                holder.student_name.setText(holder.student.getName());
                holder.student_id.setText(String.valueOf(holder.student.getId()));
        }

        @Override
        public int getItemCount() {
                return students.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
                Student student;
                TextView student_name, student_id;

                public ViewHolder(@NonNull View itemView) {
                        super(itemView);
                        student_name = itemView.findViewById(R.id.student_name);
                        student_id = itemView.findViewById(R.id.student_id);

                        //setting the click listener
                        itemView.setOnClickListener(this);
                }

                @Override
                public void onClick(View v) {
                        Log.d(TAG, student.toString());
                }
        }
}
