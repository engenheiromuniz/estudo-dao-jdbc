package application;

import javax.swing.JOptionPane;

import model.entities.Department;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Department obj = new Department(1, "Books");
		JOptionPane.showMessageDialog(null, obj, "Print obj element",
				JOptionPane.PLAIN_MESSAGE );
		
			

	}

}
