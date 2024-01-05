package com.duwan.hocba.object;

public class Diem {
	private double tongdiem;
	private int soluong;

	public Diem() {
		tongdiem = 0;
		soluong = 0;
	}
	public double getTongdiem() {
		return tongdiem;
	}
	public void setTongdiem(double tongdiem) {
		this.tongdiem = tongdiem;
	}
	public int getSoluong() {
		return soluong;
	}
	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}	
	
	public double diemTK() {
		if(soluong!=0)
			return tongdiem/soluong;
		return 0;
	}
}
