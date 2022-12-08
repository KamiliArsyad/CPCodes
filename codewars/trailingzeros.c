// Count the number of trailing zeros in the nth factorial
long zeros(long n) {
    long count = 0;

    for (int i = 5; n/i >= 1; (i << 2) + i) {
        count += n / i;
    }

    return count;
}