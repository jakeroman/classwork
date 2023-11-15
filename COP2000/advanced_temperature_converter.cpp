// Jake Roman's Temperature Calculator
#include <iostream>

float factorA = 5;
float factorB = 9;

float fahrenheit(float celsius)
{
    return (celsius/(factorA/factorB))+32;
}

float celsius(float fahrenheit)
{
    return (fahrenheit - 32)*(factorA/factorB);
}

int main()
{
    // Selection
    int choice = 0;
    std::cout << "Temperature Converter\n";
    std::cout << "1. Convert Fahrenheit to Celsius\n";
    std::cout << "2. Convert Celsius to Fahrenheit\n";
    std::cin >> choice;

    // Input
    float inputTemp;
    if (choice == 1) {
        std::cout << "\nPlease Input Temperature in Fahrenheit: ";
    }
    else if (choice == 2) {
        std::cout << "\nPlease Input Temperature in Celsius: ";
    }
    std::cin >> inputTemp;

    // Calculate
    float outputTemp;
    if (choice == 1) {
        // Fahrenheit to Celsius
        outputTemp = celsius(inputTemp);
    }
    else if (choice == 2) {
        // Celsius to Fahrenheit
        outputTemp = fahrenheit(inputTemp);
    }

    std::cout << "Result: ";
    std::cout << outputTemp;
    std::cout << "\n";

    system("Pause");
}
