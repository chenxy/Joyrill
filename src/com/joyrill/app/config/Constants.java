package com.joyrill.app.config;

public class Constants {
	public static final String RETURN = "returnStr";
	public static final String DATABASE_NAME = "zjdata";
	public static final String ARG1 = "arg1";
	public static final String ARG2 = "arg2";
	
	public static class TAG{
		public static final String APP_TAG = "JOYRILL";
		public static final String SOCKET_TAG = "JOYRILL_SOCKET";
		public static final String MESSAGE_TAG = "JOYRILL_MESSAGE";
	}
	
	public static class LANGUAGE{
		public static final String LANGUAGE_EN = "en";
		public static final String LANGUAGE_CN = "CN";
	}
	
	public static class STATUS{
		public static final String OK = "*JOYRILL*OK#";
		public static final String ERROR1 = "*JOYRILL*ERROR1#";
		public static final String ERROR2 = "*JOYRILL*ERROR2#";
		public static final String ERROR3 = "*JOYRILL*ERROR3#";
		public static final String ERROR4 = "*JOYRILL*ERROR4#";
		public static final String ERROR5 = "*JOYRILL*ERROR5#";
		public static final String ALARM = "*JOYRILL*ALARM#";
		public static final String LOGIN_SUCCESS = "*JOYRILL*ADMIN#";
		public static final String ERROR_PASS_ERROR = "*JOYRILL*PASSWD ERROR#";
		public static final String ERROR_NO_USER = "*JOYRILL*NO USER#";
		public static final String SOCKET_ERROR="*JOYRILL*SOCKETERROR#";
	}
}
