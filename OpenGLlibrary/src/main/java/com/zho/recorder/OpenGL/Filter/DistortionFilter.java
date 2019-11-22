package com.zho.recorder.OpenGL.Filter;

import android.content.res.Resources;

import com.zho.recorder.OpenGL.TransUtil;
import com.zho.recorder.OpenGL.Transformation;
import com.zho.recorder.Utils.MatrixUtils;

/**
 * 旋转，翻转，裁剪类滤镜
 * Created by Yj on 2017/10/31.
 */

public class DistortionFilter extends Filter {

	//旋转，翻转，裁剪变换类
	private Transformation mTransformation;
	private float[] mTextureCo;

	public DistortionFilter(Resources resource) {
		super(resource, "shader/base.vert","shader/base.frag");
		initTransformation();
	}

	/**
	 * 初始化变化类
	 */
	private void initTransformation() {
		mTextureCo = MatrixUtils.getOriginalTextureCo();
		if (mTransformation == null) {
			mTransformation = new Transformation();
		}
	}

	/**
	 * 设置变化类
	 * @param transformation
	 */
	public void setTransformation(Transformation transformation){
		mTransformation = transformation;
		setTextureCo(TransUtil.getTransformationCo(mTextureCo, mTransformation));
	}
}
