#include <stdlib.h>
#include <string.h>

// Compare two strings lexicographically.
int compare(const void *a, const void *b) {
    return strcmp(*(char **)a, *(char **)b);
}

char** inArray(char* array1[], int sz1, char* array2[], int sz2, int* lg) {
    char** result = malloc(sz1 * sizeof(char*));
    int count = 0;
    for (int i = 0; i < sz1; i++) {
        for (int j = 0; j < sz2; j++) {
            if (strstr(array2[j], array1[i]) != NULL) {
                result[count] = array1[i];
                count++;
                break;
            }
        }
    }
    *lg = count;

    // sort the result array lexically
    qsort(result, count, sizeof(char*), compare);
    return result;
}