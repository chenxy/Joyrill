package com.joyrill.app.controller;

import com.joyrill.app.controller.base.BaseController;

public class SecurityController extends BaseController {

	public SecurityController() {
	}

	/**
	 * 
	 * @param type
	 *            ���� INDOOR:���� OUTDOOR:����
	 * @param command
	 *            ִ���¼� 1:���� 2:����
	 * @param arg1
	 *            ִ���¼����� 0:���� 1���ӳ�
	 * @param arg2
	 *            �ӳٶ���ʱ��
	 * @param arg3
	 *            ������
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
