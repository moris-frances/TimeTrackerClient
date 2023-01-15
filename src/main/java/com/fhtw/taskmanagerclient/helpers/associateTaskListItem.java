package com.fhtw.taskmanagerclient.helpers;

public class associateTaskListItem {


    private String associateName;
    private String employeeTask;
    private String employeeDateFrom;
    private double employeeHoursSpent;


    public associateTaskListItem(String associateName, String employeeTask, String employeeDateFrom, double employeeHoursSpent) {
        this.associateName = associateName;
        this.employeeTask = employeeTask;
        this.employeeDateFrom = employeeDateFrom;
        this.employeeHoursSpent = employeeHoursSpent;
    }

    public String getAssociateName() {
        return associateName;
    }

    public String getEmployeeTask() {
        return employeeTask;
    }

    public String getEmployeeDateFrom() {
        return employeeDateFrom;
    }

    public double getEmployeeHoursSpent() {
        return employeeHoursSpent;
    }

    public void setAssociateName(String associateName) {
        this.associateName = associateName;
    }

    public void setEmployeeTask(String employeeTask) {
        this.employeeTask = employeeTask;
    }

    public void setEmployeeDateFrom(String employeeDateFrom) {
        this.employeeDateFrom = employeeDateFrom;
    }

    public void setEmployeeHoursSpent(double employeeHoursSpent) {
        this.employeeHoursSpent = employeeHoursSpent;
    }
}
