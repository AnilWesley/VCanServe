package com.vupadhi.heyhelp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.models.OffersModel;


public class OffersRecyclerAdapter extends RecyclerView.Adapter<OffersRecyclerAdapter.OffersAdapterView> {


    LayoutInflater inflater;
    Context context;
    OffersModel offersModel;


    public OffersRecyclerAdapter(OffersModel offersModel, Context context) {
        this.context = context;
        this.offersModel = offersModel;
        inflater = LayoutInflater.from(context);
    }


    private OffersRecyclerAdapter.OnitemClickListener mListner;

    public void setOnItemClickListener(OffersRecyclerAdapter.OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public OffersRecyclerAdapter.OffersAdapterView onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.offer_adapter, parent, false);
        return new OffersRecyclerAdapter.OffersAdapterView(itemView);
    }

    @Override
    public void onBindViewHolder(final OffersRecyclerAdapter.OffersAdapterView holder, final int position) {


        holder.main_title.setText(offersModel.getData().getPromotionsAndOffers().get(position).getOfferCode());
        holder.desc_title1.setText(offersModel.getData().getPromotionsAndOffers().get(position).getOfferDescription());
        holder.desc_title2.setText("Valid till "+offersModel.getData().getPromotionsAndOffers().get(position).getValidDate());

    }


    @Override
    public int getItemCount() {
        return offersModel.getData().getPromotionsAndOffers().size();
    }



    public class OffersAdapterView extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView main_title,desc_title1,desc_title2;


        public OffersAdapterView(View itemView) {
            super(itemView);

            main_title = (itemView).findViewById(R.id.main_title);
            desc_title1 = (itemView).findViewById(R.id.desc_title1);
            desc_title2 = (itemView).findViewById(R.id.desc_title2);

        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
