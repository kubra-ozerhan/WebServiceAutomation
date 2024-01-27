package TrelloService;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TrelloService {

    private static final String BASE_URL = "https://api.trello.com/1";
    private String apiKey;
    private String token;

    public TrelloService(String apiKey, String token) {
        this.apiKey = apiKey;
        this.token = token;
    }

    public Response createBoard(String name) {
        RequestSpecification request = RestAssured.given()
                .contentType(ContentType.JSON)
                .body("{ \"name\": \"" + name + "\" }")
                .queryParam("key", apiKey)
                .queryParam("token", token);
        return request.post(BASE_URL + "/boards");
    }

    public Response getCreatedBoard(String boardId) {
        RequestSpecification request = RestAssured.given()
                .queryParam("key", apiKey)
                .queryParam("token", token);
        return request.get(BASE_URL + "/boards/" + boardId);
    }

    public Response createList(String boardId, String name) {
        RequestSpecification request = RestAssured.given()
                .contentType(ContentType.JSON)
                .body("{ \"name\": \"" + name + "\", \"idBoard\": \"" + boardId + "\" }")
                .queryParam("key", apiKey)
                .queryParam("token", token);
        return request.post(BASE_URL + "/lists");
    }

    public Response createCard(String listId, String name) {
        RequestSpecification request = RestAssured.given()
                .contentType(ContentType.JSON)
                .body("{ \"name\": \"" + name + "\", \"idList\": \"" + listId + "\" }")
                .queryParam("key", apiKey)
                .queryParam("token", token);
        return request.post(BASE_URL + "/cards");
    }

    public Response updateCard(String cardId, String name) {
        RequestSpecification request = RestAssured.given()
                .contentType(ContentType.JSON)
                .body("{ \"name\": \"" + name + "\" }")
                .queryParam("key", apiKey)
                .queryParam("token", token);
        return request.put(BASE_URL + "/cards/" + cardId);
    }

    public Response deleteCard(String cardId) {
        RequestSpecification request = RestAssured.given()
                .queryParam("key", apiKey)
                .queryParam("token", token);
        return request.delete(BASE_URL + "/cards/" + cardId);
    }

    public Response getCardsOnBoard(String boardId) {
        RequestSpecification request = RestAssured.given()
                .queryParam("key", apiKey)
                .queryParam("token", token);
        return request.get(BASE_URL + "/boards/" + boardId + "/cards");
    }

    public Response deleteBoard(String boardId) {
        RequestSpecification request = RestAssured.given()
                .queryParam("key", apiKey)
                .queryParam("token", token);
        return request.delete(BASE_URL + "/boards/" + boardId);
    }


}
