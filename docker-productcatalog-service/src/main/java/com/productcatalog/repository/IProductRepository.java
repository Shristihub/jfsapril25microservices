package com.productcatalog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.productcatalog.model.Product;


public interface IProductRepository extends JpaRepository<Product, Integer>{
    //derived
//	List<Product> findByBrandBrandName(String brand);
	
	@Query("from Product p inner join p.brand b where b.brandName=?1")
	List<Product> findByBrand(String brand);
	
	 //derived
//	List<Product> findByCategoriesCategoryName(String category);
	
	@Query("from Product p inner join p.categories c where c.categoryName=?1")
	List<Product> findByCategory(String category);

	@Query(
			""" 
			from Product p inner join p.categories c where c.categoryName=?1
			and p.price <=?2
			"""	)
	List<Product> findByCategoryPrice(String category, double price);
	
	//use column name and table name
	@Query(value = "update product set stock=?2 where product_id=?1",nativeQuery = true)
	@Modifying
	void updateProductStock(int productId, int stock);
	
	
	
	
}
