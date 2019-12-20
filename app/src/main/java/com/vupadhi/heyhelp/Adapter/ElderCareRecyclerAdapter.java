package com.vupadhi.heyhelp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.models.ElderCareModel;
import com.vupadhi.heyhelp.sharepref.UserSession;
import com.vupadhi.heyhelp.ui.activity.Elder_care;

import java.util.ArrayList;
import java.util.List;


public class ElderCareRecyclerAdapter extends RecyclerView.Adapter<ElderCareRecyclerAdapter.ElderCareAdapterView> {


    LayoutInflater inflater;
    Context context;

    ElderCareModel elderCareModel;

    List<ElderCareModel.DataBean.SubservicelistBean> servicelist;


    private UserSession userSession;
    private ArrayList<String> myList;


    public ElderCareRecyclerAdapter(List<ElderCareModel.DataBean.SubservicelistBean> elderCareModel, Elder_care context) {
        this.context = context;
        this.servicelist = elderCareModel;

        inflater = LayoutInflater.from(context);
        userSession = new UserSession(context);
    }


    private ElderCareRecyclerAdapter.OnitemClickListener mListner;

    public void setOnItemClickListener(ElderCareRecyclerAdapter.OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public ElderCareRecyclerAdapter.ElderCareAdapterView onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.eldercarelist_adapter, parent, false);
        return new ElderCareRecyclerAdapter.ElderCareAdapterView(itemView);
    }

    @Override
    public void onBindViewHolder(final ElderCareRecyclerAdapter.ElderCareAdapterView holder, final int position) {


        if (position > 2) {

            holder.linearlayout.setVisibility(View.VISIBLE);

            holder.tv1.setText(servicelist.get(position).getSSubServiceName());


            if (servicelist.get(position).isClicked()) {

                 holder.switch_button_one.setImageResource(R.drawable.switchskyblue);

            } else {
              holder.switch_button_one.setImageResource(R.drawable.switchreverse);

            }


            holder.switch_button_one.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Boolean isselected=   servicelist.get(position).isClicked;
                    if (isselected) {
                        //        holder.switch_button_one.setChecked(false);
                        servicelist.get(position).setClicked(false);

                    } else {

                        //      holder.switch_button_one.setChecked(true);
                        servicelist.get(position).setClicked(true);


                    }
                    notifyItemChanged(position);

                }
            });
        } else {
            holder.linearlayout.setVisibility(View.GONE);
        }

      /*  holder.switch_button_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                servicelist.get(position).setClicked(!servicelist.get(position).isClicked());
                if (!servicelist.get(position).isClicked()) {
                    holder.switch_button_one.setChecked(true);
                    servicelist.get(position).setClicked(true);

                } else if (servicelist.get(position).isClicked()) {

                    holder.switch_button_one.setChecked(false);
                    servicelist.get(position).setClicked(false);


                }
                notifyItemChanged(position);
            }
        });
*/


    }


    @Override
    public int getItemCount() {
        return servicelist.size();
    }

    public ArrayList<ElderCareModel.DataBean.SubservicelistBean> getselectedList() {
        ArrayList<ElderCareModel.DataBean.SubservicelistBean> newSelctedList = new ArrayList<>();
        for (int y = 0; y < servicelist.size(); y++) {
            if (servicelist.get(y).isClicked()) {
                newSelctedList.add(servicelist.get(y));
            }
        }

        return newSelctedList;


    }

    public class ElderCareAdapterView extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv1;
        ImageView switch_button_one;
        LinearLayout linearlayout;


        public ElderCareAdapterView(View itemView) {
            super(itemView);

            tv1 = itemView.findViewById(R.id.tv1);
            switch_button_one = itemView.findViewById(R.id.switch_button_one);
            linearlayout = itemView.findViewById(R.id.linearlayout);

        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
