package com.bwie.ynf.bean;

import java.util.List;

public class sign_in_column {

	private DataBean data;

	public DataBean getData() {
		return data;
	}

	public void setData(DataBean data) {
		this.data = data;
	}

	public static class DataBean {

		private List<Ad5Bean> ad5;

		public List<Ad5Bean> getAd5() {
			return ad5;
		}

		public void setAd5(List<Ad5Bean> ad5) {
			this.ad5 = ad5;
		}

		public static class Ad5Bean {
			private String id;
			private String image;
			private int ad_type;
			private int sort;
			private int position;
			private int enabled;
			private String ad_type_dynamic;
			private String show_channel;
			private String title;

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

			public int getAd_type() {
				return ad_type;
			}

			public void setAd_type(int ad_type) {
				this.ad_type = ad_type;
			}

			public int getSort() {
				return sort;
			}

			public void setSort(int sort) {
				this.sort = sort;
			}

			public int getPosition() {
				return position;
			}

			public void setPosition(int position) {
				this.position = position;
			}

			public int getEnabled() {
				return enabled;
			}

			public void setEnabled(int enabled) {
				this.enabled = enabled;
			}

			public String getAd_type_dynamic() {
				return ad_type_dynamic;
			}

			public void setAd_type_dynamic(String ad_type_dynamic) {
				this.ad_type_dynamic = ad_type_dynamic;
			}

			public String getShow_channel() {
				return show_channel;
			}

			public void setShow_channel(String show_channel) {
				this.show_channel = show_channel;
			}

			public String getTitle() {
				return title;
			}

			public void setTitle(String title) {
				this.title = title;
			}
		}
	}
}
