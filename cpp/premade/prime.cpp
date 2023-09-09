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
typedef std::vector<ii> vii;
typedef std::vector<bool> vb;
typedef long long ll;
typedef std::pair<int, std::bitset<20>> ibits;
const int INF = 1e9;
const int MINUS_INF = -1e9;

bool isPrime(ll n) {
    if (n == 1) return false;
    if (n == 2) return true;
    if (n % 2ll == 0) return false;
    for (ll i = 3; i * i <= n; i += 2ll) {
        if (n % i == 0) return false;
    }
    return true;
}

// -------------------------------
// sieve of eratosthenes
// -------------------------------
ll _sieve_size;
std::bitset<10000010> bs;
vi primes;
void sieve(ll upperbound) {
    _sieve_size = upperbound + 1;
    bs.set();
    bs[0] = bs[1] = 0;

    for (ll i = 2; i <= _sieve_size; i++) if (bs[i]) {
        for (ll j = i * i; i <= _sieve_size; j += i) bs[j] = 0;
        primes.push_back((int)i);
    }
}

int main() {
}