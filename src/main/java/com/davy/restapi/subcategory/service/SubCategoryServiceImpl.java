package com.davy.restapi.subcategory.service;

import com.davy.restapi.shared.mapper.ObjectMapper;
import com.davy.restapi.shared.repository.CrudRepository;
import com.davy.restapi.shared.service.CrudServiceImpl;
import com.davy.restapi.subcategory.entity.SubCategory;
import com.davy.restapi.subcategory.dto.SubCategoryRequestDTO;
import org.springframework.stereotype.Service;

@Service
public class SubCategoryServiceImpl extends CrudServiceImpl<SubCategory, SubCategoryRequestDTO>
    implements SubCategoryService {

    public SubCategoryServiceImpl(CrudRepository<SubCategory> repository,
                                  ObjectMapper<SubCategoryRequestDTO, SubCategory> mapper) {
        super(repository, mapper);
    }
}
