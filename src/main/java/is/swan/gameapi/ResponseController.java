package is.swan.gameapi;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResponseController {

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/api/games")
    public String returnGames() {
        return GameAPI.getSimplifiedGames().toString();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/api/game/{game}")
    public String returnGame(@PathVariable("game") String game) {
        String gameString = "{\"error\":\"No such game\"}";

        for (Object object : GameAPI.getGames()) {
            JSONObject gameObject = (JSONObject) object;

            if (gameObject.getString("name").toLowerCase().equals(game.toLowerCase())) {
                gameString = gameObject.toString();
                break;
            }
        }

        return gameString;
    }
}
