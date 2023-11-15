// Areas of Rectangles Jake Roman
#include <iostream>

int main()
{
    // Variables
    float r1width;
    float r1length;
    float r2width;
    float r2length;

    // Input
    std::cout << "Enter width and length of first rectangle\n";
    std::cin >> r1width >> r1length;

    std::cout << "Enter width and length of second rectangle\n";
    std::cin >> r2width >> r2length;

    // Calculate
    float r1area = r1width * r1length;
    float r2area = r2width * r2length;

    if (r1area == r2area) {
        std::cout << "Rectangles are the same area\n";
    }
    else if (r1area > r2area) {
        std::cout << "First rectangle has more area than the second rectangle\n";
    }
    else if (r1area < r2area) {
        std::cout << "Second rectangle has more area than the first rectangle\n";
    }

    system("pause");
}
