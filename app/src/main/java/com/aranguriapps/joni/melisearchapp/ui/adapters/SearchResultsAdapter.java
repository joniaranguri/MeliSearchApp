package com.aranguriapps.joni.melisearchapp.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aranguriapps.joni.melisearchapp.R;
import com.aranguriapps.joni.melisearchapp.domain.ItemSearch;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SearchResultsAdapter extends RecyclerView.Adapter<SearchResultsAdapter.ViewHolder>{

    ArrayList<ItemSearch> itemSearches;
   private ItemClickListener clickListener;
    Context context;

    public SearchResultsAdapter(Context context) {
        this.itemSearches = new ArrayList<>();
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_searched, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ItemSearch currentArtist = itemSearches.get(position);

        holder.setArtistName(currentArtist.getTitle());

       /* if(currentArtist.getMediumImage() != null)
            holder.setArtistImage(currentArtist.getMediumImage().getUrl());

        else*/
            holder.setPlaceholderImage();
    }

    @Override
    public int getItemCount() {
        return itemSearches.isEmpty() ? 0 : itemSearches.size();

    }

    /**
     * Add item in the last index
     *
     * @param artist The item to be inserted
     */
    public void addItem(ItemSearch artist) {
        if (artist == null)
            throw new NullPointerException("The item cannot be null");

        itemSearches.add(artist);
        notifyItemInserted(getItemCount() - 1);
    }

    /**
     * Add item in determined index
     *
    / * @param  itemSearch   The event to be inserted
     * @param position Index for the new event
     */
    public void addItem(ItemSearch itemSearch, int position) {
        if (itemSearch == null)
            throw new NullPointerException("The item cannot be null");

        if (position < getItemCount() || position > getItemCount())
            throw new IllegalArgumentException("The position must be between 0 and lastIndex + 1");

        itemSearches.add(position, itemSearch);
        notifyItemInserted(position);
    }

    /**
     * Add a bunch of items
     *
     * @param artists Collection to add
     * */
    public void addAll(List<ItemSearch> artists) {
        if (artists == null)
            throw new NullPointerException("The items cannot be null");

        this.itemSearches.addAll(artists);
        notifyItemRangeInserted(getItemCount() - 1, artists.size());
    }

    public void replace(ArrayList<ItemSearch> artists){
        this.itemSearches = artists;
        notifyDataSetChanged();
    }

    /**
     * Delete all the items
     * */
    public void clear() {
        if (!itemSearches.isEmpty()) {
            itemSearches.clear();
            notifyDataSetChanged();
        }
    }

    public void setOnItemClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public static final int IMG_SIZE_DP = 112;

        public int IMG_SIZE_PX;

        @BindView(R.id.img_thum_item)
        ImageView itemImage;

        @BindView(R.id.txt_item_title)
        TextView itemTitle;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener != null)
                        clickListener.onItemClicked(getPosition());
                }
            });

            setupDimensions();
        }

        private void setupDimensions() {
            DisplayMetrics metrics = context.getResources().getDisplayMetrics();
            IMG_SIZE_PX = IMG_SIZE_DP * (metrics.densityDpi / 160);
        }

        public void setArtistName(String name){
            itemTitle.setText(name);
        }

        public void setArtistImage(String urlImage) {
            Picasso.get()
                    .load(urlImage)
                    .placeholder(R.drawable.logo)
                    .resize(IMG_SIZE_PX, IMG_SIZE_PX)
                    .into(itemImage);
        }

        public void setPlaceholderImage() {
            Picasso.get()
                    .load(R.drawable.logo)
                    .resize(IMG_SIZE_PX, IMG_SIZE_PX)
                    .into(itemImage);
        }
    }

    /**
     * Created by Pedro Antonio Hernández on 03/06/2015.
     *
     * A simple interface to be configured in a RecyclerView
     */
    public interface ItemClickListener{
        /**
         * This method will be invoked when an item from the list be clicked
         * @param position position in the list
         * */
        void onItemClicked(int position);
    }
}
