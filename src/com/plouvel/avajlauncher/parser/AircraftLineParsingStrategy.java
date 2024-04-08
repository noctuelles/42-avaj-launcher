package com.plouvel.avajlauncher.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.plouvel.avajlauncher.Flyable;
import com.plouvel.avajlauncher.exception.InvalidAircraftType;
import com.plouvel.avajlauncher.exception.ParsingException;
import com.plouvel.avajlauncher.factory.AircraftFactory;

public class AircraftLineParsingStrategy implements LineParsingStrategy {
    private Pattern lineRegex;

    public AircraftLineParsingStrategy() {
        this.lineRegex = Pattern.compile("\\s*(\\S+)\\s+(\\S+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)");
    }

    private Coordinate parseCoordinates(String longitude, String latitude, String height) throws NumberFormatException {
        return new Coordinate(Integer.parseInt(longitude), Integer.parseInt(latitude), Integer.parseInt(height));
    }

    private static int indexOfLastMatch(Pattern pattern, String input) {
        Matcher matcher = pattern.matcher(input);
        for (int i = input.length(); i > 0; --i) {
            Matcher region = matcher.region(0, i);
            if (region.matches() || region.hitEnd()) {
                return i;
            }
        }

        return 0;
    }

    @Override
    public Flyable parseLine(String line) throws ParsingException {
        try {
            Matcher matcher = this.lineRegex.matcher(line);
            String type = null;
            String name = null;
            Coordinate coordinate = null;

            if (!matcher.matches()) {
                throw new ParsingException(line, "invalid line", indexOfLastMatch(this.lineRegex, line));
            }

            type = matcher.group(1);
            name = matcher.group(2);
            coordinate = this.parseCoordinates(matcher.group(3), matcher.group(4), matcher.group(5));

            return AircraftFactory.newAircraft(type, name, coordinate);
        } catch (NumberFormatException e) {
            throw new ParsingException(line, e.getMessage());
        } catch (InvalidAircraftType e) {
            throw new ParsingException(line, e.getMessage(), 0);
        }
    }
}
