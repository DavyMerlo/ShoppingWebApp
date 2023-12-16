package com.davy.restapi.shared.service;

import com.davy.restapi.category.request.CategoryCreateRequest;
import com.davy.restapi.category.request.CategoryUpdateRequest;
import com.davy.restapi.category.response.CategoryListResponse;
import com.davy.restapi.category.response.CategoryResponse;
import com.davy.restapi.category.service.CategoryFacade;
import com.davy.restapi.product.request.ProductRequest;
import com.davy.restapi.product.response.ProductResponse;
import com.davy.restapi.product.service.ProductFacade;
import com.davy.restapi.subcategory.request.SubCategoryRequest;
import com.davy.restapi.subcategory.response.SubCategoryListResponse;
import com.davy.restapi.subcategory.response.SubCategoryResponse;
import com.davy.restapi.subcategory.service.SubCategoryFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductCatalogFacadeServiceImpl implements ProductCatalogFacadeService {

    private final ProductFacade productFacade;
    private final CategoryFacade categoryFacade;
    private final SubCategoryFacade subCategoryFacade;


    @Override
    public CategoryListResponse findAllCategories() {
        return categoryFacade.findAllCategories();
    }

    @Override
    public CategoryResponse findCategoryById(Long id) {
        return categoryFacade.findCategoryById(id);
    }

    @Override
    public CategoryResponse findCategoryBySubCategoryId(Long subCatId) {
        return categoryFacade.findCategoryBySubCategoryId(subCatId);
    }

    @Override
    public CategoryResponse updateCategoryById(Long id, CategoryUpdateRequest request) {
        return categoryFacade.updateCategoryById(id, request);
    }

    @Override
    public CategoryListResponse saveCategory(CategoryCreateRequest request) {
        return categoryFacade.saveCategory(request);
    }

    @Override
    public Map<String, Object> findAllProductsPageable(int page) {
        return productFacade.findAllProductsPageable(page);
    }

    @Override
    public Map<String, Object> filterAndSearchProductsByNamePageable(Long categoryId, Long subCategoryId, String name, int page) {
        return productFacade.filterAndSearchProductsByNamePageable(categoryId, subCategoryId, name, page);
    }

    @Override
    public ProductResponse findProductById(Long id) {
        return productFacade.findProductById(id);
    }

    @Override
    public ProductResponse saveProduct(ProductRequest request) {
        return productFacade.saveProduct(request);
    }

    @Override
    public ProductResponse updateProductById(Long id, ProductRequest request) {
        return productFacade.updateProductById(id, request);
    }

    @Override
    public SubCategoryListResponse findAllSubCategories() {
        return subCategoryFacade.findAllSubCategories();
    }

    @Override
    public SubCategoryListResponse findSubCategoriesByCategoryId(Long id) {
        return subCategoryFacade.findSubCategoriesByCategoryId(id);
    }

    @Override
    public SubCategoryResponse findSubCategoryById(Long id) {
        return subCategoryFacade.findSubCategoryById(id);
    }

    @Override
    public SubCategoryResponse updateSubCategoryById(Long id, SubCategoryRequest request) {
        return subCategoryFacade.updateSubCategoryById(id, request);
    }

    @Override
    public Boolean existsBySubCategoryId(Long id) {
        return subCategoryFacade.existsBySubCategoryId(id);
    }

    @Override
    public SubCategoryListResponse saveSubCategory(SubCategoryRequest request) {
        return subCategoryFacade.saveSubCategory(request);
    }

//    private final CategoryService categoryService;
//    private final SubCategoryService subCategoryService;
//    private final ProductService productService;
//
//
//    @Override
//    public Map<String, Object> findByCategoryIdAndSubCategoryIdPageable(Long categoryId, Long subCategoryId, String name, int page) {
//        return productService.filterAndSearchProductsByNamePageable(categoryId, subCategoryId, name, page);
//    }
//
//    @Override
//    public Map<String, Object> findAllProductsPageable(int page){
//        return productService.findAllProductsPageable(page);
//    }
//
//    @Override
//    public ProductResponse findProductById(Long id){
//        return productService.findProductById(id);
//    }
//
//    @Override
//    public Map<String, Object> saveProduct(ProductRequest request){
//        return productService.saveProduct(request);
//    }
//
//    @Override
//    public ProductResponse updateProductById(Long id, ProductRequest request){
//        return productService.updateProductById(id, request);
//    }
//
//    @Override
//    public CategoryListResponse findAllCategories(){
//        return categoryService.findAllCategories();
//    }
//
//    @Override
//    public CategoryResponse findCategoryById(Long id){
//        return categoryService.findCategoryById(id);
//    }
//
//    @Override
//    public CategoryResponse findCategoryBySubCategoryId(Long subCatId){
//        return categoryService.findCategoryBySubCategoryId(subCatId);
//    }
//
//    @Override
//    public CategoryResponse updateCategoryById(Long id, CategoryUpdateRequest request){
//        return categoryService.updateCategoryById(id, request);
//    }
//
//    @Override
//    public CategoryListResponse saveCategory(CategoryCreateRequest request){
//        return categoryService.saveCategory(request);
//    }
//
//    @Override
//    public SubCategoryListResponse findAllSubCategories(){
//        return subCategoryService.findAllSubCategories();
//    }
//
//    @Override
//    public SubCategoryListResponse findSubCategoriesByCategoryId(Long id){
//        return subCategoryService.findSubCategoriesByCategoryId(id);
//    }
//
//    @Override
//    public SubCategoryResponse findSubCategoryById(Long id){
//        return subCategoryService.findSubCategoryById(id);
//    }
//
//    @Override
//    public SubCategoryResponse updateSubCategoryById(Long id, SubCategoryRequest request){
//        return subCategoryService.updateSubCategoryById(id, request);
//    }
//
//    @Override
//    public SubCategoryListResponse saveSubCategory(SubCategoryRequest request){
//        return subCategoryService.saveSubCategory(request);
//    }
//
//    @Override
//    public Boolean existsBySubCategoryId(Long id){
//        return subCategoryService.existsBySubCategoryId(id);
//    }
}
