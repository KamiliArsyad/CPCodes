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

using namespace std;

// Define macro for sync_with_stdio(false)
#define opt ios::sync_with_stdio(false); cin
#define fin opt

typedef std::pair<int, int> ii;
typedef std::vector<int> vi;
typedef std::vector<short> vs;
typedef std::vector<ii> vii;
typedef std::vector<bool> vb;
typedef long long ll;
typedef std::pair<int, std::bitset<20>> ibits;
const int INF = 1e9;
const int MINUS_INF = -1e9;


int main() {
    ll n; fin >> n;
    int k = 0;
    int y = 2;
    while (y * y <= n) {
        if (n % y == 0) {
            n /= y;
            k++;
        } else {
            y++;
        }
    }
    cout << k + (n != 1) << endl;
    return 0;
}