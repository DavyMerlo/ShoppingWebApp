package com.davy.restapi.shared.service;

import com.davy.restapi.category.request.CategoryCreateRequest;
import com.davy.restapi.category.request.CategoryUpdateRequest;
import com.davy.restapi.category.response.CategoryListResponse;
import com.davy.restapi.category.response.CategoryResponse;
import com.davy.restapi.product.request.ProductRequest;
import com.davy.restapi.product.response.ProductResponse;
import com.davy.restapi.subcategory.request.SubCategoryRequest;
import com.davy.restapi.subcategory.response.SubCategoryListResponse;
import com.davy.restapi.subcategory.response.SubCategoryResponse;

import java.util.Map;

public interface CatalogFacadeService {

    Map<String, Object> findByCategoryIdAndSubCategoryIdPageable(Long categoryId,
                                                                 Long subCategoryId,
                                                                 String name,
                                                                 int page);
    Map<String, Object> findAllProductsPageable(int page);

    ProductResponse findProductById(Long id);

    Map<String, Object> saveProduct(ProductRequest request);

    ProductResponse updateProductById(Long id, ProductRequest request);

    CategoryListResponse findAllCategories();

    CategoryResponse findCategoryById(Long id);

    CategoryResponse findCategoryBySubCategoryId(Long subCatId);

    CategoryResponse updateCategoryById(Long id, CategoryUpdateRequest request);

    CategoryListResponse saveCategory(CategoryCreateRequest request);

    SubCategoryListResponse findAllSubCategories();

    SubCategoryListResponse findSubCategoriesByCategoryId(Long id);

    SubCategoryResponse findSubCategoryById(Long id);

    SubCategoryResponse updateSubCategoryById(Long id, SubCategoryRequest request);

    SubCategoryListResponse saveSubCategory(SubCategoryRequest request);

    Boolean existsBySubCategoryId(Long id);
}
