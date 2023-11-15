// Jake Roman / Assignment 7
#include <iostream>

int main() {
    const int numbers = 5;
    double array[numbers] {};
    
    for (int i = 0; i < numbers; i++) {
        std::cout << std::to_string(i+1) + ": ";
        std::cin >> array[i];
    }

    for (int i = numbers - 1; i >= 0; i--) {
        std::cout << std::to_string(array[i]) + "\n";
    }

    system("Pause");
}
