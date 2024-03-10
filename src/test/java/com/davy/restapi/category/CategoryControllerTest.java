package com.davy.restapi.category;

import com.davy.restapi.category.dto.CategoryTryDetailsDTO;
import com.davy.restapi.shared.TestContainer;
import com.davy.restapi.shared.utils.ExpectedDataProvider;
import com.davy.restapi.shared.utils.JSONToObject;
import com.davy.restapi.shared.utils.TestAssertionUtils;
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

import static com.davy.restapi.category.data.CategoryFieldProvider.getCategoryDetailFields;
import static com.davy.restapi.category.data.CategoryFieldProvider.getCategoryFields;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryControllerTest extends TestContainer {

    @Autowired
    private TestRestTemplate categories;

    @Autowired
    private TestRestTemplate category;

    @Autowired
    private JSONToObject<CategoryTryDetailsDTO> mapper;

    @Autowired
    private ExpectedDataProvider<CategoryTryDetailsDTO> expectedDataProvider;

    @LocalServerPort
    private int port;

    @DisplayName("Fetch all categories")
    @Test
    @Order(1)
    public void shouldFetchAllCategories() throws JSONException {
        String responseBody = categories
                .getForObject("http://localhost:" + port + "/api/v1/categories", String.class);
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response, 200);
        TestAssertionUtils.assertListResponseHasExpectedFields(response, "categories", getCategoryFields());
        TestAssertionUtils.assertListResponseHasExpectedSize(response, "categories", 10);
    }

    @DisplayName("Fetch category by id")
    @Test
    @Order(2)
    public void shouldFetchCategoryById() throws JSONException {
        long id = 3L;
        String responseBody = category
                .getForObject("http://localhost:" + port + "/api/v1/categories/" + id, String.class);
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response, 200);
        assertThat(mapper.mapJSONResponseToObject(response))
                .usingRecursiveComparison()
                .isEqualTo(expectedDataProvider.getObject());
    }

    @DisplayName("Save new category")
    @Test
    @Order(3)
    public void shouldSaveCategory() throws JSONException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "Test Category Saved");

        String url = "http://localhost:" + port + "/api/v1/categories";
        ResponseEntity<String> responseEntity = category
                .postForEntity(
                        url,
                        new HttpEntity<>(requestBody.toString(), headers),
                        String.class
                );
        String responseBody = responseEntity.getBody();
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response, 201);
        TestAssertionUtils.assertResponseHasExpectedFields(response, "category", getCategoryDetailFields());
        assertThat(mapper.mapJSONResponseToObject(response))
                .usingRecursiveComparison()
                .isEqualTo(expectedDataProvider.getSavedObject());
    }

    @DisplayName("Fetch the saved category")
    @Test
    @Order(4)
    public void shouldFetchSavedCategory() throws JSONException {
        long id = 11L;
        String responseBody = category
                .getForObject("http://localhost:" + port + "/api/v1/categories/" + id, String.class);
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response, 200);
        TestAssertionUtils.assertResponseHasExpectedFields(response, "category", getCategoryDetailFields());
        assertThat(mapper.mapJSONResponseToObject(response))
                .usingRecursiveComparison()
                .isEqualTo(expectedDataProvider.getSavedObject());
    }

    @DisplayName("Update category")
    @Test
    @Order(5)
    public void shouldUpdateCategory() throws JSONException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "Test Category Updated");

        long categoryId = 3L;
        String url = "http://localhost:" + port + "/api/v1/categories/" + categoryId;
        ResponseEntity<String> responseEntity = category.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(requestBody.toString(), headers),
                String.class
        );

        String responseBody = responseEntity.getBody();
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response, 200);
        TestAssertionUtils.assertResponseHasExpectedFields(response, "category", getCategoryDetailFields());
        assertThat(mapper.mapJSONResponseToObject(response))
                .usingRecursiveComparison()
                .isEqualTo(expectedDataProvider.getUpdatedObject());
    }

    @DisplayName("Fetch the updated category")
    @Test
    @Order(6)
    public void shouldFetchUpdatedCategory() throws JSONException {
        long id = 3L;
        String responseBody = category
                .getForObject("http://localhost:" + port + "/api/v1/categories/" + id, String.class);
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response, 200);
        TestAssertionUtils.assertResponseHasExpectedFields(response, "category", getCategoryDetailFields());
        assertThat(mapper.mapJSONResponseToObject(response))
                .usingRecursiveComparison()
                .isEqualTo(expectedDataProvider.getUpdatedObject());
    }

    @DisplayName("Return 404 for non-existing category")
    @Test
    @Order(7)
    public void shouldReturn404NonExistingProduct() throws JSONException {
        long nonExistingId = 9999L;
        String responseBody = category
                .getForObject("http://localhost:" + port + "/api/v1/categories/" + nonExistingId, String.class);
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response,404);
    }
}
