package com.quan.algorithm.ciphers;

import java.util.Scanner;

/**
 * A Java implementation of Caesar Cipher. /It is a type of substitution cipher
 * in which each letter in the plaintext is replaced by a letter some fixed
 * number of positions down the alphabet. /
 *
 * @author FAHRI YARDIMCI
 * @author khalil2535
 */
public class Caesar {

    /**
     * Encrypt text by shifting every Latin char by add number shift for ASCII
     * Example : A + 1 -> B
     *
     * @param message
     * @param shift
     * @return Encrypted message
     */
    public static String encode(String message, int shift) {
        StringBuilder encoded = new StringBuilder();


        shift %= 26;


        final int length = message.length();
        for (int i = 0; i < length; i++) {

//            int current = message.charAt(i);
// using char to shift characters because ascii is in-order latin alphabet
            // Java law : char + int = char
            char current = message.charAt(i);

            if (isCapitalLatinLetter(current)) {

                current += shift;
                // 26 = number of latin letters
                encoded.append((char) (current > 'Z' ? current - 26 : current));

            } else if (isSmallLatinLetter(current)) {

                current += shift;
                // 26 = number of latin letters
                encoded.append((char) (current > 'z' ? current - 26 : current));

            } else {
                encoded.append(current);
            }
        }
        return encoded.toString();
    }

    /**
     * Decrypt message by shifting back every Latin char to previous the ASCII
     * Example : B - 1 -> A
     *
     * @param encryptedMessage
     * @param shift
     * @return message
     */
    public static String decode(String encryptedMessage, int shift) {
        StringBuilder decoded = new StringBuilder();
        shift %= 26;

        final int length = encryptedMessage.length();
        for (int i = 0; i < length; i++) {
            char current = encryptedMessage.charAt(i);
            if (isCapitalLatinLetter(current)) {

                current -= shift;
                // 26 = number of latin letters
                decoded.append((char) (current < 'A' ? current + 26 : current));

            } else if (isSmallLatinLetter(current)) {

                current -= shift;
                // 26 = number of latin letters
                decoded.append((char) (current < 'a' ? current + 26 : current));

            } else {
                decoded.append(current);
            }
        }
        return decoded.toString();
    }

    /**
     * @param c
     * @return true if character is capital Latin letter or false for others
     */
    private static boolean isCapitalLatinLetter(char c) {
        return c >= 'A' && c <= 'Z';
    }

    /**
     * @param c
     * @return true if character is small Latin letter or false for others
     */
    private static boolean isSmallLatinLetter(char c) {
        return c >= 'a' && c <= 'z';
    }

    /**
     * @deprecated TODO remove main and make JUnit Testing
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the message (Latin Alphabet)");
        String message = input.nextLine();
        System.out.println(message);
        System.out.println("Please enter the shift number");
        int shift = input.nextInt() % 26;
        System.out.println("(E)ncode or (D)ecode ?");
        char choice = input.next().charAt(0);
        switch (choice) {
            case 'E':
            case 'e':
                //send our function to handle
                System.out.println("ENCODED MESSAGE IS \n" + encode(message, shift));
                break;
            case 'D':
            case 'd':
                System.out.println("DECODED MESSAGE IS \n" + decode(message, shift));
                break;
            default:
                System.out.println("default");
        }
        input.close();
    }

}
