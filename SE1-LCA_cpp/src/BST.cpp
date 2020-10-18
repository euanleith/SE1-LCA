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

bool BST::contains(int val) {
    return get(val, root) != nullptr;
}

BST::Node* BST::get(int val, Node* node) {
    if (node == nullptr) return nullptr;
    if (val > node->val) return get(val, node->right);
    else if (val < node->val) return get(val, node->left);
    return node;
}

int BST::lca(int val1, int val2) {
    if (!contains(val1) || !contains(val2)) return -1;
    return lca(val1, val2, root);
}

int BST::lca(int val1, int val2, Node* node) {
    if (val1 > node->val && val2 > node->val) return lca(val1, val2, node->right);
    else if (val1 < node->val && val2 < node->val) return lca(val1, val2, node->left);
    else return node->val;
}