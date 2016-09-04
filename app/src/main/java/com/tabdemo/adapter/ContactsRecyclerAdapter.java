package com.tabdemo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.tabdemo.R;
import com.tabdemo.models.ContactInfo;

import java.util.ArrayList;
import java.util.List;

public class ContactsRecyclerAdapter extends RecyclerView.Adapter<ContactsRecyclerAdapter.ViewHolder> {
    private ArrayList<ContactInfo> data = new ArrayList<>();
    private Context mContext;

    public ContactsRecyclerAdapter(Context context) {
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

        //Setting text view title
        customViewHolder.tvName.setText(feedItem.getCaption());
        customViewHolder.tvCountry.setText(feedItem.getInternational_number());
        customViewHolder.tvProfilePic.setText("");
        if (feedItem.getImagebase64() != null && feedItem.getImagebase64().length() > 0) {
            imageLoader.displayImage(feedItem.getImagebase64(), customViewHolder.ivProfilePic);
            customViewHolder.tvProfilePic.setVisibility(View.GONE);
            customViewHolder.tvProfilePic.setText("");
        } else {
            customViewHolder.ivProfilePic.setImageResource(R.color.gray);
            customViewHolder.tvProfilePic.setVisibility(View.VISIBLE);
            // splitting names by space and picking first char from it
            String s[] = feedItem.getCaption().split(" ");
            for (String s1 : s) {
                if (s1.length() > 0) {
                    customViewHolder.tvProfilePic.append(s1.charAt(0) + "");
                }
            }


        }

        Log.e("pos "+i,feedItem.getImagebase64()+" : "+   customViewHolder.tvProfilePic.getText());

        if (feedItem.getFlagImgBase64() != null && feedItem.getFlagImgBase64().length() > 0) {
            imageLoader.displayImage(feedItem.getFlagImgBase64(), customViewHolder.ivFlag);
        } else {
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

    private void initImageLoader() {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true).cacheOnDisk(true)
                .showImageForEmptyUri(R.color.gray)
                .showImageOnFail(R.color.gray)
                .showImageOnLoading(R.color.gray)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .displayer(new FadeInBitmapDisplayer(400)).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                mContext).memoryCache(new WeakMemoryCache())
                .defaultDisplayImageOptions(defaultOptions).build();

        imageLoader = ImageLoader.getInstance();
        imageLoader.init(config);
    }

}