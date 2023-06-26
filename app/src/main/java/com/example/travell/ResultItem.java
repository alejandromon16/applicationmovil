package com.example.travell;

public class ResultItem {
    private String departureTime;
    private String arrivalTime;
    private int durationAproxInMins;
    private String company;
    private String companyLogo; // Assuming it's a drawable resource ID
    private String typeOfSeat;
    private int price;
    private String origin;
    private String destination;


    public ResultItem(String departureTime, String arrivalTime, int durationAproxInMins,
                      String company, String companyLogo, String typeOfSeat, int price,
                      String origin, String destination) {
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.durationAproxInMins = durationAproxInMins;
        this.company = company;
        this.companyLogo = companyLogo;
        this.typeOfSeat = typeOfSeat;
        this.price = price;
        this.origin = origin;
        this.destination = destination;
    }
    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public int getDurationAproxInMins() {
        return durationAproxInMins;
    }

    public String getCompany() {
        return company;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public String getTypeOfSeat() {
        return typeOfSeat;
    }

    public int getPrice() {
        return price;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }
}
