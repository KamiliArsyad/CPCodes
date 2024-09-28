#include <algorithm>
#include <iostream>
#include <functional>
#include <vector>
#include <bitset>
#include <queue>
#include <math.h>
#include <unordered_set>
#include <cstring>
#include <math.h>
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
typedef std::tuple<int, int, int> output;
const int INF = 1e9;
const int MINUS_INF = -1e9;

using namespace std;

int main () {
    int t; fin >> t;

    while (t--) {
        int n, d, k; fin >> n >> d >> k;

        vi start(n + 1, 0), end(n + 1, 0);
        for (int i = 0; i < k; i++) {
            int li, ri; fin >> li >> ri;
            start[li] += 1;
            end[ri] += 1;
        }

        // Build prefix
        for (int i = 1; i <= n; i++) {
            start[i] += start[i-1];
            end[i] += end[i-1];
        }

        int min = INF;
        int max = 0;
        int bestR = 0;
        int bestM = 0;

        for (int i = d; i <= n; i++) {
            int diff = start[i] - end[i - d];
            if (diff < min) {
                bestM = i - d + 1;
                min = diff;
            }
            if (diff > max) {
                max = diff;
                bestR = i - d + 1;
            }
        }

        cout << bestR << " " << bestM << endl;
    }

    return 0;
}