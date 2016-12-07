package com.bwie.ynf.bean;

public class user_lv_column {
	private int image_id;
	private String column_name;

	public user_lv_column(int image_id, String column_name) {
		super();
		this.image_id = image_id;
		this.column_name = column_name;
	}

	public user_lv_column() {
		super();
	}

	public int getImage_id() {
		return image_id;
	}

	public void setImage_id(int image_id) {
		this.image_id = image_id;
	}

	public String getColumn_name() {
		return column_name;
	}

	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}

}
