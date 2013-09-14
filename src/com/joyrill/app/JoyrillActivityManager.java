package com.joyrill.app;

import java.util.Stack;

import com.joyrill.app.logger.Logger;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

public class JoyrillActivityManager {
	
	private static Stack<Activity> activityStack;
	private static JoyrillActivityManager instance;
	
	private JoyrillActivityManager(){}
	public static JoyrillActivityManager getAppManager(){
		if(instance==null){
			instance=new JoyrillActivityManager();
		}
		return instance;
	}
	public void addActivity(Activity activity){
		if(activityStack==null){
			activityStack=new Stack<Activity>();
		}
		Logger.d("put activity: "+ activity.getClass().getName());
		activityStack.add(activity);
	}
	public Activity currentActivity(){
		Activity activity=activityStack.lastElement();
		return activity;
	}
	public void finishActivity(){
		Activity activity=activityStack.lastElement();
		finishActivity(activity);
	}
	public void finishActivity(Activity activity){
		if(activity!=null){
			Logger.d("finish activity: "+ activity.getClass().getName());
			activityStack.remove(activity);
			activity.finish();
			activity=null;
		}
	}
	public void finishActivity(Class<?> cls){
		for (Activity activity : activityStack) {
			if(activity.getClass().equals(cls) ){
				Logger.d("finish activity: "+ cls.getName());
				finishActivity(activity);
			}
		}
	}
	public void finishAllActivity(){
		for (int i = 0, size = activityStack.size(); i < size; i++){
            if (null != activityStack.get(i)){
            	Logger.d("finish activity: "+ activityStack.get(i).getClass().getName());
            	activityStack.get(i).finish();
            }
	    }
		activityStack.clear();
	}
	public void AppExit(Context context) {
		try {
			finishAllActivity();
			ActivityManager activityMgr= (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
			activityMgr.restartPackage(context.getPackageName());
			System.exit(0);
			//android.os.Process.killProcess(android.os.Process.myPid());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}