package com.davy.restapi.subcategory.service;

import com.davy.restapi.subcategory.request.SubCategoryRequest;
import com.davy.restapi.subcategory.response.SubCategoryListResponse;
import com.davy.restapi.subcategory.response.SubCategoryResponse;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Primary
public class SubCategoryFacadeImpl implements SubCategoryFacade {

    private final SubCategoryService subCategoryService;

    @Override
    public SubCategoryListResponse findAllSubCategories() {
        return subCategoryService.findAllSubCategories();
    }

    @Override
    public SubCategoryListResponse findSubCategoriesByCategoryId(Long id) {
        return subCategoryService.findSubCategoriesByCategoryId(id);
    }

    @Override
    public SubCategoryResponse findSubCategoryById(Long id) {
        return subCategoryService.findSubCategoryById(id);
    }

    @Override
    public SubCategoryResponse updateSubCategoryById(Long id, SubCategoryRequest request) {
        return subCategoryService.updateSubCategoryById(id, request);
    }

    @Override
    public Boolean existsBySubCategoryId(Long id) {
        return subCategoryService.existsBySubCategoryId(id);
    }

    @Override
    public SubCategoryListResponse saveSubCategory(SubCategoryRequest request) {
        return subCategoryService.saveSubCategory(request);
    }
}
