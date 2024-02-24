package com.davy.restapi.address;

import com.davy.restapi.address.dto.AddressDetailDTO;
import com.davy.restapi.shared.TestContainer;
import com.davy.restapi.shared.utils.ExpectedDataProvider;
import com.davy.restapi.shared.utils.JSONResponseToObject;
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

import static com.davy.restapi.address.data.AddressFieldProvider.getAddressFields;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddressControllerTest extends TestContainer {

    @Autowired
    private TestRestTemplate addresses;

    @Autowired
    private TestRestTemplate address;

    @Autowired
    private JSONResponseToObject<AddressDetailDTO> mapper;

    @Autowired
    private ExpectedDataProvider<AddressDetailDTO> expectedDataProvider;

    @LocalServerPort
    private int port;

    @DisplayName("Fetch all addresses")
    @Test
    @Order(1)
    public void shouldFetchAllAddresses() throws JSONException {
        String responseBody = addresses
                .getForObject("http://localhost:" + port + "/api/v1/addresses", String.class);
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response, 200);
        TestAssertionUtils.assertListResponseHasExpectedFields(response, "addresses", getAddressFields());
        TestAssertionUtils.assertListResponseHasExpectedSize(response, "addresses", 1);
    }

    @DisplayName("Fetch address by id")
    @Test
    @Order(2)
    public void shouldFetchAddressById() throws JSONException {
        long id = 1L;
        String responseBody = address
                .getForObject("http://localhost:" + port + "/api/v1/addresses/" + id, String.class);
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response, 200);
        assertThat(mapper.mapJSONResponseToObject(response))
                .usingRecursiveComparison()
                .isEqualTo(expectedDataProvider.getObject());
    }

    @DisplayName("Save new address")
    @Test
    @Order(3)
    public void shouldSaveAddress() throws JSONException {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        JSONObject requestBody = new JSONObject();
        requestBody.put("street", "Teststreet Saved");
        requestBody.put("houseNumber", "50");
        requestBody.put("busNumber", "1");
        requestBody.put("postalCode", "3530");
        requestBody.put("localAuthority", "TestCity");

        String url = "http://localhost:" + port + "/api/v1/addresses";
        ResponseEntity<String> responseEntity = address
                .postForEntity(url, new HttpEntity<>(requestBody.toString(), headers), String.class);

        String responseBody = responseEntity.getBody();
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response, 201);
        TestAssertionUtils.assertResponseHasExpectedFields(response, "address", getAddressFields());
        assertThat(mapper.mapJSONResponseToObject(response))
                .usingRecursiveComparison()
                .isEqualTo(expectedDataProvider.getSavedObject());
    }

    @DisplayName("Fetch the saved address")
    @Test
    @Order(4)
    public void shouldFetchSavedAddressById() throws JSONException {
        long id = 2L;
        String responseBody = address
                .getForObject("http://localhost:" + port + "/api/v1/addresses/" + id, String.class);
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response, 200);
        TestAssertionUtils.assertResponseHasExpectedFields(response, "address", getAddressFields());
        assertThat(mapper.mapJSONResponseToObject(response))
                .usingRecursiveComparison()
                .isEqualTo(expectedDataProvider.getSavedObject());
    }

    @DisplayName("Update address")
    @Test
    @Order(5)
    public void shouldUpdateAddress() throws JSONException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        JSONObject requestBody = new JSONObject();
        requestBody.put("street", "Teststreet Updated");
        requestBody.put("houseNumber", "50");
        requestBody.put("busNumber", "1");
        requestBody.put("postalCode", "3530");
        requestBody.put("localAuthority", "TestCity");

        long addressId = 1L;
        String url = "http://localhost:" + port + "/api/v1/addresses/" + addressId;
        ResponseEntity<String> responseEntity = address.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(requestBody.toString(), headers),
                String.class
        );

        String responseBody = responseEntity.getBody();
        JSONObject response = new JSONObject(responseBody);

        TestAssertionUtils.assertResponseHasExpectedStatusCode(response, 200);
        TestAssertionUtils.assertResponseHasExpectedFields(response, "address", getAddressFields());
        assertThat(mapper.mapJSONResponseToObject(response))
                .usingRecursiveComparison()
                .isEqualTo(expectedDataProvider.getUpdatedObject());
    }

    @DisplayName("Fetch the updated address")
    @Test
    @Order(6)
    public void shouldFetchUpdatedAddressById() throws JSONException {
        long id = 1L;
        String responseBody = address
                .getForObject("http://localhost:" + port + "/api/v1/addresses/" + id, String.class);
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response, 200);
        TestAssertionUtils.assertResponseHasExpectedFields(response, "address", getAddressFields());
        assertThat(mapper.mapJSONResponseToObject(response))
                .usingRecursiveComparison()
                .isEqualTo(expectedDataProvider.getUpdatedObject());
    }

    @DisplayName("Return 404 for non-existing address")
    @Test
    @Order(7)
    public void shouldReturn404NonExistingProduct() throws JSONException {
        long nonExistingId = 9999L;
        String responseBody = address
                .getForObject("http://localhost:" + port + "/api/v1/addresses/" + nonExistingId, String.class);
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response,404);
    }
}
