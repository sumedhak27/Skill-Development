#include <iostream>

using namespace std;

struct TreeNode{
    int num;
    TreeNode *lptr,*rptr;
    bool lth,rth;
};

class BST{
    private:

    public:
        TreeNode *headNode = NULL;
        TreeNode * root = NULL;
        BST(){
            headNode = new TreeNode();
            headNode->lptr = root;
            headNode->rptr = headNode;
            headNode->lth = false;
            headNode->rth = false;
            headNode->num = NULL;
        }


        string input;
        void createBST(){
            cout<<"Enter the number string : ";
            cin>>input;
            for(int i=0; input[i]!='\0'; i++){
                TreeNode * nn = new TreeNode;
                int currNum = (int)input[i] - 48;
                nn->num = currNum;
                // cout<<nn->num<<" ";
                nn->lptr = headNode;
                nn->rptr = headNode;
                nn->lth = true;
                nn->rth = true;
                if(root == NULL){
                    root = nn;
                }
                else{
                    bool isleft;
                    TreeNode* currNode = root;
                    TreeNode* parent = currNode;
                    while(currNode != headNode){
                        isleft = false;
                        parent = currNode;
                        if(currNode->num > nn->num){
                            if(currNode->lth != true){
                                currNode = currNode->lptr;
                                isleft = true;
                            }
                            else{
                                currNode = currNode->lptr;
                                isleft = true;
                                break;
                            }

                        }
                        else{
                            if(currNode->rth != true){currNode = currNode->rptr;}
                            else{
                                currNode = currNode->rptr;
                                break;
                            }
                        }
                    }

                    if(isleft){
                        nn->lptr = parent->lptr;
                        parent->lth = false;
                        parent->lptr = nn;
                        nn->rptr = parent;
                    }
                    else{
                        nn->rptr = parent->rptr;
                        parent->rth = false;
                        parent->rptr = nn;
                        nn->lptr = parent;
                    }
                }

            }
        }
        TreeNode *subRoot = root;
        void RecInfix(TreeNode *subRoot){
            if(subRoot!=NULL){
                RecInfix(subRoot->lptr);
                cout<<subRoot->num;
                RecInfix(subRoot->rptr);
            }
            else
                return;
        }

        TreeNode* leftmost(TreeNode *node){
            //cout<<"sumedh2";
            while(node->lth != true){
                //cout<<"sumedh3  ";
                // cout<<node->num <<" test " ;
                node = node->lptr;

            }
            return node;
        }

        void inorder(TreeNode * rooot){
            TreeNode *current = rooot;
            //cout<<current->num<<" test ";
            current = leftmost(current);
            while(current != headNode){
                cout<<current->num<<" ";
                if(current->rth)
                    current = current->rptr;
                else
                    current = leftmost(current->rptr);
            }
        }


        void Inorder(){
            TreeNode* temp = root;
            while(temp->lth == false){
                temp = temp->lptr;
            }
            while(temp != headNode){
                cout<<temp->num;
                if(temp->rth){
                    temp = temp->rptr;
                }
                else{
                    temp = temp->rptr;
                    while(temp->lth!=true)
                        temp = temp->lptr;
                }
            }
        }
};

int main()
{
    BST bst1;
    char ch;
    do{
        cout<<"     M E N U     "<<endl;
        cout<<"1. Create BSTree "<<endl<<"2. Threaded inorder "<<endl;
        cout<<endl<<"Enter the choice : ";
        int choice;
        cin>>choice;
        switch(choice){
            case 1 : bst1.createBST();
            break;
            case 2 :bst1.inorder(bst1.root);
                    cout<<endl;
            break;
            default :  cout<<"Sorry Wrong choice!!"<<endl;
        }
        cout<<"Do you want to continue?[y/n]";
        cin>>ch;
    }while(ch=='Y' || ch=='y');
    return 0;
}
