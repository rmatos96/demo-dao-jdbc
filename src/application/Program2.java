package application;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

//		System.out.println("=== test 1: department insert ===");
//		Department newDepartment = new Department(null, "Futebol");
//		departmentDao.insert(newDepartment);
//		System.out.println("Novo departamento inserido com id: " + newDepartment.getId());

		System.out.println();

		System.out.println("=== test 2: department findById ===");
		Department department = departmentDao.findById(7);

		System.out.println(department);

		System.out.println();

		System.out.println("=== test 3: department update ===");
		department = departmentDao.findById(10);
		department.setName("PalmeirasSemMundial");
		departmentDao.update(department);
		System.out.println("Update concluido!");

		System.out.println();

		System.out.println("=== test 4: department deleteById ===");
		System.out.print("Escolha o Id a ser removido: ");
		int n = sc.nextInt();
		departmentDao.deleteById(n);
		System.out.println("Departamento deletado!");

		System.out.println();

		System.out.println("=== test 5: department findAll ===");
		List<Department> list = departmentDao.findAll();
		for (Department dep : list) {
			System.out.println(dep);
		}

		sc.close();
	}

}
