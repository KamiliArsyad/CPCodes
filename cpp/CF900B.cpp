#include <algorithm>
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
        int n; fin >> n;
        vi ans(n);
        // Create the ans
        for (int i = 0; i < n; ++i) {
            if (i == 0) {
                ans[i] = 1;
                continue;
            }
            if (i == 1) {
                ans[i] = 3;
                continue;
            }

            int prev = ans[i - 1] + ans[i - 2];
            int start = ans[i-1] + 1;
            while ((3*start % prev) == 0) {
                start++;
            }
            ans[i] = start;
        }
        for (int x : ans) {
            cout << x << " ";
        }
        cout << endl;
    }

    return 0;
}