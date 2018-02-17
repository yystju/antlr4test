grammar Drink;

// Parser Rules

drinkArticle : (drinkSentence) + ;

drinkSentence : ARTICLE? DRINKING_VESSEL OF drink FOR ARTICLE? somebody EOS ;

drink : TEXT (OR TEXT)*;

somebody : TEXT;

// Lexer Rules

ARTICLE : 'the' | 'an' | 'a' ;

OF : 'of' ;

OR : 'or' ;

DRINKING_VESSEL : 'cup' | 'pint' | 'shot' | 'mug' | 'glass' ;

FOR : 'for' ;

EOS : '.' ;

TEXT : ('a'..'z')+ ;

WHITESPACE : ( '\t' | ' ' | '\r' | '\n'| '\u000C' )+ -> skip ;
