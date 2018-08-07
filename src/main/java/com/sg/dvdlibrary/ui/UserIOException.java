/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.ui;

/**
 *
 * @author betzler
 */
public class UserIOException extends Exception {

    public UserIOException(String message) {
        super(message);
    }

    public UserIOException(String message, Throwable cause) {
        super(message, cause);
    }

}
