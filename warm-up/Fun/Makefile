.PHONY: visitor parse check run testParse testCheck testRun recompile test

visitor: 
	java -jar antlr.jar -no-listener -visitor src/ast/Fun.g4

parse: visitor
	javac -cp "antlr.jar" -d bin/ -sourcepath src/ src/fun/FunParse.java

check: visitor
	javac -cp "antlr.jar" -d bin/ -sourcepath src/ src/fun/FunCheck.java

run: visitor
	javac -cp "antlr.jar" -d bin/ -sourcepath src/ src/fun/FunRun.java

testParse:
	javac -cp "antlr.jar" -d bin/ -sourcepath src/ src/fun/FunParse.java

testCheck:
	javac -cp "antlr.jar" -d bin/ -sourcepath src/ src/fun/FunCheck.java

testRun:
	javac -cp "antlr.jar" -d bin/ -sourcepath src/ src/fun/FunRun.java

recompile: visitor testParse testCheck testRun

SEP=":"
ifeq ($(OS),Windows_NT)
	SEP=";"
endif

test:
	java -cp "antlr.jar$(SEP)bin" fun/FunRun tests/$(file).fun

all: run
