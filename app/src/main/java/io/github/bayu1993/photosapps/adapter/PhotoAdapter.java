package io.github.bayu1993.photosapps.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.github.bayu1993.photosapps.R;
import io.github.bayu1993.photosapps.model.PhotoModel;

/**
 * Created by Bayu teguh pamuji on 10/4/18.
 * email : bayuteguhpamuji@gmail.com.
 */
public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {
    private ArrayList<PhotoModel> photoModelsList;

    public PhotoAdapter(ArrayList<PhotoModel> photoModelsList) {
        this.photoModelsList = photoModelsList;
    }

    @NonNull
    @Override
    public PhotoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_photos, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoAdapter.ViewHolder holder, int position) {
        holder.bind(photoModelsList.get(position));
    }

    @Override
    public int getItemCount() {
        return photoModelsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private ImageView imgPhoto;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            imgPhoto = itemView.findViewById(R.id.img_photo);
        }

        void bind(PhotoModel photoModel) {
            tvTitle.setText(photoModel.getTitle());
            Picasso.get().load(photoModel.getUrl()).into(imgPhoto);
        }
    }
}
