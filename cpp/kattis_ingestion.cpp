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
typedef std::pair<int, std::bitset<20>> ibits;
const int INF = 1e9;
const int MINUS_INF = -1e9;

using namespace std;

int main() {
    int n, m; fin >> n >> m;
    vi calories(n);
    for (int i=0; i < n; i++) {
        fin >> calories[i];
    }
    vi dp[n];
    for (int i=0; i < n; i++) {
        dp[i] = vi(m+1, -1);
    }
    function<int(int, int, int)> solve = [&](int idx, int cap, int prevcap) {
        if (idx >= n) return 0;
        if (dp[idx][cap] != -1) return dp[idx][cap];
        return dp[idx][cap] = max(
                solve(idx+1, cap * 2/3, cap) + min(calories[idx], cap),
                max(solve(idx+1, prevcap, cap),
                    solve(idx+2, m, cap)
                )
        );
    };

    cout << solve(0, m, m) << endl;

    return 0;
}