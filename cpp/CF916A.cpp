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

using namespace std;

int main () {
    int t; fin >> t;

    while (t--) {
        int n; fin >> n;
        vi alphabet(26, 0);
        for (int i = 0; i < n; i++) {
            char c; fin >> c;
            alphabet[c - 'A']++;
        }
        int countSolve = 0;
        for (int i = 0; i < 26; i++) {
            if (alphabet[i] >= i + 1) {
                // problem i solved
                countSolve++;
            }
        }
        cout << countSolve << endl;
    }

    return 0;
}