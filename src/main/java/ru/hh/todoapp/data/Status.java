package ru.hh.todoapp.data;

public enum Status {
    ACTIVE (false),
    COMPLETED(true);

    private boolean status;

    Status(boolean status) { this.status = status; }

    public boolean get() { return status; }
}
