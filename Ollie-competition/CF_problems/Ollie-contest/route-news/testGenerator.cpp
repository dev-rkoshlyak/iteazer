// Wsl_F@ITeazer

#include <bits/stdc++.h>

using namespace std;
#pragma comment(linker, "/STACK:1024000000,1024000000")


typedef long long LL;
typedef unsigned long long uLL;
typedef double dbl;
typedef vector<int> vi;
typedef vector<LL> vL;
typedef vector<string> vs;
typedef pair<int, int> pii;
typedef pair<LL, LL> pLL;

#define mp(x,y)  make_pair((x),(y))
#define pb(x)  push_back(x)
#define sqr(x) ((x)*(x))

// maximum size (height & width) of maze
const int MaxN = 10*1000;
const int MAX_DIST = 1000;

vector<pair<char, int>> generate(int n, int maxDist, int p1, int p2, int p3, int p4)
{
	vector<pair<char, int>> v;

	p2 += p1;
	p3 += p2;
	p4 += p3;
	for (int i = 0; i < n; i++)
	{
		int p = rand() % p4;
		char direct = 0;
		if (p < p1) direct = 'N';
		else if (p < p2) direct = 'E';
		else if (p < p3) direct = 'W';
		else direct = 'S';

		int dist = rand() % maxDist;

		v.pb(mp(direct, dist));
	}

	return v;
}

vector<pair<char, int>> generateDirect(int n, int maxDist, char direct)
{
	vector<pair<char, int>> v;

	for (int i = 0; i < n; i++)
	{
		int dist = rand() % maxDist;
		v.pb(mp(direct, dist));
	}

	return v;
}

void print(vector<pair<char, int>> v, string fName)
{
	ofstream out(fName.c_str());
	int n = v.size();
	out<< n <<"\r\n";
	for (int i = 0; i < n; i++)
	{
		out << v[i].first << " " << v[i].second << "\r\n";
	}

	out.close();
}


void generate(int n, int minL, int maxL, int k)
{
	string prefix = "tests/";

	int sz = (maxL - minL) / k;
	int part = n / k;
	int curL = minL;
	for (int i = 1; i <= n; i++)
	{
		if ( i % part == 0)
		{
			curL += sz;
			print(generateDirect(curL, MAX_DIST, 'N'), prefix + to_string(curL) + "_N");
			print(generateDirect(curL, MAX_DIST, 'E'), prefix + to_string(curL) + "_E");
			print(generateDirect(curL, MAX_DIST, 'W'), prefix + to_string(curL) + "_W");
			print(generateDirect(curL, MAX_DIST, 'S'), prefix + to_string(curL) + "_S");
		}

		print(generate(curL, MAX_DIST, 1, 1, 1, 1), prefix + to_string(curL) + "_" + to_string(i));
	}
}

int main()
{
    ios_base::sync_with_stdio(0);

	generate(10, 1, 10, 3);
	generate(50, 50, MaxN, 10);

	return 0;
}
