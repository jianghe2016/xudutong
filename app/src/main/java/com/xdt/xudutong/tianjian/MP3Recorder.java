package com.xdt.xudutong.tianjian;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Handler;
import android.os.Message;

import com.czt.mp3recorder.util.LameUtil;

import java.io.File;
import java.io.IOException;
//是负责调用AudioRecorder进行录音的类
public class MP3Recorder implements Mp3EncodeListener {
	// =======================AudioRecord Default Settings=======================
	private static final int DEFAULT_AUDIO_SOURCE = MediaRecorder.AudioSource.MIC;
	/**
	 * 以下三项为默认配置参数。Google Android文档明确表明只有以下3个参数是可以在所有设备上保证支持的。
	 */
	private static final int DEFAULT_SAMPLING_RATE = 16000;// 模拟器仅支持从麦克风输入8kHz采样率
	private static final int DEFAULT_CHANNEL_CONFIG = AudioFormat.CHANNEL_IN_MONO;
	/**
	 * 下面是对此的封装 private static final int DEFAULT_AUDIO_FORMAT = AudioFormat.ENCODING_PCM_16BIT;
	 */
	private static final PCMFormat DEFAULT_AUDIO_FORMAT = PCMFormat.PCM_16BIT;

	// ======================Lame Default Settings=====================
	private static final int DEFAULT_LAME_MP3_QUALITY = 7;
	/**
	 * 与DEFAULT_CHANNEL_CONFIG相关，因为是mono单声，所以是1
	 */
	private static final int DEFAULT_LAME_IN_CHANNEL = 1;
	/**
	 * Encoded bit rate. MP3 file will be encoded with bit rate 32kbps
	 */
	private static final int DEFAULT_LAME_MP3_BIT_RATE = 32;

	// ==================================================================

	/**
	 * 自定义 每160帧作为一个周期，通知一下需要进行编码
	 */
	private static final int FRAME_COUNT = 160;
	private AudioRecord mAudioRecord = null;
	private int mBufferSize;
	private short[] mPCMBuffer;
	private DataEncodeThread mEncodeThread;
	private boolean mIsRecording = false;
	private final File mRecordFile;
	private final Handler uIHandler = new Handler();
	private MP3RecorderListener mp3RecorderListener;
	private long recorderLength = 0;
	private long recorderStartTime = 0;
	boolean isTrueRecorder = false;

	/**
	 * Default constructor. Setup recorder with default sampling rate 1 channel, 16 bits pcm
	 */
	public MP3Recorder(File recordFile) {
		mRecordFile = recordFile;
	}

	@Override
	public void onEncodeStop() {
		// SystemClock.setCurrentTimeMillis(150);
		uIHandler.post(new Runnable() {

			@Override
			public void run() {
				if (null != mp3RecorderListener) {
					if (null != mRecordFile && mRecordFile.exists()) {
						if (FileUtils.getSizeBytes(mRecordFile) > 2048) {
							// 32bit每秒最小为8192字节
							mp3RecorderListener.onMp3RecoderSotp(mRecordFile.getPath(), recorderLength, isTrueRecorder);
						}
						else if (recorderLength >= 1000) {
							// ToastUtil.makeShortToast(CommonApplication.getApplication(),
							// "录音失败，请检查录音权限是否开启！");
							mRecordFile.delete();
							recorderLength = 0;
							mp3RecorderListener.onMp3RecoderSotp(null, recorderLength, false);
						}
						else {
							mRecordFile.delete();
							recorderLength = 0;
							mp3RecorderListener.onMp3RecoderSotp(null, recorderLength, false);
						}
					}
					else {
						recorderLength = 0;
						mp3RecorderListener.onMp3RecoderSotp(null, recorderLength, false);
					}
				}

			}
		});

	}

