package com.duwan.hocba.object;

public class HS_KQHT_Object {
	private String lop_id;
	private String hocSinh_id;
	private String xepLoai;
	private int hocKi;
	private String monHoc_name;
	private float diem;
	private float diemGiuaKi;
	private String nhanXet;
	private String monHoc_id;
	
	public HS_KQHT_Object() {
		super();
	}
	
	public int getHocKi() {
		return hocKi;
	}

	public void setHocKi(int hocKi) {
		this.hocKi = hocKi;
	}

	public String getMonHoc_name() {
		return monHoc_name;
	}

	public void setMonHoc_name(String monHoc_name) {
		this.monHoc_name = monHoc_name;
	}

	public float getDiem() {
		return diem;
	}

	public void setDiem(float diem) {
		this.diem = diem;
	}

	public String getNhanXet() {
		return nhanXet;
	}

	public void setNhanXet(String nhanXet) {
		this.nhanXet = nhanXet;
	}

	public String getLop_id() {
		return lop_id;
	}

	public void setLop_id(String lop_id) {
		this.lop_id = lop_id;
	}

	public String getHocSinh_id() {
		return hocSinh_id;
	}

	public void setHocSinh_id(String hocSinh_id) {
		this.hocSinh_id = hocSinh_id;
	}

	public String getXepLoai() {
		return xepLoai;
	}

	public void setXepLoai(String xepLoai) {
		this.xepLoai = xepLoai;
	}
	
	public String getMonHoc_id() {
		return monHoc_id;
	}

	public void setMonHoc_id(String monHoc_id) {
		this.monHoc_id = monHoc_id;
	}
	
	public float getDiemGiuaKi() {
		return diemGiuaKi;
	}

	public void setDiemGiuaKi(float diemGiuaKi) {
		this.diemGiuaKi = diemGiuaKi;
	}

	@Override
	public String toString() {
		return "HS_KQHT_Object [lop_id=" + lop_id + ", hocSinh_id=" + hocSinh_id + ", xepLoai=" + xepLoai + ", hocKi="
				+ hocKi + ", monHoc_name=" + monHoc_name + ", diem=" + diem + ", diemGiuaKi=" + diemGiuaKi
				+ ", nhanXet=" + nhanXet + ", monHoc_id=" + monHoc_id + "]";
	}

	

}
