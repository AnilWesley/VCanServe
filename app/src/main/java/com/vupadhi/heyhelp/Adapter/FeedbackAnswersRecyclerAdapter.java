package com.vupadhi.heyhelp.Adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.dao.AdapterCallback;
import com.vupadhi.heyhelp.models.FeedbackModel;


public class FeedbackAnswersRecyclerAdapter extends RecyclerView.Adapter<FeedbackAnswersRecyclerAdapter.FeedbackAdapterView> {


    LayoutInflater inflater;
    Context context;
    FeedbackModel feedbackModel;
    public static int grpposition;
    public static int clickpos = -1;

    AdapterCallback adapterCallback;


    public FeedbackAnswersRecyclerAdapter(FeedbackModel feedbackModel, Context context, int grpposition, AdapterCallback adapterCallback) {
        this.context = context;
        this.feedbackModel = feedbackModel;
        this.grpposition = grpposition;
        inflater = LayoutInflater.from(context);
        this.adapterCallback = adapterCallback;

        clickpos = -1;
    }


    private FeedbackAnswersRecyclerAdapter.OnitemClickListener mListner;


    public void setOnItemClickListener(FeedbackAnswersRecyclerAdapter.OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public FeedbackAnswersRecyclerAdapter.FeedbackAdapterView onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.feedbackanswers_adapter, parent, false);
        return new FeedbackAnswersRecyclerAdapter.FeedbackAdapterView(itemView);
    }

    @Override
    public void onBindViewHolder(final FeedbackAnswersRecyclerAdapter.FeedbackAdapterView holder, final int position) {

        holder.answer_tv.setText(FeedbackRecyclerAdapter.feedbackModel.getData().getFeedback().getFQuestionList().get(FeedbackRecyclerAdapter.groupposition).getFAnswersList().get(position).getSAnswerName());
        holder.groupostn_tv.setText(String.valueOf(FeedbackRecyclerAdapter.groupposition));

        if (FeedbackRecyclerAdapter.feedbackModel.getData().getFeedback().getFQuestionList().get(FeedbackRecyclerAdapter.groupposition).getFAnswersList().get(position).isSelected()) {
            holder.linear_ll.setBackground(context.getResources().getDrawable(R.drawable.selected_blue_line_shape));
        } else {
            holder.linear_ll.setBackground(context.getResources().getDrawable(R.drawable.selected_white));
        }

        holder.linear_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(adapterCallback!=null){
                    adapterCallback.onClickCallback(Integer.parseInt(holder.groupostn_tv.getText().toString()),position,holder.linear_ll);

                    Log.d("grouppos", String.valueOf(grpposition));
                    Log.d("childpos", String.valueOf(position));

                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return feedbackModel.getData().getFeedback().getFQuestionList().get(grpposition).getFAnswersList().size();
    }


    public class FeedbackAdapterView extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView answer_tv, groupostn_tv;
        LinearLayout linear_ll;


        public FeedbackAdapterView(View itemView) {
            super(itemView);

            answer_tv = (itemView).findViewById(R.id.answer_tv);
            groupostn_tv = (itemView).findViewById(R.id.groupostn_tv);
            linear_ll = (itemView).findViewById(R.id.linear_ll);
            linear_ll.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
