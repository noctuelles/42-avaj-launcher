JAVAC=javac
SRCS_FOLDER=src
BIN_FOLDER=bin

MAIN=com.plouvel.avajlauncher.App

SRCS=com/plouvel/avajlauncher/App.java \
	 com/plouvel/avajlauncher/Flyable.java \
	 com/plouvel/avajlauncher/Tower.java \
	 com/plouvel/avajlauncher/WeatherTower.java \
	 com/plouvel/avajlauncher/WeatherType.java \
	 com/plouvel/avajlauncher/WeatherProvider.java \
	 com/plouvel/avajlauncher/aircraft/Aircraft.java \
	 com/plouvel/avajlauncher/aircraft/Baloon.java \
	 com/plouvel/avajlauncher/aircraft/Helicopter.java \
	 com/plouvel/avajlauncher/aircraft/JetPlane.java \
	 com/plouvel/avajlauncher/factory/AircraftFactory.java \
	 com/plouvel/avajlauncher/exception/InvalidAircraftType.java \
	 com/plouvel/avajlauncher/exception/ParsingException.java \
	 com/plouvel/avajlauncher/parser/AircraftLineParsingStrategy.java \
	 com/plouvel/avajlauncher/parser/LineParsingStrategy.java \
	 com/plouvel/avajlauncher/parser/FileParser.java \
	 com/plouvel/avajlauncher/parser/Coordinate.java \
	 com/plouvel/avajlauncher/parser/Parser.java \
	 com/plouvel/avajlauncher/parser/ParserResult.java

PREFIXED_SRCS=$(addprefix $(SRCS_FOLDER)/, $(SRCS))
PREFIXED_OBJS=$(addprefix $(BIN_FOLDER)/, $(SRCS:.java=.class))

MAIN=com.plouvel.avajlauncher.App
RM=rm -rf

ifeq (run,$(firstword $(MAKECMDGOALS)))
  RUN_ARGS := $(wordlist 2,$(words $(MAKECMDGOALS)),$(MAKECMDGOALS))
  $(eval $(RUN_ARGS):;@:)
endif

all: $(PREFIXED_OBJS)
run: $(NAME)
	@java -cp $(BIN_FOLDER) $(MAIN) $(RUN_ARGS)
clean:
	$(RM) $(BIN_FOLDER)
re: clean all
$(PREFIXED_OBJS): $(PREFIXED_SRCS)
	$(JAVAC) -d $(BIN_FOLDER) $^

.PHONY: all run clean re $(NAME)