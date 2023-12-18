package com.global.commtech.test.anagramfinder.collector;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AnagramCollectorTest {

    private AnagramCollector anagramCollector = new AnagramCollector();

    @Test
    void shouldFindAnagramsNoSet() {
        List<List<String>> anagrams = anagramCollector.findAnagrams(new ArrayList<String>());
        assertThat(anagrams.size() == 0);
    }

    @Test
    void shouldFindAnagramsOneSet() {
        List<List<String>> anagrams = anagramCollector.findAnagrams(List.of("abc", "bca", "cab"));
        assertThat(anagrams.size() == 1);
    }

    @Test
    void shouldFindAnagramsTwoSets() {
        List<List<String>> anagrams = anagramCollector.findAnagrams(List.of("abc", "bca", "cab", "abcd"));
        assertThat(anagrams.size() == 2);
    }
    
}