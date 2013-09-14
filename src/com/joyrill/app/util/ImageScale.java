package com.joyrill.app.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.joyrill.app.JoyrillApplication;
import com.joyrill.app.logger.Logger;

public class ImageScale {

	public static final String IMAGE_PATH = "image/";

	private static Map<String, SoftReference<Bitmap>> cache;

	static {
		cache = new HashMap<String, SoftReference<Bitmap>>();
	}

	public static Bitmap getImageFromAssetsFile(String fileName) {

		if (cache.containsKey(fileName)) {

			SoftReference<Bitmap> softReference = cache.get(fileName);

			Bitmap bitmap = softReference.get();

			if (null != bitmap) {
				Logger.d("loading image " + fileName + "from cache");
				return bitmap;
			}

		}
		String url = fileName;
		InputStream is = null;
		String temp = IMAGE_PATH
				+ url.substring(url.lastIndexOf("/") + 1, url.length());
		Bitmap image = null;
		AssetManager am = JoyrillApplication.getInstance().getAssets();
		try {
			is = am.open(temp);
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inPreferredConfig = Bitmap.Config.ARGB_4444;
			image = BitmapFactory.decodeStream(is, null, options);
			cache.put(fileName, new SoftReference<Bitmap>(image));
			Logger.d("loading image " + fileName + " from assert");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return image;
	}

}
