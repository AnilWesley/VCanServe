package com.vupadhi.heyhelp.adapter;

import android.content.Context;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.dao.AdapterCallback;
import com.vupadhi.heyhelp.models.FeedbackModel;


public class FeedbackRecyclerAdapter extends RecyclerView.Adapter<FeedbackRecyclerAdapter.FeedbackAdapterView>  {


    LayoutInflater inflater;
    Context context;
    public static FeedbackModel feedbackModel;
    FeedbackAnswersRecyclerAdapter answersRecyclerAdapter;
    AdapterCallback adapterCallback;
   static int groupposition=-1;


    public FeedbackRecyclerAdapter(FeedbackModel feedbackModel, Context context, AdapterCallback adapterCallback) {
        this.context = context;
        this.feedbackModel = feedbackModel;
        inflater = LayoutInflater.from(context);
        this.adapterCallback =adapterCallback;
    }


    private FeedbackRecyclerAdapter.OnitemClickListener mListner;

    public void setOnItemClickListener(FeedbackRecyclerAdapter.OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }


    public interface OnitemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public FeedbackRecyclerAdapter.FeedbackAdapterView onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.feedbacklayout_adapter, parent, false);
        return new FeedbackRecyclerAdapter.FeedbackAdapterView(itemView);
    }

    @Override
    public void onBindViewHolder(final FeedbackRecyclerAdapter.FeedbackAdapterView holder, final int position) {


        holder.question_tv.setText(feedbackModel.getData().getFeedback().getFQuestionList().get(position).getSName());

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(context);
        //setting horizontal list
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        holder.answers_rv.setLayoutManager(mLayoutManager);
        holder.answers_rv.setItemAnimator(new DefaultItemAnimator());
        groupposition=position;

        //Adding Adapter to recyclerView
        answersRecyclerAdapter = new FeedbackAnswersRecyclerAdapter(feedbackModel, context,position,adapterCallback);
        holder.answers_rv.setAdapter(answersRecyclerAdapter);

        answersRecyclerAdapter.setOnItemClickListener(new FeedbackAnswersRecyclerAdapter.OnitemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                System.out.println("fjdfjkgjdfk;gvjdfkl"+groupposition);
              /* if(adapterCallback!=null){
                   adapterCallback.onClickCallback(position,-1,holder.answers_rv);
               }*/
               // Toast.makeText(context, "group poistion" +position, Toast.LENGTH_SHORT).show();
             //   System.out.println("group posion"+position);
               /* feedbackModel.getData().getFeedback().getFQuestionList().get(position).getFAnswersList().get(position).setSelected(true);
                answersRecyclerAdapter.notifyItemChanged(position);


                for (int i = 0; i < feedbackModel.getData().getFeedback().getFQuestionList().get(position).getFAnswersList().size(); i++) {

                    if (position == i) {
                        feedbackModel.getData().getFeedback().getFQuestionList().get(position).getFAnswersList().get(i).setSelected(true);

                    } else {

                        feedbackModel.getData().getFeedback().getFQuestionList().get(position).getFAnswersList().get(i).setSelected(false);

                    }
                }
                answersRecyclerAdapter.notifyDataSetChanged();
                answersRecyclerAdapter.notifyItemChanged(position);*/

            }
        });


    }


    @Override
    public int getItemCount() {
        return feedbackModel.getData().getFeedback().getFQuestionList().size();
    }



    public class FeedbackAdapterView extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView question_tv;
        RecyclerView answers_rv;


        public FeedbackAdapterView(View itemView) {
            super(itemView);

            question_tv = (itemView).findViewById(R.id.question_tv);
            answers_rv = (itemView).findViewById(R.id.answers_rv);

        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
