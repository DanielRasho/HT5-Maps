package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/** Provides methods to read file and store it on data structures.*/
public class FileReader {
    /**
     * Reads a file and return it as an array of tokens
     * @param file File to read.
     * @param separator String that define where a Token end.
     * @return List of tokens.
     */
    public static List<String> fileToList(File file, String separator){
        List<String> fragments = new ArrayList<>();

        // INSTANTIATING SCANNER
        Scanner read;
        try {
            read = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // SETTING SEPARATOR
        read.useDelimiter(separator);

        // FINDING FRAGMENTS
        while (read.hasNext())
        {
            fragments.add(read.next());
        }
        read.close();

        return fragments;
    }

    /**
     * Fills a given map from a file according to given guidelines.
     * @param file File to read.
     * @param entriesSeparator Regex expression for separate one entry from another.
     * @param keyValueSeparator Regex expression to separate key from value in an entry.
     * @param map Map to fill
     * @return Map filled with entries.
     */
    public static Map<String, String> fillMapFromFile(
            File file, String entriesSeparator, String keyValueSeparator, Map<String, String> map){

        List<String> entries = FileReader.fileToList(file, entriesSeparator);
        for (String entry: entries) {
            String[] keyValuePair = entry.split(keyValueSeparator);
            map.put(keyValuePair[0].trim(), keyValuePair[1].trim());
        }
        return map;
    }
}
