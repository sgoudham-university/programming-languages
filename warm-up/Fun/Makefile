.PHONY: visitor
visitor: 
	java -jar antlr.jar -no-listener -visitor src/ast/Fun.g4

.PHONY: parse
parse: visitor
	javac -cp "antlr.jar" -d bin/ -sourcepath src/ src/fun/FunParse.java

.PHONY: check
check: visitor
	javac -cp "antlr.jar" -d bin/ -sourcepath src/ src/fun/FunCheck.java

.PHONY: testParse
testParse:
	javac -cp "antlr.jar" -d bin/ -sourcepath src/ src/fun/FunParse.java

.PHONY: testCheck
testCheck:
	javac -cp "antlr.jar" -d bin/ -sourcepath src/ src/fun/FunCheck.java

SEP=":"
ifeq ($(OS),Windows_NT)
	SEP=";"
endif

.PHONY: test
test: visitor testParse testCheck
	java -cp "antlr.jar$(SEP)bin" fun/FunCheck tests/$(file).fun

all: run
