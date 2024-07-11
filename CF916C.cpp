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
        int n, k; fin >> n >> k;
        vi fc(n, 0);
        vi sc(n, 0);
        for (int i = 0; i < n; i++) {
            fin >> fc[i];
        }
        for (int i = 0; i < n; i++) {
            fin >> sc[i];
        }

        ll xp = 0;
        int maxsc = 0; // max sc[i] for i <= ptr
        int lives = k;
        xp += fc[0];
        lives--;
        maxsc = sc[0];
        int sumtmp = 0;
        int livestmp = 0;
        for (int i = 1; i < n && i < lives - livestmp; i++) {
            int candidatemax = max(maxsc, sc[i]);
            livestmp++;
            if (maxsc * lives <= fc[i] + sumtmp + candidatemax * (lives - livestmp)) {
                // take fc[i]
                xp += fc[i] + sumtmp;
                lives -= (livestmp);
                livestmp = 0;
                sumtmp = 0;
                maxsc = candidatemax;
            } else {
                // take maxsc
                sumtmp += fc[i];
            }
        }

        xp += maxsc * lives;

        cout << xp << endl;
    }

    return 0;
}