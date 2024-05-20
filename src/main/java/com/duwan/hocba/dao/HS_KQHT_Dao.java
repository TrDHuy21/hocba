package com.duwan.hocba.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.duwan.hocba.object.HS_KQHT_Object;
import com.duwan.hocba.rowmapper.HS_KQHT_RowMapper;

@Repository
public class HS_KQHT_Dao extends JdbcDao {
	public List<HS_KQHT_Object> getKqhtByHocSinhId(int id) {
		String SQL = "select *  from ketquahoctap " + " JOIN monhoc on monhoc.monhoc_id = ketquahoctap.monhoc_id"
				+ " where ketquahoctap.hocsinh_id = ?";
		return getJdbcTemplate().query(SQL, new HS_KQHT_RowMapper(), id);
	}

	public List<HS_KQHT_Object> getKqhtByHocSinhId(int id, int lop) {
		String SQL = "select * from ketquahoctap " + " JOIN monhoc on monhoc.monhoc_id = ketquahoctap.monhoc_id"
				+ " where ketquahoctap.hocsinh_id = ? and ketquahoctap.hocki in (?,?)";
		return getJdbcTemplate().query(SQL, new HS_KQHT_RowMapper(), id, lop * 2 - 1, lop * 2);
	}

	public List<HS_KQHT_Object> getKqhtByHocSinhId(int id, int lop, int ki) {
		String SQL = "select * from ketquahoctap " 
				+ " JOIN monhoc on monhoc.monhoc_id = ketquahoctap.monhoc_id "
				+ " where ketquahoctap.hocsinh_id = ? and ketquahoctap.hocki = ? ";
		return getJdbcTemplate().query(SQL, new HS_KQHT_RowMapper(), id, lop * 2 - 2 + ki);
	}

	public List<HS_KQHT_Object> getKqhtByLop_MonId(String lop_id, String monhoc_id) {
		String sql = "Select * from ketquahoctap " 
				+ "JOIN monhoc ON monhoc.monhoc_id = ketquahoctap.monhoc_id "
				+ "where ketquahoctap.lop_id = ? and ketquahoctap.monhoc_id = ? ";
		return getJdbcTemplate().query(sql, new HS_KQHT_RowMapper(), lop_id, monhoc_id);
	}

	public HS_KQHT_Object getKQHTHocSinh_MonHoc(int hocsinh_id, String monhoc_id) {
		String sql = "Select * from ketquahoctap " 
				+ "JOIN monhoc ON monhoc.monhoc_id = ketquahoctap.monhoc_id "
				+ "WHERE ketquahoctap.hocsinh_id = ? and ketquahoctap.monhoc_id = ?";

		return getJdbcTemplate().queryForObject(sql, new HS_KQHT_RowMapper(), hocsinh_id, monhoc_id);
	}

	public int updateKQHT(HS_KQHT_Object kqht) {
		int kq = 0;
		String sql = "UPDATE ketquahoctap " + "SET " + "xeploai = ?, " + "hocki = ?, " + "diem = ?, "
				+ "diem_giua_ki = ?, " + "nhanxetcuagv = ?, " + "lop_id = ? " + " WHERE " + "hocsinh_id = ? AND "
				+ "monhoc_id = ?";
		kq = getJdbcTemplate().update(sql, kqht.getXepLoai(), kqht.getHocKi(), kqht.getDiemGiuaKi(), kqht.getDiem(),
				kqht.getNhanXet(), kqht.getLop_id(), kqht.getHocSinh_id(), kqht.getMonHoc_id());
		return kq;
	}

	public int insertKQHT(HS_KQHT_Object k) {
		int kq = 0;
		String sql = "INSERT INTO `hoc_ba_dien_tu`.`ketquahoctap` (`hocki`, `lop_id`, `hocsinh_id`, `monhoc_id`, `diemgiuaki` ,`diemcuoiki`, `nhanxetcuagv`, `xeploai`) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		kq = getJdbcTemplate().update(sql, k.getHocKi(), k.getLop_id(), k.getHocSinh_id(), k.getMonHoc_id(),
				k.getDiemGiuaKi(), k.getDiem(), k.getNhanXet(), k.getXepLoai());
		return kq;
	}

	public int insertCapNhatDiem(List<HS_KQHT_Object> listInsertKQHT, String monHocId, String lopId, int ki) {
		System.out.println("Dang insert");
		int kq = 0;
		String sql = "INSERT INTO `hoc_ba_dien_tu`.`ketquahoctap` "
				+ "(`hocki`, `lop_id`, `hocsinh_id`, `monhoc_id`, `diemgiuaki` ,`diemcuoiki`, `nhanxetcuagv`, `xeploai`) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		List<Object[]> batchArgs = new ArrayList<>();
		for (HS_KQHT_Object k : listInsertKQHT) {
			Object[] args = {ki, lopId, k.getHocSinh_id(), monHocId,
					k.getDiemGiuaKi(), k.getDiem(), k.getNhanXet(), k.getXepLoai() };
			batchArgs.add(args);
		}
		int[] updateCounts = getJdbcTemplate().batchUpdate(sql, batchArgs);
		for (int count : updateCounts) {
	        kq += count;
	    }
	    
	    return kq;
	}

	public int updateCapNhatDiem(List<HS_KQHT_Object> listUpdateKQHT, String monHocId, String lop_Id, int ki) {
		System.out.println("Dang update");
		int kq = 0;
		String sql = "UPDATE ketquahoctap " 
				+ "SET xeploai = ?, diemcuoiki = ?, diemgiuaki = ?, nhanxetcuagv = ? "
				+ "WHERE hocsinh_id = ? AND monhoc_id = ? AND lop_id = ? AND hocki = ?";
		List<Object[]> batchArgs = new ArrayList<>();
		for (HS_KQHT_Object kqht : listUpdateKQHT) {
			Object[] args = { kqht.getXepLoai(), kqht.getDiem(), kqht.getDiemGiuaKi(),
					kqht.getNhanXet(), kqht.getHocSinh_id(), monHocId, lop_Id, ki};
			batchArgs.add(args);
		}
		int[] updateCounts = getJdbcTemplate().batchUpdate(sql, batchArgs);
		for (int count : updateCounts) {
	        kq += count;
	    }
	    
	    return kq;
	}
}
