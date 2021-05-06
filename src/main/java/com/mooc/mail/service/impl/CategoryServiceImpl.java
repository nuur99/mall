package com.mooc.mail.service.impl;

import com.mooc.mail.bean.Category;
import com.mooc.mail.dao.CategoryMapper;
import com.mooc.mail.service.CategoryService;
import com.mooc.mail.vo.CategoryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryVo> getAllCategory() {
        List<CategoryVo> categoryVos = new ArrayList<>();
        List<Category> allCategory = categoryMapper.getAllCategory();
        for (Category category : allCategory) {
            CategoryVo categoryVo = new CategoryVo();
            if (category.getParentId().equals(0)) {
                BeanUtils.copyProperties(category, categoryVo);
                categoryVos.add(categoryVo);
            }
        }

        getSubCategories(categoryVos, allCategory);

        return categoryVos;
    }

    private void getSubCategories(List<CategoryVo> categoryVos, List<Category> allCategory) {
        for (CategoryVo categoryVo : categoryVos) {
            List<CategoryVo> subCategoryVo = new ArrayList<>();
            for (Category category : allCategory) {
                CategoryVo subcategoryVo = new CategoryVo();
                if (categoryVo.getId().equals(category.getParentId())) {
                    BeanUtils.copyProperties(category, subcategoryVo);
                    subCategoryVo.add(subcategoryVo);
                }
                subCategoryVo.sort(new Comparator<CategoryVo>() {
                    @Override
                    public int compare(CategoryVo o1, CategoryVo o2) {
                        return -(o1.getSortOrder() - o2.getSortOrder());
                    }
                });
                categoryVo.setSubCategories(subCategoryVo);
                getSubCategories(subCategoryVo, allCategory);
            }
        }
    }

    @Override
    public List<Integer> findSubCategory(Integer categoryId) {
        List<Category> allCategory = categoryMapper.getAllCategory();
        List<Integer> categoryIdList = new ArrayList<>();
        findSubCategory(categoryId, categoryIdList, allCategory);
        return categoryIdList;
    }

    public void findSubCategory(Integer categoryId, List<Integer>categoryIdList, List<Category> allCategory) {
        for (Category category : allCategory) {
            if (category.getParentId().equals(categoryId)){
                categoryIdList.add(category.getId());
                findSubCategory(category.getId(), categoryIdList, allCategory);
            }
        }
    }
}
