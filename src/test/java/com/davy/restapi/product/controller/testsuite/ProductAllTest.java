package com.davy.restapi.product.controller.testsuite;

import com.davy.restapi.product.dto.ProductDTO;
import com.davy.restapi.shared.TestContainer;
import com.davy.restapi.shared.utils.JSONResponseToObject;
import com.davy.restapi.shared.utils.TestAssertionUtils;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static com.davy.restapi.product.data.ProductDataProvider.getExpectedProductList;
import static com.davy.restapi.product.data.ProductFieldProvider.expectedProductV2Fields;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class ProductAllTest extends TestContainer {

    @Autowired
    private JSONResponseToObject<List<ProductDTO>> mapper;

    @DisplayName("Fetch all products")
    @Test
    @Order(1)
    public void shouldFetchAllProducts() throws Exception {
        String responseBody = restTemplate
                .getForObject("http://localhost:" + port + "/api/v2/products", String.class);
        JSONObject response = new JSONObject(responseBody);
        assertNotNull(responseBody, "Response body should not be null");
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response,200);
        TestAssertionUtils.assertArrayHasExpectedSize(response, "products", 11);
        TestAssertionUtils.assertListResponseHasExpectedFields(response, "products", expectedProductV2Fields());
        assertIterableEquals(mapper.mapJSONResponseToObject(response), getExpectedProductList());
    }
}
