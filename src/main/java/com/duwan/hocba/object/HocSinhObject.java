package com.duwan.hocba.object;

public class HocSinhObject extends PhuHuynhObject{
	private int hocsinh_id;
	private String lop_id;

	public HocSinhObject()
	{}

	public int getHocsinh_id() {
		return hocsinh_id;
	}

	public void setHocsinh_id(int hocsinh_id) {
		this.hocsinh_id = hocsinh_id;
	}

	public String getLop_id() {
		return lop_id;
	}

	public void setLop_id(String lop_id) {
		this.lop_id = lop_id;
	}
}