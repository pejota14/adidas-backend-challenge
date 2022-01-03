package org.adidas.backend.supportFunctions;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;

public class RequestModule {
    private final RequestSpecification requestSpecification;

    public RequestModule() {
        requestSpecification = RestAssured.given();
    }

    public void executeRequest(String method, String url) {
        setEndpoint(url);
        execute(method);
    }

    public void executeRequest(String method, String url, String body) {
        setBody(body);
        setEndpoint(url);
        execute(method);
    }

    public void setBody(String body) {
        requestSpecification.body(body);
    }

    public void addParam(String param, String value) {
        requestSpecification.param(param, value);
    }

    public void addHeader(String header, String value) {
        requestSpecification.header(header, value);
    }

    public void setEndpoint(String endpoint) {
        requestSpecification.baseUri(endpoint);
    }

    public void execute(String method) {
        Response response = requestSpecification.request(method);
        CommonsModule.setSessionVariable("response", response);
    }

    public int getResponseStatus(Response response) {
        return response.getStatusCode();
    }

    public JSONObject getResponseBodyJSONObject(Response response) {
        return new JSONObject(response.getBody().asString());
    }

    public JSONArray getResponseBodyJSONArray(Response response) {
        return new JSONArray(response.getBody().asString());
    }
}
