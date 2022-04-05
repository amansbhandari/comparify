package ca.dal.comparify.mongo;

import ca.dal.comparify.model.HashModel;
import ca.dal.comparify.utils.SecurityUtils;
import ca.dal.comparify.utils.UUIDUtils;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.print.Doc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static ca.dal.comparify.constant.ApplicationConstant.EMPTY_STRING;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.springframework.http.HttpStatus.OK;

@ContextConfiguration(classes = { MongoRepository.class, MongoConfig.class})
@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MongoRepositoryTest {

    @Autowired
    private MongoRepository mongoRepository;

    private String collectionName;

    @BeforeAll
    void setUpForTestSuite() {

        collectionName = UUIDUtils.generate();

        List<Document> dummy = new ArrayList<>();
        dummy.add(new Document("name", "John"));
        dummy.add(new Document("name", "Herry"));
        dummy.add(new Document("name", "David"));

        mongoRepository.insertMany(collectionName, dummy, Document.class);
    }

    @AfterAll
    void tearDownTestSuite() {
        mongoRepository.dropCollection(collectionName);
    }


    public static Stream<Arguments> testFindDatasource() {

        return Stream.of(
            Arguments.of(new Document(), Document.class, 3),
            Arguments.of(new Document("name", "Herry"), Document.class, 1),
            Arguments.of(new Document("name", "Herry"), null, 0));
    }

    /**
     * @param query
     * @param tClass
     * @param expected
     * @param <T>
     * @author Harsh Shah
     */
    @ParameterizedTest(name = "{index}: testFind() = {1}")
    @MethodSource("testFindDatasource")
    <T> void testFind(Document query, Class<T> tClass, int expected) {
        assertEquals(expected,
            mongoRepository.find(collectionName, query, tClass).size());
    }

}

