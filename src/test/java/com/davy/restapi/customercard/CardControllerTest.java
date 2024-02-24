package com.davy.restapi.customercard;

import com.davy.restapi.shared.TestContainer;
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

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CardControllerTest extends TestContainer {

    @Autowired
    private TestRestTemplate cards;

    @Autowired
    private TestRestTemplate card;

    @LocalServerPort
    private int port;

    @DisplayName("Fetch all customer cards")
    @Test
    @Order(1)
    public void shouldFetchAllCustomerCards() throws Exception {
        String responseBody = cards
                .getForObject("http://localhost:" + port + "/api/v1/cards", String.class);
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response, 200);
        TestAssertionUtils.assertListResponseHasExpectedFields(response, "cards", expectedCardFields());
        TestAssertionUtils.assertListResponseHasExpectedSize(response, "cards", 1);
    }

    @DisplayName("Fetch customer card by id")
    @Test
    @Order(2)
    public void shouldFetchCustomerCardById() throws JSONException {
        long id = 1L;
        String responseBody = card
                .getForObject("http://localhost:" + port + "/api/v1/cards/" + id, String.class);
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response, 200);
        TestAssertionUtils.assertResponseHasExpectedFields(response, "card", expectedCardFields());
        TestAssertionUtils.assertResponseHasExpectedValues(response,
                "card",
                expCardDetailFields(),
                expCardDetailFieldValues()
        );
    }

    @DisplayName("Save new customer card")
    @Test
    @Order(3)
    public void shouldSaveCustomerCard() throws JSONException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        JSONObject requestBody = new JSONObject();
        requestBody.put("number", "111111111111");
        requestBody.put("points", 20);

        String url = "http://localhost:" + port + "/api/v1/cards";
        ResponseEntity<String> responseEntity = card
                .postForEntity(
                        url,
                        new HttpEntity<>(requestBody.toString(), headers),
                        String.class
                );
        String responseBody = responseEntity.getBody();
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response, 201);
        TestAssertionUtils.assertResponseHasExpectedFields(response, "card", expectedCardFields());
        TestAssertionUtils.assertResponseHasExpectedValues(
                response,
                "card",
                expectedCardFields(),
                expSavedCardFieldValues()
        );
    }

    @DisplayName("Fetch the saved customer card")
    @Test
    @Order(4)
    public void shouldFetchSavedCustomerCard() throws JSONException {
        long id = 2L;
        String responseBody = card
                .getForObject("http://localhost:" + port + "/api/v1/cards/" + id, String.class);
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response, 200);
        TestAssertionUtils.assertResponseHasExpectedFields(response, "card", expectedCardFields());
        TestAssertionUtils.assertResponseHasExpectedValues(response,
                "card",
                expCardDetailFields(),
                expSavedCardFieldValues()
        );
    }


    @DisplayName("Update customer card")
    @Test
    @Order(4)
    public void shouldUpdateCustomerCard() throws JSONException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        JSONObject requestBody = new JSONObject();
        requestBody.put("number", "222222222222");
        requestBody.put("points", 25);

        long cardId = 1L;
        String url = "http://localhost:" + port + "/api/v1/cards/" + cardId;
        ResponseEntity<String> responseEntity = card.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(requestBody.toString(), headers),
                String.class
        );
        String responseBody = responseEntity.getBody();
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response, 200);
        TestAssertionUtils.assertResponseHasExpectedFields(response, "card", expectedCardFields());
        TestAssertionUtils.assertResponseHasExpectedValues(
                response,
                "card",
                expectedCardFields(),
                expUpdatedCardFieldValues()
        );
    }

    @DisplayName("Fetch the updated customer card")
    @Test
    @Order(5)
    public void shouldFetchUpdatedCustomerCard() throws JSONException {
        long id = 1L;
        String responseBody = card
                .getForObject("http://localhost:" + port + "/api/v1/cards/" + id, String.class);
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response, 200);
        TestAssertionUtils.assertResponseHasExpectedFields(response, "card", expectedCardFields());
        TestAssertionUtils.assertResponseHasExpectedValues(response,
                "card",
                expCardDetailFields(),
                expUpdatedCardFieldValues()
        );
    }

    @DisplayName("Return 404 for non-existing customer card")
    @Test
    @Order(6)
    public void shouldReturn404NotExistingCustomerCard() throws JSONException {
        long nonExistingId = 9999L;
        String responseBody = card
                .getForObject("http://localhost:" + port + "/api/v1/cards/" + nonExistingId, String.class);
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response,404);
    }


    private List<String> expectedCardFields() {
        List<String> fields = new ArrayList<>();
        fields.add("id");
        fields.add("number");
        return fields;
    }

    private List<String> expCardDetailFields() {
        List<String> fields = new ArrayList<>();
        fields.add("id");
        fields.add("number");
        fields.add("points");
        return fields;
    }

    private List<Object> expCardDetailFieldValues() {
        List<Object> fields = new ArrayList<>();
        fields.add(1);
        fields.add("15151515151515");
        fields.add(10);
        return fields;
    }

    private List<Object> expSavedCardFieldValues() {
        List<Object> fields = new ArrayList<>();
        fields.add(2);
        fields.add("111111111111");
        fields.add(20);
        return fields;
    }

    private List<Object> expUpdatedCardFieldValues() {
        List<Object> fields = new ArrayList<>();
        fields.add(1);
        fields.add("222222222222");
        fields.add(25);
        return fields;
    }
}
