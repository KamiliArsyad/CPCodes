#include <algorithm>
#include <iostream>
#include <functional>
#include <vector>
#include <bitset>
#include <queue>
#include <sstream>
#include <unordered_set>
#include <cstring>
#include <stack>
#include <chrono>

// Define macro for sync_with_stdio(false)
#define fin std::ios::sync_with_stdio(false); std::cin

typedef std::pair<int, int> ii;
typedef std::vector<int> vi;
typedef std::vector<short> vs;
typedef std::vector<ii> vii;
typedef std::vector<bool> vb;
typedef long long ll;
typedef std::tuple<int, ll, ll> edge;
typedef std::pair<int, std::bitset<20>> ibits;
const int INF = 1e9;
const int MINUS_INF = -1e9;

using namespace std;

// I create this class only because the task told me to do so even though the task can be solved without it
class Stack {
    private:
        vi arr;
        int top;
    public:
        Stack() { top = -1; }
        void push(int x);
        int pop();
        bool isEmpty();
};

void Stack::push(int x) {
    arr.push_back(x);
    top++;
}

int Stack::pop() {
    if (top == -1) {
        return -1;
    }
    int x = arr[top];
    arr.pop_back();
    top--;
    return x;
}

bool Stack::isEmpty() {
    return top == -1;
}

int main() {
    string line;
    while (getline(cin, line)) {
        Stack s;
        stringstream ss(line);

        int num;
        int count=0;
        while (ss >> num) {
            s.push(num);
            count++;
        }

        bool toPrint = true;
        for (int i = 0; i < count; i++) {
            int x = s.pop();
            if (toPrint) {
                cout << x << " ";
                toPrint = false;
            } else {
                toPrint = true;
            }
        }

        cout << endl;
    }
}