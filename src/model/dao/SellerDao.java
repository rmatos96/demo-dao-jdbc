package model.dao;

import java.util.List;

import model.entities.Seller;

public interface SellerDao {

	void insert(Seller ojb);
	void update(Seller ojb);
	void deleteById(Integer id);
	Seller findById(Integer id);
	List<Seller> findAll();
}
