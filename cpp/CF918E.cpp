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
#include <map>

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
        vector<ll> evenPsum((n % 2 == 0 ? n/2 : (n/2) + 1), 0);
        vector<ll> oddPsum(n/2, 0);
        ll evenSum = 0, oddSum = 0;
        int evenIdx = 0, oddIdx = 0;
        for (int i = 0; i < n; i++) {
            ll x; fin >> x;
            if (i % 2 == 0) {
                evenSum += x;
                evenPsum[evenIdx++] = evenSum;
            } else {
                oddSum += x;
                oddPsum[oddIdx++] = oddSum;
            }
        }

        bool found = false;
        for (int l = 0; l < evenPsum.size() && !found; l++) {
            int low = l, high = evenPsum.size() - 1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                ll evenSum = evenPsum[mid] - (l > 0 ? evenPsum[l-1] : 0);
                ll oddSum = (mid < oddPsum.size() ? oddPsum[mid] : oddPsum.back()) - (l > 0 && l-1 < oddPsum.size() ? oddPsum[l-1] : 0);

                if (evenSum == oddSum) {
                    cout << "YES" << endl;
                    found = true;
                    break;
                } else if (evenSum < oddSum) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }

        if (!found) {
            cout << "NO" << endl;
        }
    }

    return 0;
}