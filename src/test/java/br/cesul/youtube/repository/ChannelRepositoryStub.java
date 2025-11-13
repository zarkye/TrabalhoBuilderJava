package br.cesul.youtube.repository;

import br.cesul.youtube.model.Channel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ChannelRepositoryStub extends ChannelRepository {

    private Channel canal;

    @Override
    public Channel findByName(String name) {
        if (canal != null && canal.getName().equals(name)) {
            return canal;
        }
        return null;
    }

    @Override
    public void insert(Channel c) {
        this.canal = c;
    }

    @Override
    public void updateSubscribers(String name, int subscribers) {
        if (canal != null && canal.getName().equals(name)) {
            canal.setSubscribers(subscribers);
        }
    }
}
