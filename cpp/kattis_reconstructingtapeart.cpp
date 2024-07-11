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
typedef std::tuple<int, int, int> output;
const int INF = 1e9;
const int MINUS_INF = -1e9;

using namespace std;

vector<output> out;
unordered_set<int> wasPrinted;

bool print(int start, int end, int color) {
    if (wasPrinted.find(color) != wasPrinted.end()) {
        return false;
    }

    out.emplace_back(start, end, color);
    wasPrinted.insert(color);
    return true;
}

/**
 * Solves reconstructing tape art for the subproblem (from start to end, inclusive)
 * @return True if there's a solution.
 */
bool solve(int start, int end, vi &arr, vi &lastElement) {
    if (start == end) {
       bool can = print(start, end, arr[start]);
       if (!can) return false;
       return true;
    }

    int left = start; // arr[start] == arr[end] ? start + 1 : start;

    int startColor = arr[left];
    for (int i = start; i <= end; i++) {
        if (arr[i] == startColor) {
            if (i == end) {
                bool can = print(left, i, startColor);
                return can;
            }
            continue;
        }

        // This subproblem's start is continuous and ends at i - 1
        if (i - 1 == lastElement[startColor]) {
            bool can = print(left, i - 1, startColor);
            if (!can) return false;
            return solve(i, end, arr, lastElement);
        }

        // Find the next occurrence of the startColor
        int nextIdx = i - 1;
        for (int j = i; i <= end; j++) {
            if (arr[j] == startColor) {
                nextIdx = j;
                break;
            }
        }

        bool can = solve(i, nextIdx - 1, arr, lastElement);
        if (!can) return false;

        i = nextIdx;
    }

    return print(left, lastElement[startColor], startColor);
}

int main () {
    int n; fin >> n;
    vi ls;
    vi lastElement(n, -1);

    for (int i = 0; i < n; i++) {
        int l; fin >> l;
        ls.push_back(l);
    }

    for (int i = ls.size() - 1; i >= 0; i--) {
        if (lastElement[ls[i]] != - 1) continue;
        lastElement[ls[i]] = i;
    }

    bool can = solve(0, n - 1, ls, lastElement);
    if (!can) {
        cout << "IMPOSSIBLE" << endl;
        return 0;
    }

    // Print out in reverse order
    cout << out.size() << endl;
    for (int i = out.size() - 1; i >= 0; i--) {
        cout << get<0>(out[i]) + 1 << " " << get<1>(out[i]) + 1 << " " << get<2>(out[i]) << endl;
    }

    return 0;
}
