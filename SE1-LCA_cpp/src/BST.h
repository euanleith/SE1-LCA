//
// Created by Euan Leith on 07/10/2020.
//

#ifndef SE1_LCA_BST_H
#define SE1_LCA_BST_H


class BST {
public:
    class Node {
    public :
        int val;
        Node *left, *right;

        Node(int val) {
            this->val = val;
            left = nullptr;
            right = nullptr;
        }
    };

    Node* root;

    void put(int val);

    static Node* put(int val, Node* node);

    bool contains(int val);

    static Node* get(int val, Node* node);

    int lca(int val1, int val2);

    static int lca(int val1, int val2, Node* node);
};


#endif //SE1_LCA_BST_H
