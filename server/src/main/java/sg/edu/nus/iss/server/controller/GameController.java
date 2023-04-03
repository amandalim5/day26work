package sg.edu.nus.iss.server.controller;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import sg.edu.nus.iss.server.model.Game;
import sg.edu.nus.iss.server.service.GameService;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class GameController {
    @Autowired
    GameService gameService;

    @GetMapping(path = "/game")
    @ResponseBody
    public ResponseEntity<String> getGames(@RequestParam(name = "limit", required = false) String limit, @RequestParam(name = "offset", required = false)String offset){
        Integer theLimit = 25;
        Integer theOffset = 0;
        System.out.println("executing getGames");
        if(null != limit){
            theLimit = Integer.parseInt(limit);
            System.out.println("set up the limit: " + theLimit);
        }
        if(null != offset){
            theOffset = Integer.parseInt(offset);
            System.out.println("set up the offset: " + theOffset);
        }
        List<Game> list = gameService.getGames(theLimit, theOffset);
        JsonObjectBuilder responseBuilder = Json.createObjectBuilder();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for(Game g:list){
            JsonObjectBuilder singleObjectBuilder = Json.createObjectBuilder();
            singleObjectBuilder.add("game_id", g.getGid()).add("name", g.getName());
            arrayBuilder.add(singleObjectBuilder);
        }
        responseBuilder.add("games", arrayBuilder);
        responseBuilder.add("offset", theOffset).add("limit", theLimit).add("total", list.size()).add("timestamp", DateTime.now().toString());
        JsonObject result = responseBuilder.build();

        return ResponseEntity.ok(result.toString());

    }

    @GetMapping(path = "/games/rank")
    @ResponseBody
    public ResponseEntity<String> getGamesByRank(@RequestParam(name = "limit", required = false) String limit, @RequestParam(name = "offset", required = false) String offset){
        Integer theLimit = 25;
        Integer theOffset = 0;
        if(null != limit){
            theLimit = Integer.parseInt(limit);
        }
        if(null != offset){
            theOffset = Integer.parseInt(offset);
        }
        List<Game> list = gameService.getGamesByRank(theLimit, theOffset);
        JsonObjectBuilder responseBuilder = Json.createObjectBuilder();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for(Game g: list){
            JsonObjectBuilder singleObjectBuilder = Json.createObjectBuilder();
            singleObjectBuilder.add("game_id", g.getGid()).add("name", g.getName()).add("ranking", g.getRanking());
            arrayBuilder.add(singleObjectBuilder);
        }
        responseBuilder.add("games", arrayBuilder);
        responseBuilder.add("offset", theOffset).add("limit", theLimit).add("total", list.size()).add("timestamp", DateTime.now().toString());
        JsonObject result = responseBuilder.build();

        return ResponseEntity.ok(result.toString());
    }

    @GetMapping(path = "/game/{id}")
    @ResponseBody
    public ResponseEntity<String> getGameById(@PathVariable String id){
        Integer theId = Integer.parseInt(id);
        Game g = gameService.getGameById(theId);
        if(null == g){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("too bad");
        }
        JsonObjectBuilder responseBuilder = Json.createObjectBuilder();
        responseBuilder
            .add("game_id", g.getGid())
            .add("name", g.getName())
            .add("year", g.getYear())
            .add("ranking", g.getRanking())
            .add("users_rated", g.getUsers_rated())
            .add("url", g.getUrl())
            .add("image", g.getImage())
            .add("timestamp", DateTime.now().toString());
        JsonObject result = responseBuilder.build();
        return ResponseEntity.ok(result.toString());
    }
     
}
