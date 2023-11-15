// Jake Roman's Crate Calculator
#include <iostream>

int main()
{
    // Internal variables
    float labor_cost = 0.5; // Percentage of the build cost we are charging for labor

    // Crate dimensions (feet)
    std::cout << "|| Crate Settings\n";
    float crate_width;
    float crate_length;
    float crate_height;
    std::cout << "Enter Crate Dimensions in Feet (width length height):\n";
    std::cin >> crate_width >> crate_length >> crate_height;

    // Material settings
    std::cout << "\n|| Material Settings\n";
    std::cout << "Alder: $3.95, 2 lbs\n";
    float material_weight; // Weight per board foot of material in pounds
    float material_cost; // Price per board foot of material in dollars
    std::cout << "\nEnter Price Per Board Foot of Desired Material:\n";
    std::cin >> material_cost;
    std::cout << "\nEnter Weight Per Board Foot of Desired Material:\n";
    std::cin >> material_weight;

    // Calculate
    float material_board_feet = 0; // Internal variable to track amount of material used
    float crate_build_cost = 0; // Internal variable to track how much it would cost to build
    float crate_labor_cost = 0; // Internal variable to track how much it would cost to build
    float crate_weight = 0; // Internal variable to track how much the crate would weigh
    float crate_volume = 0; // Internal variable to track size of crate

    material_board_feet += (crate_width*crate_length); // Crate floor
    material_board_feet += (crate_length*crate_height*2); // Crate side walls
    material_board_feet += (crate_width*crate_height*2); // Crate front and back walls
    material_board_feet += (crate_width*crate_length); // Crate roof

    crate_build_cost = material_board_feet * material_cost; // Calculate build cost
    crate_labor_cost = crate_build_cost * labor_cost; // Calculate labor cost
    crate_weight = material_board_feet * material_weight; // Calculate weight
    crate_volume = crate_width * crate_length * crate_height; // Calculate volume

    // Display
    std::cout << "\n|| Calculation Output\n";
    std::cout << "Volume: " << crate_volume << " cubic feet\n";
    std::cout << "Weight: " << crate_weight << " lbs\n";
    std::cout << "Material Required: " << material_board_feet << " board feet\n";
    std::cout << "Material Cost: $" << crate_build_cost << "\n";
    std::cout << "Labor Cost: $" << crate_labor_cost << "\n";
    std::cout << "Total Cost: $" << crate_labor_cost + crate_build_cost << "\n";
    
    system("pause");
}
