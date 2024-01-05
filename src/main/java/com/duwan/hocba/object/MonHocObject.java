package com.duwan.hocba.object;

public class MonHocObject {
	private String monHoc_id;
	private String monHoc_name;
	private int hocKi;
	
	public MonHocObject() {}
	
	public MonHocObject(String monHoc_id, String monHoc_name, int hocKi) {
		super();
		this.monHoc_id = monHoc_id;
		this.monHoc_name = monHoc_name;
		this.hocKi = hocKi;
	}

	public String getMonHoc_id() {
		return monHoc_id;
	}

	public void setMonHoc_id(String monHoc_id) {
		this.monHoc_id = monHoc_id;
	}

	public String getMonHoc_name() {
		return monHoc_name;
	}

	public void setMonHoc_name(String monHoc_name) {
		this.monHoc_name = monHoc_name;
	}

	public int getHocKi() {
		return hocKi;
	}

	public void setHocKi(int hocKi) {
		this.hocKi = hocKi;
	}

	@Override
	public String toString() {
		return "MonHocObject [monHoc_id=" + monHoc_id + ", monHoc_name=" + monHoc_name + ", hocKi=" + hocKi + "]";
	}

}
