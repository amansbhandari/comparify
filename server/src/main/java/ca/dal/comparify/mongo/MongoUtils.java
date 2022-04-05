package ca.dal.comparify.mongo;

import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Harsh Shah
 */
public class MongoUtils {

    private static final String DOLLAR_INC = "$inc";
    public static final String LOOKUP = "$lookup";

    private MongoUtils() {
    }

    /**
     * @param key
     * @param value
     * @return
     * @author Harsh Shah
     */
    public static Document inc(String key, Integer value) {
        return new Document(DOLLAR_INC, new Document(key, value));
    }

    /**
     * @param values
     * @return
     * @author Harsh Shah
     */
    public static Bson and(Tuple... values) {

        Document output = new Document();

        for (Tuple value : values) {
            output.append(value.getKey(), value.getValue());
        }

        return output;
    }

    /**
     * @param values
     * @return
     * @author Harsh Shah
     */
    public static Bson and(Bson... values) {
        return Filters.and(values);
    }

    /**
     * @param values
     * @return
     * @author Harsh Shah
     */
    public static Document inc(Bson... values) {
        return new Document(DOLLAR_INC, and(values));
    }

    /**
     * @param fieldName
     * @param value
     * @return
     * @author Harsh Shah
     */
    public static Bson set(final String fieldName, final Object value) {
        return Updates.set(fieldName, value);
    }

    /**
     * @param fieldName
     * @param value
     * @return
     * @author Harsh Shah
     */
    public static Bson eq(final String fieldName, final Object value) {
        return Filters.eq(fieldName, value);
    }

    /**
     * @param value
     * @return
     * @author Harsh Shah
     */
    public static Bson match(final Bson value) {
        return new Document("$match", value);
    }

    /**
     * @param from
     * @param localField
     * @param foreignField
     * @param as
     * @return
     * @author Harsh Shah
     */
    public static Bson lookup(final String from, final String localField,
                              final String foreignField, final String as) {

        return new Document(LOOKUP,
            new Document("from", from)
                .append("localField", localField)
                .append("foreignField", foreignField)
                .append("as", as));
    }

    /**
     * @param from
     * @param localField
     * @param foreignField
     * @param pipeline
     * @param as
     * @return
     * @author Harsh Shah
     */
    public static Bson lookup(final String from, final String localField,
                              final String foreignField, final List<Document> pipeline,
                              final String as) {

        return new Document(LOOKUP,
            new Document("from", from)
                .append("localField", localField)
                .append("foreignField", foreignField)
                .append("pipeline", pipeline)
                .append("as", as));
    }


    /**
     * @param from
     * @param let
     * @param pipeline
     * @param as
     * @return
     * @author Harsh Shah
     */
    public static Bson lookup(final String from, final Bson let,
                              final List<Bson> pipeline,
                              final String as) {

        return new Document(LOOKUP,
            new Document("from", from)
                .append("let", let)
                .append("pipeline", pipeline)
                .append("as", as));
    }

    /**
     * @param path
     * @return
     * @author Harsh Shah
     */
    public static Bson unwind(final String path) {
        return Aggregates.unwind(path);
    }

    /**
     * @param value
     * @return
     * @author Harsh Shah
     */
    public static Bson facet(final Document value) {
        return new Document("$facet", value);
    }

    /**
     * @param field
     * @param value
     * @return
     * @author Harsh Shah
     */
    public static Bson lte(final String field, final Object value) {
        return Filters.lte(field, value);
    }

    /**
     * @param fields
     * @return
     * @author Harsh Shah
     */
    public static Bson project(final String... fields) {
        return Aggregates.project(new Document(Arrays.stream(fields).collect(Collectors.toMap(field -> field, field -> 1))));
    }


    /**
     * @param projection
     * @return
     * @author Harsh Shah
     */
    public static Bson project(final Bson projection) {
        return Aggregates.project(projection);
    }


    /**
     * @param endDate
     * @param unit
     * @return
     * @author Harsh Shah
     */
    public static Bson dateDiffWithNow(final String endDate, final String unit) {

        return new Document("$dateDiff",
            new Document("startDate", "$$NOW")
                .append("endDate", endDate)
                .append("unit", unit));

    }

    /**
     * @param fields
     * @return
     * @author Harsh Shah
     */
    public static Bson sortAscending(final String... fields) {
        return new Document("$sort",
            new Document(Arrays.stream(fields).collect(Collectors.toMap(field -> field, field -> 1))));
    }


    /**
     * @param document
     * @return
     * @author Harsh Shah
     */
    public static Bson group(final Bson document) {
        return new Document("$group", document);
    }

    /**
     * @param input
     * @param as
     * @param in
     * @return
     * @author Harsh Shah
     */
    public static Bson map(final String input, final String as, final Bson in) {
        return new Document("$map",
            new Document("input", input)
                .append("as", as)
                .append("in", in));
    }

    /**
     * @param value
     * @return
     * @author Harsh Shah
     */
    public static Bson first(final String value) {
        return new Document("$first", value);
    }
}
