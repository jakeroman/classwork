// Jake Roman's Temperature calculator
#include <iostream>

int main()
{
    float fTemp;
    float cTemp;
    float factorA = 5;
    float factorB = 9;

    std::cout << "Enter Fahrenheit Temperature:\n";
    std::cin >> fTemp;

    cTemp = (fTemp - 32)*(factorA/factorB);

    std::cout << "Celsius: " << cTemp << "*C\n";;

    system("Pause");
}
