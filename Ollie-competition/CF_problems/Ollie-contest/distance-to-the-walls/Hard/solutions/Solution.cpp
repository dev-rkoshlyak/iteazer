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
const int MaxN = 2005;
// impossibly high distance
const int infDist = 1000*1000*1000;
// number of lines in current maze
int rowsNumber = 0;
// number of columns in current maze
int columnsNum = 0;


const int ROAD = 0;
const int WALL = -3;

//  0    - road
// -3    - walls (non road)
int maze[MaxN][MaxN];
int distanceToWalls[MaxN][MaxN];

void read()
{
    for (int i = 0; i < MaxN; i++)
        for (int j = 0; j < MaxN; j++)
            maze[i][j] = WALL;
    
    cin >> rowsNumber >> columnsNum;
    
    string s;
    getline(cin, s);
    for (int i = 0; i < rowsNumber; i++)
    {
        getline(cin, s);
        for (int j = 0; j < columnsNum; j++)
        {
            if (s[j] == ' ') maze[i+1][j+1] = ROAD;
            else maze[i+1][j+1] = WALL; //  (s[j] == '*') 
        }
    }
}


void calcDistanceToWalls()
{
    queue<pii> q;

    for (int i = 1; i <= rowsNumber; i++)
        for (int j = 1; j <= columnsNum; j++)
            if (maze[i][j] == WALL)
            {
                distanceToWalls[i][j] = 0;
                q.push(mp(i,j));
            }
            else
            {
                distanceToWalls[i][j] = infDist;
            }

    while (!q.empty())
    {
        pii cur = q.front();
        q.pop();
        int curRow = cur.first;
        int curColumn = cur.second;
        int nextVal = distanceToWalls[curRow][curColumn] + 1;

        if (curRow>0)
            if (distanceToWalls[curRow-1][curColumn] > nextVal)
            {
                distanceToWalls[curRow-1][curColumn] = nextVal;
                q.push(mp(curRow-1,curColumn));
            }
        if (curRow<rowsNumber)
            if (distanceToWalls[curRow+1][curColumn] > nextVal)
            {
                distanceToWalls[curRow+1][curColumn] = nextVal;
                q.push(mp(curRow+1,curColumn));
            }
        if (curColumn>0)
            if (distanceToWalls[curRow][curColumn-1] > nextVal)
                {
                    distanceToWalls[curRow][curColumn-1] = nextVal;
                    q.push(mp(curRow,curColumn-1));
                }
        if (curColumn<columnsNum)
            if (distanceToWalls[curRow][curColumn+1] > nextVal)
                {
                    distanceToWalls[curRow][curColumn+1] = nextVal;
                    q.push(mp(curRow,curColumn+1));
                }
    }

}


int main()
{
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    read();
    calcDistanceToWalls();
    
    for (int i = 1; i <= rowsNumber; i++)
    {
        for (int j = 1; j <= columnsNum; j++)
            cout << distanceToWalls[i][j]<<" ";
        cout<<endl;
    }
    
    return 0;
}
