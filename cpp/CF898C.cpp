#include <iostream>
#include <functional>
#include <vector>
#include <bitset>
#include <queue>
#include <unordered_set>
#include <cstring>
#include <stack>
#include <chrono>

// Define macro for sync_with_stdio(false)
#define opt std::ios::sync_with_stdio(false);
#define in std::cin
#define fin opt in

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
    int t; fin >> t;

    while (t--) {
        ll score = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                char c; fin >> c;
                if (c == 'X') {
                    int ring = i == 0 || j == 0 || i == 9 || j == 9
                            ? 1
                            : i == 1 || j == 1 || i == 8 || j == 8
                                ? 2
                                : i == 2 || j == 2 || i == 7 || j == 7
                                    ? 3
                                    : i == 3 || j == 3 || i == 6 || j == 6
                                        ? 4
                                        : 5;
                    score += ring;
                }
            }
        }
        cout << score << endl;
    }

    return 0;
}
