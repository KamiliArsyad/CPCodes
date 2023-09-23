#include <iostream>
using namespace std;
#define fin std::ios::sync_with_stdio(false); std::cin
#define ll long long
int main() {
    ll n;
    while (cin >> n) {
        ll p = 1, w = 1;
        while (p < n) {p*= 2+7*w; w = 1-w;}
        cout << (!w ? "Stan wins." : "Ollie wins.") << endl;
    }
}
