package com.davy.restapi.product;

import com.davy.restapi.shared.TestContainer;
import com.davy.restapi.shared.utils.TestAssertionUtils;
import jakarta.transaction.Transactional;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class ProductByIdTest extends TestContainer {

    @DisplayName("Fetch product with ID: 1")
    @Test
    @Order(1)
    public void shouldFetchProductById() throws Exception {
        long id = 1L;
        String responseBody = restTemplate
                .getForObject("http://localhost:" + port + "/api/v1/products/" + id, String.class);
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response,200);
        TestAssertionUtils.assertResponseHasExpectedFields(response, "product", expectedProductFields());
    }

    @DisplayName("Return 404 for non-existing product")
    @Test
    @Order(2)
    public void shouldReturn404NonExistingProduct() throws Exception {
        long nonExistingId = 9999L;
        String responseBody = restTemplate
                .getForObject("http://localhost:" + port + "/api/v1/products/" + nonExistingId, String.class);
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response,404);
    }

    private List<String> expectedProductFields() {
        List<String> fields = new ArrayList<>();
        fields.add("id");
        fields.add("name");
        fields.add("imageUrl");
        fields.add("description");
        fields.add("purchasePrice");
        fields.add("sellingPrice");
        fields.add("vat");
        fields.add("category");
        fields.add("inventory");
        fields.add("sellingPrice");
        return fields;
    }
}
