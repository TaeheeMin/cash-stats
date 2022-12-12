package service;

import java.sql.*;
import java.util.*;
import dao.*;
import util.DBUtil;

public class CashService { //get, add, modify, remove
	private CashDao cashDao;
	
	public ArrayList<HashMap<String, Object>> getCashListByCategory(String category) {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		Connection conn = null;
		
		try {
			conn = new DBUtil().getConnection();
			this.cashDao = new CashDao();
			list = this.cashDao.selectCashListByCategory(conn, category);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			if(conn != null) { 
				try {
					conn.close(); 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return list;
	}
	
	public ArrayList<HashMap<String, Object>> getCashListByMonth(String category, int year) {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		Connection conn = null;
		
		try {
			conn = new DBUtil().getConnection();
			this.cashDao = new CashDao();
			list = this.cashDao.selectCashListByMonth(conn, category, year);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			if(conn != null) { 
				try {
					conn.close(); 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	// 페이징 사용 year
	public HashMap<String, Object> getMaxMinYear() {
		HashMap<String, Object> map = null;
		Connection conn = null;
		try {
			DBUtil dbUtil = new DBUtil();
			CashDao cashDao = new CashDao();
			conn = dbUtil.getConnection();
			map = cashDao.selectMaxMinYear(conn);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			if(conn != null) { 
				try {
					conn.close(); 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return map;
	}
	
}
