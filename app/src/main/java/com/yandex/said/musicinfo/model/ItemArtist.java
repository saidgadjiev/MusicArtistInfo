package com.yandex.said.musicinfo.model;

/**
 * Created by said on 26.03.16.
 */
public class ItemArtist {

    private int id;
    private String name;

    public ItemArtist() {
    }

    @Override
    public String toString() {
        return "\n id=" + id + ", name=" + name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
