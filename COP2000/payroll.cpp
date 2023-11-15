// Jake Roman / Payroll
#include <iostream>

const int employees = 7;

long int empId[employees] {
    8658845,
    4520125,
    7895122,
    8777541,
    8451277,
    1302850,
    7580489,
};
int hours[employees];
double payRate[employees];
double wages[employees];

// Program
int main() {
    // Input
    for (int i=0; i<employees; i++) {
        system("cls");
        std::cout << "Payroll | Input Information\n";
        std::cout << "---------------------------\n";
        std::cout << "Employee #" + std::to_string(empId[i]) + "\n\n";

        int inputHours = -1;
        while (inputHours < 0) {
            std::cout << "Hours Worked? ";
            std::cin >> inputHours;
        }

        int inputPayRate = -1;
        while (inputPayRate < 15) {
            std::cout << "Hourly Rate? ";
            std::cin >> inputPayRate;
        }

        wages[i] = inputPayRate * inputHours;
    }

    // Output
    system("cls");
    std::cout << "Employee\tWages\n";
    std::cout << "----------------------------\n";
    for (int i=0; i<employees; i++) {
        std::cout << "#" + std::to_string(empId[i]) + "\t$";
        printf("%.2f\n", wages[i]);
    }
}
