#include <iostream>
#include <queue>
#include <algorithm>
#include <string>
#include <vector>
#include <deque>
#include <stack>
#include <set>
#include <math.h>
#include <string.h>

using namespace std;

int n, x, y, two = 1;
int g[2][2] = { {0,1} ,{2,3} };

int calc(int x, int y, int div) {
	if (div <= 0) return 0;
	return g[(x / div) % 2][(y / div) % 2] * div * div + calc(x, y, div / 2);
}

int main() {
	cin >> n >> x >> y;
	for (int i = 0; i < n; i++) {
		two *= 2;
	}
	cout << calc(x, y, two / 2);
	return 0;
}