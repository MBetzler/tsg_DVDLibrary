/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.dto.Dvd;
import com.sg.dvdlibrary.ui.DvdLibraryView;
import com.sg.dvdlibrary.dao.DvdLibraryDao;
import com.sg.dvdlibrary.dao.DvdLibraryDaoException;
import com.sg.dvdlibrary.ui.UserIOException;
import java.util.List;

/**
 *
 * @author betzler
 */
public class DvdLibraryController {
    DvdLibraryView view;
    DvdLibraryDao dao;

    public DvdLibraryController(DvdLibraryDao dao, DvdLibraryView view) {
        this.dao = dao;
        this.view = view;
    }

    public void run() {
        int mainMenuSelection;
        int editMenuSelection;
        String repeatEditYN;

        try {
            do {
                mainMenuSelection = getMainMenuSelection();

                switch (mainMenuSelection) {
                    case 1:
                        listDvdTitles();
                        break;
                    case 2:
                        listSingleDvdDetails();
                        break;
                    case 3:
                        listAllDvdDetails();
                        break;
                    case 4:
                        searchDvdTitles();
                        break;
                    case 5:
                        createDvd();
                        break;
                    case 6:
                        List<Dvd> dvdList = dao.getAllDvdTitles();
                        
                        if (dvdList.size() > 0) {
                            do {
                                Dvd dvd = getDvdToEdit(dvdList);
                                view.displayDvdDetailsBanner();
                                view.displaySingleDvdDetails(dvd);
                                editMenuSelection = getEditMenuSelection();
                                
                                switch (editMenuSelection) {
                                    case 1:
                                        editDvdReleaseDate(dvd);
                                        break;
                                    case 2:
                                        editDvdMpaaRating(dvd);
                                        break;
                                    case 3:
                                        editDvdDirector(dvd);
                                        break;
                                    case 4:
                                        editDvdStudio(dvd);
                                        break;
                                    case 5:
                                        editDvdUserNotes(dvd);
                                        break;
                                    case 6:
                                        editDvdAllFields(dvd);
                                        break;
                                    default:
                                        unknownCommand();
                                }
                                
                                dao.updateDvd(dvd.getTitle(), dvd);//ensure changes are written out to file
                                repeatEditYN = view.displayEditRepeat();
                                
                            } while (repeatEditYN.equalsIgnoreCase("y"));
                            
                        } else {
                            view.displayDvdEditBanner();
                            view.displayLibraryEmpty();
                        }
                        
                        break;
                    case 7:
                        removeDvd();
                        break;
                    case 8:
                        break;
                    default:
                        unknownCommand();
                }
            } while (mainMenuSelection != 8);

            exitMessage();
            
        } catch (DvdLibraryDaoException e) {
            view.displayErrorMessage(e.getMessage());
        } catch (UserIOException e) {
            view.displayErrorMessage(e.getMessage());
        }

    }

    private int getMainMenuSelection() throws UserIOException {
        return view.printMainMenuAndGetSelection();
    }

    private void createDvd() throws DvdLibraryDaoException {
        String repeatYN;

        do {
            view.displayCreateDvdBanner();
            Dvd newDvd = view.getNewDvdTitle();

            if (dao.getDvd(newDvd.getTitle()) == null) {
                view.getNewDvdInfo(newDvd);
                dao.updateDvd(newDvd.getTitle(), newDvd);//ensure changes are written out to file
                view.displayCreateDvdSuccessBanner(newDvd.getTitle());
            } else {
                view.displayCreateDvdDuplicateBanner(newDvd.getTitle());
            }
            
            repeatYN = view.displayCreateRepeat();
            
        } while (repeatYN.equalsIgnoreCase("y"));

    }

    private void listDvdTitles() throws DvdLibraryDaoException {
        
        view.displayDvdTitleListBanner();
        List<Dvd> dvdList = dao.getAllDvdTitles();
        
        if (dvdList.size() > 0) {
            view.displayDvdTitleListIndexOptional(dvdList, false);
        } else {
            view.displayLibraryEmpty();
        }
    }

    private void listAllDvdDetails() throws DvdLibraryDaoException {
        
        view.displayAllDvdDetailsBanner();
        List<Dvd> dvdList = dao.getAllDvdTitles();
        
        if (dvdList.size() > 0) {
            view.displayAllDvdDetails(dvdList);
        } else {
            view.displayLibraryEmpty();
        }
    }

    private void listSingleDvdDetails() throws DvdLibraryDaoException, UserIOException {
        
        view.displayDvdDetailsViewBanner();
        List<Dvd> dvdList = dao.getAllDvdTitles();
        
        if (dvdList.size() > 0) {
            view.displayDvdTitleListIndexOptional(dvdList, true);
            int dvdIndex = view.getDvdTitleViewIndex(dvdList);
            Dvd dvd = dao.getDvdByIndex(dvdIndex);
            view.displayDvdDetailsBanner();
            view.displaySingleDvdDetails(dvd);
        } else {
            view.displayLibraryEmpty();
        }
    }

    private void removeDvd() throws DvdLibraryDaoException, UserIOException {
        String repeatYN;

        do {
            view.displayDvdRemoveBanner();
            List<Dvd> dvdList = dao.getAllDvdTitles();
            
            if (dvdList.size() > 0) {
                view.displayDvdTitleListIndexOptional(dvdList, true);
                int dvdIndex = view.getDvdTitleRemoveIndex(dvdList);
                Dvd dvd = dao.getDvdByIndex(dvdIndex);
                dao.removeDvd(dvd.getTitle());
                view.displayRemoveDvdSuccessBanner(dvd.getTitle());
                repeatYN = view.displayRemoveRepeat();
            } else {
                view.displayLibraryEmpty();
                repeatYN = "n";
            }
            
        } while (repeatYN.equalsIgnoreCase("y"));
    }

    private int getEditMenuSelection() throws UserIOException {
        return view.printEditMenuAndGetSelection();
    }

    private Dvd getDvdToEdit(List<Dvd> dvdList) throws DvdLibraryDaoException, UserIOException {
        
        view.displayDvdEditBanner();
        view.displayDvdTitleListIndexOptional(dvdList, true);
        int dvdIndex = view.getDvdTitleEditIndex(dvdList);
        Dvd dvd = dao.getDvdByIndex(dvdIndex);

        return dvd;
    }

    private void editDvdReleaseDate(Dvd dvd) {
        view.getRevisedReleaseDate(dvd);
    }

    private void editDvdMpaaRating(Dvd dvd) {
        view.getRevisedMpaaRating(dvd);
    }

    private void editDvdDirector(Dvd dvd) {
        view.getRevisedDirector(dvd);
    }

    private void editDvdStudio(Dvd dvd) {
        view.getRevisedStudio(dvd);
    }

    private void editDvdUserNotes(Dvd dvd) {
        view.getRevisedUserNotes(dvd);
    }

    private void editDvdAllFields(Dvd dvd) {
        view.getAllFieldsRevised(dvd);
    }

    private void searchDvdTitles() throws DvdLibraryDaoException {
        
        view.displayDvdTitleSearchBanner();
        List<Dvd> dvdList = dao.getAllDvdTitles();
        
        if (dvdList.size() > 0) {
            Dvd dvd = view.getSearchResults(dvdList);
            view.displayDvdSearchResultsBanner();
            view.displaySingleDvdDetails(dvd);
        } else {
            view.displayLibraryEmpty();
        }
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
}
