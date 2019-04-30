#include <iostream>

using namespace std;

const int TABLE_SIZE = 10;
int hashTable[TABLE_SIZE] ={0};

void addInTable(){
    int key;
    bool isPlaced = false;
    cout<<"Enter the key to be inserted in the table : ";
    cin>>key;
    int Hash1 = key % TABLE_SIZE;
    int Hash2 = 7 - (key % 7);
    if(hashTable[Hash1] == 0){
        hashTable[Hash1] = key;
        isPlaced = true;
    }
    else if(hashTable[Hash2] == 0){
        hashTable[Hash2] = key;
        isPlaced = true;
    }
    else{
        for(int i = 0;i<TABLE_SIZE; i++){
            if(hashTable[Hash1 + (i*Hash2)] == 0){
                hashTable[Hash1 + (i*Hash2)] = key;
                isPlaced = true;
            }
        }
    }
    if(!isPlaced){
        cout<<"The number is not inserted as array is full."<<endl;
    }
}

void displayTable(){
    for(int i = 0; i<TABLE_SIZE; i++){
        cout<<hashTable[i]<<"  ";
    }
    cout<<endl;
}

int main()
{
    char ch;
    do{
        cout<<"::::::::::::::::::::::::::::"<<endl;
        cout<<"1.add In hash Table"<<endl<<"2.show the table."<<endl;
        cout<<endl<<"Enter the choice : ";
        int choice;
        cin>>choice;
        switch(choice){
        case 1 :
            cout<<"How many elements do you want to add ? ";
            int no;
            cin>>no;
            while(no != 0){
                addInTable();
                no--;
            }
        break;
        case  2: displayTable();
        break;
        default: cout<<"Wrong Input !!"<<endl;
        }
        cout<<"Do you want to continue ? [Y/N] ";
        cin>>ch;
    }while(ch=='y' || ch=='Y');

    return 0;
}
