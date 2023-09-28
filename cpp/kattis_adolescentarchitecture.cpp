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

int main () {
    int n; fin >> n;
    vi blocks[2];

    for (int i = 0; i < n; i++) {
        string t; fin >> t;
        int s; fin >> s;
        if (t == "cylinder") {
            blocks[0].push_back(s);
        } else {
            blocks[1].push_back(s);
        }
    }

    sort(blocks[0].begin(), blocks[0].end());
    sort(blocks[1].begin(), blocks[1].end());
    vector<pair<string, int>> ans;

    int cylinder = 0, cube = 0;

    for (int i = 0; i < n; i++) {
        if (cylinder < blocks[0].size() && cube < blocks[1].size()) {
            double cyl = blocks[0][cylinder];
            double cub = blocks[1][cube];
            if (cub <= cyl*sqrt(2)) {
                ans.emplace_back("cube", blocks[1][cube]);
                cube++;
            } else if (2 * cyl <= cub) {
                ans.emplace_back("cylinder", blocks[0][cylinder]);
                cylinder++;
            } else {
                cout << "impossible" << endl;
                return 0;
            }
        } else if (cylinder < blocks[0].size()) {
            ans.emplace_back("cylinder", blocks[0][cylinder]);
            cylinder++;
        } else if (cube < blocks[1].size()) {
            ans.emplace_back("cube", blocks[1][cube]);
            cube++;
        }
    }

    for (auto [t, s] : ans) {
        cout << t << " " << s << endl;
    }

    return 0;
}