// Wsl_F@ITeazer

#include "testlib.h"
#include <bits/stdc++.h>

const int MaxN = 2000;

using namespace std;

int main()
{
	registerValidation();
	
	int n = inf.readInt(1, MaxN, "n");
	inf.readSpace();
	int m = inf.readInt(1, MaxN, "m");
	inf.readEoln();

	for (int i = 0; i < n; i++) 
	{
		string s = inf.readString();
		for (char c : s) 
		{
			ensuref(c == ' ' || c == '*', "Wrong character");
		}
		ensuref(s.length() == m, "Wrong length of the string");
	}

	inf.readEof();
	return 0;
}
