package sg.edu.nus.iss.server.repo;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class GameRepo {
    @Autowired
    MongoTemplate template;

    public List<Document> getGames(Integer limit, Integer offset){
        Criteria criteria = Criteria.where("name").exists(true);
        Query query = Query.query(criteria).with(Sort.by(Sort.Direction.ASC, "name")).skip(offset).limit(limit);
        List<Document> list = template.find(query, Document.class, "game");
        return list;
    }

    public List<Document> getGamesByRank(Integer limit, Integer offset){
        Criteria criteria = Criteria.where("ranking").exists(true);
        Query query = Query.query(criteria).with(Sort.by(Sort.Direction.ASC, "ranking")).skip(offset).limit(limit);
        List<Document> list = template.find(query,Document.class, "game");
        return list;
    }

    public Document getGameById(Integer id){
        Criteria criteria = Criteria.where("gid").is(id);
        Query query = Query.query(criteria);
        List<Document> list = template.find(query,Document.class,"game");
        if(list.size() == 0){
            return null;
        }        
        return list.get(0);
    }
    
}
