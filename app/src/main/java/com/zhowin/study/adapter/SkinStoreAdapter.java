package com.zhowin.study.adapter;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.makeramen.roundedimageview.RoundedImageView;
import com.zhowin.study.R;
import com.zhowin.study.skin.Skin;
import com.zhowin.study.utils.GlideUtils;

import java.util.List;

/**
 * author Z_B
 * date :2019/12/18 9:38
 * description: 皮肤主题的adapter
 */
public class SkinStoreAdapter extends BaseQuickAdapter<Skin, BaseViewHolder> {
    private Skin currentSkin;

    public SkinStoreAdapter(@Nullable List<Skin> data) {
        super(R.layout.include_switch_skin_item_view, data);
    }

    public void setCurrentSkin(Skin currentSkin) {
        this.currentSkin = currentSkin;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Skin item) {
        Log.e("xy", "color:" + item.getAccentColor() + "--colorName:" + mContext.getString(item.getColorName()));
        RoundedImageView rivSkinColor = helper.getView(R.id.rivSkinColor);
        ImageView ivSkinCheck = helper.getView(R.id.ivSkinCheck);
        TextView tvSkinName = helper.getView(R.id.tvSkinName);
        Skin skin = mData.get(helper.getAdapterPosition());
        tvSkinName.setText(skin.getColorName());
        rivSkinColor.setImageResource(skin.getAccentColor());
        ivSkinCheck.setVisibility(currentSkin == skin ? View.VISIBLE : View.GONE);
    }
}
