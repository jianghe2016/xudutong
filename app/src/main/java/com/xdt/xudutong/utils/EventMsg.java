/**
 * 创建日期:2016年10月8日上午11:59:05
 * 作者:Administrator
 * 描述:TODO
 */
package com.xdt.xudutong.utils;

/***

*/
public class EventMsg {
	public static final int PERSON_YU_E=1;
	public static final int TRUENAMESTATES=2;
	public static final int TUICHU=3;
	//天健的标签，用于ASKWebViewUtilsActivity类中
	public int flag;
	//相册jpg
	public static final int CAMERA11=4;
	//相册png
	public static final int PICTUREPNG=5;
	//相机jpg
	public static final int PICTUREJPG=6;

	public static final int TRUENAMENUMBER=7;
	//免密查询状态的传递
	public static final int PERSONSSECRETSTATES=10;
	//昵称发送
	public static final int PERSON_TICKNAMEEDITTEXTSTRING=8;
	//重新提交实名认证
	public static final int AGAINTURENAME=9;
	//绑卡成功的状态
	public static final int ADDCARDSTATES=11;
	//解绑许都通卡
	public static final int JIEBANGCARD=12;
	//挂失许都通卡
	public static final int GUASHICARD=13;
	public static final int LITTLEGREENSTARTTIME=14;
	public static final int LITTLEGREENENDTIME=15;
	public static final int MESSAGEINFOREADED=16;
	public static final int XIOALV = 18;
	//个人中心实名认证横幅
	public static final int TRUENAMEFLAG=17;
	//home_button发送卡号给下一页
	public int what;
	public Object data;

	public EventMsg(int what, Object data) {
		this.what = what;
		this.data = data;
	}

/*	public EventMsg(String message, Bitmap bitmap) {
		this.message = message;
		this.bitmap = bitmap;
	}*/

}
