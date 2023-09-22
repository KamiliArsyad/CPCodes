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
const int MAX = 1002;

int main() {
    int r, c;
    fin >> r >> c;
    char map[MAX][MAX];

    for (int i = 0; i < r; i++) {
        std::string s;
        fin >> s;
        for (int j = 0; j < c; j++) {
            map[i][j] = s[j];
        }
    }

    int component[MAX][MAX];
    memset(component, -1, sizeof(component));
    int componentCount = 0;

    auto bfs = [&](int i, int j, int componentCount, int component[MAX][MAX]) {
        std::queue<ii> q;
        q.emplace(i, j);

        while (!q.empty()) {
            ii u = q.front(); q.pop();
            int f = u.first;
            int s = u.second;

            // make sure not out of bounds for component
            if (f < 0 || f >= r || s < 0 || s >= c) continue;

            if (component[f][s] != -1) continue;
            component[f][s] = componentCount;

            if (f > 0 && map[f - 1][s] == map[f][s]) q.emplace(f - 1, s);
            if (f < r - 1 && map[f + 1][s] == map[f][s]) q.emplace(f + 1, s);
            if (s > 0 && map[f][s - 1] == map[f][s]) q.emplace(f, s - 1);
            if (s < c - 1 && map[f][s + 1] == map[f][s]) q.emplace(f, s + 1);
        }
    };

    for (int i = 0; i < r; i++) for (int j = 0; j < c; j++) {
        if (component[i][j] == -1) {
            bfs(i, j, componentCount, component);
            componentCount++;
        }
    }

    int n; fin >> n;
    while (n--) {
        int r1, c1, r2, c2;
        fin >> r1 >> c1 >> r2 >> c2;
        r1--; c1--; r2--; c2--;

        if (component[r1][c1] != component[r2][c2]) {
            std::cout << "neither" << std::endl;
        } else {
            std::cout << (map[r1][c1] == '0' ? "binary" : "decimal") << std::endl;
        }
    }

    return 0;
}
