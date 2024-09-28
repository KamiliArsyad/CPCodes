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
        int n; fin >> n;
        vector<ll> wealth(n);

        ll sum = 0;
        for (int i = 0; i < n; i++) {
            ll in; fin >> in;
            sum += in;
            wealth[i] = in;
        }

        if (n == 1 || n == 2) {
            cout << -1 << endl;
            continue;
        }

        sort(wealth.begin(), wealth.end());
        cout << max(0LL, (wealth[n/2] * 2 * n) - sum + 1) << endl;
    }

    return 0;
}