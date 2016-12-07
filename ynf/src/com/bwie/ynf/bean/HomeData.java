package com.bwie.ynf.bean;

import java.util.List;

public class HomeData {

	private DataBean data;

	public DataBean getData() {
		return data;
	}

	public void setData(DataBean data) {
		this.data = data;
	}

	public static class DataBean {

		private List<Ad1Bean> ad1;

		private List<Ad3Bean> ad3;

		private List<Ad4Bean> ad4;

		private List<Ad5Bean> ad5;

		private List<GroupBriefsBean> groupBriefs;

		public List<Ad1Bean> getAd1() {
			return ad1;
		}

		public void setAd1(List<Ad1Bean> ad1) {
			this.ad1 = ad1;
		}

		public List<Ad3Bean> getAd3() {
			return ad3;
		}

		public void setAd3(List<Ad3Bean> ad3) {
			this.ad3 = ad3;
		}

		public List<Ad4Bean> getAd4() {
			return ad4;
		}

		public void setAd4(List<Ad4Bean> ad4) {
			this.ad4 = ad4;
		}

		public List<Ad5Bean> getAd5() {
			return ad5;
		}

		public void setAd5(List<Ad5Bean> ad5) {
			this.ad5 = ad5;
		}

		public List<GroupBriefsBean> getGroupBriefs() {
			return groupBriefs;
		}

		public void setGroupBriefs(List<GroupBriefsBean> groupBriefs) {
			this.groupBriefs = groupBriefs;
		}

		public static class Ad1Bean {
			private int ad_type;
			private String ad_type_dynamic;
			private String ad_type_dynamic_data;
			private String createtime;
			private String createuser;
			private int enabled;
			private String id;
			private String image;
			private int position;
			private String show_channel;
			private int sort;

			public int getAd_type() {
				return ad_type;
			}

			public void setAd_type(int ad_type) {
				this.ad_type = ad_type;
			}

			public String getAd_type_dynamic() {
				return ad_type_dynamic;
			}

			public void setAd_type_dynamic(String ad_type_dynamic) {
				this.ad_type_dynamic = ad_type_dynamic;
			}

			public String getAd_type_dynamic_data() {
				return ad_type_dynamic_data;
			}

			public void setAd_type_dynamic_data(String ad_type_dynamic_data) {
				this.ad_type_dynamic_data = ad_type_dynamic_data;
			}

			public String getCreatetime() {
				return createtime;
			}

			public void setCreatetime(String createtime) {
				this.createtime = createtime;
			}

			public String getCreateuser() {
				return createuser;
			}

			public void setCreateuser(String createuser) {
				this.createuser = createuser;
			}

			public int getEnabled() {
				return enabled;
			}

			public void setEnabled(int enabled) {
				this.enabled = enabled;
			}

			public String getId() {
				return id;
			}

			public void setId(String id) {
				this.id = id;
			}

			public String getImage() {
				return image;
			}

			public void setImage(String image) {
				this.image = image;
			}

			public int getPosition() {
				return position;
			}

			public void setPosition(int position) {
				this.position = position;
			}

			public String getShow_channel() {
				return show_channel;
			}

			public void setShow_channel(String show_channel) {
				this.show_channel = show_channel;
			}

			public int getSort() {
				return sort;
			}

			public void setSort(int sort) {
				this.sort = sort;
			}
		}

		public static class Ad3Bean {
			private String end_seconds;
			private String id;
			private String lottery_img;
			private String prize_name;
			private String start_seconds;
			private String status;

			public String getEnd_seconds() {
				return end_seconds;
			}

			public void setEnd_seconds(String end_seconds) {
				this.end_seconds = end_seconds;
			}

			public String getId() {
				return id;
			}

			public void setId(String id) {
				this.id = id;
			}

			public String getLottery_img() {
				return lottery_img;
			}

			public void setLottery_img(String lottery_img) {
				this.lottery_img = lottery_img;
			}

			public String getPrize_name() {
				return prize_name;
			}

			public void setPrize_name(String prize_name) {
				this.prize_name = prize_name;
			}

			public String getStart_seconds() {
				return start_seconds;
			}

			public void setStart_seconds(String start_seconds) {
				this.start_seconds = start_seconds;
			}

			public String getStatus() {
				return status;
			}

			public void setStatus(String status) {
				this.status = status;
			}
		}

		public static class Ad4Bean {
			private int ad_type;
			private String ad_type_dynamic;
			private String ad_type_dynamic_data;
			private String createtime;
			private String createuser;
			private int enabled;

			private GoodsBean goods;
			private String id;
			private String image;
			private String lastupdateuser;
			private int position;
			private String show_channel;
			private int sort;
			private String url;

			public int getAd_type() {
				return ad_type;
			}

			public void setAd_type(int ad_type) {
				this.ad_type = ad_type;
			}

			public String getAd_type_dynamic() {
				return ad_type_dynamic;
			}

			public void setAd_type_dynamic(String ad_type_dynamic) {
				this.ad_type_dynamic = ad_type_dynamic;
			}

			public String getAd_type_dynamic_data() {
				return ad_type_dynamic_data;
			}

			public void setAd_type_dynamic_data(String ad_type_dynamic_data) {
				this.ad_type_dynamic_data = ad_type_dynamic_data;
			}

			public String getCreatetime() {
				return createtime;
			}

			public void setCreatetime(String createtime) {
				this.createtime = createtime;
			}

			public String getCreateuser() {
				return createuser;
			}

			public void setCreateuser(String createuser) {
				this.createuser = createuser;
			}

