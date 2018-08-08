/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.Dvd;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author betzler
 */
public class DvdLibraryDaoFileImpl implements DvdLibraryDao {
    public static final String LIBRARY_FILE = "library.txt";
    public static final String DELIMITER = "::";

    private final Map<String, Dvd> dvds;

    public DvdLibraryDaoFileImpl() {
        this.dvds = new HashMap<>();
    }

    @Override
    public Dvd addDvd(String title, Dvd dvd) throws DvdLibraryDaoException {
        loadLibrary();
        Dvd newDvd = dvds.put(title, dvd);
        writeLibrary();
        
        return newDvd;
    }

    @Override
    public List<Dvd> getAllDvdTitles() throws DvdLibraryDaoException {
        loadLibrary();

        return new ArrayList<>(dvds.values());
    }

    @Override
    public Dvd getDvd(String title) throws DvdLibraryDaoException {
        loadLibrary();
        
        return dvds.get(title);
    }

    @Override
    public Dvd removeDvd(String title) throws DvdLibraryDaoException {
        loadLibrary();
        Dvd removedDvd = dvds.remove(title);
        writeLibrary();
        
        return removedDvd;
    }

    @Override
    public Dvd getDvdByIndex(int index) throws DvdLibraryDaoException {
        loadLibrary();
        ArrayList<Dvd> dvdList = new ArrayList<>(dvds.values());
        
        return dvdList.get(index);
    }

    //additional dao put method to ensure changes are written out to file
    @Override
    public void updateDvd(String title, Dvd dvd) throws DvdLibraryDaoException {
        loadLibrary();
        dvds.put(title, dvd);
        writeLibrary();
    }

    private void loadLibrary() throws DvdLibraryDaoException {
        Scanner scanner;

        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(LIBRARY_FILE)));
        } catch (FileNotFoundException e) {
            throw new DvdLibraryDaoException(
                    " - Could not load library data into memory.", e);
        }

        String currentLine;
        String[] currentTokens;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            Dvd currentDvd = new Dvd(currentTokens[0]);
            currentDvd.setReleaseDate(currentTokens[1]);
            currentDvd.setMpaaRating(currentTokens[2]);
            currentDvd.setDirectorName(currentTokens[3]);
            currentDvd.setStudio(currentTokens[4]);
            currentDvd.setUserNote(currentTokens[5]);

            dvds.put(currentDvd.getTitle(), currentDvd);
        }
        scanner.close();
    }

    public void writeLibrary() throws DvdLibraryDaoException {
        PrintWriter out;

        //instantiate new to ensure all changes are written out
        List<Dvd> dvdList = new ArrayList<>(dvds.values());
        
        try {
            out = new PrintWriter(new FileWriter(LIBRARY_FILE));
        } catch (IOException e) {
            throw new DvdLibraryDaoException(
                    " - Could not save DVD data.", e);
        }

        for (Dvd currentDvd : dvdList) {
            out.println(currentDvd.getTitle() + DELIMITER
                    + currentDvd.getReleaseDate() + DELIMITER
                    + currentDvd.getMpaaRating() + DELIMITER
                    + currentDvd.getDirectorName() + DELIMITER
                    + currentDvd.getStudio() + DELIMITER
                    + currentDvd.getUserNote());
            out.flush();
        }
        
        out.close();
    }
}
