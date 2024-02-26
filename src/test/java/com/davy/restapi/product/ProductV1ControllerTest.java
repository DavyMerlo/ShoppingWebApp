package com.davy.restapi.product;

import com.davy.restapi.shared.TestContainer;
import com.davy.restapi.shared.utils.TestAssertionUtils;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
public class ProductV1ControllerTest extends TestContainer {

    @Autowired
    public TestRestTemplate products;

    @Autowired
    public TestRestTemplate product;

    @LocalServerPort
    public int port;


    @Nested
    @DisplayName("Product Pagination TestSuite")
    @Suite
    @SelectClasses( ProductV1ControllerTest.class )
    @Order(1)
    public class ProductPaginationTest
    {
        @DisplayName("Fetch products paginated without params")
        @Test
        @Order(1)
        public void shouldFetchProductsPaginated() throws Exception {
            String responseBody = products
                    .getForObject("http://localhost:" + port + "/api/v1/products", String.class);
            JSONObject response = new JSONObject(responseBody);
            TestAssertionUtils.assertResponseHasExpectedStatusCode(response,200);
            TestAssertionUtils.assertArrayHasExpectedSize(response, "products", 8);
            TestAssertionUtils.assertListResponseHasExpectedFields(response, "products", expectedProductFields());
            TestAssertionUtils.assertResultHasExpectedMetaDataFields(response);
        }

        @DisplayName("Fetch products paginated with page param: 3 and pagesize param: 4")
        @Test
        @Order(2)
        public void shouldFetchProductsPaginatedWithPage3PageSize4() throws Exception {
            int page3 = 2;
            int pageSize4 = 4;
            String responseBody = products
                    .getForObject("http://localhost:" + port + "/api/v1/products?page=" + page3 + "&pagesize=" + pageSize4,
                            String.class);
            JSONObject response = new JSONObject(responseBody);
            TestAssertionUtils.assertResponseHasExpectedStatusCode(response,200);
            TestAssertionUtils.assertArrayHasExpectedSize(response, "products", 3);
            TestAssertionUtils.assertListResponseHasExpectedFields(response, "products", expectedProductFields());
            TestAssertionUtils.assertResultHasExpectedMetaDataFields(response);
        }

        @DisplayName("Fetch products paginated with page param: 1 and pagesize param: 5")
        @Test
        @Order(3)
        public void shouldFetchProductsPaginatedWithPage1PageSize5() throws Exception {
            int page1 = 0;
            int pageSize5 = 5;
            String responseBody = products
                    .getForObject("http://localhost:" + port + "/api/v1/products?page=" + page1 + "&pagesize=" + pageSize5,
                            String.class);
            JSONObject response = new JSONObject(responseBody);
            TestAssertionUtils.assertResponseHasExpectedStatusCode(response,200);
            TestAssertionUtils.assertArrayHasExpectedSize(response, "products", 5);
            TestAssertionUtils.assertListResponseHasExpectedFields(response, "products", expectedProductFields());
            TestAssertionUtils.assertResultHasExpectedMetaDataFields(response);
        }

        @DisplayName("Fetch products paginated with search param: 'the'")
        @Test
        @Order(3)
        public void shouldFetchProductsPaginatedWithSearchFor_the() throws Exception {
            String search = "The";
            String responseBody = products
                    .getForObject("http://localhost:" + port + "/api/v1/products?search=" + search,
                            String.class);
            JSONObject response = new JSONObject(responseBody);
            TestAssertionUtils.assertResponseHasExpectedStatusCode(response,200);
            TestAssertionUtils.assertArrayHasExpectedSize(response, "products", 4);
            TestAssertionUtils.assertListResponseHasExpectedFields(response, "products", expectedProductFields());
            TestAssertionUtils.assertResultHasExpectedMetaDataFields(response);
        }

