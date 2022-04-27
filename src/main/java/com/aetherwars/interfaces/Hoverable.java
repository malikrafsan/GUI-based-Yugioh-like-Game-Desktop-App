package com.aetherwars.interfaces;

import java.util.List;

import javafx.util.Pair;

public interface Hoverable {
    List<Pair<String, String>> displayInfo();
    String getName();
    String getDesc();
    String getImagePath();
}
