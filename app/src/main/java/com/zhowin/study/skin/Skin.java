package com.zhowin.study.skin;

import android.content.res.ColorStateList;

/**
 * author Z_B
 * date :2019/12/18 9:26
 * description: 皮肤的参数
 */
public class Skin {

    // 表示未激活的灰色，
    private static final int normalColor = 0xffd5d4d9;
    private int colorName;
    // 主色，也就是标题栏的颜色，
    private int primaryColor;
    // 活跃的颜色，也就是各种按钮控件激活状态的颜色，
    private int accentColor;

    public Skin(int colorName, int color) {
        this(colorName, color, color);
    }

    public Skin(int colorName, int primaryColor, int accentColor) {
        this.colorName = colorName;
        this.primaryColor = 0xff000000 | primaryColor;
        this.accentColor = 0xff000000 | accentColor;
    }


    public int getColorName() {
        return colorName;
    }

    public int getPrimaryColor() {
        return primaryColor;
    }

    public int getAccentColor() {
        return accentColor;
    }

    /**
     * 获取tab 的color
     *
     * @return tabColor
     */
    public ColorStateList getTabColorState() {
        int[][] states = new int[][]{
                new int[]{-android.R.attr.state_checked},
                new int[]{android.R.attr.state_checked}
        };
        int[] colors = new int[]{
                normalColor,
                getAccentColor()
        };
        return new ColorStateList(states, colors);
    }
}
