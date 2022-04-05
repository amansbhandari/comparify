package ca.dal.comparify.mongo;

import ca.dal.comparify.utils.UUIDUtils;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static ca.dal.comparify.mongo.MongoUtils.*;
import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {MongoRepository.class, MongoConfig.class})
@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MongoRepositoryTest {

    private static String collectionName;
    private static String indexCollectionName;

    @Autowired
    private MongoRepository mongoRepository;

    public static Stream<Arguments> testFindDatasource() {
        return Stream.of(
            Arguments.of(new Document(), Document.class, 3),
            Arguments.of(new Document("name", "John"), Document.class, 1),
            Arguments.of(new Document("name", "John"), null, 0));
    }

    public static Stream<Arguments> testFindWithPaginationDatasource() {
        return Stream.of(
            Arguments.of(new Document("name", "John"),
                new PaginationOptions(new Document("name", 1)), Document.class, 1),
            Arguments.of(new Document("name", "John"), null, null, 0));
    }

    public static Stream<Arguments> testFindWithProjectionDatasource() {
        return Stream.of(
            Arguments.of(new Document(), new Document(), Document.class, 3),
            Arguments.of(new Document("name", "John"), new Document("name", 1), Document.class, 1),
            Arguments.of(new Document("name", "John"), null, null, 0),
            Arguments.of(new Document("name", "John"), null, Document.class, 1));
    }

    public static Stream<Arguments> testFindWithProjectionAndPaginationDatasource() {
        return Stream.of(
            Arguments.of(new Document(), new Document(),
                new PaginationOptions(new Document()), Document.class, 3),
            Arguments.of(new Document("name", "John"), new Document("name", 1),
                new PaginationOptions(new Document("name", 1)), Document.class, 1),
            Arguments.of(new Document("name", "John"), null, null, null, 0),
            Arguments.of(new Document("name", "John"), null,
                new PaginationOptions(new Document()), Document.class, 1));
    }

    public static Stream<Arguments> testFindOneDatasource() {
        return Stream.of(
            Arguments.of(new Document(), Document.class, new Document("name", "John")),
            Arguments.of(new Document(), null, null)
        );
    }

    public static Stream<Arguments> testFindOneWithPaginationDatasource() {
        return Stream.of(
            Arguments.of(new Document(), new PaginationOptions(new Document()),
                Document.class, new Document("name", "John")),
            Arguments.of(new Document(), new PaginationOptions(new Document()),
                null, null)
        );
    }

    public static Stream<Arguments> testFindOneWithProjectionDatasource() {
        return Stream.of(
            Arguments.of(new Document(), new Document("_id", 0),
                Document.class, new Document("name", "John")),
            Arguments.of(new Document(), null, null, null),
            Arguments.of(new Document(), null, Document.class, new Document("name", "John"))
        );
    }

    public static Stream<Arguments> testFindOneWithProjectionAndPaginationDatasource() {
        return Stream.of(
            Arguments.of(new Document(), new Document("_id", 0),
                new PaginationOptions(new Document()), Document.class, new Document("name", "John")),
            Arguments.of(new Document("name", "John"), new Document("_id", 0),
                new PaginationOptions(new Document("name", 1)), Document.class, new Document("name", "Herry")),
            Arguments.of(new Document("name", "John"), null, null, null, null),
            Arguments.of(new Document("name", "John"), null,
                new PaginationOptions(new Document()), Document.class, new Document("name", "Herry")));
    }

    public static Stream<Arguments> testInsertOneDatasource() {
        return Stream.of(
            Arguments.of(collectionName, new Document("name", "Maddy"), Document.class, 0),
            Arguments.of(collectionName, new Document("name", "Maddy"), null, -1),
            Arguments.of(collectionName, new Document("name", "Maddy"), Document.class, -2));
    }

    public static Stream<Arguments> testInsertManyDatasource() {

        List<Document> data = Arrays.asList(new Document("name", "Mike"));

        return Stream.of(
            Arguments.of(collectionName, data, Document.class, 0),
            Arguments.of(collectionName, data, null, -1),
            Arguments.of(collectionName, data, Document.class, -2));
    }

    public static Stream<Arguments> testCountDatasource() {
        return Stream.of(
            Arguments.of(collectionName, new Document("name", "John"), 1),
            Arguments.of(null, new Document("name", "John"), -1));
    }

    public static Stream<Arguments> testUpdateOneDatasource() {
        return Stream.of(
            Arguments.of(collectionName, new Document("name", "Herry"),
                new Bson[]{set("name", "Harry")}, true),
            Arguments.of(collectionName, new Document("name", "Herry"),
                new Bson[]{}, false),
            Arguments.of(null, new Document("name", "Herry"),
                new Bson[]{set("name", "Harry")}, false));
    }

    public static Stream<Arguments> testDeleteOneDatasource() {
        return Stream.of(
            Arguments.of(collectionName, new Document("name", "Herry"), true),
            Arguments.of(null, new Document("name", "Herry"), false));
    }

    public static Stream<Arguments> testAggregateDatasource() {

        List<Bson> pipeline = Arrays.asList(match(new Document("name", "John")));
        return Stream.of(
            Arguments.of(collectionName, pipeline,
                Document.class, 1),
            Arguments.of(null, pipeline,
                Document.class, 0));
    }

    public static Stream<Arguments> testAggregateOneDatasource() {

        List<Bson> pipeline = Arrays.asList(
            facet(new Document("user", Arrays.asList(match(new Document("name", "John"))))));

        return Stream.of(
            Arguments.of(collectionName, pipeline,
                Document.class, true),
            Arguments.of(null, pipeline,
                Document.class, false));
    }

    public static Stream<Arguments> testDropCollectionDatasource() {

        return Stream.of(
            Arguments.of("collectionName", true),
            Arguments.of(null, false));
    }

    @BeforeAll
    void setUpForTestSuite() {

        collectionName = UUIDUtils.generate();
        indexCollectionName = UUIDUtils.generate();

        mongoRepository.createIndex(collectionName, new Document("name", 1), true);

        List<Document> dummy = new ArrayList<>();
        dummy.add(new Document("name", "John"));
        dummy.add(new Document("name", "Herry"));
        dummy.add(new Document("name", "David"));

        mongoRepository.insertMany(collectionName, dummy, Document.class);
    }

    @AfterAll
    void tearDownTestSuite() {
        mongoRepository.dropCollection(collectionName);
        mongoRepository.dropCollection(indexCollectionName);
    }

    /**
     * @param query
     * @param tClass
     * @param expected
     * @param <T>
     * @author Harsh Shah
     */
    @ParameterizedTest(name = "{index}: testFind() = {0}")
    @MethodSource("testFindDatasource")
    <T> void testFind(Document query, Class<T> tClass, int expected) {
        assertEquals(expected,
            mongoRepository.find(collectionName, query, tClass).size());
    }

    /**
     * @param query
     * @param options
     * @param tClass
     * @param expected
     * @param <T>
     * @author Harsh Shah
     */
    @ParameterizedTest(name = "{index}: testFindWithPagination() = {0}")
    @MethodSource("testFindWithPaginationDatasource")
    <T> void testFindWithPagination(Document query, PaginationOptions options, Class<T> tClass, int expected) {
        assertEquals(expected,
            mongoRepository.find(collectionName, query, options, tClass).size());
    }

    /**
     * @param query
     * @param projection
     * @param tClass
     * @param expected
     * @param <T>
     * @author Harsh Shah
     */
    @ParameterizedTest(name = "{index}: testFindWithProjection() = {0}")
    @MethodSource("testFindWithProjectionDatasource")
    <T> void testFindWithProjection(Document query, Document projection, Class<T> tClass, int expected) {
        assertEquals(expected,
            mongoRepository.find(collectionName, query, projection, tClass).size());
    }

    /**
     * @param query
     * @param projection
     * @param options
     * @param tClass
     * @param expected
     * @param <T>
     * @author Harsh Shah
     */
    @ParameterizedTest(name = "{index}: testFindWithProjectionAndPagination() = {0}")
    @MethodSource("testFindWithProjectionAndPaginationDatasource")
    <T> void testFindWithProjectionAndPagination(Document query, Document projection,
                                                 PaginationOptions options,
                                                 Class<T> tClass, int expected) {
        assertEquals(expected,
            mongoRepository.find(collectionName, query, projection, options, tClass).size());
    }

    /**
     * @param query
     * @param tClass
     * @param expected
     * @param <T>
     * @author Harsh Shah
     */
    @ParameterizedTest(name = "{index}: testFindOne() = {0}")
    @MethodSource("testFindOneDatasource")
    <T> void testFindOne(Document query, Class<T> tClass, Object expected) {
        if (null == expected) {
            assertNull(mongoRepository.findOne(collectionName, query, tClass));
        } else {
            assertNotNull(mongoRepository.findOne(collectionName, query, tClass));
        }
    }

    /**
     * @param query
     * @param options
     * @param tClass
     * @param expected
     * @param <T>
     * @author Harsh Shah
     */
    @ParameterizedTest(name = "{index}: testFindOneWithPagination() = {0}")
    @MethodSource("testFindOneWithPaginationDatasource")
    <T> void testFindOneWithPagination(Document query, PaginationOptions options,
                                       Class<T> tClass, Object expected) {
        if (null == expected) {
            assertNull(mongoRepository.findOne(collectionName, query, options, tClass));
        } else {
            assertNotNull(mongoRepository.findOne(collectionName, query, options, tClass));
        }
    }

    /**
     * @param query
     * @param projection
     * @param tClass
     * @param expected
     * @param <T>
     * @author Harsh Shah
     */
    @ParameterizedTest(name = "{index}: testFindOneWithProjection() = {0}")
    @MethodSource("testFindOneWithProjectionDatasource")
    <T> void testFindOneWithProjection(Document query, Document projection,
                                       Class<T> tClass, Object expected) {
        if (null == expected) {
            assertNull(mongoRepository.findOne(collectionName, query, projection, tClass));
        } else {
            assertNotNull(mongoRepository.findOne(collectionName, query, projection, tClass));
        }
    }

    /**
     * @param query
     * @param projection
     * @param options
     * @param tClass
     * @param expected
     * @param <T>
     * @author Harsh Shah
     */
    @ParameterizedTest(name = "{index}: testFindOneWithProjectionAndPagination() = {0}")
    @MethodSource("testFindOneWithProjectionAndPaginationDatasource")
    <T> void testFindOneWithProjectionAndPagination(Document query, Document projection,
                                                    PaginationOptions options, Class<T> tClass, Object expected) {
        if (null == expected) {
            assertNull(mongoRepository.findOne(collectionName, query, projection, options, tClass));
        } else {
            assertNotNull(mongoRepository.findOne(collectionName, query, projection, options, tClass));
        }
    }

    /**
     * @param object
     * @param tClass
     * @param expected
     * @param <T>
     * @author Harsh Shah
     */
    @ParameterizedTest(name = "{index}: testInsertOne() = {3}")
    @MethodSource("testInsertOneDatasource")
    <T> void testInsertOne(String collection, T object, Class<T> tClass, int expected) {
        assertEquals(expected, mongoRepository.insertOne(collection, object, tClass));
    }

    @ParameterizedTest(name = "{index}: testInsertMany() = {3}")
    @MethodSource("testInsertManyDatasource")
    <T> void testInsertMany(String collection, List<T> objects, Class<T> tClass, int expected) {
        assertEquals(expected, mongoRepository.insertMany(collection, objects, tClass));
    }

    /**
     * @param collection
     * @param query
     * @param expected
     * @param <T>
     * @author Harsh Shah
     */
    @ParameterizedTest(name = "{index}: testCount() = {1}")
    @MethodSource("testCountDatasource")
    <T> void testCount(String collection, Document query, int expected) {
        assertEquals(expected, mongoRepository.count(collection, query));
    }

    @ParameterizedTest(name = "{index}: testUpdateOne() = {1}")
    @MethodSource("testUpdateOneDatasource")
    <T> void testUpdateOne(String collection, Document query, Bson[] values, boolean expected) {
        assertEquals(expected, mongoRepository.updateOne(collection, query, values));
    }

    /**
     * @param collection
     * @param query
     * @param expected
     * @param <T>
     * @author Harsh Shah
     */
    @ParameterizedTest(name = "{index}: testDeleteOne() = {1}")
    @MethodSource("testDeleteOneDatasource")
    <T> void testDeleteOne(String collection, Document query, boolean expected) {
        assertEquals(expected, mongoRepository.deleteOne(collection, query));
    }

    /**
     * @param collection
     * @param pipeline
     * @param tClass
     * @param expected
     * @param <T>
     * @author Harsh Shah
     */
    @ParameterizedTest(name = "{index}: testAggregate() = {3}")
    @MethodSource("testAggregateDatasource")
    <T> void testAggregate(String collection, List<Bson> pipeline, Class<T> tClass, int expected) {
        assertEquals(expected, mongoRepository.aggregate(collection, pipeline, tClass).size());
    }

    /**
     * @param collection
     * @param pipeline
     * @param tClass
     * @param expected
     * @param <T>
     * @author Harsh Shah
     */
    @ParameterizedTest(name = "{index}: testAggregateOne() = {3}")
    @MethodSource("testAggregateOneDatasource")
    <T> void testAggregateOne(String collection, List<Bson> pipeline, Class<T> tClass, boolean expected) {
        if (expected) {
            assertNotNull(mongoRepository.aggregateOne(collection, pipeline, tClass));
        } else {
            assertNull(mongoRepository.aggregateOne(collection, pipeline, tClass));
        }
    }

    /**
     * @param collection
     * @param expected
     * @param <T>
     * @author Harsh Shah
     */
    @ParameterizedTest(name = "{index}: testDropCollection() = {1}")
    @MethodSource("testDropCollectionDatasource")
    <T> void testDropCollection(String collection, boolean expected) {
        assertEquals(expected, mongoRepository.dropCollection(collection));
    }

    public static Stream<Arguments> testCreateIndexDatasource() {
        return Stream.of(
            Arguments.of(indexCollectionName, new Document("name", 1), true),
            Arguments.of(null, new Document("name", "1"), false));
    }

    @ParameterizedTest(name = "{index}: testDropCollection() = {1}")
    @MethodSource("testCreateIndexDatasource")
    <T> void testCreateIndex(String collection, Document index, boolean expected) {
        if(expected){
            assertNotNull( mongoRepository.createIndex(collection, index, true));
        } else {
            assertNull( mongoRepository.createIndex(collection, index, true));
        }
    }

}

