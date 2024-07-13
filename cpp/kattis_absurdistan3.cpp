#include <algorithm>
#include <iostream>
#include <functional>
#include <vector>
#include <bitset>
#include <queue>
#include <math.h>
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

using namespace std;

int aug(int u, const vector<vi> &AL, vi &match, vb &visited) {
    if (visited[u]) return 0;
    visited[u] = true;

    for (auto &v : AL[u]) {
        if (match[v] == -1 || aug(match[v], AL, match, visited)) {
            match[v] = u;
            return 1;
        }
    }

    return 0;
}

int main () {
    int n; fin >> n;
    vector<vi> AL(2*n,vi());

    for (int i = 0; i < n; i++) {
        int x, y; fin >> x >> y;
        x--; y--;
        int eNum = n + i;

        AL[eNum].push_back(x);AL[eNum].push_back(y);
        AL[x].push_back(eNum);AL[y].push_back(eNum);
    }

    vi match(2*n, -1);
    vb visited(2*n, false);
    for (int i = 0; i < n; i++) {
        visited.assign(2*n, false);
        aug(i, AL, match, visited);
    }

    for (int i = 0; i < n; i++) {
        int tCity = AL[n + i][0] == match[n + i] ? AL[n + i][1] : AL[n + i][0];
        cout << match[n + i] + 1 << " " << tCity + 1 << endl;
    }

    return 0;
}