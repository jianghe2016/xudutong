/**
 *	@copyright 亿康通-2015 
 * 	@author wanruome  
 * 	@create 2015年11月12日 下午4:45:15 
 */
package com.xdt.xudutong.tianjian;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xdt.xudutong.R;

import org.greenrobot.eventbus.EventBus;


public class HtmlChatInputView extends LinearLayout {
	public final long DELETE_TIME_VALUE = 2000;

	private ChatInputSendListener mSendListener;
	private ChatInputStatusListener mStatusListener;
	private InputMethodManager inputManager;
	private Context mContext;
	private View mView;
	private final Views views = new Views();
	private int scrollMaxWidth = 0;
	private int scrollDelWidth = 0;
	private int scrolldownX;
	private int touchDownY = 0;
	private int touchDownYMin = 0;
	private int recordStatus = ChatInputConfig.VOICE_END;
	private boolean isCanSpeecher = true;
	private String speerDisableInfo = "";

	private final Handler uIHandler = new Handler();

	class Views {
		// 文字输入区域
		EditText contentET;
		// 发送信息按钮
		TextView sendtext;
		// 文字为空时候的显示
		ImageView sendimage;
		ImageView divisionImg;

		// 录音删除提示
		TextView sendspeech_textdel;
		// 录音状态
		ImageView speechstatus_image;
		// 录音时间
		TextView speechstatus_time;
		// 录音按钮
		ImageView sendspeech;
		// 录音滑动区块
		LinearLayout container;
		LinearLayout container_text;
		LinearLayout container_speech;

	}

	public ChatInputSendListener getSendListener() {
		return mSendListener;
	}

	public void setSendListener(ChatInputSendListener chatInputSendListener) {
		this.mSendListener = chatInputSendListener;
	}

	public ChatInputStatusListener getStatusListener() {
		return mStatusListener;
	}

	public void setStatusListener(ChatInputStatusListener mStatusListener) {
		this.mStatusListener = mStatusListener;
	}

	public void setSpeecherBtnEnable(boolean isCanSpeecher, String speerDisableInfo) {
		this.isCanSpeecher = isCanSpeecher;
		this.speerDisableInfo = speerDisableInfo;
	}

	public HtmlChatInputView(Context context) {
		super(context);
		initView(context, null, 0);
	}

