package com.vupadhi.heyhelp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.models.PaymentMethodModel;


public class PaymentMethodRecyclerAdapter extends RecyclerView.Adapter<PaymentMethodRecyclerAdapter.PaymentMethodAdapterView> {


    LayoutInflater inflater;
    Context context;
    PaymentMethodModel paymentMethodModel;


    public PaymentMethodRecyclerAdapter(PaymentMethodModel paymentMethodModel, Context context) {
        this.context = context;
        this.paymentMethodModel = paymentMethodModel;
        inflater = LayoutInflater.from(context);
    }


    private PaymentMethodRecyclerAdapter.OnitemClickListener mListner;

    public void setOnItemClickListener(PaymentMethodRecyclerAdapter.OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public PaymentMethodRecyclerAdapter.PaymentMethodAdapterView onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.paymentmethod_adapter, parent, false);
        return new PaymentMethodRecyclerAdapter.PaymentMethodAdapterView(itemView);
    }

    @Override
    public void onBindViewHolder(final PaymentMethodRecyclerAdapter.PaymentMethodAdapterView holder, final int position) {


        holder.service_text.setText(paymentMethodModel.getData().getRegistrationFee().getServicesInclude().get(position).getSubService());

    }


    @Override
    public int getItemCount() {
        return paymentMethodModel.getData().getRegistrationFee().getServicesInclude().size();
    }



    public class PaymentMethodAdapterView extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView service_text;


        public PaymentMethodAdapterView(View itemView) {
            super(itemView);

            service_text = (itemView).findViewById(R.id.service_text);


        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
