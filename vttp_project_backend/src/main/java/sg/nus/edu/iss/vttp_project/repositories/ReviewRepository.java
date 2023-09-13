package sg.nus.edu.iss.vttp_project.repositories;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.client.result.UpdateResult;

public class ReviewRepository {

    //Refer to Workshop 27

      @Autowired
    private MongoTemplate mongoTemplate;

    public Boolean checkIfTaskExists(Integer Id) {
        return mongoTemplate.exists(Query.query(Criteria.where("taskId").is(Id)), "tasks");
    }

    public Document addReviewToDatabase(Document reviewDocument) {
        return mongoTemplate.insert(reviewDocument, "reviews");
    }

    public String getTaskNameById(Integer Id) {
        return mongoTemplate.findOne(Query.query(Criteria.where("taskId").is(Id)), Document.class, "tasks").getString("name");
    }

    public Boolean checkIfReviewExistsById(String reviewId) { //refers to object id
        return mongoTemplate.exists(Query.query(Criteria.where("_id").is(reviewId)), "reviews");
    }

    public Boolean addUpdateToReview(Document update, String reviewId) {
        Document retrievedDoc = mongoTemplate.findOne(Query.query(Criteria.where("_id").is(reviewId)), Document.class,
                "reviews");

        if (retrievedDoc.getList("edited", Document.class) == null) {
            List<Document> editedList = new ArrayList<>();
            editedList.add(update);
            retrievedDoc.append("edited", editedList);
        } else {
            List<Document> editedList = retrievedDoc.getList("edited", Document.class);
            editedList.add(update);
            retrievedDoc.append("edited", editedList);
        }

        UpdateResult result = mongoTemplate.upsert(Query.query(Criteria.where("_id").is(reviewId)),
                Update.fromDocument(retrievedDoc), "reviews");

        return result.wasAcknowledged();
    }

    public Document getReviewById(String reviewId) {
        return mongoTemplate.findOne(Query.query(Criteria.where("_id").is(reviewId)), Document.class, "reviews");
    }

    
}
