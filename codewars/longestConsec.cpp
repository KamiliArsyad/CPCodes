#include <string>
#include <vector>

class LongestConsec {
public:
    static std::string longestConsec(const std::vector<std::string> &strarr, int k);
};

std::string LongestConsec::longestConsec(const std::vector<std::string> &strarr, int k) {
    if (k <= 0 || k > (int) strarr.size()) {
        return "";
    }

    int max = 0;
    int index = 0;

    for (int i = 0; i < (int) strarr.size() - k + 1; i++) {
        int sum = 0;
        for (int j = i; j < i + k; j++) {
            sum += strarr[j].length();
        }
        if (sum > max) {
            max = sum;
            index = i;
        }
    }

    std::string result = "";
    for (int i = index; i < index + k; i++) {
        result += strarr[i];
    }

    return result;
}
