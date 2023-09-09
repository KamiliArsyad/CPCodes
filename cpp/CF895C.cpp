#include <iostream>
#include <functional>
#include <vector>
#include <bitset>
#include <queue>
#include <unordered_set>
#include <cstring>
#include <stack>
#include <cmath>

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

// From gfg
#include <bits/stdc++.h>
using namespace std;
const int MAX = 1e7 + 1;

// Auxiliary function to pre-compute
// the answer for each array
void preCalculate(vector<int>& phi,
                  vector<int>& ans)
{
    phi[0] = 0;
    phi[1] = 1;

    // Iterate over the range [1, MAX]
    for (int i = 2; i <= MAX; i++)
        phi[i] = i;

    // Iterate over the range [1, MAX]
    for (int i = 2; i <= MAX; i++) {

        // If the number is prime
        if (phi[i] == i) {

            for (int j = i; j <= MAX; j += i)

                // Subtract the number of
                // pairs which has i as one
                // of their factors
                phi[j] -= (phi[j] / i);
        }
    }

    // Iterate over the range [1, MAX]
    for (int i = 1; i <= MAX; i++)
        ans[i] = ans[i - 1] + (i - phi[i]);
}

// Function to count the number of
// non co-prime pairs for each query
void countPairs(int* arr, int N)
{
    // The i-th element stores
    // the count of element that
    // are co-prime with i
    vector<int> phi(1e7, 0);

    // Stores the resulting array
    vector<int> ans(1e7, 0);

    // Function Call
    preCalculate(phi, ans);

    // Traverse the array arr[]
    for (int i = 0; i < N; ++i) {
        cout << ans[arr[i]] << " ";
    }
}


int main() {
    int n;
    fin >> n;

    while (n-- > 0) {
        int l, r;
        fin >> l >> r;

        int arr[r];
        for (int i = 0; i < r; i++) {
            arr[i] = i + 1;
        }

        next:;
    }

    return 0;
}

