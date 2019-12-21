package com.vupadhi.heyhelp.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.models.Individual_locModel;


public class LocationsRecyclerAdapter extends RecyclerView.Adapter<LocationsRecyclerAdapter.LocationsAdapterView> {


    LayoutInflater inflater;
    Context context;
    Individual_locModel individual_locModel;


    public LocationsRecyclerAdapter(Individual_locModel individual_locModel, Context context) {
        this.context = context;
        this.individual_locModel = individual_locModel;
        inflater = LayoutInflater.from(context);
    }


    private LocationsRecyclerAdapter.OnitemClickListener mListner;

    public void setOnItemClickListener(LocationsRecyclerAdapter.OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public LocationsRecyclerAdapter.LocationsAdapterView onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.location_items, parent, false);
        return new LocationsRecyclerAdapter.LocationsAdapterView(itemView);
    }

    @Override
    public void onBindViewHolder(final LocationsRecyclerAdapter.LocationsAdapterView holder, final int position) {

        holder.locname.setText(individual_locModel.getData().getIndividualSelection().getSLocation().get(position).getSLocationName());

    }


    @Override
    public int getItemCount() {
        return individual_locModel.getData().getIndividualSelection().getSLocation().size();
    }



    public class LocationsAdapterView extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView locname;


        public LocationsAdapterView(View itemView) {
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
