package com.joyrill.app.exception;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;

import org.apache.commons.httpclient.HttpException;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Environment;
import android.os.Looper;
import android.widget.Toast;

import com.joyrill.app.JoyrillActivityManager;
import com.joyrill.app.JoyrillApplication;
import com.joyrill.app.R;
import com.joyrill.app.util.UIHelper;

public class JoyrillException extends Exception implements
		UncaughtExceptionHandler {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final static boolean Debug = true;
	public final static byte TYPE_NETWORK = 0x01;
	public final static byte TYPE_SOCKET = 0x02;
	public final static byte TYPE_HTTP_CODE = 0x03;
	public final static byte TYPE_HTTP_ERROR = 0x04;
	public final static byte TYPE_XML = 0x05;
	public final static byte TYPE_IO = 0x06;
	public final static byte TYPE_RUN = 0x07;

	private byte type;
	private int code;

	private Thread.UncaughtExceptionHandler mDefaultHandler;

	private JoyrillException() {
		this.mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
	}

	private JoyrillException(byte type, int code, Exception excp) {
		super(excp);
		this.type = type;
		this.code = code;
		if (Debug) {
			this.saveErrorLog(excp);
		}
	}

	public int getCode() {
		return this.code;
	}

	public int getType() {
		return this.type;
	}

	public void makeToast(Context ctx) {
		switch (this.getType()) {
		case TYPE_HTTP_CODE:
			String err = ctx.getString(R.string.http_status_code_error,
					this.getCode());
			Toast.makeText(ctx, err, Toast.LENGTH_SHORT).show();
			break;
		case TYPE_HTTP_ERROR:
			Toast.makeText(ctx, R.string.http_exception_error,
					Toast.LENGTH_SHORT).show();
			break;
		case TYPE_SOCKET:
			Toast.makeText(ctx, R.string.socket_exception_error,
					Toast.LENGTH_SHORT).show();
			break;
		case TYPE_NETWORK:
			Toast.makeText(ctx, R.string.network_not_connected,
					Toast.LENGTH_SHORT).show();
			break;
		case TYPE_XML:
			Toast.makeText(ctx, R.string.xml_parser_failed, Toast.LENGTH_SHORT)
					.show();
			break;
		case TYPE_IO:
			Toast.makeText(ctx, R.string.io_exception_error, Toast.LENGTH_SHORT)
					.show();
			break;
		case TYPE_RUN:
			Toast.makeText(ctx, R.string.app_run_code_error, Toast.LENGTH_SHORT)
					.show();
			break;
		}
	}

	public void saveErrorLog(Exception excp) {
		String errorlog = "errorlog.txt";
		String savePath = "";
		String logFilePath = "";
		FileWriter fw = null;
		PrintWriter pw = null;
		try {
			String storageState = Environment.getExternalStorageState();
			if (storageState.equals(Environment.MEDIA_MOUNTED)) {
				savePath = Environment.getExternalStorageDirectory()
						.getAbsolutePath() + "/joyrill/log/";
				File file = new File(savePath);
				if (!file.exists()) {
					file.mkdirs();
				}
				logFilePath = savePath + errorlog;
			}
			if (logFilePath == "") {
				return;
			}
			File logFile = new File(logFilePath);
			if (!logFile.exists()) {
				logFile.createNewFile();
			}
			fw = new FileWriter(logFile, true);
			pw = new PrintWriter(fw);
			pw.println("--------------------" + (new Date().toLocaleString())
					+ "---------------------");
			excp.printStackTrace(pw);
			pw.close();
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.close();
			}
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
				}
			}
		}

	}

	public static JoyrillException http(int code) {
		return new JoyrillException(TYPE_HTTP_CODE, code, null);
	}

	public static JoyrillException http(Exception e) {
		return new JoyrillException(TYPE_HTTP_ERROR, 0, e);
	}

	public static JoyrillException socket(Exception e) {
		return new JoyrillException(TYPE_SOCKET, 0, e);
	}

	public static JoyrillException io(Exception e) {
		if (e instanceof UnknownHostException || e instanceof ConnectException) {
			return new JoyrillException(TYPE_NETWORK, 0, e);
		} else if (e instanceof IOException) {
			return new JoyrillException(TYPE_IO, 0, e);
		}
		return run(e);
	}

	public static JoyrillException xml(Exception e) {
		return new JoyrillException(TYPE_XML, 0, e);
	}

	public static JoyrillException network(Exception e) {
		if (e instanceof UnknownHostException || e instanceof ConnectException) {
			return new JoyrillException(TYPE_NETWORK, 0, e);
		} else if (e instanceof HttpException) {
			return http(e);
		} else if (e instanceof SocketException) {
			return socket(e);
		}
		return http(e);
	}

	public static JoyrillException run(Exception e) {
		return new JoyrillException(TYPE_RUN, 0, e);
	}

	public static JoyrillException getAppExceptionHandler() {
		return new JoyrillException();
	}

	@Override
	public void uncaughtException(Thread thread, Throwable ex) {

		if (!handleException(ex) && mDefaultHandler != null) {
			mDefaultHandler.uncaughtException(thread, ex);
		}

	}

	private boolean handleException(Throwable ex) {
		if (ex == null) {
			return false;
		}
		final Context context = JoyrillActivityManager.getAppManager().currentActivity();
		if(context == null) {
			return false;
		}
		final String crashReport = getCrashReport(context, ex);
		new Thread() {
			public void run() {
				Looper.prepare();
				UIHelper.sendAppCrashReport(context, crashReport);
				Looper.loop();
			}

		}.start();
		return true;
	}

	/**
	 * 获取APP崩溃异常报告
	 * @param ex
	 * @return
	 */
	private String getCrashReport(Context context, Throwable ex) {
		PackageInfo pinfo = ((JoyrillApplication)context.getApplicationContext()).getPackageInfo();
		StringBuffer exceptionStr = new StringBuffer();
		exceptionStr.append("Version: "+pinfo.versionName+"("+pinfo.versionCode+")\n");
		exceptionStr.append("Android: "+android.os.Build.VERSION.RELEASE+"("+android.os.Build.MODEL+")\n");
		exceptionStr.append("Exception: "+ex.getMessage()+"\n");
		StackTraceElement[] elements = ex.getStackTrace();
		for (int i = 0; i < elements.length; i++) {
			exceptionStr.append(elements[i].toString()+"\n");
		}
		return exceptionStr.toString();
	}
}
