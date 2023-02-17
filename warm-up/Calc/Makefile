visitor: 
	java -jar antlr.jar -no-listener -visitor src/ast/Calc.g4

compile: visitor
	javac -cp "antlr.jar" -d bin/ -sourcepath src/ src/calc/CalcRun.java

test: compile
	java -cp "antlr.jar:bin" calc/CalcRun tests/$(file).calc

all: run
