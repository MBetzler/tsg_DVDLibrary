/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.ui;

import java.util.Scanner;

/**
 *
 * @author betzler
 */
public class UserIOConsoleImpl implements UserIO {

    Scanner sc = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.print(message);
    }

    @Override
    public void printLine(String message) {
        System.out.println(message);
    }

    @Override
    public double readDouble(String prompt, boolean nextLine) {

        if (nextLine) {
            printLine(prompt);
        } else {
            print(prompt);
        }
        return (Double.parseDouble(sc.nextLine()));
    }

    @Override
    public double readDouble(String prompt, double min, double max, boolean nextLine) {
        boolean validResponse = false;
        double responseValue = 0;

        do {
            if (nextLine) {
                printLine(prompt);
            } else {
                print(prompt);
            }
            responseValue = Double.parseDouble(sc.nextLine());

            if (responseValue >= min && responseValue <= max) {
                validResponse = true;
            } else {
                System.out.println("That value does not fall within the range of " + min + " to " + max + ".");
            }

        } while (!validResponse);
        
        return (responseValue);
    }

    @Override
    public float readFloat(String prompt, boolean nextLine) {

        if (nextLine) {
            printLine(prompt);
        } else {
            print(prompt);
        }
        
        return (Float.parseFloat(sc.nextLine()));
    }

    @Override
    public float readFloat(String prompt, float min, float max, boolean nextLine) {
        boolean validResponse = false;
        float responseValue = 0;

        do {
            if (nextLine) {
                printLine(prompt);
            } else {
                print(prompt);
            }
            responseValue = Float.parseFloat(sc.nextLine());

            if (responseValue >= min && responseValue <= max) {
                validResponse = true;
            } else {
                System.out.println("That value does not fall within the range of " + min + " to " + max + ".");
            }

        } while (!validResponse);
        
        return (responseValue);
    }

    @Override
    public int readInt(String prompt, boolean nextLine) {

        if (nextLine) {
            printLine(prompt);
        } else {
            print(prompt);
        }

        return (Integer.parseInt(sc.nextLine()));
    }

    @Override
    public int readInt(String prompt, int min, int max, boolean nextLine) throws UserIOException {
        boolean validResponse = false;
        int responseValue = 0;

        do {
            if (nextLine) {
                printLine(prompt);
            } else {
                print(prompt);
            }

            try {
                responseValue = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                throw new UserIOException(" - invalid input; numeric entry only.", e);
            }

            if (responseValue >= min && responseValue <= max) {
                validResponse = true;
            } else {
                System.out.println("That value does not fall within the range of " + min + " to " + max + ".");
            }

        } while (!validResponse);
        
        return (responseValue);
    }

    @Override
    public long readLong(String prompt, boolean nextLine) {

        if (nextLine) {
            printLine(prompt);
        } else {
            print(prompt);
        }
        return (Long.parseLong(sc.nextLine()));
    }

    @Override
    public long readLong(String prompt, long min, long max, boolean nextLine) {
        boolean validResponse = false;
        long responseValue = 0;

        do {
            if (nextLine) {
                printLine(prompt);
            } else {
                print(prompt);
            }
            responseValue = Long.parseLong(sc.nextLine());

            if (responseValue >= min && responseValue <= max) {
                validResponse = true;
            } else {
                System.out.println("That value does not fall within the range of " + min + " to " + max + ".");
            }

        } while (!validResponse);
        
        return (responseValue);
    }

    @Override
    public String readString(String prompt, boolean nextLine) {

        if (nextLine) {
            printLine(prompt);
        } else {
            print(prompt);
        }
        
        return (sc.nextLine());
    }

    public String readString(String prompt, String options, boolean nextLine) {
        boolean validResponse;
        String response = "";

        do {
            validResponse = false;
            if (nextLine) {
                printLine(prompt);
            } else {
                print(prompt);
            }

            response = sc.nextLine();

            for (int i = 0; i < options.length(); i++) {
                if (response.equalsIgnoreCase(String.valueOf(options.charAt(i)))) {
                    validResponse = true;
                    break;
                }
            }

            if (!validResponse) {
                System.out.print("\nThat isn't a valid response.  Please respond with ");
                for (int i = 0; i < options.length(); i++) {
                    if (i < options.length() - 1) {
                        System.out.print("\"" + options.charAt(i) + "\", ");
                    } else {
                        System.out.println("\"" + options.charAt(i) + "\".");
                    }
                }
            }
            
        } while (!validResponse);

        return response;
    }
}
