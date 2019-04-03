package com.aranguriapps.joni.melisearchapp.ui.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aranguriapps.joni.melisearchapp.R;
import com.aranguriapps.joni.melisearchapp.common.MercadoLibreUtils;
import com.aranguriapps.joni.melisearchapp.domain.ItemSearch;
import com.aranguriapps.joni.melisearchapp.ui.activities.DetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
        ItemSearch currentItem = itemSearches.get(position);

        holder.setItemTitle(currentItem.getTitle());

        if(currentItem.getThumbnail() != null)
            holder.setItemThumbnail(currentItem.getThumbnail());
        holder.setItemPrice(currentItem.getPrice());
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

         static final int IMG_SIZE_DP = 112;

         int IMG_SIZE_PX;

        @BindView(R.id.img_thum_item)
        ImageView itemImage;

        @BindView(R.id.txt_item_title)
        TextView itemTitle;
        @BindView(R.id.txt_item_price)
        TextView itemPrice;

         ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener != null)
                        clickListener.onItemClicked(itemSearches.get(getAdapterPosition()).getId());

                }
            });

            setupDimensions();
        }

        private void setupDimensions() {
            DisplayMetrics metrics = context.getResources().getDisplayMetrics();
            IMG_SIZE_PX = IMG_SIZE_DP * (metrics.densityDpi / 160);
        }

        void setItemTitle(String name){
            itemTitle.setText(name);
        }
        void setItemPrice(String price){
            itemPrice.setText(context.getResources().getString(R.string.pesos_sig).concat(price));
        }


         void setItemThumbnail(String urlImage) {
            Picasso.get()
                    .load(MercadoLibreUtils.getImageGoodQuality(urlImage))
                    .placeholder(R.drawable.background_item)
                  // .resize(IMG_SIZE_PX, IMG_SIZE_PX)//change to a full image
                    .into(itemImage);
        }
    }

    /**
     * A simple interface to be configured in a RecyclerView
     */
    public interface ItemClickListener{
        /**
         * This method will be invoked when an item from the list be clicked
         * @param idItem of Item clicked
         * */
        void onItemClicked(String idItem);
    }
}
