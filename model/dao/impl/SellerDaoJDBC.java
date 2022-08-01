package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao{
	
	private Connection conn;
	
	public SellerDaoJDBC(Connection conn){
		this.conn = conn;
	}

	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub		
	}

	@Override
	public Seller findById(Integer id) {

		String sql = "SELECT seller.*, department.Name as DepName "
			        +" FROM seller "
			        +" INNER JOIN department "
				    +" ON seller.DepartmentId = department.Id "
			        +" WHERE seller.Id = ?";
	
		PreparedStatement pst = null;
		ResultSet         rs  = null;
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs  = pst.executeQuery();
			
			if(rs.next()) {
				Department dep = instantiateDepartment(rs);				
				Seller obj     = instatiateSeller(rs, dep);
				
				return obj;	
			}
		} 
		catch (Exception e) {
			// TODO: handle exception
			//throw new DbException(e.getMessage());
			e.printStackTrace();
		}
		finally{
			DB.closeStatement(pst);
			DB.closeResultSet(rs);
		}
		return null;
	}

	private Seller instatiateSeller(ResultSet rs, Department dep) throws SQLException {
		Seller obj =  new Seller();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setBaseSalary(rs.getDouble("BaseSalary"));
		obj.setBirthDate(rs.getDate("BirthDate"));
		obj.setDepartment(dep);
		
		return obj;				
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("Id"));
		//dep.setName(rs.getString("DepName"));
		dep.setName(rs.getString("Name"));
		
		return dep;
	}

	@Override
	public List<Seller> findAll() {
		
		return null;
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		
		String sql = 
				 " SELECT s.*, d.Name "
				+" FROM seller s "
				+" INNER JOIN department d "
				+" ON s.DepartmentId = d.id "
				+" WHERE departmentId = ? ";				
	
		PreparedStatement pst = null;
		ResultSet         rs  = null;
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, department.getId());
			rs  = pst.executeQuery();
			
			List<Seller> list = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>(); 
			
			while(rs.next()) {
				
				Department dep = map.get(rs.getInt("DepartmentId"));
								
				if(dep == null){
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);					
				}
				
				Seller obj     = instatiateSeller(rs, dep);
				list.add(obj);
			}
			
			return list;
		} 
		catch (Exception e) {
			// TODO: handle exception
			//throw new DbException(e.getMessage());
			e.printStackTrace();
		}
		finally{
			DB.closeStatement(pst);
			DB.closeResultSet(rs);
		}		

		
		return null;
	}

}

	
