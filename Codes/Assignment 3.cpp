#include <bits/stdc++.h>

using namespace std;

const int  SIZE = 5;

string cities[SIZE] = {"PUNE", "MUMBAI", "NAGPUR", "AMRAVATI", "DELHI"};

int paths[SIZE][SIZE] = {
    {0,67,-1,-1,345},
    {67,0,-1,45,-1},
    {89,-1,0,-1,-1},
    {-1,-1,-1,0,-1},
    {-1,-1,-1,95,0}
};

int indexOfCity(string cityName){
    transform(cityName.begin(), cityName.end(), cityName.begin(), ::toupper);
    for(int i =0; i< SIZE; i++){

        if( cityName == cities[i]){
            return i;
        }
    }
        return -1;
}

void checkPath(){
    string from, to;
    cout<<" THE ULTIMATE PATH FINDER  "<<endl;
    cout<<"Enter the details below : "<<endl;
    cout<<"From : ";
    cin>>from;
    cout<<"To : ";
    cin>>to;
    //cout<<indexOfCity(from)<<"  "<<indexOfCity(to)<<endl;
    if(paths[indexOfCity(from)][indexOfCity(to)] != -1){
        if(paths[indexOfCity(from)][indexOfCity(to)] == 0)
        {
            cout<<"Please change your destination city ."<<endl;
        }
        else{
            cout<<"We found the path."<<endl;
            cout<<"The Distance between two cities is "<<paths[indexOfCity(from)][indexOfCity(to)]<<" km"<<endl;
        }
    }
    else{
        cout<<"Sorry there is no path between these two cities"<<endl;
    }
}

int main()
{
    checkPath();
    return 0;
}
