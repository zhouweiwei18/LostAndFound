package edu.njxz.lostandfound.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.njxz.lostandfound.entity.Category;
import edu.njxz.lostandfound.entity.CategoryExample;
import edu.njxz.lostandfound.mapper.CategoryMapper;
import edu.njxz.lostandfound.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryMapper categoryMapper;

	@Override
	public Category getCateById(Integer cateId) {

		Category category = categoryMapper.selectByPrimaryKey(cateId);

		return category;
	}

	public List<Category> getAllCategories() {

		List<Category> list = categoryMapper.selectByExample(new CategoryExample());

		return list;
	}

}
