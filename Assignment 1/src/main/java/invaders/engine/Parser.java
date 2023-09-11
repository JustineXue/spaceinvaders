package invaders.engine;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Parser{

    private String config;

    public Parser(String config){
        this.config = config;
    }

    public void parse() {
        JSONParser parser = new JSONParser();

        try {
            // Read the JSON file
            FileReader reader = new FileReader(this.config); // Replace with your JSON file path
            Object obj = parser.parse(reader);

            // Convert the top-level JSON object
            JSONObject jsonObject = (JSONObject) obj;

            // Access nested elements
            JSONObject gameObject = (JSONObject) jsonObject.get("Game");
            JSONObject sizeObject = (JSONObject) gameObject.get("size");
            int gameSizeX = Math.toIntExact((Long) sizeObject.get("x"));
            int gameSizeY = Math.toIntExact((Long) sizeObject.get("y"));

            JSONObject playerObject = (JSONObject) jsonObject.get("Player");
            String playerColor = (String) playerObject.get("colour");
            int playerSpeed = Math.toIntExact((Long) playerObject.get("speed"));
            int playerLives = Math.toIntExact((Long) playerObject.get("lives"));
            JSONObject playerPosition = (JSONObject) playerObject.get("position");
            int playerPosX = Math.toIntExact((Long) playerPosition.get("x"));
            int playerPosY = Math.toIntExact((Long) playerPosition.get("y"));

            System.out.println("Game Size X: " + gameSizeX);
            System.out.println("Game Size Y: " + gameSizeY);
            System.out.println("Player Color: " + playerColor);
            System.out.println("Player Speed: " + playerSpeed);
            System.out.println("Player Lives: " + playerLives);
            System.out.println("Player Position X: " + playerPosX);
            System.out.println("Player Position Y: " + playerPosY);

            // Access Bunkers and Enemies arrays
            JSONArray bunkersArray = (JSONArray) jsonObject.get("Bunkers");
            for (Object bunkerObj : bunkersArray) {
                JSONObject bunker = (JSONObject) bunkerObj;
                JSONObject bunkerPosition = (JSONObject) bunker.get("position");
                int bunkerPosX = Math.toIntExact((Long) bunkerPosition.get("x"));
                int bunkerPosY = Math.toIntExact((Long) bunkerPosition.get("y"));
                JSONObject bunkerSize = (JSONObject) bunker.get("size");
                int bunkerSizeX = Math.toIntExact((Long) bunkerSize.get("x"));
                int bunkerSizeY = Math.toIntExact((Long) bunkerSize.get("y"));

                System.out.println("Bunker Position X: " + bunkerPosX);
                System.out.println("Bunker Position Y: " + bunkerPosY);
                System.out.println("Bunker Size X: " + bunkerSizeX);
                System.out.println("Bunker Size Y: " + bunkerSizeY);
            }

            JSONArray enemiesArray = (JSONArray) jsonObject.get("Enemies");
            for (Object enemyObj : enemiesArray) {
                JSONObject enemy = (JSONObject) enemyObj;
                JSONObject enemyPosition = (JSONObject) enemy.get("position");
                int enemyPosX = Math.toIntExact((Long) enemyPosition.get("x"));
                int enemyPosY = Math.toIntExact((Long) enemyPosition.get("y"));
                String enemyProjectile = (String) enemy.get("projectile");

                System.out.println("Enemy Position X: " + enemyPosX);
                System.out.println("Enemy Position Y: " + enemyPosY);
                System.out.println("Enemy Projectile: " + enemyProjectile);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

}