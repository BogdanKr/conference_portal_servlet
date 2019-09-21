package ua.krasun.conference_portal_servlet.model.entity.exception;

import java.util.function.Supplier;

public class WrongInputException extends Exception implements Supplier<WrongInputException> {
    public WrongInputException(String message) {
        super(message);
    }


    @Override
    public WrongInputException get() {
        return this;
    }
}

