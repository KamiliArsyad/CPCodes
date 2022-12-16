#include <vector>
#include <queue>

long queueTime(std::vector<int> customers, int n){
    std::priority_queue<int> pq;

    long time = 0;
    for (int i = 0; i < (int) customers.size(); i++) {
        if (pq.size() < n) {
            pq.push(-customers[i] - time);
        } else {
            time = -pq.top();

            while (!pq.empty() && -pq.top() == time) {
                pq.pop();
            }

            pq.push(-customers[i] - time);
        }
    }

    while (!pq.empty()) {
        time = -pq.top();
        pq.pop();
    }

    return time;
}