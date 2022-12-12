package dao;
import java.sql.*;
import java.util.*;

public class CashDao {
	// 년도별 수입/지출
	public ArrayList<HashMap<String, Object>> selectCashListByCategory(Connection conn, String category) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT"
				+ " EXTRACT(YEAR FROM cash_date) year"
				+ ", COUNT(*)"
				+ ", SUM(cash_price) price"
				+ " FROM cash"
				+ " WHERE category = ?"
				+ " GROUP BY EXTRACT(YEAR FROM cash_date)"
				+ " ORDER BY EXTRACT(YEAR FROM cash_date) ASC";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, category);
		rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("year", rs.getInt("year"));
			m.put("price", rs.getInt("price"));
			list.add(m);
		}
		return list;
	}
	
	// 월별 수입/지출
	public ArrayList<HashMap<String, Object>> selectCashListByMonth(Connection conn, String category, int year) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql ="SELECT"
				+ " EXTRACT(YEAR FROM cash_date) year"
				+ ", EXTRACT(MONTH FROM cash_date) month"
				+ ", COUNT(*)"
				+ ", SUM(cash_price) price"
				+ " FROM cash"
				+ " GROUP BY EXTRACT(MONTH FROM cash_date)"
				+ " HAVING EXTRACT(YEAR FROM cash_date) = ? AND category = ?"
				+ " ORDER BY EXTRACT(MONTH FROM cash_date) ASC";
		
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, year);
		stmt.setString(2, category);
		rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("year", rs.getString("year"));
			m.put("month", rs.getString("month"));
			m.put("price", rs.getInt("price"));
			list.add(m);
		}
		return list;
	}
	
	// 년도
	public HashMap<String, Object> selectMaxMinYear(Connection conn) throws Exception {
		HashMap<String, Object> map = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT"
				+ "	(SELECT MIN(EXTRACT(MONTH FROM cash_date)) FROM cash) minYear"
				+ ", (SELECT MAX(EXTRACT(MONTH FROM cash_date)) FROM cash) maxYear"
				+ " FROM DUAL";
		stmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery();
		while(rs.next()) {
			map = new HashMap<String, Object>();
			map.put("minYear", rs.getInt("minYear"));
			map.put("maxYear", rs.getInt("maxYear"));
		}
		return map;
	}
}