	/**
	 * Start recording. Create an encoding thread. Start record from this thread.
	 *
	 * @throws IOException
	 */
	public void start() {
		if (mIsRecording) {
			return;
		}
		try {
			isTrueRecorder = true;
			initAudioRecorder();
		}
		catch (Exception e) {
			e.printStackTrace();
			isTrueRecorder = false;

		}
		if (null != mp3RecorderListener) {
			mp3RecorderListener.onMp3RecoderStart(isTrueRecorder);
		}
		try {
			mAudioRecord.startRecording();
		}
		catch (Exception e) {
			isTrueRecorder = false;
		}
		if (!isTrueRecorder) {
			return;
		}

		new Thread() {

			@Override
			public void run() {
				// 设置线程权限
				android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_URGENT_AUDIO);
				mIsRecording = true;
				recorderStartTime = System.currentTimeMillis();
				while (mIsRecording) {
					int readSize = mAudioRecord.read(mPCMBuffer, 0, mBufferSize);
					if (readSize > 0) {
						mEncodeThread.addTask(mPCMBuffer, readSize);
						calculateRealVolume(mPCMBuffer, readSize);
					}
				}
				// release and finalize audioRecord
				mAudioRecord.stop();
				mAudioRecord.release();
				mAudioRecord = null;
				if (recorderStartTime > 0) {
					recorderLength = System.currentTimeMillis() - recorderStartTime;
				}
				else {
					recorderLength = 0;
				}
				// stop the encoding thread and try to wait
				// until the thread finishes its job
				Message msg = Message.obtain(mEncodeThread.getHandler(), DataEncodeThread.PROCESS_STOP);
				msg.sendToTarget();
				// SystemClock.setCurrentTimeMillis(150);
				// uIHandler.post(new Runnable() {
				//
				// @Override
				// public void run() {
				// if (null != mp3RecorderListener) {
				// mp3RecorderListener.onMp3RecoderSotp(mRecordFile, recorderLength,
				// isTrueRecorder);
				// }
				//
				// }
				// });
			}

			/**
			 * 此计算方法来自samsung开发范例
			 *
			 * @param buffer
			 *            buffer
			 * @param readSize
			 *            readSize
			 */
			private void calculateRealVolume(short[] buffer, int readSize) {
				int sum = 0;
				for (int i = 0; i < readSize; i++) {
					// 这里没有做运算的优化，为了更加清晰的展示代码
					sum += buffer[i] * buffer[i];
				}
				if (readSize > 0) {
					double amplitude = sum / readSize;
					mVolume = (int) Math.sqrt(amplitude);
				}
			}
		}.start();
	}

	private int mVolume;

	public int getVolume() {
		return mVolume;
	}

	private static final int MAX_VOLUME = 2000;

	public int getMaxVolume() {
		return MAX_VOLUME;
	}

	public void stop() {
		mIsRecording = false;
		if (!isTrueRecorder) {
			if (null != mp3RecorderListener) {
				mp3RecorderListener.onMp3RecoderSotp(null, 0, isTrueRecorder);
			}
		}
	}

	public boolean isRecording() {
		return mIsRecording;
	}

	public MP3RecorderListener getRecorderListener() {
		return mp3RecorderListener;
	}

	public void setRecorderListener(MP3RecorderListener mp3RecorderListener) {
		this.mp3RecorderListener = mp3RecorderListener;
	}

	public long getLengthOfMp3Recorder() {
		return recorderLength;
	}

	/**
	 * Initialize audio recorder
	 */
	private void initAudioRecorder() throws IOException {
		mBufferSize = AudioRecord.getMinBufferSize(DEFAULT_SAMPLING_RATE, DEFAULT_CHANNEL_CONFIG,
				DEFAULT_AUDIO_FORMAT.getAudioFormat());

		int bytesPerFrame = DEFAULT_AUDIO_FORMAT.getBytesPerFrame();
		/*
		 * Get number of samples. Calculate the buffer size (round up to the factor of given frame
		 * size) 使能被整除，方便下面的周期性通知
		 */
		//计算缓冲区的大小，使其是设置周期帧数的整数倍，方便循环
		int frameSize = mBufferSize / bytesPerFrame;
		if (frameSize % FRAME_COUNT != 0) {
			frameSize += FRAME_COUNT - frameSize % FRAME_COUNT;
			mBufferSize = frameSize * bytesPerFrame;
		}

		/* Setup audio recorder */
		mAudioRecord = new AudioRecord(DEFAULT_AUDIO_SOURCE, DEFAULT_SAMPLING_RATE, DEFAULT_CHANNEL_CONFIG,
				DEFAULT_AUDIO_FORMAT.getAudioFormat(), mBufferSize);

		mPCMBuffer = new short[mBufferSize];
		/*
		 * Initialize lame buffer mp3 sampling rate is the same as the recorded pcm sampling rate
		 * The bit rate is 32kbps
		 */
        LameUtil.init(DEFAULT_SAMPLING_RATE, DEFAULT_LAME_IN_CHANNEL, DEFAULT_SAMPLING_RATE, DEFAULT_LAME_MP3_BIT_RATE,
				DEFAULT_LAME_MP3_QUALITY);
		// Create and run thread used to encode data
		// The thread will
		// 创建转码的线程
		mEncodeThread = new DataEncodeThread(mRecordFile, mBufferSize);
		mEncodeThread.setMp3EncodeListener(this);
		mEncodeThread.start();
		//给AudioRecord设置刷新监听，待录音帧数每次达到FRAME_COUNT，就通知转换线程转换一次数据
		mAudioRecord.setRecordPositionUpdateListener(mEncodeThread, mEncodeThread.getHandler());
		mAudioRecord.setPositionNotificationPeriod(FRAME_COUNT);
	}

}
