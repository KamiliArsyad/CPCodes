#include <stdbool.h>
#include <stdlib.h>

int cmpp(const void *a, const void *b) {
    return *(int *)a - *(int *)b;
}

bool comp(const int *a, const int *b, size_t n) {
    // create an array of size n to store the squares of a
    int *squares = malloc(n * sizeof(int));
    for (int i = 0; i < n; i++) {
        squares[i] = a[i] * a[i];
    }

    // sort squares and a copy of b
    qsort(squares, n, sizeof(int), cmpp);
    int *b_copy = malloc(n * sizeof(int));
    for (int i = 0; i < n; i++) {
        b_copy[i] = b[i];
    }
    qsort(b_copy, n, sizeof(int), cmpp);

    for (int i = 0; i < n; i++) {
        if (squares[i] != b_copy[i]) {
            return false;
        }
    }

    return true;
}