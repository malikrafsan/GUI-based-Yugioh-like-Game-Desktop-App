package com.aetherwars.event;

import com.aetherwars.interfaces.IEvent;
import com.aetherwars.model.Card;

import java.util.List;

public class PickCardEvent implements IEvent {
    List<Card> data;

    public PickCardEvent(List<Card> data) {
        this.data = data;
    }

    public List<Card> getData() {
        return this.data;
    }
}
