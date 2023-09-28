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
        int n, k; fin >> n >> k;
        string s; fin >> s;
        vi left(k, 0), right(k, 0);
        for (int i = 0; i < k; ++i) {
            fin >> left[i];
        }
        for (int i = 0; i < k; ++i) {
            fin >> right[i];
        }
        int q; fin >> q;
        vi mods(q, 0);
        for (int i = 0; i < q; ++i) {
            fin >> mods[i];
        }

        unordered_map<int, int> rightToIndex;
        for (int i = 0; i < k; ++i) {
            rightToIndex[right[i]] = i;
        }
        sort(right.begin(), right.end());

        vii modifications;

        for (int i = 0; i < q; ++i) {
            // Find min right such that right >= mods[i] using lower_bound
            int minRight = right[lower_bound(right.begin(), right.end(), mods[i]) - right.begin()];
            int index = rightToIndex[minRight];
            int a = min(mods[i], minRight + left[index - 1] + 1 - mods[i]);
            int b = max(mods[i], minRight + left[index - 1] + 1 - mods[i]);
            modifications.emplace_back(a, b);
        }

        // For all modifications, reverse the string from a to b
        for (ii modification : modifications) {
            int a = modification.first;
            int b = modification.second;
            reverse(s.begin() + a - 1, s.begin() + b);
        }

        cout << s << endl;
    }

    return 0;
}