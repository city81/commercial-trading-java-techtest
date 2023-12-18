package com.global.commtech.test.anagramfinder.processor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import com.global.commtech.test.anagramfinder.collector.AnagramCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnagramFinderProcessor {

    private AnagramCollector anagramCollector;

    @Autowired
    public AnagramFinderProcessor(AnagramCollector anagramCollector) {
        this.anagramCollector = anagramCollector;
    }

    /**
     * For a given file, process the lines in batches of same size lines by passing each
     * batch to the AnagramCollector
     *
     * @param file file containing the anagrams
     * @return a list of string lists where each string list contains the matching anagrams
     * @throws AnagramFinderProcessorException
     */
    public List<List<String>> parseFile(String file) throws AnagramFinderProcessorException {

        List<List<String>> anagrams = new ArrayList<>();

        List<String> sameLengthStringList = new ArrayList<>();

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = br.readLine();

            if (line != null) {

                int stringLength = line.length();

                // while there are still lines in the file
                while (line != null) {
                    if (line.length() == stringLength) {
                        sameLengthStringList.add(line);
                        line = br.readLine();
                    } else {
                        // no more same length strings so find the anagrams
                        anagrams.addAll(anagramCollector.findAnagrams(sameLengthStringList));
                        stringLength = line.length();
                        sameLengthStringList.clear();
                    }
                }
                // no more lines so call findAnagrams one final time
                anagrams.addAll(anagramCollector.findAnagrams(sameLengthStringList));
            }
        } catch (IOException ioException) {
            throw new AnagramFinderProcessorException(ioException.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ioException) {
                throw new AnagramFinderProcessorException(ioException.getMessage());
            }
        }

        return anagrams;

    }

}
