package com.example.pacinterviewtesting.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pacinterviewtesting.R;
import com.example.pacinterviewtesting.model.detailMovie.Cast;
import com.example.pacinterviewtesting.model.movieList.Result;
import com.example.pacinterviewtesting.movieList.MovieListActivity;
import com.example.pacinterviewtesting.network.ApiClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class CastAdapter extends RecyclerView.Adapter<CastAdapter.CustomViewHolder> {

    ApiClient apiClient;
    private Context mContext;
    private ArrayList<Cast> castList;

    public CastAdapter(Context mContext, ArrayList<Cast> castList) {
        this.mContext = mContext;
        this.castList = castList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public TextView tvCharacter;

        public TextView tvName;

        public ImageView ivProfilePic;


        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            tvCharacter = itemView.findViewById(R.id.tv_character);
            tvName = itemView.findViewById(R.id.tv_name);
            ivProfilePic = itemView.findViewById(R.id.iv_profile_pic);

        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cast_card_row, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Cast cast = castList.get(position);
        Picasso.get().load(ApiClient.IMAGE_BASE_URL+cast.getProfilePath()).into(holder.ivProfilePic);

        holder.tvCharacter.setText(cast.getCharacter());
        holder.tvName.setText(cast.getName());

    }


    @Override
    public int getItemCount() {
        return castList.size();
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public void removeItem(int position) {
        castList.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(Cast item, int position) {
        castList.add(position, item);
        notifyItemInserted(position);
    }

    public ArrayList<Cast> getData() {
        return castList;
    }

}
