package is.swan.gameapi;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@SpringBootApplication
public class GameAPI {

    private static JSONArray games;
    private static JSONArray simplifiedGames = new JSONArray();

    public static void main(String[] args) {
        SpringApplication.run(GameAPI.class, args);
    }

    public GameAPI() {
        loadGames();

        for (Object object : GameAPI.getGames()) {
            JSONObject gameObject = (JSONObject) object;
            JSONObject newGameObject = new JSONObject();
            newGameObject.put("name", gameObject.getString("name"));
            newGameObject.put("image", gameObject.getString("image"));
            simplifiedGames.put(newGameObject);
        }
    }

    private void loadGames() {
        if (!new File(Paths.get("games.json").toAbsolutePath().toString()).exists()) return;

        String content = "";

        try {
            for (String line : Files.lines(Paths.get("games.json")).collect(Collectors.toList())) {
                content += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = new JSONObject(content);
        JSONArray gamesArray = jsonObject.getJSONArray("games");

        games = gamesArray;
    }

    public static JSONArray getGames() {
        return games;
    }

    public static JSONArray getSimplifiedGames() {
        return simplifiedGames;
    }
}
