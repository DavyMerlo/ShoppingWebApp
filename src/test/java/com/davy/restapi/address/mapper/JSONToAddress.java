package com.davy.restapi.address.mapper;

import com.davy.restapi.address.dto.AddressDetailDTO;
import com.davy.restapi.shared.utils.JSONToObject;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class JSONToAddress implements JSONToObject<AddressDetailDTO> {

    @Override
    public AddressDetailDTO mapJSONResponseToObject(JSONObject response)
            throws JSONException {
        var result = response.getJSONObject("result");
        var address = result.getJSONObject("address");
        return new AddressDetailDTO(
                address.getLong("id"),
                address.getString("street"),
                address.getString("houseNumber"),
                address.getString("busNumber"),
                address.getString("postalCode"),
                address.getString("localAuthority")
        );
    }
}
