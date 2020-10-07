//
// Created by Euan Leith on 07/10/2020.
//

#include <iostream>
#include "BST.h"

void BST::put(int val) {
    root = put(val, root);
}

BST::Node* BST::put(int val, Node* node) {
    if (node == nullptr) return new Node(val);
    if (val > node->val) node->right = put(val, node->right);
    else if (val < node->val) node->left = put(val, node->left);
    else node->val = val;
    return node;
}

int BST::LCA(int val1, int val2) {
    return LCA(val1, val2, root);
}

int BST::LCA(int val1, int val2, Node* node) {
    if (val1 > node->val && val2 > node->val) return LCA(val1, val2, node->right);
    else if (val1 < node->val && val2 < node->val) return LCA(val1, val2, node->left);
    else return node->val;
}

int main() {
    BST* bst = new BST();
    bst->put(2);
    bst->put(4);
    bst->put(1);
    bst->put(3);
    bst->put(6);

    std::cout << bst->LCA(1, 4) << std::endl;
    std::cout << bst->LCA(3, 6) << std::endl;
    return 0;
}