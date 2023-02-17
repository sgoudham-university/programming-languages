visitor: 
	java -jar antlr.jar -no-listener -visitor src/ast/Fun.g4

compile: visitor
	javac -cp "antlr.jar" -d bin/ -sourcepath src/ src/fun/FunCheck.java

test: compile
	java -cp "antlr.jar:bin" fun/FunCheck tests/$(file).fun

all: run