        @DisplayName("Fetch products paginated with search param: 'fairy tale'")
        @Test
        @Order(4)
        public void shouldFetchProductsPaginatedWithSearchFor_fairy_tale() throws Exception {
            String search = "fairy tale";
            String responseBody = products
                    .getForObject("http://localhost:" + port + "/api/v1/products?search=" + search,
                            String.class);
            JSONObject response = new JSONObject(responseBody);
            TestAssertionUtils.assertResponseHasExpectedStatusCode(response,200);
            TestAssertionUtils.assertArrayHasExpectedSize(response, "products", 1);
            TestAssertionUtils.assertListResponseHasExpectedFields(response, "products", expectedProductFields());
            TestAssertionUtils.assertResultHasExpectedMetaDataFields(response);
        }

        @DisplayName("Fetch products paginated with sort param: 'id' and order param: 'asc'")
        @Test
        @Order(5)
        public void shouldFetchProductsPaginatedWithSortOnIdOrderAsc() throws Exception {
            String sortBy= "id";
            String order = "asc";
            String responseBody = products
                    .getForObject("http://localhost:" + port + "/api/v1/products?sort=" + sortBy + "&order=" + order,
                            String.class);
            JSONObject response = new JSONObject(responseBody);
            TestAssertionUtils.assertResponseHasExpectedStatusCode(response,200);
            TestAssertionUtils.assertArrayHasExpectedSize(response, "products", 8);
            TestAssertionUtils.assertListResponseHasExpectedFields(response, "products", expectedProductFields());
            TestAssertionUtils.assertResultHasExpectedMetaDataFields(response);
        }

        @DisplayName("Fetch products paginated with sort param: 'id' and order param: 'desc'")
        @Test
        @Order(6)
        public void shouldFetchProductsPaginatedWithSortOnIdOrderDesc() throws Exception {
            String sortBy= "id";
            String order = "desc";
            String responseBody = products
                    .getForObject("http://localhost:" + port + "/api/v1/products?sort=" + sortBy + "&order=" + order,
                            String.class);
            JSONObject response = new JSONObject(responseBody);
            TestAssertionUtils.assertResponseHasExpectedStatusCode(response,200);
            TestAssertionUtils.assertArrayHasExpectedSize(response, "products", 8);
            TestAssertionUtils.assertListResponseHasExpectedFields(response, "products", expectedProductFields());
            TestAssertionUtils.assertResultHasExpectedMetaDataFields(response);
        }

        @DisplayName("Fetch products paginated with sort param: 'name' and order param: 'asc'")
        @Test
        @Order(7)
        public void shouldFetchProductsPaginatedWithSortOnNameOrderAsc() throws Exception {
            String sortBy= "name";
            String order = "desc";
            String responseBody = products
                    .getForObject("http://localhost:" + port + "/api/v1/products?sort=" + sortBy + "&order=" + order,
                            String.class);
            JSONObject response = new JSONObject(responseBody);
            TestAssertionUtils.assertResponseHasExpectedStatusCode(response,200);
            TestAssertionUtils.assertArrayHasExpectedSize(response, "products", 8);
            TestAssertionUtils.assertListResponseHasExpectedFields(response, "products", expectedProductFields());
            TestAssertionUtils.assertResultHasExpectedMetaDataFields(response);
        }

        @DisplayName("Fetch products paginated with sort param: 'name' and order param: 'desc'")
        @Test
        @Order(8)
        public void shouldFetchProductsPaginatedWithSortOnNameOrderDesc() throws Exception {
            String sortBy= "name";
            String order = "desc";
            String responseBody = products
                    .getForObject("http://localhost:" + port + "/api/v1/products?sort=" + sortBy + "&order=" + order,
                            String.class);
            JSONObject response = new JSONObject(responseBody);
            TestAssertionUtils.assertResponseHasExpectedStatusCode(response,200);
            TestAssertionUtils.assertArrayHasExpectedSize(response, "products", 8);
            TestAssertionUtils.assertListResponseHasExpectedFields(response, "products", expectedProductFields());
            TestAssertionUtils.assertResultHasExpectedMetaDataFields(response);
        }
    }

