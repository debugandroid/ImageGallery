package com.debugandroid.imagegallery;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Pawan on 2/20/2016.
 */
public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  List<ImageItem> videoList;
    Context context;
    private final static int FADE_DURATION = 1000;
    public static Glide glid;
    String name;
    Bundle bundle=new Bundle();
    private static final int TYPE_ITEM = 1;
    public MyAdapter(List<ImageItem> videoList, Context context) {
        this.videoList = videoList;
        this.context=context;
    }
    @Override
    public int getItemViewType(int position) {
        return TYPE_ITEM;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {


            View itemView = LayoutInflater.
                    from(viewGroup.getContext()).
                    inflate(R.layout.item_view, viewGroup, false);

            VideoViewHolder holder = new VideoViewHolder(itemView);
            itemView.setTag(holder);

            return holder;
        }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


            ImageItem mediaObject=getItem(position);
            name=mediaObject.getDISPLAY_NAME();
            if (name.length() > 25) {
             ((VideoViewHolder) holder).vName.setText(name.substring(0, 25) + "..");
             } else {
              ((VideoViewHolder) holder).vName.setText(name);
             }

            ((VideoViewHolder) holder).vImage.setImageResource(R.color.cardview_dark_background);
            ((VideoViewHolder) holder).vFilePath = mediaObject.getDATA();
            ((VideoViewHolder) holder).context = context;
            ((VideoViewHolder) holder).position = position;

            glid.with(context)
                    .load(mediaObject.getDATA())
                    .centerCrop()
                    .placeholder(R.color.cardview_dark_background)
                    .crossFade()
                    .into(((VideoViewHolder) holder).vImage);

            setScaleAnimation(((VideoViewHolder) holder).vImage);

    }

    private void setScaleAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }
    private void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    @Override
    public int getItemCount() {

        int itemCount = videoList.size();
        return itemCount;
    }


    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    private boolean isPositionFooter(int position) {
        return position == getItemCount() - 1;
    }

    protected ImageItem getItem(int position) {
        return  videoList.get(position);
    }

    private int getItemPosition(int position){
        return position;
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {
        protected ImageView vImage;
        protected TextView vName;
        protected String vFilePath;
        protected  Context context;
        protected int position;
        public VideoViewHolder(View v) {
            super(v);
            vName =  (TextView) v.findViewById(R.id.media_name);
            vImage = (ImageView)  v.findViewById(R.id.media_img_bck);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(vFilePath));
                    intent.setDataAndType(Uri.parse(vFilePath), "image/*");
                    context.startActivity(intent);
                }
            });
        }


    }
}
