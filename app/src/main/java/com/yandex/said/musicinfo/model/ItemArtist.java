package com.yandex.said.musicinfo.model;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by said on 26.03.16.
 */
public class ItemArtist implements Serializable {
    private int id;
    private String name;
    private List<String> genres;
    private int countTracks;
    private int countAlbums;
    private String link;
    private String description;
    private Avatar cover;

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

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public int getCountTracks() {
        return countTracks;
    }

    public void setCountTracks(int countTracks) {
        this.countTracks = countTracks;
    }

    public int getCountAlbums() {
        return countAlbums;
    }

    public void setCountAlbums(int countAlbums) {
        this.countAlbums = countAlbums;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getSmallAvatarUrl() {
        return cover.getSmall();
    }

    public String getBigAvatarUrl() {
        return cover.getBig();
    }

    public void setCover(Avatar cover) {
        this.cover = cover;
    }

    public Avatar getCover() {
        return cover;
    }
}
