package com.vupadhi.heyhelp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.models.MyServicerequestsModel;


public class ServiceRequestsRecyclerAdapter extends RecyclerView.Adapter<ServiceRequestsRecyclerAdapter.ServiceRequestsAdapterView> {


    LayoutInflater inflater;
    Context context;
    MyServicerequestsModel myServicerequestsModel;


    public ServiceRequestsRecyclerAdapter(MyServicerequestsModel myServicerequestsModel, Context context) {
        this.context = context;
        this.myServicerequestsModel = myServicerequestsModel;
        inflater = LayoutInflater.from(context);
    }


    private ServiceRequestsRecyclerAdapter.OnitemClickListener mListner;

    public void setOnItemClickListener(ServiceRequestsRecyclerAdapter.OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public ServiceRequestsRecyclerAdapter.ServiceRequestsAdapterView onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.myservicereq_adapter, parent, false);
        return new ServiceRequestsRecyclerAdapter.ServiceRequestsAdapterView(itemView);
    }

    @Override
    public void onBindViewHolder(final ServiceRequestsRecyclerAdapter.ServiceRequestsAdapterView holder, final int position) {


            holder.servicerequest_text.setText(myServicerequestsModel.getData().getStatusVM().get(position).getServiceName());
            holder.status_text.setText(myServicerequestsModel.getData().getStatusVM().get(position).getStatus());

            if (myServicerequestsModel.getData().getStatusVM().get(position).getStatus()!=null) {
                if (myServicerequestsModel.getData().getStatusVM().get(position).getStatus().equalsIgnoreCase("underProcess")) {

                    holder.service_pay_now_lay_out.setVisibility(View.VISIBLE);
                } else {

                    holder.service_pay_now_lay_out.setVisibility(View.GONE);

                }
            }

    }


    @Override
    public int getItemCount() {
        return myServicerequestsModel.getData().getStatusVM().size();
    }



    public class ServiceRequestsAdapterView extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView servicerequest_text,status_text;
        LinearLayout service_pay_now_lay_out;



        public ServiceRequestsAdapterView(View itemView) {
            super(itemView);

            servicerequest_text = (itemView).findViewById(R.id.servicerequest_text);
            status_text = (itemView).findViewById(R.id.status_text);
            service_pay_now_lay_out = (itemView).findViewById(R.id.service_pay_now_lay_out);

        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
