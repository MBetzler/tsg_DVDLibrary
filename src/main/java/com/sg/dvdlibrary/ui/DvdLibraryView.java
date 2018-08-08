/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.ui;

import com.sg.dvdlibrary.dto.Dvd;
import java.util.List;

/**
 *
 * @author betzler
 */
public class DvdLibraryView {
    private final UserIO io;

    public DvdLibraryView(UserIO io) {
        this.io = io;
    }

    public int printMainMenuAndGetSelection() throws UserIOException {
        io.print("\n******************************"
                + "\n****   DVD Library Menu   ****"
                + "\n******************************"
                + "\n1. List DVD titles in library"
                + "\n2. Display a DVD's details"
                + "\n3. Display details of each DVD in library"
                + "\n4. Search for a DVD by title"
                + "\n5. Add a DVD to library"
                + "\n6. Edit a DVD from library"
                + "\n7. Remove a DVD from library"
                + "\n8. Exit\n");

        return io.readInt("\nPlease select an option:   ", 1, 8, false);
    }

    public Dvd getNewDvdTitle() {
        String title = io.readString("Enter the DVD title:  ", false);
        Dvd currentDvd = new Dvd(title);

        return currentDvd;
    }

    public Dvd getNewDvdInfo(Dvd dvd) {
        String releaseDate = io.readString("Enter the DVD Release Date:   ", false);
        String mpaaRating = io.readString("Enter the DVD MPAA Rating:   ", false);
        String directorName = io.readString("Enter the Director of the DVD:   ", false);
        String studio = io.readString("Enter the DVD release Studio:   ", false);
        String userNote = io.readString("Enter your personal rating or notes:   ", false);
        dvd.setReleaseDate(releaseDate);
        dvd.setMpaaRating(mpaaRating);
        dvd.setDirectorName(directorName);
        dvd.setStudio(studio);
        dvd.setUserNote(userNote);

        return dvd;
    }

    public void displayCreateDvdBanner() {
        io.printLine("\n===  Add DVD  ===");
    }

    public void displayDvdDetailsBanner() {
        io.printLine("\n===  DVD Details  ===");
    }

    public void displayCreateDvdSuccessBanner(String title) {
        io.printLine("\nDVD \"" + title + "\" successfully created.");
    }

    public void displayCreateDvdDuplicateBanner(String title) {
        io.printLine("\nDVD \"" + title + "\" already exists in the library.");
    }

    public String displayCreateRepeat() {
        return io.readString("Would you like to add another DVD? (y/n)   ", "yn", false);
    }

    public String displayRemoveRepeat() {
        return io.readString("Would you like to remove another DVD? (y/n)   ", "yn", false);
    }

    public String displayEditRepeat() {
        return io.readString("Would you like to edit another DVD? (y/n)   ", "yn", false);
    }

    public void displayDvdTitleListBanner() {
        io.printLine("\n===  DVD Titles  ===");
    }

    public void displayAllDvdDetails(List<Dvd> dvdList) {
        int counter = 0;

        for (int i = 0; i < dvdList.size(); i++) {
            io.printLine("Title: " + dvdList.get(i).getTitle());
            io.printLine("Release Date:  " + dvdList.get(i).getReleaseDate());
            io.printLine("MPAA Rating:  " + dvdList.get(i).getMpaaRating());
            io.printLine("Director:  " + dvdList.get(i).getDirectorName());
            io.printLine("Studio:  " + dvdList.get(i).getStudio());
            io.printLine("Notes:  " + dvdList.get(i).getUserNote() + "\n");
            counter++;
            if (counter == 5 && i < dvdList.size() - 1) {
                counter = 0;
                io.readString("\nPress enter to continue.", true);
            }
        }
        
        io.readString("\nPress enter to continue.", true);
    }

    public void displayDvdTitleSearchBanner() {
        io.printLine("\n===  DVD Search  ===");
    }

    public void displayAllDvdDetailsBanner() {
        io.printLine("\n===  All DVD Details  ===");
    }

    public void displayDvdDetailsViewBanner() {
        io.printLine("\n===          DVD Titles          ===");
        io.printLine("===  Note the DVD title to view  ===");
    }

    public void displayDvdRemoveBanner() {
        io.printLine("\n===           DVD Titles           ===");
        io.printLine("===  Note the DVD title to remove  ===");
    }

    public void displayDvdEditBanner() {
        io.printLine("\n===          DVD Titles          ===");
        io.printLine("===  Note the DVD title to edit  ===");
    }

    public void displayDvdTitleListIndexOptional(List<Dvd> dvdList, boolean indexed) {
        int counter = 0;

        for (int i = 0; i < dvdList.size(); i++) {
            if (indexed) {
                io.print((i + 1) + ": ");
            }
            io.printLine(dvdList.get(i).getTitle());

            counter++;
            if (counter == 15 && i < dvdList.size() - 1) {
                counter = 0;
                io.readString("\nPress enter to continue.", true);
            }
        }
        
        io.readString("\nPress enter to continue.", true);
    }

