package com.epam.training.gen.ai.dto;

public record VacationDetails (
    Integer id,
    String destination,
    String days,
    int numberOfTravelers,
    String accommodationType,
    String activities,
    String budget
) {
    public static VacationDetails of(Integer id, String destination, String days, int numberOfTravelers, String accommodationType, String activities, String budget) {
        return new VacationDetails(id, destination, days, numberOfTravelers, accommodationType, activities, budget);
    }

    public String toString() {
        return "VacationDetails{" +
                ", days='" + days + '\'' +
                ", numberOfTravelers=" + numberOfTravelers +
                ", accommodationType='" + accommodationType + '\'' +
                ", activities='" + activities + '\'' +
                ", budget='" + budget + '\'' +
                '}';
    }
}
