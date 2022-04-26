package com.aetherwars.model;

import javafx.util.Pair;

import java.util.List;

public abstract class Card implements Hoverable {
  protected int id;
  protected String name;
  protected String description;
  protected String image_path;
  protected int mana;

  public Card() {
    this.id = 0;
    this.name = "";
    this.description = "";
    this.image_path = "card/image/unknown.png";
    this.mana = 0;
  }

  public Card(int id, String name, String description, String image_path, int mana) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.image_path = image_path;
    this.mana = mana;
  }

  public String getName(){
    return this.name;
  }

  public int getMana() {
    return this.mana;
  }

  abstract public String preview();

  abstract public String toString();

  abstract public List<Pair<String,String>> displayInfo();
}
