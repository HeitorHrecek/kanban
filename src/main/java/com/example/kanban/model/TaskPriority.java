package com.example.kanban.model;
public enum TaskPriority {
    LOW(3),
    MEDIUM(2),
    HIGH(1);

    private int number;

    TaskPriority(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
