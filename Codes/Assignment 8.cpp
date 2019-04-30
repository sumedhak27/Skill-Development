#include <iostream>
#include<cstring>
#include<fstream>

using namespace std;

class Student{
private:
    char name[30], div, address[100];
    int rollNo;
public:
    Student(){
    }
    Student(char name[30],int rollNo,char div,char address[100])
    {
        strcpy(this->name, name);
        this->rollNo = rollNo;
        this->div = div;
        strcpy(this->address, address);
    }
    Student(const Student &s2){
        cout<<"I am here.."<<endl;
        strcpy(name,s2.name);
        rollNo = s2.rollNo;
        div = s2.div;
        strcpy(address,s2.address);

    }
    int getRollNo(){ return rollNo;}
    void displayStudentData(){
        cout<<":::::::::::::::::::::::::"<<endl;
        cout<<"Name : "<<this->name<<endl;
        cout<<"Roll No. : "<<this->rollNo<<endl;
        cout<<"Division : "<<this->div<<endl;
        cout<<"Address : "<<this->address<<endl;
        cout<<":::::::::::::::::::::::::"<<endl;
    }
};

void addStudentToFile(){
    char name[30], div, address[100];
    int rollNo;
    cout<<"Enter students Information : "<<endl;
    cout<<"Name : ";
    cin>>name;
    cout<<"Roll No. : ";
    cin>>rollNo;
    cout<<"Division : ";
    cin>>div;
    cout<<"Address : ";
    cin>>address;
    Student s(name, rollNo, div, address);

    ofstream outstream;
    outstream.open("students.txt", ios::app);
    outstream.write((char*)&s, sizeof(s));
}

void searchStudentFromFile(){
    cout<<"......Search........"<<endl;
    cout<<"Roll No. : ";
    int rollNo;
    cin>>rollNo;

    ifstream inStream;
    inStream.open("students.txt", ios::in);
    Student s,s2;
    inStream.read((char*)&s, sizeof(s));

    while(!inStream.eof()){
        if(s.getRollNo() == rollNo){
            s2 = s;
            break;
        }
        inStream.read((char*)&s, sizeof(s));
    }
    if(s2.getRollNo() == rollNo){
        s2.displayStudentData();
    }
    else{
        cout<<"Student is not found."<<endl;
    }

}


int main()
{
    char ch;
    do{
        cout<<"::::::::::::::::::::::::::::::"<<endl;
        cout<<"1.Add Student"<<endl<<"2.Search Student"<<endl;
        int choice;
        cin>>choice;
        switch(choice){
        case 1 :
            addStudentToFile();
            break;
        case 2 :
            searchStudentFromFile();
            break;
        default :
            cout<<"Wrong Input!!"<<endl;
        }
        cout<<"Do you want to continue?[Y/N]";
        cin>>ch;
    }while(ch=='y' || ch=='Y');

    return 0;
}
