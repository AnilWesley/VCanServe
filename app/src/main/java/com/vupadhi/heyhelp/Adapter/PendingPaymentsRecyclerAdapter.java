package com.vupadhi.heyhelp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.models.PendingPayModel;


public class PendingPaymentsRecyclerAdapter extends RecyclerView.Adapter<PendingPaymentsRecyclerAdapter.PendingPaymentsAdapterView> {


    LayoutInflater inflater;
    Context context;
    PendingPayModel pendingPayModel;


    public PendingPaymentsRecyclerAdapter(PendingPayModel pendingPayModel, Context context) {
        this.context = context;
        this.pendingPayModel = pendingPayModel;
        inflater = LayoutInflater.from(context);
    }


    private PendingPaymentsRecyclerAdapter.OnitemClickListener mListner;

    public void setOnItemClickListener(PendingPaymentsRecyclerAdapter.OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public PendingPaymentsRecyclerAdapter.PendingPaymentsAdapterView onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.pendingpay_adapter, parent, false);
        return new PendingPaymentsRecyclerAdapter.PendingPaymentsAdapterView(itemView);
    }

    @Override
    public void onBindViewHolder(final PendingPaymentsRecyclerAdapter.PendingPaymentsAdapterView holder, final int position) {


        holder.service_text.setText(pendingPayModel.getData().getPendingPaymnets().get(position).getSServiceName());
        holder.amount_text.setText(pendingPayModel.getData().getPendingPaymnets().get(position).getAmount());
        holder.pay_text.setText(pendingPayModel.getData().getPendingPaymnets().get(position).getPendingHistory());

    }


    @Override
    public int getItemCount() {
        return pendingPayModel.getData().getPendingPaymnets().size();
    }



    public class PendingPaymentsAdapterView extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView service_text,amount_text,pay_text;


        public PendingPaymentsAdapterView(View itemView) {
            super(itemView);

            service_text = (itemView).findViewById(R.id.service_text);
            amount_text = (itemView).findViewById(R.id.amount_text);
            pay_text = (itemView).findViewById(R.id.pay_text);

        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
