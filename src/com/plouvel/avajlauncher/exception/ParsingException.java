package com.plouvel.avajlauncher.exception;

public class ParsingException extends Exception {
    private int indexLastMatch;
    private String line;
    private String reason;

    public ParsingException(String line, String reason) {
        super();
        this.line = line;
        this.reason = reason;
        this.indexLastMatch = -1;
    }

    public ParsingException(String line, String reason, Throwable cause) {
        super(cause);
        this.line = line;
        this.reason = reason;
        this.indexLastMatch = -1;
    }

    public ParsingException(String line, String reason, int indexLastMatch) {
        super();
        this.line = line;
        this.reason = reason;
        this.indexLastMatch = indexLastMatch;
    }


    public ParsingException(String line, String reason, int indexLastMatch, Throwable cause) {
        super(cause);
        this.line = line;
        this.reason = reason;
        this.indexLastMatch = indexLastMatch;
    }

    @Override
    public String getMessage() {
        if (this.indexLastMatch != -1) {
            return String.format("\"%s\" : %s\n%s", this.line, this.reason,
                    String.format("%" + (this.indexLastMatch + 2) + "c", '^'));
        }
        return String.format("\"%s\" : %s", this.line, this.reason);
    }
}
