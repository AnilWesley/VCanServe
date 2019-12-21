package com.vupadhi.heyhelp.Adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.models.PackageSelectionModel;


public class UltraPremiumPkgRecyclerAdapter extends RecyclerView.Adapter<UltraPremiumPkgRecyclerAdapter.UltraPremiumPkgAdapterView> {


    LayoutInflater inflater;
    Context context;
    PackageSelectionModel packageSelectionModel;


    public UltraPremiumPkgRecyclerAdapter(PackageSelectionModel packageSelectionModel, Context context) {
        this.context = context;
        this.packageSelectionModel = packageSelectionModel;
        inflater = LayoutInflater.from(context);
    }


    private UltraPremiumPkgRecyclerAdapter.OnitemClickListener mListner;

    public void setOnItemClickListener(UltraPremiumPkgRecyclerAdapter.OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public UltraPremiumPkgRecyclerAdapter.UltraPremiumPkgAdapterView onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.ultrapremiumpkg_adapter, parent, false);
        return new UltraPremiumPkgRecyclerAdapter.UltraPremiumPkgAdapterView(itemView);
    }

    @Override
    public void onBindViewHolder(final UltraPremiumPkgRecyclerAdapter.UltraPremiumPkgAdapterView holder, final int position) {


            holder.text.setText(packageSelectionModel.getData().getUltraPremiumPackage().get(position).getUltraPremiumPackage());


    }


    @Override
    public int getItemCount() {
        return packageSelectionModel.getData().getPremiumPackage().size();
    }



    public class UltraPremiumPkgAdapterView extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView text;
        RadioButton radio_but;


        public UltraPremiumPkgAdapterView(View itemView) {
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
