package org.adidas.backend.modules;

import org.adidas.backend.supportFunctions.CommonsModule;
import org.adidas.backend.supportFunctions.RequestModule;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Date;

public class PetModule {
    RequestModule requestModule = new RequestModule();

    public void getPetsByStatus(String status) {
        requestModule.addParam("status", status);
        requestModule.addHeader("accept", "application/json");
        requestModule.executeRequest("get", "https://petstore.swagger.io/v2/" + "pet/findByStatus");
    }

    public void createPet() {
        JSONObject petBody = CommonsModule.getJSONfromFile("src/test/resources/requests/bodies/pet.json");
        long id = new Date().getTime();
        CommonsModule.setSessionVariable("id", id);
        if (petBody != null) {
            petBody.put("id", id);
            requestModule.addHeader("accept", "application/json");
            requestModule.addHeader("Content-Type", "application/json");
            requestModule.executeRequest("post", "https://petstore.swagger.io/v2/" + "pet/", petBody.toString());
        }
    }

    public void changePetStatus(long id, String status) {
        JSONObject petBody = CommonsModule.getJSONfromFile("src/test/resources/requests/bodies/pet.json");
        if (petBody != null) {
            petBody.put("id", id);
            petBody.put("status", status);
            requestModule.addHeader("accept", "application/json");
            requestModule.addHeader("Content-Type", "application/json");
            requestModule.executeRequest("put", "https://petstore.swagger.io/v2/" + "pet/", petBody.toString());
        }
    }

    public void deletePet(long id) {
        requestModule.addHeader("accept", "application/json");
        requestModule.executeRequest("delete", "https://petstore.swagger.io/v2/" + "pet/" + id);
    }

    public boolean verifyPetIsCreated(long id) {
        JSONObject petResponse = requestModule.getResponseBodyJSONObject(CommonsModule.getSessionVariable("response"));
        return Long.parseLong(petResponse.get("id").toString()) == id;
    }

    public boolean verifyPetIsDeleted(long id) {
        JSONObject petResponse = requestModule.getResponseBodyJSONObject(CommonsModule.getSessionVariable("response"));
        return Long.parseLong(petResponse.get("message").toString()) == id;
    }

    public String getPetStatus(long id) {
        JSONObject petResponse = requestModule.getResponseBodyJSONObject(CommonsModule.getSessionVariable("response"));
        if (Long.parseLong(petResponse.get("id").toString()) == id) {
            return petResponse.get("status").toString();
        } else {
            return "Pet with id: " + id + " not found";
        }
    }

    public boolean verifyPetsReceivedByStatus(String status) {
        JSONArray petResponse = requestModule.getResponseBodyJSONArray(CommonsModule.getSessionVariable("response"));
        int petIndex;
        for (petIndex = 0; petIndex < petResponse.length(); petIndex++) {
            JSONObject pet = (JSONObject) petResponse.get(petIndex);
            if (!pet.get("status").toString().matches(status)) {
                return false;
            }
        }
        return true;
    }
}
