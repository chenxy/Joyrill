package com.joyrill.app.model;


public class Url {

	private String address;
	private String port;

	public Url() {
		super();
	}

	public Url(String address, String port) {
		super();
		this.address = address;
		this.port = port;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	@Override
	public String toString() {
		return "Url [address=" + address + ", port=" + port + "]";
	}
}
