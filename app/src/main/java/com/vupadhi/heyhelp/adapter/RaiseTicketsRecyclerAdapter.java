package com.vupadhi.heyhelp.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.models.RaiseTicketsModel;


public class RaiseTicketsRecyclerAdapter extends RecyclerView.Adapter<RaiseTicketsRecyclerAdapter.RaiseTicketsAdapterView> {


    LayoutInflater inflater;
    Context context;
    RaiseTicketsModel raiseTicketsModel;


    public RaiseTicketsRecyclerAdapter(RaiseTicketsModel raiseTicketsModel, Context context) {
        this.context = context;
        this.raiseTicketsModel = raiseTicketsModel;
        inflater = LayoutInflater.from(context);
    }


    private RaiseTicketsRecyclerAdapter.OnitemClickListener mListner;

    public void setOnItemClickListener(RaiseTicketsRecyclerAdapter.OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public RaiseTicketsRecyclerAdapter.RaiseTicketsAdapterView onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.location_items, parent, false);
        return new RaiseTicketsRecyclerAdapter.RaiseTicketsAdapterView(itemView);
    }

    @Override
    public void onBindViewHolder(final RaiseTicketsRecyclerAdapter.RaiseTicketsAdapterView holder, final int position) {

        holder.locname.setText(raiseTicketsModel.getData().getServiceCategory().get(position).getName());

    }


    @Override
    public int getItemCount() {
        return raiseTicketsModel.getData().getServiceCategory().size();
    }



    public class RaiseTicketsAdapterView extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView locname;


        public RaiseTicketsAdapterView(View itemView) {
            super(itemView);

            locname = (itemView).findViewById(R.id.locname);
            locname.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
