// Wsl_F@ITeazer

#include <bits/stdc++.h>

using namespace std;

typedef pair<int, int> pii;

#define mp(x,y)  make_pair((x),(y))

// maximum size (height & width) of maze
const int MaxN = 105;
// impossibly high distance
const int infDist = 1000*1000*1000;

const int ROAD = 0;
const int WALL = -1;

//  0    - road
// -1    - walls (non road)
int maze[MaxN][MaxN];
int distanceToWalls[MaxN][MaxN];

void read(int &rowsNumber, int &columnsNum)
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

int findWall(int x, int y, int rowsNumber, int columnsNum) 
{
    if (maze[x][y] == WALL) 
        return 0;
    
    int d = infDist;
    for (int i = 1; i <= rowsNumber; i++)
        for (int j = 1; j <= columnsNum; j++)
            if (maze[i][j] == WALL)
                d = min(d, abs(x-i) + abs(y-j));
    
    return d;
}

void calcDistanceToWalls(int rowsNumber, int columnsNum)
{
    for (int i = 1; i <= rowsNumber; i++)
        for (int j = 1; j <= columnsNum; j++)
            distanceToWalls[i][j] = findWall(i,j,rowsNumber,columnsNum);
}


int main()
{
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    // number of lines in current maze
    int rowsNumber = 0;
    // number of columns in current maze
    int columnsNum = 0;
    
    read(rowsNumber, columnsNum);
    calcDistanceToWalls(rowsNumber, columnsNum);
    
    for (int i = 1; i <= rowsNumber; i++)
    {
        for (int j = 1; j <= columnsNum; j++)
            cout << distanceToWalls[i][j]<<" ";
        cout<<endl;
    }
    
    return 0;
}
