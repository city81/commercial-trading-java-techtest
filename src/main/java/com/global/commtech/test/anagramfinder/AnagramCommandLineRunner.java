package com.global.commtech.test.anagramfinder;

import java.io.File;

import com.global.commtech.test.anagramfinder.processor.AnagramFinderProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class AnagramCommandLineRunner implements CommandLineRunner {

    private AnagramFinderProcessor anagramFinderProcessor;

    @Autowired
    public AnagramCommandLineRunner(AnagramFinderProcessor anagramFinderProcessor) {
        this.anagramFinderProcessor = anagramFinderProcessor;
    }

    @Override
    public void run(final String... args) throws Exception {
        Assert.isTrue(args.length == 1, "Please ensure that the input file is provided");

        final File file = new File(args[0]);
        Assert.isTrue(file.exists(), args[0] + " Does not exist");

        try {
            anagramFinderProcessor.parseFile(args[0]).forEach(v -> System.out.println(String.join(",", v)));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

}
