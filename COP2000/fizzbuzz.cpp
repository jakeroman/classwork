// Jake Roman / Fizzbuzz
#include <iostream>

int main() {
    int fizz = 0;
    int buzz = 0;
    int fizzbuzz = 0;

    for (int i=1; i <= 100; i++) {
        if (i % 3 == 0 && i % 5 == 0) {
            // fizzbuzz
            std::cout << "fizzbuzz";
            fizzbuzz++;
        }
        else if (i % 3 == 0) {
            // fizz
            std::cout << "fizz";
            fizz++;
        }
        else if (i % 5 == 0) {
            // buzz
            std::cout << "buzz";
            buzz++;
        }
        else {
            // neither
            std::cout << std::to_string(i);
        }
        std::cout << "\n";
    }

    std::cout << "\nfizz:\t\t" + std::to_string(fizz);
    std::cout << "\nbuzz:\t\t" + std::to_string(buzz);
    std::cout << "\nfizzbuzz:\t" + std::to_string(fizzbuzz) + "\n";
}
