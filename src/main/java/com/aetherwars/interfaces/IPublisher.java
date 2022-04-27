package com.aetherwars.interfaces;

import java.util.List;
import java.util.Map;

public interface IPublisher {
    public void publish(String topic, IEvent event);
}
