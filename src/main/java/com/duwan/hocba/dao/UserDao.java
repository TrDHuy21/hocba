package com.duwan.hocba.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.duwan.hocba.object.UserObject;
import com.duwan.hocba.rowmapper.UserRowMapper;

@Repository
public class UserDao extends JdbcDao {
	public UserObject getUserByTendangnhap(String user_tendangnhap) {
		String SQL = "SELECT * FROM user WHERE user_tendangnhap = ?";
		return getJdbcTemplate().queryForObject(SQL, new UserRowMapper(), user_tendangnhap);
	}
	
	public List<UserObject> getAllUser() {
		String SQL = "SELECT * FROM user";
		return getJdbcTemplate().query(SQL, new UserRowMapper());
	}
	
	public boolean updateUserByTDN(String ho, String ten, String sdt, String email, String diachi, Date ngaysinh, int gioitinh, String tendangnhap) {
        String sql = "UPDATE user "
        		+ "SET user_ho = ?, user_ten = ?, user_sdt = ?, user_email = ?, user_diachi = ?, user_ngaysinh = ?, user_gioitinh = ? "
        		+ "WHERE user_tendangnhap = ?";

        Object[] params = {ho, ten, sdt, email, diachi, ngaysinh, gioitinh, tendangnhap};

        int rowsAffected = jdbcTemplate.update(sql, params);

        return rowsAffected > 0;
    }
	
	public boolean updateUserPassword(String matkhau_moi, String tendangnhap) {
        String sql = "UPDATE user "
        		+ "SET user_password = ? "
        		+ "WHERE user_tendangnhap = ?";

        Object[] params = {matkhau_moi, tendangnhap};

        int rowsAffected = jdbcTemplate.update(sql, params);

        return rowsAffected > 0;
    }
}
