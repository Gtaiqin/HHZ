package com.bwie.ynf.bean;

import java.util.List;

public class MoreBean {

	private int code;
	private String msg;

	private List<DataBean> data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<DataBean> getData() {
		return data;
	}

	public void setData(List<DataBean> data) {
		this.data = data;
	}

	public static class DataBean {
		private String id;
		private String goods_name;
		private double shop_price;
		private double market_price;
		private boolean is_coupon_allowed;
		private boolean is_allow_credit;
		private String goods_img;
		private int sales_volume;
		private String efficacy;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getGoods_name() {
			return goods_name;
		}

		public void setGoods_name(String goods_name) {
			this.goods_name = goods_name;
		}

		public double getShop_price() {
			return shop_price;
		}

		public void setShop_price(double shop_price) {
			this.shop_price = shop_price;
		}

		public double getMarket_price() {
			return market_price;
		}

		public void setMarket_price(double market_price) {
			this.market_price = market_price;
		}

		public boolean isIs_coupon_allowed() {
			return is_coupon_allowed;
		}

		public void setIs_coupon_allowed(boolean is_coupon_allowed) {
			this.is_coupon_allowed = is_coupon_allowed;
		}

		public boolean isIs_allow_credit() {
			return is_allow_credit;
		}

		public void setIs_allow_credit(boolean is_allow_credit) {
			this.is_allow_credit = is_allow_credit;
		}

		public String getGoods_img() {
			return goods_img;
		}

		public void setGoods_img(String goods_img) {
			this.goods_img = goods_img;
		}

		public int getSales_volume() {
			return sales_volume;
		}

		public void setSales_volume(int sales_volume) {
			this.sales_volume = sales_volume;
		}

		public String getEfficacy() {
			return efficacy;
		}

		public void setEfficacy(String efficacy) {
			this.efficacy = efficacy;
		}
	}
}
