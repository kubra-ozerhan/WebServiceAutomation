import TrelloService.TrelloService;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import testlogger.TestResultLogger;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(TestResultLogger.class)
public class BaseTest {
    protected TrelloService trelloService;
    protected String boardId;

    @BeforeEach
    public void setUp() {
        // Setup TrelloService with your API key and token
        trelloService = new TrelloService("My API Key"
                , "My Token");

    }

}
