cmake_minimum_required (VERSION 2.6)

project (subProcess)

add_executable(subProcess subProcess.cpp)

set(CEF_HOME "/home/dschaefer/cef/cef_binary")

target_include_directories(subProcess PUBLIC
	${CEF_HOME}
)

target_link_libraries(subProcess
	${CEF_HOME}/Release/libcef.so
)
