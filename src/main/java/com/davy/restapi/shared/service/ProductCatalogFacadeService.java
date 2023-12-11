package com.davy.restapi.shared.service;

import com.davy.restapi.category.service.CategoryFacade;
import com.davy.restapi.product.service.ProductFacade;
import com.davy.restapi.subcategory.service.SubCategoryFacade;

public interface ProductCatalogFacadeService extends ProductFacade, CategoryFacade, SubCategoryFacade { }
