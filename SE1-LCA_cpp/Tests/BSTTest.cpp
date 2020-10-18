//
// Created by Euan Leith on 17/10/2020.
//

#include "gtest/gtest.h"
#include "BST.h"

TEST(BSTTest, containsTest) {
    BST * bst = new BST();

    EXPECT_FALSE(bst->contains(2));

    bst->put(2);
    bst->put(4);
    bst->put(1);
    bst->put(3);

    EXPECT_TRUE(bst->contains(2));
    EXPECT_FALSE(bst->contains(5));
}

TEST(BSTTest, lca) {
    BST * bst = new BST();

    EXPECT_EQ(-1, bst->lca(1, 4));

    bst->put(2);
    bst->put(2);
    bst->put(4);
    bst->put(1);
    bst->put(3);
    bst->put(6);

    EXPECT_EQ(2, bst->lca(1, 4));
    EXPECT_EQ(4, bst->lca(6, 3));
    EXPECT_EQ(-1, bst->lca(1, 5));
}
