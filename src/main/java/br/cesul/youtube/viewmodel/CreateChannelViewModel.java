package br.cesul.youtube.viewmodel;

import br.cesul.youtube.model.Channel;
import br.cesul.youtube.repository.ChannelRepository;

public class CreateChannelViewModel {
    private final ChannelRepository repository = new ChannelRepository();

    public void createChannel(String name) {
        if (name != null && !name.isBlank()) {
            Channel channel = new Channel(name);
            repository.insert(channel);
        }
    }
}
