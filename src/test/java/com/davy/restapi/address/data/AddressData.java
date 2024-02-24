package com.davy.restapi.address.data;

import com.davy.restapi.address.dto.AddressDetailDTO;
import com.davy.restapi.shared.utils.ExpectedDataProvider;
import org.springframework.stereotype.Component;

@Component
public class AddressData implements ExpectedDataProvider<AddressDetailDTO> {

    @Override
    public AddressDetailDTO getObject() {
        return new AddressDetailDTO(
                1L,
                "Rootstraat",
                "30",
                "1",
                "3630",
                "Maasmechelen"
        );
    }

    @Override
    public AddressDetailDTO getSavedObject() {
        return new AddressDetailDTO(
                2L,
                "Teststreet Saved",
                "50",
                "1",
                "3530",
                "TestCity"
        );
    }

    @Override
    public AddressDetailDTO getUpdatedObject() {
        return new AddressDetailDTO(
                1L,
                "Teststreet Updated",
                "50",
                "1",
                "3530",
                "TestCity"
        );
    }
}
