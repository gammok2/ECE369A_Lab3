#include <iostream>
#include <vector>
#include <fstream>
#include <string>
using std::cout;
using std::cin;
using std::vector;
using std::ifstream;

int main(){
    int framesize = 16;
    int windowsize = 4;

    vector<vector<int>> frame(framesize, vector<int>(framesize));

    vector<vector<int>> window(windowsize, vector<int>(windowsize));

    ifstream infile("test.txt");
    if (!infile) {
        cout << "Failed to open test.txt\n";
        return -1;
    }

    // Read values into frame and print with aligned columns
    int max_width = 0;
    // First, read all values and find max width for formatting
    for (int i = 0; i < framesize; i++) {
        for (int j = 0; j < framesize; j++) {
            if (!(infile >> frame[i][j])) {
                cout << "Not enough values in names.txt\n";
                return 1;
            }
            int width = std::to_string(frame[i][j]).length();
            if (width > max_width) max_width = width;
        }
    }

    // Print with aligned columns
    for (int i = 0; i < framesize; i++) {
        for (int j = 0; j < framesize; j++) {
            cout.width(max_width + 1);
            cout << frame[i][j];
        }
        cout << "\n";
    }

    cout << "\n";
    cout << "\n";
    cout << "\n";
    
    // Fill window with values from test.txt (start from beginning)
    for (int i = 0; i < windowsize; i++) {
        for (int j = 0; j < windowsize; j++) {
            if (!(infile >> window[i][j])) {
                cout << "Not enough values in test.txt for window\n";
                return 1;
            }
            cout << window[i][j] << " ";
        }
        cout << "\n";
    }
    infile.close();

    return 0;
}