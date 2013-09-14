package com.joyrill.app.thread;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.os.Handler;
import android.os.Message;

public class WeatherThread implements Runnable {

	private static final String NAMESPACE = "http://WebXml.com.cn/";
	private static final String URL = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx?wsdl";
	private static final String METHOD_NAME = "getWeatherbyCityName";
	private static final String SOAP_ACTION = "http://WebXml.com.cn/getWeatherbyCityName";

	private Handler handler = null;
	private int operate = 0;
	private String cityName;
	public WeatherThread(Handler handler , int operate , String cityName){
		this.handler = handler;
		this.operate = operate;
		this.cityName = cityName;
	}
	
	public void run() {
		try{
			Message msg = new Message();
			msg.what = operate;
			msg.obj = getWeather(cityName);
			handler.sendMessage(msg);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
	}

	public List<String> getWeather(String cityName) throws Exception {
		List<String> list = new ArrayList<String>();
		SoapObject rpc = new SoapObject(NAMESPACE, METHOD_NAME);
		rpc.addProperty("theCityName", cityName);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.bodyOut = rpc;
		envelope.dotNet = true;
		HttpTransportSE ht = new HttpTransportSE(URL);
		ht.call(SOAP_ACTION, envelope);
		envelope.setOutputSoapObject(rpc);
		SoapObject result = (SoapObject) envelope.bodyIn;
		SoapObject name = (SoapObject) result
				.getProperty("getWeatherbyCityNameResult");
		for (int i = 0; i < name.getPropertyCount(); i++) {
			list.add(name.getProperty(i).toString());
		}
		return list;
	}

}
