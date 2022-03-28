package ca.dal.comparify.mongo;

import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.List;

/**
 * @author Harsh Shah
 */
public class MongoUtils {

    private static final String DOLLAR_INC = "$inc";

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
    public static Bson match(final Document value) {
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

        return new Document("$lookup",
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

        return new Document("$lookup",
            new Document("from", from)
                .append("localField", localField)
                .append("foreignField", foreignField)
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


}