    @DisplayName("Product by ID TestSuite")
    @Nested
    @Suite
    @Order(2)
    @SelectClasses( ProductV1ControllerTest.class )
    class ProductByIdTest{

        @DisplayName("Fetch product with ID: 1")
        @Test
        @Order(9)
        public void shouldFetchProductById() throws Exception {
            long id = 1L;
            String responseBody = product
                    .getForObject("http://localhost:" + port + "/api/v1/products/" + id, String.class);
            JSONObject response = new JSONObject(responseBody);
            TestAssertionUtils.assertResponseHasExpectedStatusCode(response,200);
            TestAssertionUtils.assertResponseHasExpectedFields(response, "product", expectedProductFields());
        }

        @DisplayName("Return 404 for non-existing product")
        @Test
        @Order(10)
        public void shouldReturn404NonExistingProduct() throws Exception {
            long nonExistingId = 9999L;
            String responseBody = product
                    .getForObject("http://localhost:" + port + "/api/v1/products/" + nonExistingId, String.class);
            JSONObject response = new JSONObject(responseBody);
            TestAssertionUtils.assertResponseHasExpectedStatusCode(response,404);
        }
    }

    @DisplayName("Save product TestSuite")
    @Nested
    @Suite
    @Order(3)
    @SelectClasses( ProductV1ControllerTest.class )
    class ProductSaveTest{

        @DisplayName("Save new product")
        @Test
        @Order(11)
        public void shouldSaveProduct() throws JSONException {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            String url = "http://localhost:" + port + "/api/v1/products";
            ResponseEntity<String> responseEntity = product
                    .postForEntity(
                            url,
                            new HttpEntity<>(getJsonObjectToSave().toString(), headers),
                            String.class
                    );
            String responseBody = responseEntity.getBody();
            JSONObject response = new JSONObject(responseBody);
            TestAssertionUtils.assertResponseHasExpectedStatusCode(response, 201);
            TestAssertionUtils.assertResponseHasExpectedFields(response, "product", expectedProductFields());
        }
    }

    @DisplayName("Update product TestSuite")
    @Nested
    @Suite
    @Order(4)
    @SelectClasses( ProductV1ControllerTest.class )
    class ProductUpdateTest{
        @DisplayName("Update product with ID: 5")
        @Test
        @Order(12)
        public void shouldUpdateProduct() throws JSONException {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");
            JSONObject requestBody = getJsonObjectToUpdate();

            long productId = 5L;
            String url = "http://localhost:" + port + "/api/v1/products/" + productId;
            ResponseEntity<String> responseEntity = product.exchange(
                    url,
                    HttpMethod.PUT,
                    new HttpEntity<>(requestBody.toString(), headers),
                    String.class
            );
            String responseBody = responseEntity.getBody();
            JSONObject response = new JSONObject(responseBody);
            TestAssertionUtils.assertResponseHasExpectedStatusCode(response, 200);
            TestAssertionUtils.assertResponseHasExpectedFields(response, "product", expectedProductFields());
        }
    }

    @NotNull
    private static JSONObject getJsonObjectToUpdate() throws JSONException {
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "Test Product Updated");
        requestBody.put("description", "Test Product Description Updated");
        requestBody.put("imageUrl", "https://example.com/image.jpg");
        requestBody.put("purchasePrice", 49.99);
        requestBody.put("sellingPrice", 59.99);
        requestBody.put("vat", 0);
        requestBody.put("quantity", 100);
        requestBody.put("categoryId", 1);
        requestBody.put("subCategoryId", 2);
        return requestBody;
    }

    @NotNull
    private static JSONObject getJsonObjectToSave() throws JSONException {
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "Test Product Saved");
        requestBody.put("description", "Test Product Description");
        requestBody.put("imageUrl", "https://example.com/image.jpg");
        requestBody.put("purchasePrice", 49.99);
        requestBody.put("sellingPrice", 59.99);
        requestBody.put("vat", 0);
        requestBody.put("quantity", 100);
        requestBody.put("categoryId", 1);
        requestBody.put("subCategoryId", 2);
        return requestBody;
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
