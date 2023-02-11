grammar Calc; 

@header{
	package ast;
}


// Programs 

prog
	:	com* EOF
	;

// Commands

com
	:	PUT expr EOL         # put
	|	SET var ASSN expr EOL # set
	;

// Expressions

expr
	:	prim (operator+=(PLUS | MINUS | TIMES) prim)* # op
	;

prim		        
	:	NUM                  # num
	|	ID                   # id
	|	LPAR expr RPAR       # parens
	;
	
var
	:	ID
	;

// Lexicon

PUT	:	'put' ;
SET	:	'set' ;

ASSN	:	'=' ;
PLUS	:	'+' ;
MINUS	:	'-' ;
TIMES	:	'*' ;
LPAR	:	'(' ;
RPAR	:	')' ;

ID	:	'a'..'z' ;
NUM	:	'0'..'9'+ ;

EOL	:	'\r'? '\n' ;

SPACE	:	(' ' | '\t')+  -> skip ;

// (.)
//      match any char (INCLUDING EOL)
// (.)*
//      match any char (INCLUDING EOL) - 0 or more times, this is greedy so captures everything in one go.
// (.)*?
//      match any char (INCLUDING EOL) - 0 or more times, this is non-greedy with the introduction of the `?` as it now treats it char by char allowing it match EOL separately.

COMMENT : '//' (.)*? EOL -> skip ;
