package com.tabdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.tabdemo.R;
import com.tabdemo.models.ContactInfo;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {
    private ArrayList<ContactInfo> data = new ArrayList<>();
    private Context mContext;

    public MyRecyclerAdapter(Context context) {
        this.mContext = context;
        initImageLoader();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_contact, null);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder customViewHolder, int i) {
        ContactInfo feedItem = data.get(i);

//        //Download image using picasso library
//        Picasso.with(mContext).load(feedItem.getThumbnail())
//                .error(R.drawable.placeholder)
//                .placeholder(R.drawable.placeholder)
//                .into(customViewHolder.imageView);
//
//        //Setting text view title
        customViewHolder.tvName.setText(feedItem.getCaption());
        customViewHolder.tvCountry.setText(feedItem.getCountry_code());

        if(feedItem.getImagebase64()!=null && feedItem.getImagebase64().length()>0)
        {
            imageLoader.displayImage(feedItem.getImagebase64(),customViewHolder.ivProfilePic);
            customViewHolder.tvProfilePic.setVisibility(View.GONE);
            customViewHolder.tvProfilePic.setText("");
        }
        else
        {
            customViewHolder.ivProfilePic.setImageResource(0);
            customViewHolder.tvProfilePic.setVisibility(View.VISIBLE);
            customViewHolder.tvProfilePic.setText("");
            String s[]=feedItem.getCaption().split(" ");
            for (String s1 : s) {
                if(s1.length()>0) {
                    customViewHolder.tvProfilePic.append(s1.charAt(0) + "");
                }
            }


        }

        if(feedItem.getFlagImgBase64()!=null && feedItem.getFlagImgBase64().length()>0)
        {
            imageLoader.displayImage(feedItem.getFlagImgBase64(),customViewHolder.ivFlag);
        }
        else
        {
            customViewHolder.ivFlag.setImageResource(0);
        }

    }

    @Override
    public int getItemCount() {
        return (null != data ? data.size() : 0);
    }


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tvName;
        public TextView tvCountry;
        public TextView tvProfilePic;
        RoundedImageView ivProfilePic;
        ImageView ivFlag;

        public ViewHolder(View v) {
            super(v);
            tvName = (TextView) v.findViewById(R.id.tvName);
            tvProfilePic = (TextView) v.findViewById(R.id.tvProfilePic);
            tvCountry = (TextView) v.findViewById(R.id.tvCountry);
            ivProfilePic = (RoundedImageView) v.findViewById(R.id.ivProfilePic);
            ivFlag = (ImageView) v.findViewById(R.id.ivFlag);
        }
    }

    public void addAll(List<ContactInfo> dataList) {

        data.clear();
        if (dataList != null) {
            data.addAll(dataList);
        }

        notifyDataSetChanged();
    }

    public void add(int position, ContactInfo item) {
        data.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(ContactInfo item) {
        int position = data.indexOf(item);
        data.remove(position);
        notifyItemRemoved(position);
    }

    ImageLoader imageLoader;
    private void initImageLoader()
    {
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(mContext));

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.color.gray)
                .showImageForEmptyUri(R.color.gray)
                .showImageOnFail(R.color.gray)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .build();

    }

}