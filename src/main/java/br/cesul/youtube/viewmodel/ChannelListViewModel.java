package br.cesul.youtube.viewmodel;

import br.cesul.youtube.model.Channel;
import br.cesul.youtube.repository.ChannelRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ChannelListViewModel {

    private final ChannelRepository repository = new ChannelRepository();
    private final ObservableList<Channel> channels = FXCollections.observableArrayList();

    public ChannelListViewModel() {
        loadChannels();
    }

    public void loadChannels() {
        channels.setAll(repository.findAll());
    }

    public ObservableList<Channel> getChannels() {
        return channels;
    }
}
