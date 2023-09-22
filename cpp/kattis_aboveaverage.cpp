#include <iostream>
#include <iomanip>
#include <functional>
#include <vector>
#include <bitset>
#include <queue>
#include <unordered_set>
#include <cstring>
#include <cmath>
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
const int MAX_N = 1000;

int main() {
    int c; fin >> c;

    while(c--) {
        int N; fin >> N;
        int sum=0;
        vi inp;
        for (int i = 0; i < N; i++) {
            int in0; fin >> in0;
            sum+=in0;
            inp.push_back(in0);
        }
        double mean = sum/N;
        int count=0;
        for (int i = 0; i < N; i++) {
            if (inp[i] > mean) count++;
        }
        double res = (double)count/N*100;
        std::cout << std::fixed << std::setprecision(3) << res << "%" << std::endl;
    }

    return 0;
}
