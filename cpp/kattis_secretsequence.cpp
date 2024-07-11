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
typedef std::pair<int, std::bitset<20>> ibits;
const int INF = 1e9;
const int MINUS_INF = -1e9;
const int MAX = 1002;

int main() {
    int n; fin >> n;

    auto query = [&](int w, int x, int y, int z) {
        std::cout << '?' << ' ' << w << ' ' << x << ' ' << y << ' ' << z << std::endl;
        std::cout.flush();
        int result; fin >> result;
        return result;
    };

    auto test = [&]() {return true;};




    return 0;
}
