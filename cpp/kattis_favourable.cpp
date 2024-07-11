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
const int MAX_N = 401;

using namespace std;

int main () {
    int t; fin >> t;

    function<ll(vi[],vb&,vector<ll>&, int)> dfs = [&] (vi AL[], vb& visited, vector<ll>& ALu, int u) {
        if (visited[u]) return ALu[u];
        visited[u] = true;
        ll ans = 0;
        if (AL[u].size() == 1) {
            if (AL[u][0] == INF) ans++;
            return ALu[u] = ans;
        }

        for (auto v : AL[u]) {
            ans += dfs(AL, visited, ALu, v);
        }

        return ALu[u] = ans;
    };

    while (t--) {
        int s; fin >> s;
        vi AL[MAX_N];
        for (int i = 0; i < s; i++) {
            int pn; fin >> pn;
            string first; fin >> first;
            if (first != "favourably" && first != "catastrophically") {
                // `first` and the next two inputs are numbers
                int a, b; fin >> a >> b;
                AL[pn].push_back(stoi(first));
                AL[pn].push_back(a);
                AL[pn].push_back(b);
            } else if (first == "favourably") {
                AL[pn].push_back(INF);
            } else AL[pn].push_back(MINUS_INF);
        }

        vb visited(MAX_N, false);
        vector<ll> ALu(MAX_N, 0);
        ll ans = dfs(AL, visited, ALu, 1);
        cout << ans << endl;
    }

    return 0;
}