/*
 * Copyright 2014 Hieu Rocker
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zhowin.study.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhowin.study.R;
import com.zhowin.study.emoticon.Emojicon;

import java.util.List;


/**
 * Emoji 的adapter
 */
public class EmojiAdapter extends BaseAdapter {

    private Context context;
    private List<Emojicon> mData;
    private int itemWidth;

    public EmojiAdapter(Context context, List<Emojicon> data, int itemWidth) {
        this.context = context;
        mData = data;
        this.itemWidth = itemWidth;
    }

    @Override
    public int getCount() {
        return mData.size() + 1;
    }

    @Override
    public Emojicon getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_emoji, null);
            ViewHolder holder = new ViewHolder();
            holder.icon = (TextView) convertView.findViewById(R.id.icon);
            holder.ivDelete = (ImageView) convertView.findViewById(R.id.ivDelete);
            convertView.setTag(holder);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(itemWidth, itemWidth);
        holder.icon.setPadding(itemWidth / 8, itemWidth / 8, itemWidth / 8, itemWidth / 8);
        holder.icon.setLayoutParams(params);
        holder.ivDelete.setLayoutParams(params);
        if (position == mData.size()) {
            holder.icon.setText("");
            holder.ivDelete.setVisibility(View.VISIBLE);
        } else {
            holder.ivDelete.setVisibility(View.GONE);
            Emojicon emoji = getItem(position);
            holder.icon.setText(emoji.getEmoji());
        }
        return convertView;
    }

    static class ViewHolder {
        TextView icon;
        ImageView ivDelete;
    }
}