    public void displayLibraryEmpty() {
        io.printLine("\nThere are no DVD's in the library.");
        io.readString("\nPress enter to continue.", true);
    }

    public int getDvdTitleViewIndex(List<Dvd> dvdList) throws UserIOException {
        return io.readInt("\nPlease select the DVD to view (1 - " + dvdList.size() + "):   ", 1, dvdList.size(), false) - 1;
    }

    public int getDvdTitleRemoveIndex(List<Dvd> dvdList) throws UserIOException {
        return io.readInt("\nPlease select the DVD to remove (1 - " + dvdList.size() + "):   ", 1, dvdList.size(), false) - 1;
    }

    public int getDvdTitleEditIndex(List<Dvd> dvdList) throws UserIOException {
        return io.readInt("\nPlease select the DVD to edit (1 - " + dvdList.size() + "):   ", 1, dvdList.size(), false) - 1;
    }

    public Dvd getSearchResults(List<Dvd> dvdList) {
        String title = io.readString("Enter the DVD title:   ", false);

        Dvd dvd = null;
        for (Dvd currentDvd : dvdList) {
            if (title.equalsIgnoreCase(currentDvd.getTitle())) {
                dvd = currentDvd;
            }
        }

        return dvd;
    }

    public void displayDvdSearchResultsBanner() {
        io.printLine("\n===  DVD Search Results  ===");
    }

    public void displaySingleDvdDetails(Dvd dvd) {
        if (dvd != null) {
            io.printLine("Title: " + dvd.getTitle());
            io.printLine("Release Date:  " + dvd.getReleaseDate());
            io.printLine("MPAA Rating:  " + dvd.getMpaaRating());
            io.printLine("Director:  " + dvd.getDirectorName());
            io.printLine("Studio:  " + dvd.getStudio());
            io.printLine("Notes:  " + dvd.getUserNote() + "\n");
        } else {
            io.printLine("That DVD title is not in the library.");
        }
        
        io.readString("\nPress enter to continue.", true);
    }

    public void displayRemoveDvdSuccessBanner(String title) {
        io.printLine("\nDVD \"" + title + "\" successfully removed.");
    }

    public int printEditMenuAndGetSelection() throws UserIOException {
        io.print("\n******************************"
                + "\n****    DVD Edit Menu     ****"
                + "\n******************************"
                + "\n1. Edit the Release Date"
                + "\n2. Edit the MPAA Rating"
                + "\n3. Edit the Director's Name"
                + "\n4. Edit the release Studio"
                + "\n5. Edit your personal rating or notes"
                + "\n6. Edit all the above fields\n");

        return io.readInt("\nPlease select an option:   ", 1, 6, false);
    }

    public Dvd getRevisedReleaseDate(Dvd dvd) {
        String releaseDate = io.readString("Enter the DVD Release Date:   ", false);
        dvd.setReleaseDate(releaseDate);
        io.printLine("\nDVD \"" + dvd.getTitle() + "\" successfully edited.");

        return dvd;
    }

    public Dvd getRevisedMpaaRating(Dvd dvd) {
        String mpaaRating = io.readString("Enter the DVD MPAA Rating:   ", false);
        dvd.setMpaaRating(mpaaRating);
        io.printLine("\nDVD \"" + dvd.getTitle() + "\" successfully edited.");

        return dvd;
    }

    public Dvd getRevisedDirector(Dvd dvd) {
        String directorName = io.readString("Enter the Director of the DVD:   ", false);
        dvd.setDirectorName(directorName);
        io.printLine("\nDVD \"" + dvd.getTitle() + "\" successfully edited.");

        return dvd;
    }

    public Dvd getRevisedStudio(Dvd dvd) {
        String studio = io.readString("Enter the DVD release Studio:   ", false);
        dvd.setStudio(studio);
        io.printLine("\nDVD \"" + dvd.getTitle() + "\" successfully edited.");

        return dvd;
    }

    public Dvd getRevisedUserNotes(Dvd dvd) {
        String userNote = io.readString("Enter your personal rating or notes:   ", false);
        dvd.setUserNote(userNote);
        io.printLine("\nDVD \"" + dvd.getTitle() + "\" successfully edited.");

        return dvd;
    }

    public Dvd getAllFieldsRevised(Dvd dvd) {
        String releaseDate = io.readString("Enter the DVD Release Date:   ", false);
        String mpaaRating = io.readString("Enter the DVD MPAA Rating:   ", false);
        String directorName = io.readString("Enter the Director of the DVD:   ", false);
        String studio = io.readString("Enter the DVD release Studio:   ", false);
        String userNote = io.readString("Enter your personal rating or notes:   ", false);
        dvd.setReleaseDate(releaseDate);
        dvd.setMpaaRating(mpaaRating);
        dvd.setDirectorName(directorName);
        dvd.setStudio(studio);
        dvd.setUserNote(userNote);
        io.printLine("\nDVD \"" + dvd.getTitle() + "\" successfully edited.");

        return dvd;
    }

    public void displayExitBanner() {
        io.print("\nYou spend too much money on DVD's .....");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
}
