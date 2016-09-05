package projectf.cormag.saves;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import projectf.cormag.gfx.Assets;
import projectf.cormag.main.Game;
import projectf.cormag.main.Handler;

public class SaveGameManager {

	private final static String SAVE_FILE_EXTENSION = ".pfsave";

	private SaveGameManager() {
	}

	public static SavedGame loadSavedGame(String name) {

		SavedGame savedGame = null;
		ObjectInputStream ois = null;

		try {
			ois = new ObjectInputStream(
					new FileInputStream(Assets.class.getResource("/saves/").getPath() + name + SAVE_FILE_EXTENSION));
			savedGame = (SavedGame) ois.readObject();
		} catch (ClassNotFoundException | IOException e) {
			System.err.println("Error while loading gameState: " + name);
			System.err.println(e);
			e.printStackTrace();
		} finally {

			if (ois != null) {
				try {
					ois.close();

				} catch (IOException e) {
					System.err.println("Error while closing FileInputStream");
					System.err.println(e);
				}
			}

		}

		return savedGame;
	}

	public static void saveGame(String name, Game game, Handler handler) {

		ObjectOutputStream oos = null;

		try {
			oos = new ObjectOutputStream(
					new FileOutputStream(Assets.class.getResource("/saves/").getPath() + name + SAVE_FILE_EXTENSION));
			oos.writeObject(new SavedGame(game, handler));
		} catch (IOException e) {

			System.err.println("Error while saving gameState: " + name);
			System.err.println(e);
			e.printStackTrace();

		} finally {

			try {
				if (oos != null) {
					oos.close();
				}
			} catch (IOException e) {
				System.err.println("Errow closing Stream");
				System.err.println(e);
			}

		}

	}

}
