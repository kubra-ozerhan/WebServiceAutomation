
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import io.restassured.response.Response;

import java.util.List;
import java.util.Random;

public class TrelloAPITests  extends BaseTest{
    @Test
    @Order(1)
    @DisplayName("Create Board and Cards")
    public void testCreateBoardAndCards() {
        // Create a board
        Response boardResponse = trelloService.createBoard("Test Board");
        assertEquals(200, boardResponse.getStatusCode());

        // Get board ID
        boardId = boardResponse.jsonPath().get("id");

        // Create a list on the board
        Response listResponse = trelloService.createList(boardId, "Test List");
        assertEquals(200, listResponse.getStatusCode());
        String listId = listResponse.jsonPath().get("id");

        // Create two cards on the list
        Response card1Response = trelloService.createCard(listId, "Card 1");
        assertEquals(200, card1Response.getStatusCode());

        Response card2Response = trelloService.createCard(listId, "Card 2");
        assertEquals(200, card2Response.getStatusCode());
    }

    @Test
    @Order(2)
    @DisplayName("Update randomly one of the cards then Delete the Card")
    public void testUpdateAndDeleteCard() {
        // Get board details
        Response boardDetailsResponse = trelloService.getCreatedBoard(boardId);
        assertEquals(200, boardDetailsResponse.getStatusCode());

        // Get board ID
        String boardId = boardDetailsResponse.jsonPath().get("id");

        // Get all cards on the board
        Response cardsResponse = trelloService.getCardsOnBoard(boardId);
        assertEquals(200, cardsResponse.getStatusCode());

        List<String> cardIds = cardsResponse.jsonPath().getList("id");

        // Choose a random card from the list of cards
        Random random = new Random();
        String randomCardId = cardIds.get(random.nextInt(cardIds.size()));

        // Update the random card
        String updatedCardName = "Updated Card Name";
        Response updateResponse = trelloService.updateCard(randomCardId, updatedCardName);
        assertEquals(200, updateResponse.getStatusCode());

        // Delete the random card
        Response deleteResponse = trelloService.deleteCard(randomCardId);
        assertEquals(200, deleteResponse.getStatusCode());

    }

    @Test
    @Order(3)
    @DisplayName("Delete Board")
    public void DeleteBoard() {
        // Clean up - delete board after each test
        if (boardId != null) {
            trelloService.deleteBoard(boardId);
        }
    }
}
