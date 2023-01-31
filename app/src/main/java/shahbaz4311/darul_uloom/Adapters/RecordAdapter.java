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
        holder.date.setText(holder.record.getDate());
        holder.sabaq.setText(holder.record.getSabaq());
        holder.start.setText(holder.record.getStart());
        holder.end.setText(holder.record.getEnd());
        holder.sabqi.setText(holder.record.isSabqi()?String.valueOf(R.string.yes):String.valueOf(R.string.no));
        holder.manzil.setText(holder.record.getManzil());

    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    public static class RecordViewHolder extends RecyclerView.ViewHolder {
        Record record;
        TextView date,sabaq,start,end,sabqi,manzil;
        public RecordViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            sabaq = itemView.findViewById(R.id.sabaq);
            start = itemView.findViewById(R.id.start);
            end = itemView.findViewById(R.id.end);
            sabqi = itemView.findViewById(R.id.sabqi);
            manzil = itemView.findViewById(R.id.manzil);
        }
    }
}
