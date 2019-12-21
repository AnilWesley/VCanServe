package com.vupadhi.heyhelp.Adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.models.MarkAbsentWorkersModel;

public class Mark_absent_adapter extends RecyclerView.Adapter<Mark_absent_adapter.ViewHolder> {
    Context context;
    MarkAbsentWorkersModel markAbsentWorkersModel;

    public Mark_absent_adapter(MarkAbsentWorkersModel markAbsentWorkersModel,Context context) {

        this.context = context;
        this.markAbsentWorkersModel = markAbsentWorkersModel;
    }

    private Mark_absent_adapter.OnitemClickListener mListner;

    public void setOnItemClickListener(Mark_absent_adapter.OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.mark_absent_adapter_lay_out,viewGroup,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.workername_tv.setText(markAbsentWorkersModel.getData().getWorkerList().get(i).getWorkerName());
    }

    @Override
    public int getItemCount() {
        return markAbsentWorkersModel.getData().getWorkerList().size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView workername_tv;
        ImageView calndr_img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            workername_tv=itemView.findViewById(R.id.workername_tv);
            calndr_img=itemView.findViewById(R.id.calndr_img);

            calndr_img.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
