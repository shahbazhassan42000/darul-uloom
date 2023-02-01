package shahbaz4311.darul_uloom.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import shahbaz4311.darul_uloom.Models.Record;
import shahbaz4311.darul_uloom.R;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordViewHolder>{
    List<Record> records;

    public RecordAdapter(List<Record> records) {
        this.records = records;
    }

    @NonNull
    @Override
    public RecordAdapter.RecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_record, parent, false);
        return new RecordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordAdapter.RecordViewHolder holder, int position) {
        holder.record = records.get(position);
        holder.date_output.setText(holder.record.getDate());
        holder.sabaq_output.setText(String.valueOf(holder.record.getSabaq()));
        holder.start_output.setText(holder.record.getStart());
        holder.end_output.setText(holder.record.getEnd());
        holder.sabqi_output.setText(holder.record.isSabqi()? "ہاں" : "نہیں");
        holder.manzil_output.setText(String.valueOf(holder.record.getManzil()));

    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    public static class RecordViewHolder extends RecyclerView.ViewHolder {
        Record record;
        TextView date_output,sabaq_output,start_output,end_output,sabqi_output,manzil_output;
        public RecordViewHolder(@NonNull View itemView) {
            super(itemView);
            date_output = itemView.findViewById(R.id.date_output);
            sabaq_output = itemView.findViewById(R.id.sabaq_output);
            start_output = itemView.findViewById(R.id.start_output);
            end_output = itemView.findViewById(R.id.end_output);
            sabqi_output = itemView.findViewById(R.id.sabqi_output);
            manzil_output = itemView.findViewById(R.id.manzil_output);
        }
    }
}
