package com.zhowin.study.skin;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zhowin.study.R;
import com.zhowin.study.utils.PreferenceUtils;

import java.util.Arrays;
import java.util.List;

/**
 * author Z_B
 * date :2019/12/18 9:25
 * description: 皮肤的工具类
 */
public class SkinUtils {


    /**
     * 添加皮肤在这里加一行就可以，
     * 然后字符串要国际化，改皮肤页面显示用，
     * 保存primaryColor作本地持久化，所以primaryColor必须唯一，
     */
    public static List<Skin> initDefaultSkins = Arrays.asList(
            // 小蚁logo色#14a399，淡钴绿  Viridian green
            new Skin(R.string.skin_viridian_green, R.color.color_14A399),
            // 粉叶绿#BAE019，粉叶绿  Leaf green
            new Skin(R.string.skin_leaf_green, R.color.color_BAE019),
            // 粉天蓝#7ED1FB，粉天蓝  Powder azure
            new Skin(R.string.skin_powder_azure, R.color.color_7ED1FB),
            // facebook蓝#3C589B， 商务蓝  business blue
            new Skin(R.string.skin_business_blue, R.color.color_3C589B),
            // 大海蓝，
            new Skin(R.string.skin_sea_blue, R.color.color_099FDE),
            // 粉红#FA99A0，感性粉
            new Skin(R.string.skin_perceptual_pink, R.color.color_FA99A0),
            // 中国红,
            new Skin(R.string.skin_chinese_red, R.color.color_DE3031),
            // 琥珀黄 #FFC400, 琥珀黄  Amber yellow
            new Skin(R.string.skin_amber_yellow, R.color.color_FFC400),
            // 桔色#FE7B21， 橘黄色   orange
            new Skin(R.string.skin_orange, R.color.color_FE7B21),
            // 马卡龙让JA色#C17E61，浅咖色 light coffee
            new Skin(R.string.skin_light_coffe, R.color.color_C17E61),
            // 蓝灰#547A8C，蓝灰色  blue gray
            new Skin(R.string.skin_blue_gray, R.color.color_547A8C),
            // 深茶色#4E2505， 深茶色  burnt Umber
            new Skin(R.string.skin_burnt_umber, R.color.color_4E2505)
    );
    // 默认淡钴绿,
    private static final Skin DEFAULT_SKIN = initDefaultSkins.get(0);
    // key
    public static final String KEY_SKIN_COLOR = "KEY_SKIN_COLOR";

    @Nullable
    private static Skin currentSkin = null;

    public static Skin getSkin(Context ctx) {
        return requireSkin(ctx);
    }

    public static void setSkin(Context ctx, Skin skin) {
        currentSkin = skin;
        PreferenceUtils.putInt(ctx, KEY_SKIN_COLOR, skin.getPrimaryColor());
    }

    @NonNull
    private static Skin requireSkin(Context ctx) {
        if (currentSkin != null) {
            return currentSkin;
        }
        synchronized (SkinUtils.class) {
            if (currentSkin == null) {
                int savedSkinColor = PreferenceUtils.getInt(ctx, KEY_SKIN_COLOR, DEFAULT_SKIN.getPrimaryColor());
                for (Skin skin : initDefaultSkins) {
                    if (skin.getPrimaryColor() == savedSkinColor) {
                        currentSkin = skin;
                        break;
                    }
                }
                if (currentSkin == null) {
                    // 本地保存的皮肤数据出了异常，比如高版本删除了低版本存在的皮肤，
                    currentSkin = DEFAULT_SKIN;
                }
            }
        }
        return currentSkin;
    }

}
