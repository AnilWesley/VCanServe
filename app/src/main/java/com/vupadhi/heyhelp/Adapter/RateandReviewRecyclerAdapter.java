package com.vupadhi.heyhelp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.dao.AdapterratingCallback;
import com.vupadhi.heyhelp.models.RateandReviewModel;


public class RateandReviewRecyclerAdapter extends RecyclerView.Adapter<RateandReviewRecyclerAdapter.RateandReviewAdapterView> {


    LayoutInflater inflater;
    Context context;
    public static RateandReviewModel rateandReviewModel;
    AdapterratingCallback adapterratingCallback;
    String ratingvalue;
    int rating;


    public RateandReviewRecyclerAdapter(RateandReviewModel rateandReviewModel, Context context, AdapterratingCallback adapterratingCallback) {
        this.context = context;
        this.rateandReviewModel = rateandReviewModel;
        this.adapterratingCallback = adapterratingCallback;
        inflater = LayoutInflater.from(context);
    }


    private RateandReviewRecyclerAdapter.OnitemClickListener mListner;

    public void setOnItemClickListener(RateandReviewRecyclerAdapter.OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public RateandReviewRecyclerAdapter.RateandReviewAdapterView onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.rateandreview_adapter, parent, false);
        return new RateandReviewRecyclerAdapter.RateandReviewAdapterView(itemView);
    }

    @Override
    public void onBindViewHolder(final RateandReviewRecyclerAdapter.RateandReviewAdapterView holder, final int position) {

        holder.workername_tv.setText(rateandReviewModel.getData().getWorkerRatingVM().getWorkerList().get(position).getWorkerName());

        holder.rating_bar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                if (rating == 1.0) {
                    ratingvalue = "1.0";

                } else if (rating == 2.0) {

                    ratingvalue = "2.0";
                } else if (rating == 3.0) {
                    ratingvalue = "3.0";

                } else if (rating == 4.0) {

                    ratingvalue = "4.0";

                } else if (rating == 5.0) {
                    ratingvalue = "5.0";
                }

                if(adapterratingCallback!=null){
                    adapterratingCallback.onClickCallback(ratingvalue,position,holder.rating_bar);


                }

            }
        });

    }


    @Override
    public int getItemCount() {
        return rateandReviewModel.getData().getWorkerRatingVM().getWorkerList().size();
    }

    public class RateandReviewAdapterView extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView workername_tv;
        RatingBar rating_bar;


        public RateandReviewAdapterView(View itemView) {
            super(itemView);

            workername_tv = (itemView).findViewById(R.id.workername_tv);
            rating_bar = (itemView).findViewById(R.id.rating_bar);

        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
