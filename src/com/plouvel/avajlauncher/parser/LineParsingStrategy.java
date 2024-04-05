package com.plouvel.avajlauncher.parser;

import com.plouvel.avajlauncher.Flyable;
import com.plouvel.avajlauncher.exception.ParsingException;

interface LineParsingStrategy {
    public Flyable parseLine(String line) throws ParsingException;
}
