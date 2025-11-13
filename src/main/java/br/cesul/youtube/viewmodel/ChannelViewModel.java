package br.cesul.youtube.viewmodel;

import br.cesul.youtube.model.Channel;
import br.cesul.youtube.repository.ChannelRepository;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ChannelViewModel {

    private final ChannelRepository repository;
    private Channel channel;
    private final StringProperty name = new SimpleStringProperty();
    private final IntegerProperty subscribers = new SimpleIntegerProperty();

    // Construtor padrão (ELE É DE FACHADA, VOCÊ INSTANCIA A VM PASSANDO APENAS O NOME, AI POR TRÁS DOS PANO ELE CHAMA
    // O OUTRO CONSTRUTOR, QUE ESSE SIM PODE SER USADO NOS TESTES, ENCAPSULAMENTO DE CONSTRUTOR, TOP)
    public ChannelViewModel(String channelName) {
        this(channelName, new ChannelRepository());
    }

    // ESSE AQUI É O CONSTRUTOR QUE REALMENTE SETTA OS ELEMENTOS NA TELA, E É USADO NOS TESTES, PARA PODER CHAMAR O REPOSTUB
    public ChannelViewModel(String channelName, ChannelRepository repository) {
        this.repository = repository;

        this.channel = repository.findByName(channelName);

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
