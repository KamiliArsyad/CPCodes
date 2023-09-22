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

int main () {
    int n, s, m;
    fin >> n >> s >> m;
    s--;
    vb visited(n, false);
    vi arr(n);
    int idx = s;
    for (int i = 0; i < n; i++) {
        fin >> arr[i];
    }
    int count = 0;
    while (true) {
        if (idx >= n) {
            std::cout << "right" << std::endl;
            break;
        }
        if (idx < 0) {
            std::cout << "left" << std::endl;
            break;
        }
        if (arr[idx] == m) {
            std::cout << "magic" << std::endl;
            break;
        }
        if (visited[idx]) {
            std::cout << "cycle" << std::endl;
            break;
        }
        visited[idx] = true;
        idx += arr[idx];
        count++;
    }
    std::cout << count << std::endl;
    return 0;
}