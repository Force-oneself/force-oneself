package com.quan.framework.mongo.helper;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Component;

/**
 * @author Force-oneself
 * @description BsonCollectionHandler
 * @date 2022-01-25
 */
@Component
public class BsonCollectionHandler implements CollectionHandler{

    @Override
    public boolean support(CollectionHolder<?> holder) {
        return holder.methodOptions().isExist(Bson.class);
    }

    @Override
    public void handle(CollectionHolder<?> holder) {
        Bson filter = holder.methodOptions().find(Bson.class);
        if (filter instanceof Document) {
            Document document = (Document) filter;

            document.append("tenantId", "000000");
            System.out.println(document);
        }
    }
}
