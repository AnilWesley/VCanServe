package com.vupadhi.heyhelp.Adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.models.HelpMaidScreenModel;
import com.vupadhi.heyhelp.sharepref.UserSession;
import com.vupadhi.heyhelp.ui.activity.Help_maid_screen;


import java.util.ArrayList;
import java.util.List;


public class HelpMaidRecyclerAdapter extends RecyclerView.Adapter<HelpMaidRecyclerAdapter.HelpMaidAdapterView> {


    LayoutInflater inflater;
    Context context;

    HelpMaidScreenModel helpMaidScreenModel;

    List<HelpMaidScreenModel.DataBean.SubservicelistBean> servicelist;


    private UserSession userSession;
    private ArrayList<String> myList;


    public HelpMaidRecyclerAdapter( List<HelpMaidScreenModel.DataBean.SubservicelistBean> helpMaidScreenModel, Help_maid_screen context) {
        this.context = context;
        this.servicelist = helpMaidScreenModel;

        inflater = LayoutInflater.from(context);
        userSession = new UserSession(context);
    }


    private HelpMaidRecyclerAdapter.OnitemClickListener mListner;

    public void setOnItemClickListener(HelpMaidRecyclerAdapter.OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public HelpMaidRecyclerAdapter.HelpMaidAdapterView onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.help_maid_adapter, parent, false);
        return new HelpMaidRecyclerAdapter.HelpMaidAdapterView(itemView);
    }

    @Override
    public void onBindViewHolder(final HelpMaidRecyclerAdapter.HelpMaidAdapterView holder, final int position) {



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



      /*  holder.switch_button_one.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {

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

*/

    }


    @Override
    public int getItemCount() {
        return servicelist.size();
    }

    public ArrayList<HelpMaidScreenModel.DataBean.SubservicelistBean> getselectedList() {
        ArrayList<HelpMaidScreenModel.DataBean.SubservicelistBean> newSelctedList = new ArrayList<>();
        for (int y = 0; y < servicelist.size(); y++) {
            if (servicelist.get(y).isClicked()) {
                newSelctedList.add(servicelist.get(y));
            }
        }

        return newSelctedList;


    }

    public class HelpMaidAdapterView extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv1;
        ImageView switch_button_one;



        public HelpMaidAdapterView(View itemView) {
            super(itemView);

            tv1=itemView.findViewById(R.id.tv1);
            switch_button_one=itemView.findViewById(R.id.switch_button_one);

        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
