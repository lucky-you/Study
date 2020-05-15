package com.zhowin.study.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zhowin.study.R;
import com.zhowin.study.model.ThatMessageList;

import java.util.List;

/**
 * author Z_B
 * date :2020/5/15 9:45
 * description:
 */
public class GalleryListAdapter extends RecyclerView.Adapter<GalleryListAdapter.ViewHolder> implements View.OnClickListener {

    private Context mContext;
    private List<ThatMessageList> mItems;
    private OnItemClickListener mOnItemClickListener;

    public GalleryListAdapter(List<ThatMessageList> items) {
        this.mItems = items;
    }

    public GalleryListAdapter(Context mContext, List<ThatMessageList> mItems) {
        this.mContext = mContext;
        this.mItems = mItems;
    }

    public GalleryListAdapter setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
        return this;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.include_gallery_list_item_view, parent, false);
        itemView.setOnClickListener(this);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String item = mItems.get(position).getTitle();
        holder.text.setText(item);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mItems.isEmpty() ? 0 : mItems.size();
    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(view, (int) view.getTag());
        }
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        public TextView text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.item_tv_title);
        }
    }

    public interface OnItemClickListener {

        void onItemClick(View view, int position);

    }
}
