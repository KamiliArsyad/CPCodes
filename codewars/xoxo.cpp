#include <string>

bool XO(const std::string& str)
{
    int countx = 0;
    int counto = 0;

    for (int i = 0; i < (int) str.length(); i++) {
        countx = str[i] == 'x' || str[i] == 'X' ? countx + 1 : countx;
        counto = str[i] == 'o' || str[i] == 'O' ? counto + 1 : counto;
    }

    return countx == counto;
}