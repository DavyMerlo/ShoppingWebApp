package com.davy.restapi.address.controller.testsuite;

import com.davy.restapi.address.dto.AddressDTO;
import com.davy.restapi.address.dto.AddressDetailDTO;
import com.davy.restapi.product.dto.ProductDTO;
import com.davy.restapi.shared.TestContainer;
import com.davy.restapi.shared.utils.JSONToObject;
import com.davy.restapi.shared.utils.TestAssertion;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static com.davy.restapi.product.data.ProductDataProvider.getExpectedProductList;
import static com.davy.restapi.product.data.ProductFieldProvider.expectedProductV2Fields;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class AddressFetchAllTest extends TestContainer {

//    @Autowired
//    private JSONToObject<List<AddressDetailDTO>> mapper;
//
//    @Autowired
//    private TestAssertion testAssertion;
//
//    @DisplayName("Fetch all products")
//    @Test
//    @Order(1)
//    public void shouldFetchAllProducts() throws Exception {
//        String responseBody = restTemplate
//                .getForObject("http://localhost:" + port + "/api/v2/products", String.class);
//        JSONObject response = testAssertion.provideResponse(responseBody);
//        testAssertion.listResponseHasExpectedFields("products", expectedProductV2Fields());
//        testAssertion.responseHasExpectedStatusCode(200);
//        testAssertion.arrayHasExpectedSize("products", 11);
//        assertIterableEquals(mapper.mapJSONResponseToObject(response), getExpectedProductList());
//    }
}
