package ca.dal.comparify.mongo;

import org.bson.Document;
import org.bson.conversions.Bson;

/**
 * @author Harsh Shah
 */
public class PaginationOptions {

    private Bson sort;

    public PaginationOptions(Bson sort) {
        this.sort = sort;
    }

    public Bson getSort() {
        return sort == null ? new Document() : sort;
    }

    public void setSort(Bson sort) {
        this.sort = sort;
    }
}
