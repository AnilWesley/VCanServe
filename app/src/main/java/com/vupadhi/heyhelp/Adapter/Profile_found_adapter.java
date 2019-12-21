package com.vupadhi.heyhelp.Adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.models.ProfilesModel;

public class Profile_found_adapter extends RecyclerView.Adapter<Profile_found_adapter.ViewHolder> {
    String onclick = "1";
    Context context;

    ProfilesModel profilesModel;

    public Profile_found_adapter(ProfilesModel profilesModel,Context context) {
        this.context = context;
        this.profilesModel = profilesModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.profile_found_adapter_lay_out, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.profilenum_tv.setText("Profile "+(i+1));
        viewHolder.rating_tv.setText(profilesModel.getData().getSetlist().get(i).getWorkerRating());
        viewHolder.name_tv.setText(profilesModel.getData().getSetlist().get(i).getNWorkerName());
        viewHolder.ratingBar.setRating(Float.parseFloat(profilesModel.getData().getSetlist().get(i).getWorkerRating()));

        viewHolder.imageView_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onclick.equalsIgnoreCase("1")) {
                    viewHolder.imageView_two.setImageResource(R.drawable.right_blue_img);
                    onclick = "2";

                } else {
                    viewHolder.imageView_two.setImageResource(R.drawable.blue_plus);
                    onclick = "1";
                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return profilesModel.getData().getSetlist().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView_one, imageView_two;
        RatingBar ratingBar;
        String onclick = "1";

        TextView rating_tv,profilenum_tv,name_tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView_one = itemView.findViewById(R.id.img_view_one);
            imageView_two = itemView.findViewById(R.id.img_view_two);
            ratingBar = itemView.findViewById(R.id.rating_bar);
            rating_tv = itemView.findViewById(R.id.rating_tv);
            profilenum_tv = itemView.findViewById(R.id.profilenum_tv);
            name_tv = itemView.findViewById(R.id.name_tv);

        }
    }
}