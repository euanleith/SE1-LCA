//
// Created by Euan Leith on 17/10/2020.
//

#include "gtest/gtest.h"
#include "BST.h"

class BSTTest : public ::testing::Test {

protected:
    virtual void SetUp()
    {
        bst = new BST();
    }

    virtual void TearDown() {
        delete bst;
    }

    BST * bst;
};


TEST(BSTTest, putTest) {
    //todo
}

TEST(BSTTest, LCA) {
    //todo
}
