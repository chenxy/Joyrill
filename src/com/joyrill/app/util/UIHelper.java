package com.joyrill.app.util;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.joyrill.app.JoyrillActivityManager;
import com.joyrill.app.R;

public class UIHelper {

	public UIHelper() {
	}

	/**
	 * 发送App异常崩溃报告
	 * @param cont
	 * @param crashReport
	 */
	public static void sendAppCrashReport(final Context cont, final String crashReport)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(cont);
		builder.setIcon(android.R.drawable.ic_dialog_info);
		builder.setTitle("应用程序错误");
		builder.setMessage("很抱歉，应用程序出现错误，即将退出。\n请提交错误报告，我们会尽快修复这个问题！");
		builder.setPositiveButton("提交报告", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				try{
					
					MailSenderInfo mailInfo = new MailSenderInfo();    
					mailInfo.setMailServerHost("smtp.qq.com");    
					mailInfo.setMailServerPort("25");    
					mailInfo.setValidate(true);    
					mailInfo.setUserName("308068086@qq.com");  //你的邮箱地址  
					mailInfo.setPassword("heyi8801131431.");//您的邮箱密码    
					mailInfo.setFromAddress("308068086@qq.com");    
					mailInfo.setToAddress("308068086@qq.com");    
					mailInfo.setSubject("JoyrillHD错误日志");    
					mailInfo.setContent(crashReport);    
					
					//这个类主要来发送邮件   
					SimpleMailSender sms = new SimpleMailSender();   
					sms.sendTextMail(mailInfo);//发送文体格式    
				}catch(Exception e){
					e.printStackTrace();
				}
				JoyrillActivityManager.getAppManager().AppExit(cont);
			}
		});
		builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				//退出
				JoyrillActivityManager.getAppManager().AppExit(cont);
			}
		});
		builder.show();
	}
	
	/**
	 * 退出程序
	 * 
	 * @param cont
	 */
	public static void Exit(final Context cont) {
		AlertDialog.Builder builder = new AlertDialog.Builder(cont);
		builder.setIcon(android.R.drawable.ic_dialog_info);
		builder.setTitle(R.string.app_msg_exit);
		builder.setPositiveButton(R.string.msg_btn_yes,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						// 退出
						JoyrillActivityManager.getAppManager().AppExit(cont);
					}
				});
		builder.setNegativeButton(R.string.msg_btn_no,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		builder.show();
	}
}
