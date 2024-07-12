#include <iostream>
typedef long long ll;


int main () {
    ll a,b; std::cin >> a >> b;
    if (a%2==1) {
        std::cout << 0;
        return 0;
    }

    ll tmp = a/2;
    for (int i = 2; i < b; i*=2) {
        tmp = tmp % a;
        tmp = tmp * tmp % a;
        if (b%2==1) tmp = tmp * a / 2;
    }

    tmp = tmp % a;
    std::cout << tmp;

    return 0;
}