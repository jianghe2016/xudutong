package com.xdt.xudutong.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.xdt.xudutong.utils.Maths;

import java.util.ArrayList;
import java.util.List;

public class AnnualyieldView extends View {
	private Paint mTextPaint, mLinePaint,mPathPaint,mPointPaint;

	private float mPaintRectWidth;

	private Path mPath;

	private float mWidth, mHeight;

	private final float mCount = 6;

	private final float offsets=1;

	private float mRectHeight;

	private List<Float> xline=new ArrayList<Float>();
	private List<Float> yline=new ArrayList<Float>();


	private float []x={2.46f,2.45f,2.44f,2.43f,2.42f,2.41f,2.40f};

	public AnnualyieldView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		initView();

	}

	public AnnualyieldView(Context context, AttributeSet attrs) {
		super(context, attrs);

		initView();
	}

	public AnnualyieldView(Context context) {
		super(context);

		initView();
	}

	private void initView() {

		mTextPaint = new Paint();
		mTextPaint.setAntiAlias(true);
		mTextPaint.setColor(Color.parseColor("#cccccc"));
		mTextPaint.setTextSize(25);
		mTextPaint.setStrokeWidth(1);


		mPointPaint= new Paint();
		mPointPaint.setAntiAlias(true);
		mPointPaint.setColor(Color.parseColor("#000000"));
		mPointPaint.setTextSize(25);
		mPointPaint.setStrokeWidth(5);

		mLinePaint = new Paint();
		mLinePaint.setAntiAlias(true);

		mPathPaint= new Paint();
		mPathPaint.setAntiAlias(true);
		mPathPaint.setColor(Color.parseColor("#ff0000"));
		mPathPaint.setStyle(Style.STROKE);

		mPath=new Path();

	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		mWidth=(float)(getWidth()-getWidth()*0.1);
		mHeight=(float)(getHeight()-getHeight()*0.1);
		mRectHeight=(float)(getHeight()-getHeight()*0.1);
		mPaintRectWidth=(float) (mWidth*0.8/mCount);
		mLinePaint.setStrokeWidth(mPaintRectWidth);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		onDrawRect(canvas);		
		onDrawLine(canvas);
		canvasPath(canvas);
	}

	private void onDrawRect(Canvas canvas) {
		for (int i = 0; i < 7; i++) {
			if (i%2==0) {
				mLinePaint.setColor(Color.parseColor("#eeeeee"));
			}else {
				mLinePaint.setColor(Color.parseColor("#ece1f3"));
			}	
			float left  =(float) (mWidth * 0.4 / 2 + mPaintRectWidth * i + offsets);
			float right=(float) (mWidth * 0.4 / 2 + mPaintRectWidth* (i + 1));
			canvas.drawRect(left,(float)(mRectHeight*0.01),right, mHeight, mLinePaint);
		}

	}

	private void onDrawLine(Canvas canvas){

		canvas.drawLine(mPaintRectWidth-mPaintRectWidth/2, (float)(mRectHeight*0.01), getWidth(),  (float)(mRectHeight*0.01), mTextPaint);

		float height;

		for (float i = 0; i < 7; i++) {

			if (i==0) {
				height=i;
			}else {
				height=mRectHeight*(i/6);
				float size=mTextPaint.measureText(x[(int)i]+"");

				canvas.drawLine(mPaintRectWidth+mPaintRectWidth/2, height, getWidth(),  height, mTextPaint);

				canvas.drawText(x[(int)i]+"", (float)(mPaintRectWidth-mPaintRectWidth*0.35), height+size/5, mTextPaint);
			}

		}

		canvas.drawLine((float) (mPaintRectWidth-mPaintRectWidth/2),0, (float) (mPaintRectWidth-mPaintRectWidth/2),  mHeight, mTextPaint);
		for (float i = 0; i < 7; i++) {

			canvas.drawLine((float) (mWidth * 0.4 / 2 + mPaintRectWidth * i),0, (float) (mWidth * 0.4 / 2 + mPaintRectWidth * i),  mHeight, mTextPaint);

			canvas.drawText("07-0"+((int)i+1), (float) (mWidth * 0.34 / 2 + mPaintRectWidth * i), (float)(mHeight+mHeight*0.1), mTextPaint);

			xline.add((float) (mWidth * 0.4 / 2 + mPaintRectWidth * i));
		}

		xline.add((float) (mPaintRectWidth-mPaintRectWidth/2));



	}

	public void canvasPath(Canvas canvas){


		for (int j = 0; j < yline.size(); j++) {

			float x=xline.get(j);
			float y =yline.get(j);
			float aftery= Maths.initData(y);
			if (j==0) {
				mPath.moveTo(x,aftery );
			}else{

				mPath.lineTo(x,aftery );
			}
			canvas.drawPoint(x, aftery, mPointPaint);
			float size=mPointPaint.measureText(y+"");
			canvas.drawText(y+"", (float)(x-size/2), (float)(aftery+size*0.25), mPointPaint);
		}
		canvas.drawPath(mPath, mPathPaint);
	}

	public void setDataY( List<Float> yline) {
		this.yline.clear();
		this.yline=yline;
	}

	public void invalidata(){
		invalidate();
	}

}
