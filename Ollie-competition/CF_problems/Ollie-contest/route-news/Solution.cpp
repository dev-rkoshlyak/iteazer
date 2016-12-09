// Wsl_F@ITeazer

#include <bits/stdc++.h>

using namespace std;

typedef pair<int, int> pii;

#define mp(x,y)  make_pair((x),(y))


int main()
{
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int n;
    cin >> n;
    int distance[] = {0, 0, 0, 0};
    
    for (int i = 0; i < n; i++) 
    {
        char dir;
        int dist;
        int index = -1;
        cin >> dir >> dist;
        switch (dir) 
        {
            case 'N' : 
                index = 0;
                break;
            case 'E' : 
                index = 1;
                break;
            case 'W' :
                index = 2;
                break;
            default:
            index = 3;
        }
        
        distance[index] += dist;
    }
    
    for (int i = 0; i < 4; i++)
        cout << distance[i] << endl;
    
    
    return 0;
}
