package application;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {

		SellerDao sellerDao = DaoFactory.createSellerDao();

		System.out.println("=== Test 1: seller findById ===");
		Seller seller = sellerDao.findById(3);

		System.out.println(seller);

		System.out.println();

		System.out.println("=== Test 1: seller findByDepartment ===");
		Department dep = new Department(2, null);
		List<Seller> list = sellerDao.findByDepartment(dep);

		for (Seller objSeller : list) {
			System.out.println(objSeller);
		}

		System.out.println();

		System.out.println("=== test 3: seller findAll ===");
		list = sellerDao.findAll();
		for (Seller obj : list) {
			System.out.println(obj);
		}

		System.out.println();

		System.out.println("=== test 4: seller insert ===");
		Seller newSeller = new Seller(null, "Rodrigo", "r.matos96", new Date(), 3200.0, dep);
		sellerDao.insert(newSeller);
		System.out.println("Inserido, novo id: " + newSeller.getId());

		System.out.println();

		System.out.println("=== test 5: seller update ===");
		seller = sellerDao.findById(1);
		seller.setName("roscarne Rodrigues");
		sellerDao.update(seller);
		System.out.println("Update feito!");
		
		System.out.println();
		
		System.out.println("=== test 6: delete seller ===");
		sellerDao.deleteById(9);
		System.out.println("Seller deletado!");

	}

}
