package com.example.kanban.model;
public enum TaskStatus {
    TO_DO(1),
    IN_PROGRESS(2),
    DONE(3);

    private int number;

    TaskStatus(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
