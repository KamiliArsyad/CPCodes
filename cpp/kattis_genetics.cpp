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
const int MAX = 4101;

int main() {
    int n, m, k;
    fin >> n >> m >> k;
    bool isAns[MAX];
    memset(isAns, true, sizeof isAns);
    ll rn[MAX];
    ll aux[MAX][4];

    std::vector<vs> v(n + 1, vs(m));

    for (int i = 1; i <= n; i++) {
        std::string s;
        fin >> s;
        for (int j = 0; j < m; j++) {
            int c = s[j] - 'A';
            c = c == 0 ? 0 : c == 2 ? 1 : c == 6 ? 2 : 3; // A, C, G, T
            v[i][j] = c;
        }
    }

    int ret = 10;
    srand(time(NULL));
    while (ret--) {
        memset(aux, 0, sizeof aux);
        ll sum = 0;

        for (int i = 1; i <= n; i++) {
            rn[i] = (rand() << 16) | rand();
            sum += rn[i] * k;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < m; j++) {
                aux[j][v[i][j]]+=rn[i];
            }
        }

        for (int i = 1; i <= n; i++) {
            ll x = 0;
            for (int j = 0; j < m; j++) {
                if (v[i][j] != 0) x += aux[j][0];
                if (v[i][j] != 1) x += aux[j][1];
                if (v[i][j] != 2) x += aux[j][2];
                if (v[i][j] != 3) x += aux[j][3];
            }

            if (x != sum - rn[i] * k) {
                // This is probably not the answer
                isAns[i] = false;
            }
        }
    }

    for (int i = 1; i <= n; i++) {
        if (isAns[i]) {
            std::cout << i << std::endl;
            return 0;
        }
    }

    std::cout << -1 << std::endl;

    return 0;
}
