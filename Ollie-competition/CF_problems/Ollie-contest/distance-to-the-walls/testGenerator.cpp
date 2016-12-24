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
const int MaxN = 2000;

char maze[MaxN][MaxN];

void generate(int n, int m, int p)
{
	for (int i = 0; i < n; i++)
		for (int j  = 0; j < m; j++)
			maze[i][j] = rand() % p == 0 ? '*' : ' ';
}


void generateBorder(int n, int m)
{
    for (int i = 0; i < n; i++)
        for (int j = 0; j < m; j++)
            maze[i][j] = ' ';
    for (int i = 0; i < n ; i++)
        maze[i][0] = '*';
    for (int i = 0; i < n ; i++)
        maze[i][m-1] = '*';

    for (int j = 0; j < m; j++)
        maze[0][j] = '*';
    for (int j = 0; j < m; j++)
        maze[n-1][j] = '*';

}

void generateBorder2(int n, int m)
{
    for (int i = 0; i < n; i++)
        for (int j = 0; j < m; j++)
            maze[i][j] = ' ';
    for (int i = 0; i < n ; i++)
        maze[i][0] = '*';
}


void generateBorder3(int n, int m)
{
    for (int i = 0; i < n; i++)
        for (int j = 0; j < m; j++)
            maze[i][j] = ' ';

    maze[n/2][m/2] = '*';
}

void generateBorder4(int n, int m)
{
    for (int i = 0; i < n; i++)
        for (int j = 0; j < m; j++)
            maze[i][j] = ' ';

    maze[n-1][m-1] = '*';
}

void print(int n, int m, string fName)
{
	ofstream out(fName.c_str());
	out<<n<<" "<<m<<"\r\n";
	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < m-1; j++)
			out<<maze[i][j];
		out<<maze[i][m-1]<<"\r\n";
	}
	out.close();
}


void generate(int n, int m) 
{
	string prefix = to_string(n) + "x" + to_string(m) + "/";
	
	generateBorder(n,m);
    print(n,m,prefix+"border1");
	
	generateBorder2(n,m);
    print(n,m,prefix+"border2");
	
	generateBorder3(n,m);
    print(n,m,prefix+"border3");
	
	generateBorder4(n,m);
    print(n,m,prefix+"border4");
	
	srand(n*m + m-1);
	for (int t = 2; t < 12; t++)
	{
		generate(n, m, t);
		print(n,m, prefix + "walls=1:" + to_string(t));
	}

	int p = max(n,m);
	generate(n, m, p);
	print(n,m,prefix + "walls=1:" + to_string(p));

	p = max( min(n,m)*min(n,m)/10, 1);
	generate(n, m, p);
	print(n,m,prefix + "walls=1:" + to_string(p));
}

int main()
{
    ios_base::sync_with_stdio(0);
	int n = 100;
	int m = 100;

	generate(n,m);
	
	return 0;
}
