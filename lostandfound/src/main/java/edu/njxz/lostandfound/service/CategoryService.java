package edu.njxz.lostandfound.service;

import java.util.List;

import edu.njxz.lostandfound.entity.Category;

public interface CategoryService {

	// 查询所有类别
	List<Category> getAllCategories();

	// 根据类别好查询类别名称
	Category getCateById(Integer cateId);
}
