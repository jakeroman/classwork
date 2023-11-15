// Jake Roman / Test Score Program
#include <iostream>

void insertionSort(float *array, int size) {
    // this is not my function
    float key;
    int address;
    for(int i = 1; i<size; i++) {
        key = array[i]; // take value
        address = i;
        while(address > 0 && array[address-1]>key) {
            array[address] = array[address-1];
            address--;
        }
        array[address] = key; // insert in right place
    }
}

float averageScores(float *array, int size) {
    float total = 0;
    for (int i = 0; i < size; i++) {
        total += array[i];
    }
    return total/size;
}

int main() {
    // ask for array size
    system("cls");
    std::cout << "\n Scoring Program | Define Size\n";
    int scoreCount;
    while (scoreCount < 1) {
        // input validation
        std::cout << "\n Number of test scores? ";
        std::cin >> scoreCount;
        if (scoreCount < 1) {
            std::cout << " Invalid size.";
        }
    }

    // gather test scores
    float scores[scoreCount];
    system("cls");
    std::cout << "\n Scoring Program | Input Scores\n\n";
    for (int i = 0; i < scoreCount; i++) {
        float score = -1;
        while (score < 0) {
            std::cout << " Score #" + std::to_string(i+1) + ": ";
            std::cin >> score;
            if (score < 0) {
                std::cout << " Invalid score.\n";
            }
        }
        scores[i] = score;
    }

    // sort test scores
    insertionSort(scores, scoreCount);

    // output data
    system("cls");
    std::cout << "\n Scoring Program | Output\n\n ";
    for (int i = 0; i < scoreCount; i++) {
        printf("%.2f", scores[i]);
        std::cout << "%\n ";
    }
    std::cout << "\n Average: ";
    printf("%.2f", averageScores(scores, scoreCount));
    std::cout << "%\n\n ";
    system("pause");
}
