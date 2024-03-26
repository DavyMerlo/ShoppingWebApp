package com.davy.restapi.product.controller.testsuite;

import com.davy.restapi.shared.TestContainer;
import com.davy.restapi.shared.utils.TestAssertion;

import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import static com.davy.restapi.product.data.ProductFieldProvider.expectedProductV1Fields;

@DisplayName("Fetch products paginated without and without params")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class ProductPaginationTest extends TestContainer {

    @Autowired
    private TestAssertion testAssertion;

    @DisplayName("Fetch products paginated without params")
    @Test
    @Order(1)
    public void shouldFetchProductsPaginated() throws Exception {
        int productCount = 8;
        String responseBody = restTemplate
                .getForObject("http://localhost:" + port + "/api/v1/products", String.class);
        JSONObject response = testAssertion.provideResponse(responseBody);
        testAssertion.listResponseHasExpectedFields("products", expectedProductV1Fields());
        testAssertion.responseHasExpectedStatusCode(200);
        testAssertion.arrayHasExpectedSize("products", productCount);
        testAssertion.responseHasMetaDataFields("products");
        testAssertion.assertArrayContains("products", "name", "The Women in Me");
    }

    @DisplayName("Fetch products paginated with page param: 3 and pagesize param: 4")
    @Test
    @Order(2)
    public void shouldFetchProductsPaginatedWithPage3PageSize4() throws Exception {
        int page = 2;
        int pageSize = 4;
        int productCount = 3;
        String responseBody = restTemplate
                .getForObject("http://localhost:" + port + "/api/v1/products?page=" + page + "&pagesize=" + pageSize,
                        String.class);
        JSONObject response = testAssertion.provideResponse(responseBody);
        testAssertion.listResponseHasExpectedFields("products", expectedProductV1Fields());
        testAssertion.responseHasExpectedStatusCode(200);
        testAssertion.arrayHasExpectedSize("products", productCount);
        testAssertion.responseHasMetaDataFields("products");
    }

    @DisplayName("Fetch products paginated with page param: 1 and pagesize param: 5")
    @Test
    @Order(3)
    public void shouldFetchProductsPaginatedWithPage1PageSize5() throws Exception {
        int page = 0;
        int pageSize = 5;
        int productCount = 5;
        String responseBody = restTemplate
                .getForObject("http://localhost:" + port + "/api/v1/products?page=" + page + "&pagesize=" + pageSize,
                        String.class);
        JSONObject response = testAssertion.provideResponse(responseBody);
        testAssertion.listResponseHasExpectedFields("products", expectedProductV1Fields());
        testAssertion.responseHasExpectedStatusCode(200);
        testAssertion.arrayHasExpectedSize("products", productCount);
        testAssertion.responseHasMetaDataFields("products");
    }

    @DisplayName("Fetch products paginated with search param: 'the'")
    @Test
    @Order(4)
    public void shouldFetchProductsPaginatedWithSearchFor_the() throws Exception {
        String search = "The";
        int productCount = 4;
        String responseBody = restTemplate
                .getForObject("http://localhost:" + port + "/api/v1/products?search=" + search,
                        String.class);
        JSONObject response = testAssertion.provideResponse(responseBody);
        testAssertion.listResponseHasExpectedFields("products", expectedProductV1Fields());
        testAssertion.responseHasExpectedStatusCode(200);
        testAssertion.arrayHasExpectedSize("products", productCount);
        testAssertion.responseHasMetaDataFields("products");
    }

    @DisplayName("Fetch products paginated with search param: 'fairy tale'")
    @Test
    @Order(5)
    public void shouldFetchProductsPaginatedWithSearchFor_fairy_tale() throws Exception {
        String search = "fairy tale";
        int productCount = 1;
        String responseBody = restTemplate
                .getForObject("http://localhost:" + port + "/api/v1/products?search=" + search,
                        String.class);
        JSONObject response = testAssertion.provideResponse(responseBody);
        testAssertion.listResponseHasExpectedFields("products", expectedProductV1Fields());
        testAssertion.responseHasExpectedStatusCode(200);
        testAssertion.arrayHasExpectedSize("products", productCount);
        testAssertion.responseHasMetaDataFields("products");
    }

    @DisplayName("Fetch products paginated with sort param: 'id' and order param: 'asc'")
    @Test
    @Order(6)
    public void shouldFetchProductsPaginatedWithSortOnIdOrderAsc() throws Exception {
        String sortBy= "id";
        String order = "asc";
        int productCount = 8;
        String responseBody = restTemplate
                .getForObject("http://localhost:" + port + "/api/v1/products?sort=" + sortBy + "&order=" + order,
                        String.class);
        JSONObject response = testAssertion.provideResponse(responseBody);
        testAssertion.listResponseHasExpectedFields("products", expectedProductV1Fields());
        testAssertion.responseHasExpectedStatusCode(200);
        testAssertion.arrayHasExpectedSize("products", productCount);
        testAssertion.responseHasMetaDataFields("products");
    }

    @DisplayName("Fetch products paginated with sort param: 'id' and order param: 'desc'")
    @Test
    @Order(7)
    public void shouldFetchProductsPaginatedWithSortOnIdOrderDesc() throws Exception {
        String sortBy= "id";
        String order = "desc";
        int productCount = 8;
        String responseBody = restTemplate
                .getForObject("http://localhost:" + port + "/api/v1/products?sort=" + sortBy + "&order=" + order,
                        String.class);
        JSONObject response = testAssertion.provideResponse(responseBody);
        testAssertion.listResponseHasExpectedFields("products", expectedProductV1Fields());
        testAssertion.responseHasExpectedStatusCode(200);
        testAssertion.arrayHasExpectedSize("products", productCount);
        testAssertion.responseHasMetaDataFields("products");
    }

    @DisplayName("Fetch products paginated with sort param: 'name' and order param: 'asc'")
    @Test
    @Order(8)
    public void shouldFetchProductsPaginatedWithSortOnNameOrderAsc() throws Exception {
        String sortBy= "name";
        String order = "desc";
        int productCount = 8;
        String responseBody = restTemplate
                .getForObject("http://localhost:" + port + "/api/v1/products?sort=" + sortBy + "&order=" + order,
                        String.class);
        JSONObject response = testAssertion.provideResponse(responseBody);
        testAssertion.listResponseHasExpectedFields("products", expectedProductV1Fields());
        testAssertion.responseHasExpectedStatusCode(200);
        testAssertion.arrayHasExpectedSize("products", productCount);
        testAssertion.responseHasMetaDataFields("products");
    }

    @DisplayName("Fetch products paginated with sort param: 'name' and order param: 'desc'")
    @Test
    @Order(9)
    public void shouldFetchProductsPaginatedWithSortOnNameOrderDesc() throws Exception {
        String sortBy= "name";
        String order = "desc";
        int productCount = 8;
        String responseBody = restTemplate
                .getForObject("http://localhost:" + port + "/api/v1/products?sort=" + sortBy + "&order=" + order,
                        String.class);
        JSONObject response = testAssertion.provideResponse(responseBody);
        testAssertion.listResponseHasExpectedFields("products", expectedProductV1Fields());
        testAssertion.responseHasExpectedStatusCode(200);
        testAssertion.arrayHasExpectedSize("products", productCount);
        testAssertion.responseHasMetaDataFields("products");
    }
}
