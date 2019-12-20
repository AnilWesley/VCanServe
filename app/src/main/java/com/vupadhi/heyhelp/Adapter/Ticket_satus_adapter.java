package com.vupadhi.heyhelp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.models.TicketStatusModel;

public class Ticket_satus_adapter extends RecyclerView.Adapter<Ticket_satus_adapter.ViewHolder> {
    Context context;
    TicketStatusModel ticketStatusModel;

    public Ticket_satus_adapter(TicketStatusModel ticketStatusModel,Context context) {
        this.context = context;
        this.ticketStatusModel = ticketStatusModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.ticket_recycle_adapter_lay_out,viewGroup,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.msg_tv.setText(ticketStatusModel.getData().getTicketsStatus().get(i).getData());
        viewHolder.status_tv.setText(ticketStatusModel.getData().getTicketsStatus().get(i).getNStatus());
    }

    @Override
    public int getItemCount() {
        return ticketStatusModel.getData().getTicketsStatus().size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView msg_tv,status_tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            msg_tv=itemView.findViewById(R.id.msg_tv);
            status_tv=itemView.findViewById(R.id.status_tv);
        }
    }
}
