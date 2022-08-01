package application;

import java.util.ArrayList;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;


public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		Department obj    = new Department(1, "Books");
//		Seller     seller = new Seller(35, "Ana Maria Braga", "ana.maria@globo.com", new Date(), 2000.0, obj); 
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
//		JOptionPane.showMessageDialog(null, "Department: "+obj+"\nSeller: "+seller, "Print obj element",
//				JOptionPane.PLAIN_MESSAGE );
		
		System.out.println("---Teste 1, Picking up SellerById---");
		Seller seller = sellerDao.findById(9);
		System.out.println(seller);
		
		System.out.println("\n---Teste 2, Picking up SellerByDepartmentById---");
		Department department = new Department(4, null);
		List<Seller> list = sellerDao.findByDepartment(department);
		  		
		for (Seller s : list) {
			System.out.println(s);
		}	

	}

}
