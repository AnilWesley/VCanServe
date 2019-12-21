package com.vupadhi.heyhelp.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.models.PackageSelectionModel;


public class PremiumPkgRecyclerAdapter extends RecyclerView.Adapter<PremiumPkgRecyclerAdapter.PremiumPkgAdapterView> {


    LayoutInflater inflater;
    Context context;
    PackageSelectionModel packageSelectionModel;


    public PremiumPkgRecyclerAdapter(PackageSelectionModel packageSelectionModel, Context context) {
        this.context = context;
        this.packageSelectionModel = packageSelectionModel;
        inflater = LayoutInflater.from(context);
    }


    private PremiumPkgRecyclerAdapter.OnitemClickListener mListner;

    public void setOnItemClickListener(PremiumPkgRecyclerAdapter.OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public PremiumPkgRecyclerAdapter.PremiumPkgAdapterView onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.premiumpkg_adapter, parent, false);
        return new PremiumPkgRecyclerAdapter.PremiumPkgAdapterView(itemView);
    }

    @Override
    public void onBindViewHolder(final PremiumPkgRecyclerAdapter.PremiumPkgAdapterView holder, final int position) {


            holder.text.setText(packageSelectionModel.getData().getPremiumPackage().get(position).getPremiumPackage());


    }


    @Override
    public int getItemCount() {
        return packageSelectionModel.getData().getPremiumPackage().size();
    }



    public class PremiumPkgAdapterView extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView text;
        RadioButton radio_but;


        public PremiumPkgAdapterView(View itemView) {
            super(itemView);

            text = (itemView).findViewById(R.id.text);
            radio_but = (itemView).findViewById(R.id.radio_but);

        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
