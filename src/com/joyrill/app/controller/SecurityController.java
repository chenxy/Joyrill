package com.joyrill.app.controller;

import com.joyrill.app.controller.base.BaseController;

public class SecurityController extends BaseController {

	public SecurityController() {
	}

	/**
	 * 
	 * @param type
	 *            类型 INDOOR:室内 OUTDOOR:室外
	 * @param command
	 *            执行事件 1:布防 2:撤防
	 * @param arg1
	 *            执行事件类型 0:立即 1：延迟
	 * @param arg2
	 *            延迟多少时间
	 * @param arg3
	 *            操作码
	 */
	public void indoorArming(String type, int command, int arg1, String arg2,
			int arg3) {
		// *JOYRILL*SECURITY*TYPE*COMMAND*ARG1*ARG2*CRC#
		StringBuffer sb = new StringBuffer();
		sb.append("*JOYRILL*SECURITY*");
		sb.append(type).append("*");
		sb.append(command).append("*");
		if (null != arg2) {
			sb.append(arg1).append("*").append(arg2).append("*CRC#");
		} else {
			sb.append(arg1).append("*").append("*CRC#");
		}
		super.sentMsg(sb.toString());

	}

}
