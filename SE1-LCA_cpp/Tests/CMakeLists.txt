project(Tests)
add_subdirectory(lib)

include_directories(${gtest_SOURCE_DIR}/include ${gtest_SOURCE_DIR})
include_directories(SYSTEM ../src)

add_executable(Google_Tests_run BSTTest.cpp)
target_link_libraries(Google_Tests_run gtest gtest_main)