/**
 *	@copyright 亿康通-2015 
 * 	@author wanruome  
 * 	@create 2015年11月14日 下午1:42:53 
 */
package com.xdt.xudutong.tianjian;


public interface MP3RecorderListener {
	void onMp3RecoderStart(boolean isTrueRecorder);

	void onMp3RecoderSotp(String mp3FilePath, long length, boolean isTrueRecorder);

}
