// Change for a Dollar Game / Jake Roman
#include <iostream>
#include <string>

int main()
{
    // Variables
    float quarters = 0;
    float dimes = 0;
    float nickels = 0;
    float pennies = 0;
    float total = 0;

    // Input
    std::cout << "Change for a Dollar\n";
    std::cout << "Mix together each type of coin so that it totals one dollar\n";
    std::cout << "\nHow many quarters? ";
    std::cin >> quarters;
    std::cout << "\nHow many dimes? ";
    std::cin >> dimes;
    std::cout << "\nHow many nickels? ";
    std::cin >> nickels;
    std::cout << "\nHow many pennies? ";
    std::cin >> pennies;

    // Calculation
    total = (quarters * 0.25F) + (dimes * 0.1F) + (nickels * 0.05F) + (pennies * 0.01);
    
    // Output
    std::cout << "\n"; // Make whitespace to keep things neat
    if (total == 1) {
        std::cout << "Great job!\nYour coins add up to 1 dollar.\n";
    }
    else if (total > 1) {
        std::cout << "Sorry, that's too many coins\nYour coins add up to " + std::to_string(total) + " dollars\n";
    }
    else if (total < 1) {
        std::cout << "Sorry, that's not enough coins\nYour coins add up to " + std::to_string(total) + " dollars\n";
    }

    system("pause");
}
