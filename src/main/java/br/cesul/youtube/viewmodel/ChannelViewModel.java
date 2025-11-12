package br.cesul.youtube.viewmodel;

import br.cesul.youtube.model.Channel;
import br.cesul.youtube.repository.ChannelRepository;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ChannelViewModel {

    private final ChannelRepository repository = new ChannelRepository();
    private Channel channel;
    private final StringProperty name = new SimpleStringProperty();
    private final IntegerProperty subscribers = new SimpleIntegerProperty();

    public ChannelViewModel(String channelName) {
        this.channel = repository.findByName(channelName);
        if (channel == null) {
            channel = new Channel(channelName);
            repository.insert(channel);
        }

        this.name.set(channel.getName());
        this.subscribers.set(channel.getSubscribers());
    }

    public StringProperty nameProperty() {
        return name;
    }

    public IntegerProperty subscribersProperty() {
        return subscribers;
    }

    public void subscribe() {
        channel.subscribe();
        subscribers.set(channel.getSubscribers());
        repository.updateSubscribers(channel.getName(), channel.getSubscribers());
    }
}
