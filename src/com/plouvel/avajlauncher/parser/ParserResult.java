package com.plouvel.avajlauncher.parser;

import java.util.List;

import com.plouvel.avajlauncher.Flyable;

public record ParserResult(int nbrSimulation, List<Flyable> flyables) {
};
