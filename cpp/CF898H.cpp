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
        int n, a, b; fin >> n >> a >> b;
        a--; b--;

        vi AL[n];
        for (int i = 0; i < n; i++) {
            int u, v;
            fin >> u >> v;
            u--; v--;
            AL[u].push_back(v);
            AL[v].push_back(u);
        }

        if (a == b) {
            cout << "NO" << endl;
            continue;
        }

        int next = 0;
        int count = 0;
        vb visited(n, false);
        vb visited2(n, false);
        while (true) {
            if (visited[next]) {
                break;
            }
            visited[next] = true;
            int deg = 0;
            for (auto x : AL[next]) {
                if (visited[x]) {
                    deg++;
                }
            }

            count++;

            if (deg > 1) {
                // Cycle found
                break;
            }

            for (auto x : AL[next]) {
                if (!visited[x]) {
                    next = x;
                    break;
                }
            }
        }
        if (count == n) {
            // Cycle is the entire graph
            cout << "YES" << endl;
            continue;
        }
        // now that cycle has been found, find the pivot (the node that connects the cycle to the rest of the graph)
        int pivot = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                next = i;
                break;
            }
        }

        // Perform BFS to find pivot
        queue<int> q;
        q.push(next);
        visited2[next] = true;
        while (!q.empty()) {
            int u = q.front(); q.pop();
            if (visited[u]) {
                pivot = u;
                break;
            }
            for (auto v : AL[u]) {
                if (!visited2[v]) {
                    visited2[v] = true;
                    q.push(v);
                }
            }
        }

        // Find distance from a to pivot
        vb visited3(n, false);
        q = queue<int>();
        q.push(a);
        visited3[a] = true;
        int dist_a = 0;
        while (!q.empty()) {
            int u = q.front(); q.pop();
            if (u == pivot) {
                break;
            }
            for (auto v : AL[u]) {
                if (!visited3[v]) {
                    visited3[v] = true;
                    q.push(v);
                }
            }
            dist_a++;
        }

        // Find distance from b to pivot
        vb visited4(n, false);
        q = queue<int>();
        q.push(b);
        visited4[b] = true;
        int dist_b = 0;
        while (!q.empty()) {
            int u = q.front(); q.pop();
            if (u == pivot) {
                break;
            }
            for (auto v : AL[u]) {
                if (!visited4[v]) {
                    visited4[v] = true;
                    q.push(v);
                }
            }
            dist_b++;
        }

        if (dist_a >= dist_b) {
            cout << "YES" << endl;
        } else {
            cout << "NO" << endl;
        }
    }

    return 0;
}
