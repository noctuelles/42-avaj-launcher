package com.plouvel.avajlauncher.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.plouvel.avajlauncher.exception.ParsingException;

public abstract class Parser {
    protected LineParsingStrategy lineParsingStrategy;
    private Pattern nbrSimulationLineRegex;

    Parser(LineParsingStrategy lineParsingStrategy) {
        this.lineParsingStrategy = lineParsingStrategy;
        this.nbrSimulationLineRegex = Pattern.compile("^\\s*([1-9]\\d*)$");
    }

    protected int parseSimulationNumberLine(String line) throws ParsingException {
        try {
            Matcher matcher = this.nbrSimulationLineRegex.matcher(line);

            if (!matcher.matches()) {
                throw new ParsingException(line, "invalid simulation number");
            }

            return Integer.parseInt(matcher.group(1));
        } catch (NumberFormatException e) {
            /* Unchecked exception : should never happens, because the regex has already validated the integer ! */

            throw new RuntimeException("unexpected error while parsing simulation number");
        }
    }

    public abstract ParserResult parse() throws ParsingException;
}
