package com.duwan.hocba.object;

public class HS_TKB_Object {
	private int thoigian;
	private int tiethoc;
	private String monhoc_name;
	
	public HS_TKB_Object() {
		// TODO Auto-generated constructor stub
	}
	
	public HS_TKB_Object(String monhoc){
		this.monhoc_name = monhoc;
	}
	
	public int getThoigian() {
		return thoigian;
	}

	public void setThoigian(int thoigian) {
		this.thoigian = thoigian;
	}

	public int getTiethoc() {
		return tiethoc;
	}

	public void setTiethoc(int tiethoc) {
		this.tiethoc = tiethoc;
	}

	public String getMonhoc_name() {
		return monhoc_name;
	}

	public void setMonhoc_name(String monhoc_name) {
		this.monhoc_name = monhoc_name;
	}	
}
