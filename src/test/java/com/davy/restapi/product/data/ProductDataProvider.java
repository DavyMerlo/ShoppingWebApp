package com.davy.restapi.product.data;

import com.davy.restapi.product.dto.ProductDTO;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class ProductDataProvider {

    public static List<ProductDTO> getExpectedProductList() {
        return Arrays.asList(
                new ProductDTO(1L, "Fairy Tale"),
                new ProductDTO(2L, "Elon Musk"),
                new ProductDTO(3L, "The Women in Me"),
                new ProductDTO(4L, "Elon Musk"),
                new ProductDTO(5L, "ChatGPT 02 2023"),
                new ProductDTO(6L, "Puzzlesport - Puzzle book"),
                new ProductDTO(7L, "Transformers - Rise Of The Beasts(Blu-ray)"),
                new ProductDTO(8L, "TMission: Impossible - Dead Reckoning Part One (Blu-ray)"),
                new ProductDTO(9L, "The Rolling Stones - Hackney Diamonds(CD)"),
                new ProductDTO(10L, "Taylor Swift - 1989 (Taylor's Version) (CD)"),
                new ProductDTO(11L, "Suicide Squad: Kill The Justice League - PlayStation 5")
        );
    }

    @NotNull
    public static JSONObject getJsonObjectToUpdate() throws JSONException {
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
    public static JSONObject getJsonObjectToSave() throws JSONException {
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
}
