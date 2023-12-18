package com.global.commtech.test.anagramfinder.processor;

import com.global.commtech.test.anagramfinder.collector.AnagramCollector;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AnagramFinderProcessorTest {

    private static AnagramFinderProcessor anagramFinderProcessor;

    @BeforeAll
    static void setup() {
        anagramFinderProcessor = new AnagramFinderProcessor(new AnagramCollector());
    }

    @Test
    void shouldFindAnagrams() throws AnagramFinderProcessorException {

        List<List<String>> anagrams = anagramFinderProcessor.parseFile("src/test/resources/example1.txt");
        assertThat(anagrams.size() == 3);
    }

    @Test
    void shouldFindOneAnagram() throws AnagramFinderProcessorException {

        List<List<String>> anagrams = anagramFinderProcessor.parseFile("src/test/resources/exampleSingleWordList.txt");
        assertThat(anagrams.size() == 3);
    }

    @Test
    void shouldFindNoAnagrams() throws AnagramFinderProcessorException {

        List<List<String>> anagrams = anagramFinderProcessor.parseFile("src/test/resources/exampleNoWords.txt");
        assertThat(anagrams.size() == 3);
    }

    @Test
    void shouldThrowExceptionWhenFileNotFound() {

        Exception exception = assertThrows(AnagramFinderProcessorException.class, () -> {
            anagramFinderProcessor.parseFile("src/test/resources/noFile.txt");
        });
        assertTrue(exception.getMessage().contains("src/test/resources/noFile.txt (No such file or directory)"));
    }

}