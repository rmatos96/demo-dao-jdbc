package application;

import java.util.Date;

import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {

		Department objDepartment = new Department(1, "books");
		
		Seller seller = new Seller(1, "Rodrigo", "email.com", new Date(), 3200.0, objDepartment);
		
		System.out.println(seller);
	}

}
