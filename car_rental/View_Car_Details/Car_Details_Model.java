package com.sparklab.car_rental.View_Car_Details;

public class Car_Details_Model {
    public String getForm() {
        return Form;
    }

    public void setForm(String form) {
        Form = form;
    }

    private String Form;
private String To;
private String Description;

    public Car_Details_Model() {
    }


    public String getTo() {
        return To;
    }

    public void setTo(String to) {
        To = to;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
