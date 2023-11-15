// Jake Roman's Hospital Calculator
#include <iostream>
using namespace std;

float calculate_cost(float days_stayed, float daily_rate, float medication_cost, float actual_service) 
{
    return (days_stayed * daily_rate) + (medication_cost + actual_service);
}

float calculate_cost(float medication_cost, float actual_service) 
{
    return (medication_cost + actual_service);
}

int main()
{
    // Variables
    float days_stayed = 0;
    float daily_rate = 0;
    float medication_cost = 0;
    float actual_service = 0;

    // Service Type Input
    std::cout << "Hospital Service Cost Calculator\n";
    std::cout << "Select service type:\n";
    std::cout << "1. Inpatient\n";
    std::cout << "2. Outpatient\n";
    float choice = 0;
    bool choice_valid = false;
    while (choice_valid == false)
    {
        std::cin >> choice;
        if (choice == 1 || choice == 2)
            choice_valid = true;
    }

    // Information Input
    std::cout << "\nInput service information:\n";
    if (choice == 1) {
        std::cout << "Days stayed? ";
        while (days_stayed <= 0)
        {
            std::cin >> days_stayed;
        }
        
        std::cout << "Daily rate? $";
        while (daily_rate <= 0)
        {
            std::cin >> daily_rate;
        }
    }

    std::cout << "Medication cost? $";
    while (medication_cost <= 0)
    {
        std::cin >> medication_cost;
    }

    std::cout << "Actual service cost? $";
    while (actual_service <= 0)
    {
        std::cin >> actual_service;
    }

    // Process Information
    float cost;
    if (choice == 1) {
        cost = calculate_cost(days_stayed, daily_rate, medication_cost, actual_service);
    }
    else if (choice == 2) {
        cost = calculate_cost(medication_cost, actual_service);
    }

    // Output Data
    std::cout << "\nTotal Cost: $";
    printf("%.2f\n", cost);

    system("Pause");
}
