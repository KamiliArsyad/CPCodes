#include <algorithm>
#include <iostream>
#include <functional>
#include <vector>
#include <bitset>
#include <queue>
#include <unordered_set>
#include <unordered_map>
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
    int N; fin >> N;

    // -1: not visited, 0: visited once, x > 0: length of cycle
    vector<int> cycle(26, -1);
    unordered_map<int, int> firstOccurence;
    int maxPos = 0;
    int mx = -1;


    for (int i = 0; i < N; i++) {
        int loc; string word; fin >> loc >> word;

        // For every letter c, if cycle[c] == -1, then cycle[c] = 0
        for (char c : word) {
            if (cycle[c - 'a'] > 0) {
                continue;
            }
            if (cycle[c - 'a'] == -1) {
                cycle[c - 'a'] = 0;
                firstOccurence[c - 'a'] = i;
            } else {
                cycle[c - 'a'] = i - firstOccurence[c - 'a'];
                maxPos = cycle[c - 'a'] > mx ? c - 'a' : maxPos;
                mx = max(mx, cycle[c - 'a']);
            }
        }
    }

    // Print the char with the longest cycle
    cout << (char) (maxPos + 'a') << endl;

    return 0;
}