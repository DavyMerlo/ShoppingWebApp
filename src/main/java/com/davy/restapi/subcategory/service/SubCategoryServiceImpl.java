package com.davy.restapi.subcategory.service;

import com.davy.restapi.shared.mapper.ObjectMapper;
import com.davy.restapi.shared.repository.CrudRepository;
import com.davy.restapi.shared.service.CrudServiceImpl;
import com.davy.restapi.subcategory.entity.SubCategoryEntity;
import com.davy.restapi.subcategory.dto.SubCategoryRequestDTO;
import org.springframework.stereotype.Service;

@Service
public class SubCategoryServiceImpl extends CrudServiceImpl<SubCategoryEntity, SubCategoryRequestDTO>
    implements SubCategoryService {

    public SubCategoryServiceImpl(CrudRepository<SubCategoryEntity> repository,
                                  ObjectMapper<SubCategoryRequestDTO, SubCategoryEntity> mapper) {
        super(repository, mapper);
    }
}
