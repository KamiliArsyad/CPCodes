#include <iostream>
#define fin std::ios::sync_with_stdio(false); std::cin

int main () {
    int n, b; fin >> n >> b;
    long double ans = 1;
    for (int i = 0; i < b; i++) {
        ans *= (n - i);
        ans /= (n + i + 1);
    }
    std::cout << ans * ans << std::endl;
    return 0;
}