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
        bool answered = false;
        for (int i = 0; i < 3; i++) {
            string s; fin >> s;
            if (answered) continue;

            for (int j = 0; j < 3; j++) {
                if (s[j] == '?') {
                    bool a, b, c;
                    a = b = c = false;
                    for (int k = 0; k < 3; k++) {
                        if (s[k] == 'A') a = true;
                        if (s[k] == 'B') b = true;
                        if (s[k] == 'C') c = true;
                    }

                    // Print the one absent
                    if (!a) {
                        cout << 'A';
                    } else if (!b) {
                        cout << 'B';
                    } else if (!c) {
                        cout << 'C';
                    }

                    answered = true;
                    break;
                }
            }
        }
        cout << endl;
    }

    return 0;
}