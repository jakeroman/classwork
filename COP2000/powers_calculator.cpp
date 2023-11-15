// Jake Roman / Mathematical Power Function
#include <iostream>

int power(int base, int exponent) {
    int number = base;
    for (int i = 1; i < exponent; i++) {
        number *= base;
    }
    return number;
}

int main() {
    int base, exponent;
    std::cout << "Base? ";
    std::cin >> base;
    std::cout << "Exponent? ";
    std::cin >> exponent;
    std::cout << "Result: " + std::to_string(power(base,exponent)) + "\n";
    system("pause");
}
