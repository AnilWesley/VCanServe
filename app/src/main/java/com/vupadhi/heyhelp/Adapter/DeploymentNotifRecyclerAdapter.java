package com.vupadhi.heyhelp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.models.DeploymentNotifModel;


public class DeploymentNotifRecyclerAdapter extends RecyclerView.Adapter<DeploymentNotifRecyclerAdapter.DeploymentNotifAdapterView> {


    LayoutInflater inflater;
    Context context;
    DeploymentNotifModel deploymentNotifModel;


    public DeploymentNotifRecyclerAdapter(DeploymentNotifModel deploymentNotifModel, Context context) {
        this.context = context;
        this.deploymentNotifModel = deploymentNotifModel;
        inflater = LayoutInflater.from(context);
    }


    private DeploymentNotifRecyclerAdapter.OnitemClickListener mListner;

    public void setOnItemClickListener(DeploymentNotifRecyclerAdapter.OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public DeploymentNotifRecyclerAdapter.DeploymentNotifAdapterView onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.deploymentnotif_adapter, parent, false);
        return new DeploymentNotifRecyclerAdapter.DeploymentNotifAdapterView(itemView);
    }

    @Override
    public void onBindViewHolder(final DeploymentNotifRecyclerAdapter.DeploymentNotifAdapterView holder, final int position) {


        holder.notif_tv.setText(deploymentNotifModel.getData().getNotification().get(position).getSName());

    }


    @Override
    public int getItemCount() {
        return deploymentNotifModel.getData().getNotification().size();
    }



    public class DeploymentNotifAdapterView extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView notif_tv;


        public DeploymentNotifAdapterView(View itemView) {
            super(itemView);

            notif_tv = (itemView).findViewById(R.id.notif_tv);
        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