			public int getEnabled() {
				return enabled;
			}

			public void setEnabled(int enabled) {
				this.enabled = enabled;
			}

			public GoodsBean getGoods() {
				return goods;
			}

			public void setGoods(GoodsBean goods) {
				this.goods = goods;
			}

			public String getId() {
				return id;
			}

			public void setId(String id) {
				this.id = id;
			}

			public String getImage() {
				return image;
			}

			public void setImage(String image) {
				this.image = image;
			}

			public String getLastupdateuser() {
				return lastupdateuser;
			}

			public void setLastupdateuser(String lastupdateuser) {
				this.lastupdateuser = lastupdateuser;
			}

			public int getPosition() {
				return position;
			}

			public void setPosition(int position) {
				this.position = position;
			}

			public String getShow_channel() {
				return show_channel;
			}

			public void setShow_channel(String show_channel) {
				this.show_channel = show_channel;
			}

			public int getSort() {
				return sort;
			}

			public void setSort(int sort) {
				this.sort = sort;
			}

			public String getUrl() {
				return url;
			}

			public void setUrl(String url) {
				this.url = url;
			}

			public static class GoodsBean {
				private int allocated_stock;
				private int collect_count;
				private boolean is_coupon_allowed;
				private int is_gift;
				private boolean reservable;
				private int restrict_purchase_num;
				private int restriction;

				public int getAllocated_stock() {
					return allocated_stock;
				}

				public void setAllocated_stock(int allocated_stock) {
					this.allocated_stock = allocated_stock;
				}

				public int getCollect_count() {
					return collect_count;
				}

				public void setCollect_count(int collect_count) {
					this.collect_count = collect_count;
				}

				public boolean isIs_coupon_allowed() {
					return is_coupon_allowed;
				}

				public void setIs_coupon_allowed(boolean is_coupon_allowed) {
					this.is_coupon_allowed = is_coupon_allowed;
				}

				public int getIs_gift() {
					return is_gift;
				}

				public void setIs_gift(int is_gift) {
					this.is_gift = is_gift;
				}

				public boolean isReservable() {
					return reservable;
				}

				public void setReservable(boolean reservable) {
					this.reservable = reservable;
				}

				public int getRestrict_purchase_num() {
					return restrict_purchase_num;
				}

				public void setRestrict_purchase_num(int restrict_purchase_num) {
					this.restrict_purchase_num = restrict_purchase_num;
				}

				public int getRestriction() {
					return restriction;
				}

				public void setRestriction(int restriction) {
					this.restriction = restriction;
				}
			}
		}

		public static class Ad5Bean {
			private int ad_type;
			private String ad_type_dynamic;
			private int enabled;
			private String id;
			private String image;
			private int position;
			private String show_channel;
			private int sort;
			private String title;

			public int getAd_type() {
				return ad_type;
			}

			public void setAd_type(int ad_type) {
				this.ad_type = ad_type;
			}

			public String getAd_type_dynamic() {
				return ad_type_dynamic;
			}

			public void setAd_type_dynamic(String ad_type_dynamic) {
				this.ad_type_dynamic = ad_type_dynamic;
			}

			public int getEnabled() {
				return enabled;
			}

			public void setEnabled(int enabled) {
				this.enabled = enabled;
			}

			public String getId() {
				return id;
			}

			public void setId(String id) {
				this.id = id;
			}

			public String getImage() {
				return image;
			}

			public void setImage(String image) {
				this.image = image;
			}

			public int getPosition() {
				return position;
			}

			public void setPosition(int position) {
				this.position = position;
			}

			public String getShow_channel() {
				return show_channel;
			}

			public void setShow_channel(String show_channel) {
				this.show_channel = show_channel;
			}

			public int getSort() {
				return sort;
			}

			public void setSort(int sort) {
				this.sort = sort;
			}

			public String getTitle() {
				return title;
			}

			public void setTitle(String title) {
				this.title = title;
			}
		}

		public static class GroupBriefsBean {
			private String id;
			private String image;
			private boolean more_flag;
			private String name;

			private List<BriefListBean> briefList;

			public String getId() {
				return id;
			}

			public void setId(String id) {
				this.id = id;
			}

			public String getImage() {
				return image;
			}

			public void setImage(String image) {
				this.image = image;
			}

			public boolean isMore_flag() {
				return more_flag;
			}

			public void setMore_flag(boolean more_flag) {
				this.more_flag = more_flag;
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public List<BriefListBean> getBriefList() {
				return briefList;
			}

			public void setBriefList(List<BriefListBean> briefList) {
				this.briefList = briefList;
			}

			public static class BriefListBean {
				private String goods_img;
				private String goods_name;
				private String id;
				private double market_price;
				private boolean reservable;
				private double shop_price;

				public String getGoods_img() {
					return goods_img;
				}

				public void setGoods_img(String goods_img) {
					this.goods_img = goods_img;
				}

				public String getGoods_name() {
					return goods_name;
				}

				public void setGoods_name(String goods_name) {
					this.goods_name = goods_name;
				}

				public String getId() {
					return id;
				}

				public void setId(String id) {
					this.id = id;
				}

				public double getMarket_price() {
					return market_price;
				}

				public void setMarket_price(double market_price) {
					this.market_price = market_price;
				}

				public boolean isReservable() {
					return reservable;
				}

				public void setReservable(boolean reservable) {
					this.reservable = reservable;
				}

				public double getShop_price() {
					return shop_price;
				}

				public void setShop_price(double shop_price) {
					this.shop_price = shop_price;
				}
			}
		}
	}
}
