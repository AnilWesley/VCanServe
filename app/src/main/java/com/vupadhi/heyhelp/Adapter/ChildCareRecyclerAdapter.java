package com.vupadhi.heyhelp.Adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.dao.AdapterCallback;
import com.vupadhi.heyhelp.models.ChildCareScreenModel;
import com.vupadhi.heyhelp.sharepref.UserSession;
import com.vupadhi.heyhelp.ui.activity.Child_care;

import java.util.ArrayList;
import java.util.List;


public class ChildCareRecyclerAdapter extends RecyclerView.Adapter<ChildCareRecyclerAdapter.ChildCareAdapterView> {


    LayoutInflater inflater;
    Context context;

    ChildCareScreenModel childCareScreenModel;

    public static List<ChildCareScreenModel.DataBean.SubservicelistBean> servicelist;


    private UserSession userSession;
    private ArrayList<String> myList;
    AdapterCallback adapterCallback;

    public ChildCareRecyclerAdapter(List<ChildCareScreenModel.DataBean.SubservicelistBean> childCareScreenModel, Child_care context, AdapterCallback adapterCallback) {
        this.context = context;
        this.servicelist = childCareScreenModel;

        inflater = LayoutInflater.from(context);
        userSession = new UserSession(context);
        this.adapterCallback=adapterCallback;
    }


    private ChildCareRecyclerAdapter.OnitemClickListener mListner;

    public void setOnItemClickListener(ChildCareRecyclerAdapter.OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public ChildCareRecyclerAdapter.ChildCareAdapterView onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.childcarelist_adapter, parent, false);
        return new ChildCareRecyclerAdapter.ChildCareAdapterView(itemView);
    }

    @Override
    public void onBindViewHolder(final ChildCareRecyclerAdapter.ChildCareAdapterView holder, final int position) {

        if (position > 1) {
            holder.linearlayout.setVisibility(View.VISIBLE);


            holder.tv1.setText(servicelist.get(position).getSSubServiceName());

            //final SelectExamModel.ResponseBean model = examslist.get(position);
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

//        if (stringlist != null) {
//            for (int i = 0; i < examslist.size(); i++) {
//                if (examslist.get(position).getName().equalsIgnoreCase(stringlist.get(i))) {
//                    examslist.get(position).setClicked(true);
//                } else {
//                    examslist.get(position).setClicked(false);
//                }
//            }
//        }
        }else {

            holder.linearlayout.setVisibility(View.GONE);
        }

    }


    @Override
    public int getItemCount() {
        return servicelist.size();
    }

    public ArrayList<ChildCareScreenModel.DataBean.SubservicelistBean> getselectedList() {
        ArrayList<ChildCareScreenModel.DataBean.SubservicelistBean> newSelctedList = new ArrayList<>();
        for (int y = 0; y < servicelist.size(); y++) {
            if (servicelist.get(y).isClicked()) {
                newSelctedList.add(servicelist.get(y));
            }
        }

        return newSelctedList;


    }

    public class ChildCareAdapterView extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv1;
        ImageView switch_button_one;
        LinearLayout linearlayout;


        public ChildCareAdapterView(View itemView) {
            super(itemView);

            tv1 = itemView.findViewById(R.id.tv1);
            switch_button_one = itemView.findViewById(R.id.switch_button_one);
            linearlayout = itemView.findViewById(R.id.linearlayout);

            //eamcet_tik.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
