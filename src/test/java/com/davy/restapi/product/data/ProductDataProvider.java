package com.davy.restapi.product.data;

import com.davy.restapi.product.dto.ProductDTO;

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
}
