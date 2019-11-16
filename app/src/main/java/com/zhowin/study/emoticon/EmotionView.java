package com.zhowin.study.emoticon;

import android.content.Context;
import android.text.SpannableString;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import androidx.viewpager.widget.ViewPager;

import com.shuyu.gsyvideoplayer.utils.CommonUtil;
import com.zhowin.study.R;
import com.zhowin.study.adapter.EmojiAdapter;
import com.zhowin.study.adapter.EmotionPagerAdapter;
import com.zhowin.study.utils.DisplayUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/4/21.
 */
public class EmotionView extends LinearLayout implements ViewPager.OnPageChangeListener {

    private LinearLayout llEmotion;
    private ViewPager vpEmotion;
    private LinearLayout llPoint;
    private LinearLayout llPic;
    private RelativeLayout llShow;
    private ImageView pic;
    private ImageView ivDelete;
    private TextView tvHint;
    private Context mContext;
    private EmotionPagerAdapter emotionPagerGvAdapter;
    private int lastPosition = 0;
    private EditText etContent;
    private ImageButton btnPhoto;
    private ImageButton btnCamera;
    private OnItemClickListener listener;

    public EmotionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    private void initView() {
        View.inflate(mContext, R.layout.layout_emotion, this);
        llEmotion = (LinearLayout) findViewById(R.id.llEmotion);
        vpEmotion = (ViewPager) findViewById(R.id.vpEmotion);
        llPoint = (LinearLayout) findViewById(R.id.llPoint);
        llPic = (LinearLayout) findViewById(R.id.llPic);
        llShow = (RelativeLayout) findViewById(R.id.llShow);
        pic = (ImageView) findViewById(R.id.pic);
        ivDelete = (ImageView) findViewById(R.id.ivDelete);
        tvHint = (TextView) findViewById(R.id.tvHintMessage);
        btnPhoto = (ImageButton) findViewById(R.id.btnPhoto);
        btnCamera = (ImageButton) findViewById(R.id.btnCamera);
        btnPhoto.setOnClickListener(v -> listener.selectPic());
        btnCamera.setOnClickListener(v -> listener.takePhotos());
        ivDelete.setOnClickListener(v -> {
            listener.deletePic();
            showPicSelect();
        });
        initEmotion();
    }

    /**
     * 初始化表情面板内容
     */
    private void initEmotion() {
        int width = getResources().getDisplayMetrics().widthPixels;
        int spacing = DisplayUtils.dp2px(mContext, 8);
        //每列显示7个表情，显示3行
        int itemWidth = (width - spacing * 8) / 7;
        int gvHeight = itemWidth * 3 + spacing * 4;
        List<GridView> gvs = new ArrayList<GridView>();
        List<Emojicon> icons = new ArrayList<>();
        for (Emojicon icon : People.DATA) {
            icons.add(icon);
            if (icons.size() == 20) {
                GridView gv = createEmotionGridView(icons, width, spacing, itemWidth, gvHeight);
                gvs.add(gv);
                icons = new ArrayList<>();
            }
        }
        //最后一页不够20个需加一个
        if (icons.size() > 0) {
            GridView gv = createEmotionGridView(icons, width, spacing, itemWidth, gvHeight);
            gvs.add(gv);
        }
        emotionPagerGvAdapter = new EmotionPagerAdapter(gvs);
        LayoutParams params = new LayoutParams(width, gvHeight);
        vpEmotion.setLayoutParams(params);
        vpEmotion.setAdapter(emotionPagerGvAdapter);
        vpEmotion.addOnPageChangeListener(this);
        initPoint(gvs.size());
    }

    /**
     * 初始化表情切换点
     */
    private void initPoint(int size) {
        for (int i = 0; i < size; i++) {
            ImageView point = new ImageView(mContext);
            point.setImageResource(R.drawable.sel_viewpager_point);
            int dx = CommonUtil.dip2px(mContext, 8);
            LayoutParams layoutParams = new LayoutParams(dx, dx);
            layoutParams.rightMargin = CommonUtil.dip2px(mContext, 5);
            point.setLayoutParams(layoutParams);
            if (i == 0) {
                point.setEnabled(true);
            } else {
                point.setEnabled(false);
            }
            llPoint.addView(point);
        }
    }

    /**
     * 创建显示表情的GridView
     *
     * @param icons
     * @param gvWidth
     * @param padding
     * @param itemWidth
     * @param gvHeight
     * @return
     */

    private GridView createEmotionGridView(List<Emojicon> icons, int gvWidth, final int padding, int itemWidth, int gvHeight) {
        GridView gv = new GridView(mContext);
        gv.setSelector(R.color.transparent);
        gv.setNumColumns(7);
        gv.setPadding(padding, padding, padding, padding);
        gv.setHorizontalSpacing(padding);
        gv.setVerticalSpacing(padding);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(gvWidth, gvHeight);
        gv.setLayoutParams(params);
        EmojiAdapter adapter = new EmojiAdapter(mContext, icons, itemWidth);
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EmojiAdapter emotionAdapter = (EmojiAdapter) parent.getAdapter();
                if (position == emotionAdapter.getCount() - 1) {
                    etContent.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
                } else {
                    Emojicon item = emotionAdapter.getItem(position);
                    int curPosition = etContent.getSelectionStart();
                    StringBuilder sb = new StringBuilder(etContent.getText().toString());
                    sb.insert(curPosition, item.getEmoji());
                    etContent.setText(sb.toString());
                    try {
                        etContent.setSelection(curPosition + item.getEmoji().length());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        return gv;
    }

    /**
     * 绑定EditText
     *
     * @param et
     */
    public void setEditText(EditText et) {
        this.etContent = et;
    }

    /**
     * 文字提示
     *
     * @param hint
     */
    public void setHint(String hint) {
        tvHint.setText(hint);
    }

    public void hideHint() {
        tvHint.setVisibility(View.GONE);
    }

    public boolean isVisible() {
        return getVisibility() == View.VISIBLE;
    }

    public void show() {
        setVisibility(View.VISIBLE);
    }

    public void hide() {
        setVisibility(View.GONE);
    }

    public void showEmotion() {
        show();
        llPic.setVisibility(View.GONE);
        llShow.setVisibility(View.GONE);
        llEmotion.setVisibility(View.VISIBLE);
        tvHint.setText(R.string.emotion_hint);
    }

    public void showPicSelect() {
        show();
        llEmotion.setVisibility(View.INVISIBLE);
        llShow.setVisibility(View.GONE);
        llPic.setVisibility(View.VISIBLE);
        tvHint.setText(R.string.pic_hint);
    }

    public void showImage(String path) {
        show();
        llEmotion.setVisibility(View.INVISIBLE);
        llPic.setVisibility(View.GONE);
        tvHint.setText(R.string.pic_add_hint);
        llShow.setVisibility(View.VISIBLE);
        if (path != null) {
//            ImageLoaderUtils.displayImage("file://" + path, pic);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        // 改变指示点状态
        llPoint.getChildAt(lastPosition).setEnabled(false);
        llPoint.getChildAt(position).setEnabled(true);
        lastPosition = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public interface OnItemClickListener {
        void selectPic();

        void takePhotos();

        void deletePic();
    }
}
