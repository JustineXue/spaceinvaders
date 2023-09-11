import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ConfigReader {

	/**
	 * You will probably not want to use a static method/class for this.
	 * 
	 * This is just an example of how to access different parts of the json
	 * 
	 * @param path The path of the json file to read
	 */
	public static void parse(String path) {

		JSONParser parser = new JSONParser();
		try {
			Object object = parser.parse(new FileReader(path));

			// convert Object to JSONObject
			JSONObject jsonObject = (JSONObject) object;

			// reading the Game section:
			JSONObject jsonGame = (JSONObject) jsonObject.get("Game");

			// reading a coordinate from the nested section within the game
			// note that the game x and y are of type Long (i.e. they are integers)
			Long gameX = (Long) ((JSONObject) jsonGame.get("size")).get("x");
			// TODO: Long gameY =

			// TODO: delete me, this is just a demonstration:
			System.out.println("Game details: x: " + gameX);

			// reading the "Enemies" array:
			JSONArray jsonEnemies = (JSONArray) jsonObject.get("Enemies");

			// reading from the array:
			for (Object obj : jsonEnemies) {
				JSONObject jsonEnemy = (JSONObject) obj;
				
				// the enemy position is a double
				Double positionX = (Double) ((JSONObject) jsonEnemy.get("position")).get("x");
				// TODO: Double positionY =

				String projectileStrategy = (Double) jsonEnemy.get("projectile");

				// TODO: delete me, this is just a demonstration:
				System.out.println("Enemey x: " + positionX + ", projectile: " + projectileStrategy);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Your main method will probably be in another file!
	 * 
	 * @param args First argument is the path to the config file
	 */
	public static void main(String[] args) {
		// if a command line argument is provided, that should be used as the path
		// if not, you can hard-code a default. e.g. "src/main/resources/config.json"
		// this makes it easier to test your program with different config files
		String configPath;
		if (args.length > 0) {
			configPath = args[0];
		} else {
			configPath = "src/main/resources/config.json";
		}
		// parse the file:
		ConfigReader.parse(configPath);
	}

}
