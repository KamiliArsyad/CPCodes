#include <algorithm>
#include <iostream>
#include <functional>
#include <vector>
#include <bitset>
#include <queue>
#include <unordered_set>
#include <unordered_map>
#include <cstring>
#include <stack>
#include <chrono>

// Define macro for sync_with_stdio(false)
#define fin std::ios::sync_with_stdio(false); std::cin

typedef std::pair<int, int> ii;
typedef std::vector<int> vi;
typedef std::vector<short> vs;
typedef std::vector<ii> vii;
typedef std::vector<bool> vb;
typedef long long ll;
typedef std::tuple<int, ll, ll> edge;
typedef std::pair<int, std::bitset<20>> ibits;
const int INF = 1e9;
const int MINUS_INF = -1e9;

using namespace std;

int main () {
    string bunnies; fin >> bunnies; // E (empty) or B (bunny)
    int len = bunnies.length();

    auto calculateCost = [&](int pivot) {
        int countLeft = 0;
        int countRight = 0;
        int costLeft = 0;
        int costRight = 0;
        for (int i = 0; i < len; i++) {
            if (bunnies[i] == 'B') {
                if (i < pivot) {
                    costLeft += (pivot - countLeft - 1) - i;
                    countLeft++;
                } else if (i > pivot) {
                    costRight += i - (pivot + countRight + 1);
                    countRight++;
                }
            }
        }
        return costLeft + costRight;
    };

    int minCost = INF;
    for (int i = 0; i < len; i++) {
        if (bunnies[i] == 'B') {
            minCost = min(minCost, calculateCost(i));
        }
    }

    cout << minCost << endl;

    return 0;
}