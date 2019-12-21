package com.vupadhi.heyhelp.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.models.Company_locModel;


public class CompanyLocationsRecyclerAdapter extends RecyclerView.Adapter<CompanyLocationsRecyclerAdapter.LocationsAdapterView> {


    LayoutInflater inflater;
    Context context;
    Company_locModel company_locModel;


    public CompanyLocationsRecyclerAdapter(Company_locModel company_locModel, Context context) {
        this.context = context;
        this.company_locModel = company_locModel;
        inflater = LayoutInflater.from(context);
    }


    private CompanyLocationsRecyclerAdapter.OnitemClickListener mListner;

    public void setOnItemClickListener(CompanyLocationsRecyclerAdapter.OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public CompanyLocationsRecyclerAdapter.LocationsAdapterView onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.location_items, parent, false);
        return new CompanyLocationsRecyclerAdapter.LocationsAdapterView(itemView);
    }

    @Override
    public void onBindViewHolder(final CompanyLocationsRecyclerAdapter.LocationsAdapterView holder, final int position) {

        holder.locname.setText(company_locModel.getData().getCompanySelection().getSLocation().get(position).getSLocationName());

    }


    @Override
    public int getItemCount() {
        return company_locModel.getData().getCompanySelection().getSLocation().size();
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
