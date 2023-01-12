package com.fhtw.taskmanagerclient.model;

public class EntryModel {
    private int id;
    private String name;

    private String task;

    private String dateFrom;

    private String dateTo;


    public EntryModel(int id, String name, String task, String dateFrom, String dateTo) {
        this.id = id;
        this.name = name;
        this.task = task;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTask() {
        return task;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    @Override
    public String toString() {
        return this.id +
                ";" + this.name+
                ";" +this.task+
                ";" +this.dateFrom+
                ";" +this.dateTo;
    }
}
