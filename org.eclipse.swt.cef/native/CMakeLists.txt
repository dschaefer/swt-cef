cmake_minimum_required (VERSION 3.10)

set(CMAKE_EXPORT_COMPILE_COMMANDS ON)

project (subProcess)

find_package(PkgConfig REQUIRED)
pkg_check_modules(GTK3 REQUIRED gtk+-3.0)

include(ExternalProject)

set(CEF_DIR ${CMAKE_SOURCE_DIR}/../cef)
set(CEF_PREFIX ${CMAKE_CURRENT_BINARY_DIR}/cef)

ExternalProject_Add(cef-minimal
	PREFIX ${CEF_PREFIX}
    URL http://opensource.spotify.com/cefbuilds/cef_binary_73.1.12%2Bgee4b49f%2Bchromium-73.0.3683.75_linux64_minimal.tar.bz2
    INSTALL_COMMAND cmake -E copy_directory ${CEF_PREFIX}/src/cef-minimal/include ${CEF_DIR}/include
        COMMAND cmake -E copy_directory ${CEF_PREFIX}/src/cef-minimal/Release ${CEF_DIR}/lib
        COMMAND cmake -E copy_directory ${CEF_PREFIX}/src/cef-minimal/Resources ${CEF_DIR}/lib
        COMMAND cmake -E copy ${CEF_PREFIX}/src/cef-minimal-build/libcef_dll_wrapper/libcef_dll_wrapper.a ${CEF_DIR}/lib
)

set(JDK_PREFIX ${CMAKE_CURRENT_BINARY_DIR}/jdk)

ExternalProject_Add(jdk
	PREFIX ${JDK_PREFIX}
    URL https://github.com/AdoptOpenJDK/openjdk8-binaries/releases/download/jdk8u202-b08/OpenJDK8U-jdk_x64_linux_hotspot_8u202b08.tar.gz 
    http://opensource.spotify.com/cefbuilds/cef_binary_73.1.12%2Bgee4b49f%2Bchromium-73.0.3683.75_linux64_minimal.tar.bz2
    CONFIGURE_COMMAND ""
    BUILD_COMMAND ""
    INSTALL_COMMAND cmake -E copy_directory ${JDK_PREFIX}/src/jdk ${CEF_DIR}/jdk
    	COMMAND cmake -E copy_directory ${CEF_DIR}/lib ${CEF_DIR}/jdk/jre/bin
)

add_dependencies(jdk cef-minimal)

add_executable(subProcess
	subProcess.cpp
)

add_dependencies(subProcess cef-minimal jdk)

target_include_directories(subProcess PUBLIC
	${CEF_DIR}
)

target_link_libraries(subProcess
	-L${CEF_DIR}/lib
	cef_dll_wrapper
	cef
)

set_target_properties(subProcess PROPERTIES RUNTIME_OUTPUT_DIRECTORY "${CEF_DIR}/lib")

add_library(swt-cef SHARED
	SWTApp.cpp
	SWTLib.cpp
)

add_dependencies(swt-cef cef-minimal jdk)

target_include_directories(swt-cef PUBLIC
	${CEF_DIR}
	${CEF_DIR}/jdk/include
	${CEF_DIR}/jdk/include/linux   # TODO others obviously :)
	${GTK3_INCLUDE_DIRS}
)

target_compile_options(swt-cef PUBLIC
	${GTK3_CFLAGS_OTHER}
)

target_link_libraries(swt-cef
	-L${CEF_DIR}/lib
	cef_dll_wrapper
	cef
	${GTK3_LIBRARIES}
	X11
)

set_target_properties(swt-cef PROPERTIES LIBRARY_OUTPUT_DIRECTORY "${CEF_DIR}/lib")

add_executable(cef-test
	testApp.cpp
)

add_dependencies(cef-test cef-minimal jdk)

target_include_directories(cef-test PUBLIC
	${CEF_DIR}
)

target_link_libraries(cef-test
	-L${CEF_DIR}/lib
	cef_dll_wrapper
	cef
)
