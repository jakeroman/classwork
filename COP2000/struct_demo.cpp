// Jake Roman / Assignment 8
#include <iostream>

struct engine {
    int current_rpms;
    int max_rpms;
    int num_cylinders;
    float displacement;

    engine(int iCurrent_rpms, int iMax_rpms, int iNum_cylinders, float fDisplacement) {
        current_rpms = iCurrent_rpms;
        max_rpms = iMax_rpms;
        num_cylinders = iNum_cylinders;
        displacement = fDisplacement;
    };
};

bool is_safe(engine eng) {
    return (eng.current_rpms < eng.max_rpms);
}

int main() {
    // 5000 rpm
    engine car_engine(5000, 6000, 8, 6.0);
    std::cout << "5000 rpm case:\n";
    std::cout << (is_safe(car_engine) ? "true" : "false");

    // 6100 rpm
    car_engine.current_rpms = 6100;
    std::cout << "\n6100 rpm case:\n";
    std::cout << (is_safe(car_engine) ? "true" : "false");
}
