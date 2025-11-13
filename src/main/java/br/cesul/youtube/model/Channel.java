package br.cesul.youtube.model;

import org.bson.types.ObjectId;

public class Channel {
    private ObjectId id;
    private String name;
    private int subscribers;

    public Channel() {} // Necessário para o driver do MongoDB

    public Channel(String name) {
        this.name = name;
        this.subscribers = 0;
    }
    // CONSTRUTOR QUE USA NOS TESTES
    public Channel(String name, int subscribers){
        this.name = name;
        this.subscribers = subscribers;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(int subscribers) {
        this.subscribers = subscribers;
    }

    public void subscribe() {
        subscribers++;
    }
}
