package com.yandex.said.musicinfo.model;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by said on 26.03.16.
 */
public class ItemArtist {

    private int id;
    private Bitmap image;
    private String name;
    private String genre;
    private int countAlbums;
    private int countTracks;

    public ItemArtist() {
    }

    @Override
    public String toString() {
        return "\n id=" + id + ", name=" + name + ", countAlbums=" + countAlbums + ",countTracks=" + countTracks;
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

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public int getCountAlbums() {
        return countAlbums;
    }

    public void setCountAlbums(int countAlbums) {
        this.countAlbums = countAlbums;
    }

    public int getCountTracks() {
        return countTracks;
    }

    public void setCountTracks(int countTracks) {
        this.countTracks = countTracks;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
