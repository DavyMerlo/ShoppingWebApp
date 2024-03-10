package com.davy.restapi.subcategory;

import com.davy.restapi.shared.TestContainer;
import com.davy.restapi.shared.utils.ExpectedDataProvider;
import com.davy.restapi.shared.utils.JSONToObject;
import com.davy.restapi.shared.utils.TestAssertionUtils;
import com.davy.restapi.subcategory.dto.SubCategoryDetailDTO;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static com.davy.restapi.subcategory.data.SubCategoryFieldProvider.getSubCategoryDetailFields;
import static com.davy.restapi.subcategory.data.SubCategoryFieldProvider.getSubCategoryFields;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SubCategoryEntityControllerTestEntity extends TestContainer {

    @Autowired
    private TestRestTemplate subCategories;

    @Autowired
    private TestRestTemplate subCategory;

    @Autowired
    private JSONToObject<SubCategoryDetailDTO> mapper;

    @Autowired
    private ExpectedDataProvider<SubCategoryDetailDTO> expectedDataProvider;

    @LocalServerPort
    private int port;

    @DisplayName("Fetch all sub categories")
    @Test
    @Order(1)
    public void shouldFetchAllSubCategories() throws JSONException {
        String responseBody = subCategories
                .getForObject("http://localhost:" + port + "/api/v1/subcategories", String.class);
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response, 200);
        TestAssertionUtils.assertListResponseHasExpectedFields(response, "subcategories", getSubCategoryFields());
        TestAssertionUtils.assertListResponseHasExpectedSize(response, "subcategories", 56);
    }

    @DisplayName("Fetch sub category by id")
    @Test
    @Order(2)
    public void shouldFetchSubCategoryById() throws JSONException {
        long id = 35L;
        String responseBody = subCategory
                .getForObject("http://localhost:" + port + "/api/v1/subcategories/" + id, String.class);
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response, 200);
        assertThat(mapper.mapJSONResponseToObject(response))
                .usingRecursiveComparison()
                .isEqualTo(expectedDataProvider.getObject());
    }

    @DisplayName("Save new sub category")
    @Test
    @Order(3)
    public void shouldSaveSubCategory() throws JSONException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "Test Sub Category Saved");
        requestBody.put("categoryId", 7);

        String url = "http://localhost:" + port + "/api/v1/subcategories";
        ResponseEntity<String> responseEntity = subCategory
                .postForEntity(
                        url,
                        new HttpEntity<>(requestBody.toString(), headers),
                        String.class
                );
        String responseBody = responseEntity.getBody();
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response, 201);
        TestAssertionUtils.assertResponseHasExpectedFields(response, "subCategory", getSubCategoryDetailFields());
        assertThat(mapper.mapJSONResponseToObject(response))
                .usingRecursiveComparison()
                .isEqualTo(expectedDataProvider.getSavedObject());
    }

    @DisplayName("Fetch the saved sub category")
    @Test
    @Order(4)
    public void shouldFetchSavedSubCategoryById() throws JSONException {
        long id = 57L;
        String responseBody = subCategory
                .getForObject("http://localhost:" + port + "/api/v1/subcategories/" + id, String.class);
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response, 200);
        TestAssertionUtils.assertResponseHasExpectedFields(response, "subCategory", getSubCategoryDetailFields());
        assertThat(mapper.mapJSONResponseToObject(response))
                .usingRecursiveComparison()
                .isEqualTo(expectedDataProvider.getSavedObject());
    }

    @DisplayName("Update sub category")
    @Test
    @Order(5)
    public void shouldUpdateSubCategory() throws JSONException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "Test Sub Category Updated");
        requestBody.put("categoryId", 2);

        long subCategoryId = 35L;
        String url = "http://localhost:" + port + "/api/v1/subcategories/" + subCategoryId;
        ResponseEntity<String> responseEntity = subCategory.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(requestBody.toString(), headers),
                String.class
        );

        String responseBody = responseEntity.getBody();
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response, 200);
        TestAssertionUtils.assertResponseHasExpectedFields(response, "subCategory", getSubCategoryDetailFields());
        assertThat(mapper.mapJSONResponseToObject(response))
                .usingRecursiveComparison()
                .isEqualTo(expectedDataProvider.getUpdatedObject());
    }

    @DisplayName("Return 404 for non-existing address")
    @Test
    @Order(7)
    public void shouldReturn404NonExistingProduct() throws JSONException {
        long nonExistingId = 9999L;
        String responseBody = subCategory
                .getForObject("http://localhost:" + port + "/api/v1/subcategories/" + nonExistingId, String.class);
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response,404);
    }
}
