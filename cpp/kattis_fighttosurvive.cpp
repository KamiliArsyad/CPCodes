#include <algorithm>
#include <iostream>
#include <functional>
#include <vector>
#include <bitset>
#include <queue>
#include <math.h>
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

int main () {
    int s, m; fin >> s >> m;
    vi oHealth(s);
    vi cHealth(s);
    vi lastAttack(s, -1);
    for (int i = 0; i < s; i++) {
        fin >> oHealth[i];
        cHealth[i] = oHealth[i];
    }

    // pair(a,b) sorts by b (descending), then by oHealth[a]
    auto cmp = [&oHealth](ii a, ii b) {
        if (a.second == b.second) {
            return oHealth[a.first] < oHealth[b.first];
        }

        return a.second < b.second;
    };

    priority_queue<ii, vii, decltype(cmp)> pq(cmp);
    priority_queue<ii, vii, decltype(cmp)> wardPQ(cmp);

    for (int i = 0; i < s; i++) {
        pq.emplace(i, oHealth[i]);
    }

    int e; fin >> e;
    queue<int> zombieQueue;
    queue<int> restingQueue;

    for (int i = 0; i < e; i++) {
        bool isMedicineTime = (i + 1) % m == 0;
        if (isMedicineTime && !wardPQ.empty()) {
            ii top = wardPQ.top(); wardPQ.pop();
            cHealth[top.first] = floor(oHealth[top.first] / 2);
            lastAttack[top.first] = -1;
            pq.emplace(top.first, cHealth[top.first]);
        }

        // Get everyone who is resting
        if (i > 1
            && !restingQueue.empty()
            && lastAttack[restingQueue.front()] <= i - 2
        ) {
            while (!restingQueue.empty()
                && lastAttack[restingQueue.front()] <= i - 2
            ) {
                int idx = restingQueue.front(); restingQueue.pop();
                pq.emplace(idx, cHealth[idx]);
            }
        }

        string event; fin >> event;
        if (event == "ATTACK") {
            int zombie = zombieQueue.front(); zombieQueue.pop();
            while (zombie > 0) {
                if (pq.empty()) {
                    cout << "overrun" << endl;
                    return 0;
                }

                ii top = pq.top(); pq.pop();
                if (top.second > zombie) {
                    cHealth[top.first] -= zombie;
                    lastAttack[top.first] = i;
                    restingQueue.push(top.first);
                    zombie = 0;
                    continue;
                } else {
                    zombie -= top.second;
                    cHealth[top.first] = 0;
                    lastAttack[top.first] = i;
                    wardPQ.emplace(top.first, oHealth[top.first]);
                }
            }
        } else {
            int x; fin >> x;
            zombieQueue.push(x);
        }
    }

    cout << "success" << endl;

    return 0;
}