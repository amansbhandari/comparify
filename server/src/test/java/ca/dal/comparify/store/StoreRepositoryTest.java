package ca.dal.comparify.store;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * aman singh bhandari
 */
@SpringBootTest
@ActiveProfiles("test")
public class StoreRepositoryTest {

    @Autowired
    private StoreRepository storeRepository;

    @Test
    void getResponseMessageTestException()
    {
        assertEquals(storeRepository.getResponseMessage(-2),"Exception in mongodb");

    }

    @Test
    void getResponseMessageTest()
    {
        assertEquals(storeRepository.getResponseMessage(0),"success");

    }

    @Test
    void getResponseMessageTestNoCollection()
    {
        assertEquals(storeRepository.getResponseMessage(-1),"No collection with name store found");

    }


}
