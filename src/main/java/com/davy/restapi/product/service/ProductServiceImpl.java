package com.davy.restapi.product.service;

import com.davy.restapi.category.dto.CategoryRequestDTO;
import com.davy.restapi.category.entity.Category;
import com.davy.restapi.product.dto.ProductDetailsDTO;
import com.davy.restapi.product.entity.Product;
import com.davy.restapi.product.dto.ProductRequestDTO;
import com.davy.restapi.product.repository.ProductRepository;
import com.davy.restapi.shared.exceptions.ThrowException;
import com.davy.restapi.shared.mapper.ObjectMapper;
import com.davy.restapi.shared.repository.CrudRepository;
import com.davy.restapi.shared.service.CrudServiceImpl;
import com.davy.restapi.subcategory.dto.SubCategoryDTO;
import com.davy.restapi.subcategory.dto.SubCategoryRequestDTO;
import com.davy.restapi.subcategory.entity.SubCategory;
import com.davy.restapi.subcategory.repository.SubCategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl extends CrudServiceImpl<Product, ProductRequestDTO>
        implements ProductService {

    private final ProductRepository productRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final ObjectMapper<SubCategoryRequestDTO, com.davy.restapi.subcategory.entity.SubCategory> subCategoryMapper;
    private final ObjectMapper<ProductRequestDTO, Product> productMapper;

    public ProductServiceImpl(CrudRepository<Product> repository,
                              SubCategoryRepository subCategoryRepository,
                              ProductRepository productRepository,
                              ObjectMapper<SubCategoryRequestDTO, SubCategory> subCategoryMapper,
                              ObjectMapper<ProductRequestDTO, Product> productMapper) {
        super(repository, productMapper);
        this.subCategoryRepository = subCategoryRepository;
        this.productRepository = productRepository;
        this.subCategoryMapper = subCategoryMapper;
        this.productMapper = productMapper;
    }

    @Override
    public Map<String, Object> filterProductsPageable(Long catId,
                                                      Long subCatId,
                                                      String name,
                                                      int page,
                                                      int pageSize,
                                                      String sortBy,
                                                      String sortOrder) {
        Pageable pageable = makePageable(page, pageSize, sortBy, sortOrder);
        Specification<Product> spec = specification(catId, subCatId, name);
        Page<Product> productPage = productRepository.findAll(spec, pageable);
        return mappedProductPage(productPage);
    }

//    private void checkIfCategoryIdAndSubCategoryIdExists(ProductRequestDTO request){
//        if (!categoryRepository.existsById(request.getCategoryId())) {
//            ThrowException.objectByIdException(request.getCategoryId(), "Category");
//        } else if (!inventoryRepository.existsById(request.getSubCategoryId())) {
//            ThrowException.objectByIdException(request.getSubCategoryId(), "Subcategory");
//        }
//    }

    private Map<String, Object> mappedProductPage(Page productPage){
        List<Product> productEntities = productPage.getContent();
        Map<String, Object> mappedProducts = new HashMap<>();
        List<ProductDetailsDTO> productDetails = new ArrayList<>();

        if(productEntities.isEmpty()){
            ThrowException.objectException("Products");
        }
        List<ProductDetailsDTO> productsTest = productEntities
                .stream()
                .map(entity -> (ProductDetailsDTO) productMapper.mapToDetailsDto(entity))
                .toList();

        for(var item : productsTest){
            var subCategoryId = item.getSubCategory().getId();
            item.setSubCategory(mappedSubCategoryItems(subCategoryId));
            productDetails.add(item);
        }

        mappedProducts.put("products", productDetails);
        mappedProducts.put("currentPage", productPage.getNumber());
        mappedProducts.put("count", productPage.getTotalElements());
        mappedProducts.put("totalPages", productPage.getTotalPages());
        mappedProducts.put("pageSize", productPage.getSize());
        mappedProducts.put("next", productPage.nextPageable().isPaged());
        mappedProducts.put("previous", productPage.previousPageable().isPaged());
        return mappedProducts;
    }


    private SubCategoryDTO mappedSubCategoryItems(Long subCategoryIdd){
        return (SubCategoryDTO) subCategoryRepository.findById(subCategoryIdd)
                .stream()
                .map(subCategoryMapper::mapToDto)
                .findFirst()
                .get();
    }

    private Pageable makePageable(int page, int pageSize, String sortBy, String sortOrder) {
        if (sortBy != null && sortOrder != null) {
            Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortBy);
            return PageRequest.of(page, pageSize, sort);
        } else {
            return PageRequest.of(page, pageSize);
        }
    }

    private Specification<Product> specification(Long catId, Long subCatId, String name) {
        Specification<Product> spec = Specification.where(null);

        if (name != null) {
            spec = spec.and(ProductSpecification.nameLike(name));
        }
        if (catId != null) {
            spec = spec.and(ProductSpecification.byCategory(catId));
        }
        if (subCatId != null) {
            spec = spec.and(ProductSpecification.bySubCategory(subCatId));
        }
        if (catId != null && subCatId != null) {
            spec = spec.and(ProductSpecification.byCategoryAndSubCategory(catId, subCatId));
        }
        return spec;
    }
}
