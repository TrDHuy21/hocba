package com.duwan.hocba.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.duwan.hocba.object.HS_TKB_Object;

public class HS_TKB_RowMapper implements RowMapper<HS_TKB_Object> {
	@Override
	public HS_TKB_Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		HS_TKB_Object tkb = new HS_TKB_Object();
		tkb.setThoigian(rs.getInt("thoigian"));
		tkb.setTiethoc(rs.getInt("tiethoc"));
		tkb.setMonhoc_name(rs.getString("monhoc_name"));
		return tkb;
	}
}
