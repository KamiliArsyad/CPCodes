#include <inttypes.h>

uint32_t ips_between (const char *start, const char *end) {
    // Parse the IPv4 addresses
    uint32_t start_ip = 0, end_ip = 0;
    int dot_count = 0;
    int num = 0;

    while (*start != '\0') {
        if (*start == '.') {
            start_ip = (start_ip << 8) + num;
            num = 0;
            dot_count++;
        } else {
            num = num * 10 + (*start - '0');
        }
        start++;
    }

    start_ip = (start_ip << 8) + num;
    num = 0;
    dot_count = 0;

    while (*end != '\0') {
        if (*end == '.') {
            end_ip = (end_ip << 8) + num;
            num = 0;
            dot_count++;
        } else {
            num = num * 10 + (*end - '0');
        }
        end++;
    }

    end_ip = (end_ip << 8) + num;

    return end_ip - start_ip;
}