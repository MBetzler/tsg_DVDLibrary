/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dto;

/**
 *
 * @author betzler
 */
public class Dvd {
    private String title;
    private String releaseDate;
    private String mpaaRating;
    private String directorName;
    private String studio;
    private String userNote;

    public Dvd(String title) {
        this.title = title;
    }

    public Dvd(String releaseDate, String mpaaRating, String directorName, String studio, String userNote) {
        this.releaseDate = releaseDate;
        this.mpaaRating = mpaaRating;
        this.directorName = directorName;
        this.studio = studio;
        this.userNote = userNote;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getUserNote() {
        return userNote;
    }

    public void setUserNote(String userNote) {
        this.userNote = userNote;
    }

}
