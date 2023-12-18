package com.global.commtech.test.anagramfinder.collector;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
public class AnagramCollector {

    /**
     * Iterate over each element in the list, sort the char array and check if an anagram of the
     * string has already been processed. If not create a new list, if already processed, add string
     * to the existing anagram list.
     *
     * @param stringList list of strings to be sorted into anagram lists
     * @return a list of string lists where each string list contains the matching anagrams
     */
    public List<List<String>> findAnagrams(List<String> stringList) {

        Map<String, List<String>> anagramMap = new HashMap<>();

        stringList.forEach(str -> {
            String sortedString = str.chars().sorted().collect(
                    StringBuilder::new,
                    StringBuilder::appendCodePoint,
                    StringBuilder::append
            ).toString();
            if (anagramMap.containsKey(sortedString)) {
                anagramMap.get(sortedString).add(str);
            } else {
                anagramMap.put(sortedString, new ArrayList<>(List.of(str)));
            }
        });

        return anagramMap.values().stream().collect(Collectors.toList());

    }

}
