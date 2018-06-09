/**
 *	@copyright 亿康通-2015 
 * 	@author wanruome  
 * 	@create 2015年11月13日 下午1:01:36 
 */
package com.xdt.xudutong.tianjian;

import android.view.View;

public interface ChatInputSendListener {
	void onChatSendText(View v, String msgString);

	void onChatSendVoice(View v, int sendStatus);

	void onChatSendImage(View v);
}
