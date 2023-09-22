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
        int n, x; fin >> n >> x;
        vi arr(n);
        for (int i = 0; i < n; i++) {
            fin >> arr[i];
        }
        sort(arr.begin(), arr.end());
        int vol = 0;
        vi prefixVol(n);
        for (int i = 1; i < n; i++) {
            prefixVol[i] = prefixVol[i-1] + (arr[i] - arr[i-1]) * i;
        }
        if (prefixVol[n-1] < x) {
            cout << arr[n-1] + (x - prefixVol[n-1]) / n << endl;
            continue;
        }

        for (int i = 0; i < n; i++) {
            if (prefixVol[i] > x) {
                break;
            } else {
                vol = i;
            }
        }

        cout << arr[vol] + (x - prefixVol[vol]) / (vol + 1) << endl;
    }

    return 0;
}