	public HtmlChatInputView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context, attrs, 0);
	}

	@SuppressLint("NewApi")
	public HtmlChatInputView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
		initView(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	private void initView(Context context, AttributeSet attrs, int defStyle) {
		mContext = context;
		touchDownYMin = DisplayUtil.getDispalyHeight(mContext) - DisplayUtil.dipTopx(mContext, 40);
		mView = inflate(context, R.layout.htmlchatinputview_layout, this);
		views.contentET = (EditText) mView.findViewById(R.id.chat_contentET);
		views.divisionImg = (ImageView) mView.findViewById(R.id.chat_divisionImg);
		views.sendimage = (ImageView) mView.findViewById(R.id.chat_sendimage);
		views.sendspeech = (ImageView) mView.findViewById(R.id.chat_sendspeech);
		views.container = (LinearLayout) mView.findViewById(R.id.chat_container);
		views.container_speech = (LinearLayout) mView.findViewById(R.id.chat_container_speech);
		views.container_text = (LinearLayout) mView.findViewById(R.id.chat_container_text);
		views.sendspeech_textdel = (TextView) mView.findViewById(R.id.chat_sendspeech_textdel);
		views.sendtext = (TextView) mView.findViewById(R.id.chat_sendtext);
		views.speechstatus_image = (ImageView) mView.findViewById(R.id.chat_speechstatus_image);
		views.speechstatus_time = (TextView) mView.findViewById(R.id.chat_speechstatus_time);
		views.contentET.addTextChangedListener(etTextWatcher);
		views.sendimage.setOnClickListener(chatinputOnClickListener);
		views.sendtext.setOnClickListener(chatinputOnClickListener);
		views.sendspeech.setOnTouchListener(speechOnTouchListener);
		views.sendspeech.setOnLongClickListener(speechLongClickListener);
		views.sendspeech.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				RecoderEvent mpevent=new RecoderEvent();
				EventBus.getDefault().post(mpevent);
			}
		});
		updateUIFirst();
	}

	private void updateUIFirst() {
		views.speechstatus_image.setVisibility(View.GONE);
		views.speechstatus_time.setVisibility(View.GONE);
		views.container_speech.setVisibility(View.GONE);
		views.sendtext.setVisibility(View.GONE);
	}

	private void updateUIByContent() {
		String etString = views.contentET.getEditableText().toString();
		if (TextUtils.isEmpty(etString)) {
			updateUIByContentNull();
		}
		else {
			updateUIByContentReal();
		}

	}

	private void updateUIByContentNull() {
		// views.speechstatus_image.setVisibility(View.GONE);
		views.speechstatus_time.setVisibility(View.GONE);
		views.container_speech.setVisibility(View.GONE);
		views.container_text.setVisibility(View.VISIBLE);
		views.sendtext.setVisibility(View.GONE);
		views.sendspeech.setVisibility(View.VISIBLE);
//		views.sendimage.setVisibility(View.VISIBLE);
//		views.divisionImg.setVisibility(View.VISIBLE);
	}

	private void updateUIByContentReal() {
		// views.speechstatus_image.setVisibility(View.GONE);
		views.speechstatus_time.setVisibility(View.GONE);
		views.container_speech.setVisibility(View.GONE);
		views.container_text.setVisibility(View.VISIBLE);
		views.sendtext.setVisibility(View.VISIBLE);
		views.sendspeech.setVisibility(View.GONE);
		views.sendimage.setVisibility(View.GONE);
		views.divisionImg.setVisibility(View.GONE);
	}

	private void updateUIToRecord() {
		views.speechstatus_image.setVisibility(View.VISIBLE);
		views.speechstatus_time.setVisibility(View.VISIBLE);
		views.container_speech.setVisibility(View.VISIBLE);
		views.container_text.setVisibility(View.GONE);
		views.sendtext.setVisibility(View.GONE);
		views.sendspeech.setVisibility(View.VISIBLE);
		views.sendimage.setVisibility(View.GONE);
		views.divisionImg.setVisibility(View.GONE);
	}

	private void updateUIByVoiceStatus(int voiceStatus) {
		if (voiceStatus == ChatInputConfig.VOICE_END) {
			views.container.scrollTo(0, 0);
			updateUIByContent();
			views.speechstatus_image.setAlpha(255);
			views.speechstatus_image.setVisibility(View.GONE);
		}
		else if (voiceStatus == ChatInputConfig.VOICE_End_Cancle) {
			updateUIByContent();
		}
		else if (voiceStatus == ChatInputConfig.VOICE_Start_OK) {

			updateUIToRecord();
			views.speechstatus_image.setVisibility(View.VISIBLE);
			views.speechstatus_time.setText("0:00");
			views.speechstatus_image.setAlpha(255);
		}

	}

	private final TextWatcher etTextWatcher = new TextWatcher() {

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			// TODO Auto-generated method stub

		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			// TODO Auto-generated method stub

		}

		@Override
		public void afterTextChanged(Editable s) {
			updateUIByContent();
		}
	};
	//发送文本、图片
	private final OnClickListener chatinputOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			int vID = v.getId();
			if (vID == R.id.chat_sendtext) {
				String msgString = views.contentET.getEditableText().toString();
				if (!TextUtils.isEmpty(msgString)) {
					if (null != mSendListener) {
						mSendListener.onChatSendText(v, msgString);
					}
					if(msgString.length()>500){
						views.contentET.setText(msgString);
					}else{
						views.contentET.setText(null);						
					}
				}
			}
			else if (vID == R.id.chat_sendimage) {
				if (null != mSendListener) {
					mSendListener.onChatSendImage(v);
				}
			}

		}
	};
	//长按录音
	private final OnLongClickListener speechLongClickListener = new OnLongClickListener() {

		@Override
		public boolean onLongClick(View v) {
			if (touchDownY > touchDownYMin) {

				if (PackageUtils.hasPermission(mContext, "android.permission.RECORD_AUDIO")) {
					if (isCanSpeecher) {
						speecherStart();
						return false;
					}
					else {
						if (TextUtils.isEmpty(speerDisableInfo)) {
							Toast.makeText(mContext,"暂时不能录音",Toast.LENGTH_LONG).show();
						}
						else {
							Toast.makeText(mContext,speerDisableInfo,Toast.LENGTH_LONG).show();
						}
						return true;
					}

				}
				else {
					Toast.makeText(mContext,"请先开启录音权限",Toast.LENGTH_LONG).show();
					return true;
				}
			}
			else {
				touchDownY = 0;
				return true;
			}

		}
	};
	private final OnTouchListener speechOnTouchListener = new OnTouchListener() {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			int eventAction = event.getAction();
			if (eventAction == MotionEvent.ACTION_DOWN) {
				touchDownY = (int) event.getRawY();
				scrolldownX = (int) event.getRawX();

			}
			else if (eventAction == MotionEvent.ACTION_MOVE) {
				if (recordStatus == ChatInputConfig.VOICE_END) {
					scrolldownX = (int) event.getRawX();
				}
				else if (recordStatus == ChatInputConfig.VOICE_Start_OK) {
					int scrollX = (int) (scrolldownX - event.getRawX());
					scrollContainer(scrollX);
				}
			}
			else {
				speecherEND();
			}

			return false;
		}
	};

	public void stopSpeecher(boolean isSendEndMsg) {
		int preStatus = recordStatus;
		recordStatus = ChatInputConfig.VOICE_END;
		updateUIByVoiceStatus(ChatInputConfig.VOICE_END);
		if (isSendEndMsg) {
			if (preStatus == ChatInputConfig.VOICE_Start_OK) {

				if (null != mSendListener) {
					mSendListener.onChatSendVoice(views.sendspeech, ChatInputConfig.VOICE_End_OK);
				}
			}
		}
	}

	private void speecherStart() {
		int preStatus = recordStatus;
		recordStatus = ChatInputConfig.VOICE_Start_OK;
		updateUIByVoiceStatus(recordStatus);
		new SpeechThread().start();
		if (null != mSendListener && preStatus != ChatInputConfig.VOICE_Start_OK) {
			mSendListener.onChatSendVoice(views.sendspeech, ChatInputConfig.VOICE_Start_OK);
		}
	}

	private void speecherEND() {
		int preStatus = recordStatus;
		recordStatus = ChatInputConfig.VOICE_END;
		if (preStatus == ChatInputConfig.VOICE_End_Cancle) {
			updateUIByVoiceStatus(ChatInputConfig.VOICE_End_Cancle);
		}
		else {
			updateUIByVoiceStatus(ChatInputConfig.VOICE_END);
		}
		if (preStatus == ChatInputConfig.VOICE_Start_OK) {
			if (null != mSendListener) {
				mSendListener.onChatSendVoice(views.sendspeech, ChatInputConfig.VOICE_End_OK);
			}
		}

	}

	private void scrollContainer(int scrollX) {
		if (scrollMaxWidth == 0) {
			scrollMaxWidth = views.container.getWidth() - DisplayUtil.dipTopx(mContext, 40);
			scrollDelWidth = scrollMaxWidth * 3 / 5;
		}

		int realScrollx = scrollX;
		if (scrollX < 0) {
			realScrollx = 0;
		}
		if (scrollX > scrollMaxWidth) {
			realScrollx = scrollMaxWidth;
		}
		views.container.scrollTo(realScrollx, 0);
		if (realScrollx > scrollDelWidth) {
			if (recordStatus == ChatInputConfig.VOICE_Start_OK) {

				recordStatus = ChatInputConfig.VOICE_End_Cancle;
				views.container.scrollTo(0, 0);
				if (null != mSendListener) {
					mSendListener.onChatSendVoice(views.sendspeech, ChatInputConfig.VOICE_End_Cancle);
				}
				new StatusThread().start();
			}
		}

	}

	class StatusThread extends Thread {
		long timeStart = 0;

		public StatusThread() {
			super();
			timeStart = System.currentTimeMillis();
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			uIHandler.post(new Runnable() {

				@Override
				public void run() {
					if (null != mStatusListener) {
						mStatusListener.onDeleteViewStart(DELETE_TIME_VALUE);
					}

				}
			});
			while (true) {
				uIHandler.post(new Runnable() {

					@Override
					public void run() {
						views.speechstatus_image.setAlpha(255);
						views.speechstatus_image.setVisibility(View.VISIBLE);
						views.speechstatus_image.setSelected(true);

					}
				});
				if (System.currentTimeMillis() - timeStart > DELETE_TIME_VALUE) {
					uIHandler.post(new Runnable() {

						@Override
						public void run() {
							if (recordStatus == ChatInputConfig.VOICE_END) {
								views.speechstatus_image.setVisibility(View.GONE);
								views.speechstatus_image.setSelected(false);
							}
							else {
								views.speechstatus_image.setSelected(false);
								if (recordStatus == ChatInputConfig.VOICE_End_Cancle) {
									recordStatus = ChatInputConfig.VOICE_END;
								}
							}
							if (null != mStatusListener) {
								mStatusListener.onDeleteViewFinish();
							}

						}
					});

					break;
				}
				if (recordStatus == ChatInputConfig.VOICE_Start_OK) {
					uIHandler.post(new Runnable() {

						@Override
						public void run() {
							views.speechstatus_image.setVisibility(View.VISIBLE);
							views.speechstatus_image.setSelected(false);
							if (null != mStatusListener) {
								mStatusListener.onDeleteViewFinish();
							}

						}
					});
					break;

				}
				SystemClock.sleep(100);

			}
		}
	}

	class SpeechThread extends Thread {

		long timeStart = 0;
		long timeRealSercond = -1;
		int alpha = 255;

		public SpeechThread() {
			super();
			timeStart = System.currentTimeMillis();
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();

			while (true) {

				if (recordStatus == ChatInputConfig.VOICE_END || recordStatus == ChatInputConfig.VOICE_End_Cancle) {
					break;
				}
				long timeValue = System.currentTimeMillis() - timeStart;
				if (timeValue > 59 * 1000) {
					uIHandler.post(new Runnable() {

						@Override
						public void run() {
							speecherEND();
						}
					});
					break;
				}
				final long timeSercond = timeValue / 1000;

				alpha = alpha - 25;
				if (alpha < 0) {
					alpha = 255;
				}

				uIHandler.post(new Runnable() {

					@Override
					public void run() {
						if (timeRealSercond != timeSercond) {
							timeRealSercond = timeSercond;
							long sercond = timeSercond % 60;
							long minute = timeSercond / 60;
							views.speechstatus_time.setText(minute + ":" + String.format("%02d", sercond));
						}
						views.speechstatus_image.setAlpha(alpha);

					}
				});
				SystemClock.sleep(100);
			}

		}
	}
}
