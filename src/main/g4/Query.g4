grammar Query;

queries : (query)* EOF ;

query : SELECT query_fields FROM view where_clause? order_by_clause? SEMICOMMA ;

field : TEXT ;

alias : TEXT ;

view : TEXT ;

query_fields : query_field (COMMA query_field)* ;

query_field : field alias? ;

where_clause : WHERE criteria ;

criteria :  expression ( '(' expression ')' )* ;

expression : sub_expression (('AND' | 'OR') sub_expression )* ;

sub_expression : field ('>' | '<' | '=' | '<>') (field | string) ;

//order_by_clause : ORDER BY order_by_field (COMMA order_by_field)* ;
order_by_clause : ORDER BY order_by_field (COMMA order_by_field)* ;

order_by_field : field (ASC | DSC)? ;

string : ('\'' (~ ('\\' | '"'))* '\'') ;

SELECT : 'SELECT' ;

COMMA : ',' ;

SEMICOMMA : ';' ;

FROM : 'FROM' ;

WHERE : 'WHERE' ;

ORDER : 'ORDER' ;

BY : 'BY' ;

ASC : 'ASC' ;

DSC : 'DSC' ;

TEXT : [0-9a-zA-Z]+ ;

WHITESPACE : ( '\t' | ' ' | '\r' | '\n' | '\u000C' )+ -> skip ;
