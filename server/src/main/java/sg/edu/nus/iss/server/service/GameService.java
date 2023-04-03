package sg.edu.nus.iss.server.service;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.server.model.Game;
import sg.edu.nus.iss.server.repo.GameRepo;

@Service
public class GameService {

    @Autowired
    GameRepo gameRepo;

    public List<Game> getGames(Integer limit, Integer offset){
        List<Game> list = gameRepo.getGames(limit, offset).stream().map(g -> Game.createSummary(g)).toList();
        return list;

    }

    public List<Game> getGamesByRank(Integer limit, Integer offset){
        List<Game> list = gameRepo.getGamesByRank(limit, offset).stream().map(g -> Game.createSummary(g)).toList();
        return list;
    }

    public Game getGameById(Integer id){
        Document d = gameRepo.getGameById(id);
        if(null==d){
            return null;
        }
        Game g = Game.createSummary(d);
        return g;
    }
}
