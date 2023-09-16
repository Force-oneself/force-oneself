package com.quan.framework.mongo.driver;

import java.util.List;

/**
 * @author Force-oneself
 * @Description DriverEntity
 * @date 2021-08-21
 */
public class DriverEntity {

    private String plot;
    private List<String> genres;
    private String title;


    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Movie [\n  plot=" + plot + ",\n  genres=" + genres + ",\n  title=" + title + "\n]";
    }
}
