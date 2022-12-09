package com.example.kennethndungu.lab2class;

public class Tasks {
    String Title,Description,StartDate,DueDate,SubMethod;

    public Tasks(){

    }
    public Tasks(String Title,String Description,String StartDate,String DueDate,String SubMethod){
    this.Title=Title;
    this.Description=Description;
    this.StartDate=StartDate;
    this.DueDate=DueDate;
    this.SubMethod=SubMethod;
    }
    //Title
    public String getTitle() {
        return Title;
    }
    public void setTitle(String Title) {
        this.Title = Title;
    }

    //Description
    public String getDescription() {
        return Description;
    }
    public void setDescription(String description) {
        Description = description;
    }

    //StartDate
    public String getStartDate() {
        return StartDate;
    }
    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    //DueDate
    public String getDueDate() {
        return DueDate;
    }
    public void setDueDate(String dueDate) {
        DueDate = dueDate;
    }

    //Submission method
    public String getSubMethod() {
        return SubMethod;
    }
    public void setSubMethod(String subMethod) {
        SubMethod = subMethod;
    }
}
