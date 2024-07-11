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
        vb word(n, false);
        vb separators(n, false); // s[i] true if there is a separator before word[i]
        string s; fin >> s;

        for (int i = 0; i < n; i++) {
            word[i] = s[i] == 'a' || s[i] == 'e';
        }

        int start = n - 1;
        while (start > 1) {
            if (word[start]) {
                separators[start - 1] = true;
                start -= 2;
            } else {
                separators[start - 2] = true;
                start -= 3;
            }
        }

        for (int i = 0; i < n; i++) {
            if (separators[i] && i > 0) cout << '.';
            cout << s[i];
        }
        cout << endl;
    }

    return 0;
}