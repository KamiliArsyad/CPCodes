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
        int n; fin >> n;
        vi arr(n);
        int maxIdx = 0;
        for (int i = 0; i < n; i++) {
            fin >> arr[i];
            if (arr[i] < arr[maxIdx]) {
                maxIdx = i;
            }
        }

        ll product = 1;
        for (int i = 0; i < n; i++) {
            if (i != maxIdx) {
                product *= arr[i];
            } else {
                product *= arr[i] + 1;
            }
        }

        cout << product << endl;
    }
    return 0;
}
