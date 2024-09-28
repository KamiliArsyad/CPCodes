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
        int n, k; fin >> n >> k;
        int start = n - k + 1;
        ll res = (n + 1) / 2 - start / 2;

        cout << (res % 2 == 0 ? "YES" : "NO") << endl;
    }

    return 0;
}