package com.davy.restapi.customercard;

import com.davy.restapi.shared.utils.TestAssertionUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CardControllerTest {

    @Autowired
    private TestRestTemplate cards;

    @Autowired
    private TestRestTemplate card;

    @LocalServerPort
    private int port;

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

    @Test
    @Order(3)
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
}
