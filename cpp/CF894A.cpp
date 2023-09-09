#include <iostream>
#include <functional>
#include <vector>
#include <bitset>
#include <queue>
#include <unordered_set>
#include <cstring>
#include <stack>

// Define macro for sync_with_stdio(false)
#define opt std::ios::sync_with_stdio(false);
#define in std::cin
#define fin opt in

typedef std::pair<int, int> ii;
typedef std::vector<int> vi;
typedef std::vector<ii> vii;
typedef std::vector<bool> vb;
typedef std::pair<int, std::bitset<20>> ibits;
const int INF = 1e9;
const int MINUS_INF = -1e9;

const int MAX_N = 30;

int main() {
    int t;
    fin >> t;

    while (t-- > 0) {
        int n, m;
        fin >> n >> m;

        int sawV=-1;
        int sawI=-1;
        int sawK=-1;
        int sawA=-1;

        for (int i = 0; i < n; i++) {
            // get m character
            std::string s;
            fin >> s;
            for (int j = 0; j < m; j++) {
                if (s[j] == 'v' && sawV == -1) {
                    sawV = j;
                } else if (j > sawV && s[j] == 'i' && sawI == -1) {
                    sawI = j;
                } else if (j > sawI && s[j] == 'k' && sawK == -1) {
                    sawK = j;
                } else if (j > sawK && s[j] == 'a' && sawA == -1) {
                    sawA = j;
                }
            }

            if (sawV != -1 && sawI != -1 && sawK != -1 && sawA != -1) {
                std::cout << "YES" << std::endl;
                goto next;
            }
        }

        std::cout << "NO" << std::endl;
        next:;
    }

    return 0;
}
