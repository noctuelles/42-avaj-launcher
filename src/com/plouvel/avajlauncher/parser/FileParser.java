package com.plouvel.avajlauncher.parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.plouvel.avajlauncher.Flyable;
import com.plouvel.avajlauncher.exception.ParsingException;

public class FileParser extends Parser implements AutoCloseable {
    private BufferedReader bufferedReader;
    private ParserResult parserResult;
    private String currentLine;
    private int currentLineNbr;

    public FileParser(String fileName, LineParsingStrategy lineParsingStrategy) throws FileNotFoundException {
        super(lineParsingStrategy);

        this.bufferedReader = new BufferedReader(new FileReader(fileName));
        this.currentLine = null;
        this.parserResult = null;
        this.currentLineNbr = 0;
    }

    @Override
    public ParserResult parse() throws ParsingException {
        if (this.parserResult != null) {
            return this.parserResult;
        }

        try {
            int nbrSimulation = 0;
            List<Flyable> flyables = new ArrayList<>();

            while ((this.currentLine = this.bufferedReader.readLine()) != null) {
                if (this.currentLine.length() == 0) {
                    continue;
                }
                if (this.currentLineNbr++ == 0) {
                    nbrSimulation = this.parseSimulationNumberLine(this.currentLine);
                } else {
                    flyables.add(this.lineParsingStrategy.parseLine(this.currentLine));
                }
            }
            this.parserResult = new ParserResult(nbrSimulation, flyables);

            return this.parserResult;
        } catch (IOException e) {
            throw new ParsingException(this.currentLine, "a system error occured", e);
        }
    }

    @Override
    public void close() throws IOException {
        this.bufferedReader.close();
    }
}
