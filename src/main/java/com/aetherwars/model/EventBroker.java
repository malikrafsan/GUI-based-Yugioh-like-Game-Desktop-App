package com.aetherwars.model;

import java.util.*;

import com.aetherwars.interfaces.IEvent;
import com.aetherwars.interfaces.ISubscriber;

public class EventBroker {
  Map<String, ArrayList<ISubscriber>> topics;

  public EventBroker() {
    this.topics = new HashMap<String, ArrayList<ISubscriber>>();
  }

  public void sendEvent(String topic, IEvent event) {
    ArrayList<ISubscriber> subscribers = this.topics.get(topic);

    for (ISubscriber subscriber : subscribers) {
      subscriber.onEvent(event);
    }
  }

  public void addSubscriber(String topic, ISubscriber subscriber) {
    ArrayList<ISubscriber> subscribers = this.topics.get(topic);

    if (subscribers == null) {
      subscribers = new ArrayList<>();
      this.topics.put(topic, subscribers);
    }

    subscribers.add(subscriber);
  }
}
