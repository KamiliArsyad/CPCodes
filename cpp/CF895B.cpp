#include <iostream>
#include <functional>
#include <vector>
#include <bitset>
#include <queue>
#include <unordered_set>
#include <cstring>
#include <stack>
#include <cmath>

// Define macro for sync_with_stdio(false)
#define opt std::ios::sync_with_stdio(false);
#define in std::cin
#define fin opt in

typedef std::pair<int, int> ii;
typedef std::vector<int> vi;
typedef std::vector<ii> vii;
typedef std::vector<bool> vb;
typedef std::pair<int, std::bitset<20>> ibits;
const int INF = 1e9;
const int MINUS_INF = -1e9;

const int MAX_N = 1000;

int main() {
    int n;
    fin >> n;

    while (n-- > 0) {
        int t;
        fin >> t;
        vi traps(MAX_N, -1);

        for (int i = 0; i < t; i++) {
            int di, si;
            fin >> di >> si;
            traps[di] = traps[di] == -1 ? si : std::min(traps[di], si);
        }

        int max = 5000;

        for (int i = 0; i < max; i++) {
            if (traps[i] != -1) {
                max = std::min(max, i + (traps[i] - 1)/2);
            }
        }

        std::cout << max << std::endl;
    }

    return 0;
}
