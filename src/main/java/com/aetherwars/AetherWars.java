package com.aetherwars;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import com.aetherwars.model.Type;
import com.aetherwars.model.Character;
import com.aetherwars.util.CSVReader;

public class AetherWars extends Application {
  private static final String CHARACTER_CSV_FILE_PATH = "card/data/character.csv";

  public void loadCards() throws IOException, URISyntaxException {
    File characterCSVFile = new File(getClass().getResource(CHARACTER_CSV_FILE_PATH).toURI());
    CSVReader characterReader = new CSVReader(characterCSVFile, "\t");
    characterReader.setSkipHeader(true);
    List<String[]> characterRows = characterReader.read();
    for (String[] row : characterRows) {
      Character c = new Character(row[1], row[3], Type.valueOf(row[2]));
      System.out.println(c);
    }
  }

  @Override
  public void start(Stage stage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("/view/App.fxml"));
    stage.setScene(new Scene(root));
    stage.show();

    this.loadCards();

  }

  public static void main(String[] args) {
    launch();
  }
}
