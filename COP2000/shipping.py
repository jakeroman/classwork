// Jake Roman's Shipping Calculator
#include <iostream>
using namespace std;

int main()
{
    // Variables
    float weight = 0; // Weight of package
    float priceTier = 0; // Price per pound based on package weight tier
    float speedFactor = 0; // Multiplier for shipping cost based on speed selection

    // Package Weight
    std::cout << "Shipping Cost Calculator\n";
    std::cout << "0-1 lbs: $5 per pound\n1-3 lbs: $3 per pound\n3-10 lbs: $2 per pound\n\n";
    std::cout << "Enter package weight: ";
    std::cin >> weight;

    if (weight > 3) {
        std::cout << "Price Tier: Large Items ($2/lb)\n";
        priceTier = 2;
    }
    else if (weight > 1) {
        std::cout << "Price Tier: Medium Items ($3/lb)\n";
        priceTier = 3;
    }
    else if (weight > 0) {
        std::cout << "Price Tier: Small Items ($5/lb)\n";
        priceTier = 5;
    }
    else {
        std::cout << "Invalid item weight.\n";
    }

    // Input
    int choice = 0;
    std::cout << "\nShipping Speed\n";
    std::cout << "1. Slow (standard)\n";
    std::cout << "2. Medium (10% extra)\n";
    std::cout << "3. Fast (50% extra)\n";
    std::cout << "Select: ";
    std::cin >> choice;

    if (choice == 1)
        speedFactor = 1;
    else if (choice == 2)
        speedFactor = 1.1;
    else if (choice == 3)
        speedFactor = 1.5;

    // Calculate
    float cost = ((weight * priceTier) * speedFactor);
    std::cout << "\nShipping: $";
    printf("%.2f\n", cost);

    system("Pause");
}
