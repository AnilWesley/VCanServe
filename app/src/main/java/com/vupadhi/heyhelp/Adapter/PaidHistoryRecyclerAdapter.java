package com.vupadhi.heyhelp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.models.PaidHistoryModel;


public class PaidHistoryRecyclerAdapter extends RecyclerView.Adapter<PaidHistoryRecyclerAdapter.PaidHistoryAdapterView> {


    LayoutInflater inflater;
    Context context;
    PaidHistoryModel paidHistoryModel;


    public PaidHistoryRecyclerAdapter(PaidHistoryModel paidHistoryModel, Context context) {
        this.context = context;
        this.paidHistoryModel = paidHistoryModel;
        inflater = LayoutInflater.from(context);
    }


    private PaidHistoryRecyclerAdapter.OnitemClickListener mListner;

    public void setOnItemClickListener(PaidHistoryRecyclerAdapter.OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public PaidHistoryRecyclerAdapter.PaidHistoryAdapterView onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.pendingpay_adapter, parent, false);
        return new PaidHistoryRecyclerAdapter.PaidHistoryAdapterView(itemView);
    }

    @Override
    public void onBindViewHolder(final PaidHistoryRecyclerAdapter.PaidHistoryAdapterView holder, final int position) {


        holder.service_text.setText(paidHistoryModel.getData().getPaidHistory().get(position).getSServiceName());
        holder.amount_text.setText(paidHistoryModel.getData().getPaidHistory().get(position).getAmount());
        holder.pay_text.setText(paidHistoryModel.getData().getPaidHistory().get(position).getPaymentHistory());

    }


    @Override
    public int getItemCount() {
        return paidHistoryModel.getData().getPaidHistory().size();
    }



    public class PaidHistoryAdapterView extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView service_text,amount_text,pay_text;


        public PaidHistoryAdapterView(View itemView) {
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
