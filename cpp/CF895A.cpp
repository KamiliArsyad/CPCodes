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

const int MAX_N = 30;

int main() {
    int n;
    fin >> n;

    while (n-- > 0) {
        int a, b, c;
        fin >> a >> b >> c;
        double eq = (a + b) / 2;
        int stepsNeeded = b > a ? std::ceil((b - eq) / c) : std::ceil((a - eq) / c);
        std::cout << stepsNeeded << std::endl;
    }

    return 0;
}
