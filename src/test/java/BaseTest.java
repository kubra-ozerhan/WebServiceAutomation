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
        trelloService = new TrelloService("e9a0f4f375d925a3146febbebc5fbdaa"
                , "ATTAfe8d44733ba907b8b0dad3e321e9a4ce717c296827303cfe6634dac7339b85ea7BCF4AB6");

    }

}
