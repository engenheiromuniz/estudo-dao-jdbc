package application;

import java.util.Date;

import javax.swing.JOptionPane;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Department obj    = new Department(1, "Books");
		Seller     seller = new Seller(35, "Ana Maria Braga", "ana.maria@globo.com", new Date(), 2000.0, obj); 
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		JOptionPane.showMessageDialog(null, "Department: "+obj+"\nSeller: "+seller, "Print obj element",
				JOptionPane.PLAIN_MESSAGE );
		
		
		
			

	}

}
