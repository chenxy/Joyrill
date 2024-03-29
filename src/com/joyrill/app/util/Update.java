package com.joyrill.app.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

public class Update implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3770198436345107080L;
	public final static String UTF8 = "UTF-8";
	public final static String NODE_ROOT = "Joyrill";

	private int versionCode;
	private String versionName;
	private String downloadUrl;
	private String updateLog;

	public int getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(int versionCode) {
		this.versionCode = versionCode;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public String getUpdateLog() {
		return updateLog;
	}

	public void setUpdateLog(String updateLog) {
		this.updateLog = updateLog;
	}

	public static Update parse(InputStream inputStream) throws IOException {
		Update update = null;
		XmlPullParser xmlParser = Xml.newPullParser();
		try {
			xmlParser.setInput(inputStream, UTF8);
			int evtType = xmlParser.getEventType();
			while (evtType != XmlPullParser.END_DOCUMENT) {
				String tag = xmlParser.getName();
				switch (evtType) {
				case XmlPullParser.START_TAG:
					if (tag.equalsIgnoreCase("android")) {
						update = new Update();
					} else if (update != null) {
						if (tag.equalsIgnoreCase("versionCode")) {
							update.setVersionCode(StringUtils.toInt(
									xmlParser.nextText(), 0));
						} else if (tag.equalsIgnoreCase("versionName")) {
							update.setVersionName(xmlParser.nextText());
						} else if (tag.equalsIgnoreCase("downloadUrl")) {
							update.setDownloadUrl(xmlParser.nextText());
						} else if (tag.equalsIgnoreCase("updateLog")) {
							update.setUpdateLog(xmlParser.nextText());
						}
					}
					break;
				case XmlPullParser.END_TAG:
					break;
				}
				evtType = xmlParser.next();
			}
		} catch (XmlPullParserException e) {
		} finally {
			inputStream.close();
		}
		return update;
	}
}
