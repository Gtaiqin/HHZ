package com.bwie.ynf.bean;

public class Goods {
	private int _id;
	private String goodid;
	private String goodname;
	private double price;
	private int count;
	private String url;

	public Goods() {
		super();
	}

	public Goods(String goodsid, String goodname, double price, int count, String url) {
		super();
		this.goodid = goodsid;
		this.goodname = goodname;
		this.price = price;
		this.count = count;
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getGoodid() {
		return goodid;
	}

	public void setGoodid(String goodid) {
		this.goodid = goodid;
	}

	public String getGoodname() {
		return goodname;
	}

	public void setGoodname(String goodname) {
		this.goodname = goodname;
	}

	@Override
	public String toString() {
		return "shop_car_goods [ goodid=" + goodid + ", goodname=" + goodname + "]";
	}

}
