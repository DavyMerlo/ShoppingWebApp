package com.davy.restapi.product.service;

import com.davy.restapi.product.dto.ProductRequestDTO;
import com.davy.restapi.product.entity.ProductEntity;
import com.davy.restapi.shared.service.CrudService;

import java.util.Map;

public interface ProductService extends CrudService<ProductEntity, ProductRequestDTO> {

    Map<String, Object> filterProductsPageable(Long categoryId,
                                                              Long subCategoryId,
                                                              String name,
                                                              int page,
                                                              int pageSize,
                                                              String sortBy,
                                                              String sortOrder);
}
