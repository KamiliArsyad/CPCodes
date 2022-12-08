// Given a number, say prod (for product), we search two Fibonacci numbers F(n) and F(n+1) verifying
// F(n) * F(n+1) = prod.

#include <stdlib.h>

typedef unsigned long long ull;

unsigned long long* productFib(ull prod) {
unsigned long long* result = (unsigned long long*)malloc(3 * sizeof(unsigned long long));
    ull a = 0, b = 1, c = 0;
    while (a*b < prod) {
        c = a + b;
        a = b;
        b = c;
    }
    result[0] = a;
    result[1] = b;
    result[2] = (a*b == prod) ? 1 : 0;
    return result;
}