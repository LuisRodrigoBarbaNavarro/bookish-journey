package Code;
import java_cup.runtime.Symbol;

%%
%class LexerCup
%type java_cup.runtime.Symbol
%cup
%full
%line
%char

WHITE=[ \t\r\n]+
IDENTIFIER=[:jletter:][:jletterdigit:]*
REAL_TEXT=\"([^\"]*)\"
REAL_INTEGER=[0-9]*
REAL_FLOAT=[0-9]+\.[0-9]*
REAL_SYMBOL=\'[a-zA-Z0-9]\'
REAL_BOOLEAN=verdad|falso
REAL_DATETIME=[0-9]{4}[\-]((01|03|05|07|08|10|12)[\-](0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)[\-](0[1-9]|[1-2][0-9]|30)|02[\-](0[1-9]|[1-2][0-9]))[\/](0[0-9]|1[0-9]|2[0-4])[\:][0-5][0-9][\:][0-5][0-9]
REAL_DATE=[0-9]{4}[\-]((01|03|05|07|08|10|12)[\-](0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)[\-](0[1-9]|[1-2][0-9]|30)|02[\-](0[1-9]|[1-2][0-9]))
REAL_TIME=(0[0-9]|1[0-9]|2[0-4])[\:][0-5][0-9][\:][0-5][0-9]
REAL_RFC=([A-ZÑ\x26]{3,4}([0-9]{2})(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1]))[A-Z0-9]{3}
REAL_CURP=[A-Z]{1}[AEIOU]{1}[A-Z]{2}[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])[HM]{1}(AS|BC|BS|CC|CS|CH|CL|CM|DF|DG|GT|GR|HG|JC|MC|MN|MS|NT|NL|OC|PL|QT|QR|SP|SL|SR|TC|TS|TL|VZ|YN|ZS|NE)[B-DF-HJ-NP-TV-Z]{3}[0-9A-Z]{1}[0-9]{1}
%{
    private Symbol symbol(int type, Object value){
        return new Symbol(type, yyline, yycolumn, value);
    }
    private Symbol symbol(int type){
        return new Symbol(type, yyline, yycolumn);
    }
%}

%%
{REAL_TEXT}           {return new Symbol(sym.tk_texto_real, yychar, yyline, yytext());}
{REAL_INTEGER}        {return new Symbol(sym.tk_entero_real, yychar, yyline, yytext());}
{REAL_FLOAT}          {return new Symbol(sym.tk_decimal_real, yychar, yyline, yytext());}
{REAL_SYMBOL}         {return new Symbol(sym.tk_simbolo_real, yychar, yyline, yytext());}
{REAL_BOOLEAN}        {return new Symbol(sym.tk_logico_real, yychar, yyline, yytext());}
{REAL_DATETIME}       {return new Symbol(sym.tk_fecha_hora_real, yychar, yyline, yytext());}
{REAL_DATE}           {return new Symbol(sym.tk_fecha_real, yychar, yyline, yytext());}
{REAL_TIME}           {return new Symbol(sym.tk_hora_real, yychar, yyline, yytext());}
{REAL_RFC}            {return new Symbol(sym.tk_rfc_real, yychar, yyline, yytext());}
{REAL_CURP}           {return new Symbol(sym.tk_curp_real, yychar, yyline, yytext());}

variables             {return new Symbol(sym.tk_variables, yychar, yyline, yytext());}
variable              {return new Symbol(sym.tk_variable, yychar, yyline, yytext());}
entero                {return new Symbol(sym.tk_entero, yychar, yyline, yytext());}
decimal               {return new Symbol(sym.tk_decimal, yychar, yyline, yytext());}
simbolo               {return new Symbol(sym.tk_simbolo, yychar, yyline, yytext());}
texto                 {return new Symbol(sym.tk_texto, yychar, yyline, yytext());}
logico                {return new Symbol(sym.tk_logico, yychar, yyline, yytext());}
fecha-hora            {return new Symbol(sym.tk_fecha_hora, yychar, yyline, yytext());}
fecha                 {return new Symbol(sym.tk_fecha, yychar, yyline, yytext());}
hora                  {return new Symbol(sym.tk_hora, yychar, yyline, yytext());}
curp                  {return new Symbol(sym.tk_curp, yychar, yyline, yytext());}
rfc                   {return new Symbol(sym.tk_rfc, yychar, yyline, yytext());}
codigo-principal      {return new Symbol(sym.tk_codigo_principal, yychar, yyline, yytext());}
funciones             {return new Symbol(sym.tk_funciones, yychar, yyline, yytext());}
procedimientos        {return new Symbol(sym.tk_procedimientos, yychar, yyline, yytext());}
funcion               {return new Symbol(sym.tk_funcion, yychar, yyline, yytext());}
procedimiento         {return new Symbol(sym.tk_procedimiento, yychar, yyline, yytext());}
devolver              {return new Symbol(sym.tk_devolver, yychar, yyline, yytext());}
como                  {return new Symbol(sym.tk_como, yychar, yyline, yytext());}
inicio                {return new Symbol(sym.tk_inicio, yychar, yyline, yytext());}
fin                   {return new Symbol(sym.tk_fin, yychar, yyline, yytext());}
si                    {return new Symbol(sym.tk_si, yychar, yyline, yytext());}
entonces              {return new Symbol(sym.tk_entonces, yychar, yyline, yytext());}
sino                  {return new Symbol(sym.tk_sino, yychar, yyline, yytext());}
mientras              {return new Symbol(sym.tk_mientras, yychar, yyline, yytext());}
para                  {return new Symbol(sym.tk_para, yychar, yyline, yytext());}
romper                {return new Symbol(sym.tk_romper, yychar, yyline, yytext());}

"("                   {return new Symbol(sym.tk_parentesis_abierto, yychar, yyline, yytext());}
")"                   {return new Symbol(sym.tk_parentesis_cerrado, yychar, yyline, yytext());}
":"                   {return new Symbol(sym.tk_dos_puntos, yychar, yyline, yytext());}
"\,"                  {return new Symbol(sym.tk_coma, yychar, yyline, yytext());}
"+"                   {return new Symbol(sym.tk_suma, yychar, yyline, yytext());}
"-"                   {return new Symbol(sym.tk_resta, yychar, yyline, yytext());}
"*"                   {return new Symbol(sym.tk_multiplicacion, yychar, yyline, yytext());}
"/"                   {return new Symbol(sym.tk_division, yychar, yyline, yytext());}
"%"                   {return new Symbol(sym.tk_modulo, yychar, yyline, yytext());}
"++"                  {return new Symbol(sym.tk_incremento, yychar, yyline, yytext());}
"--"                  {return new Symbol(sym.tk_decremento, yychar, yyline, yytext());}
"="                   {return new Symbol(sym.tk_asignacion, yychar, yyline, yytext());}
"+="                  {return new Symbol(sym.tk_asignacion_suma, yychar, yyline, yytext());}
"-="                  {return new Symbol(sym.tk_asignacion_resta, yychar, yyline, yytext());}
"*="                  {return new Symbol(sym.tk_asignacion_multiplicacion, yychar, yyline, yytext());}
"/="                  {return new Symbol(sym.tk_asignacion_division, yychar, yyline, yytext());}
"%="                  {return new Symbol(sym.tk_asignacion_modulo, yychar, yyline, yytext());}
"=="                  {return new Symbol(sym.tk_igualdad, yychar, yyline, yytext());}
"!="                  {return new Symbol(sym.tk_desigualdad, yychar, yyline, yytext());}
">"                   {return new Symbol(sym.tk_mayor, yychar, yyline, yytext());}
"<"                   {return new Symbol(sym.tk_menor, yychar, yyline, yytext());}
">="                  {return new Symbol(sym.tk_mayor_igual, yychar, yyline, yytext());}
"<="                  {return new Symbol(sym.tk_menor_igual, yychar, yyline, yytext());}
"_Y_"                 {return new Symbol(sym.tk_y, yychar, yyline, yytext());}
"_O_"                 {return new Symbol(sym.tk_o, yychar, yyline, yytext());}
"~"                   {return new Symbol(sym.tk_negacion, yychar, yyline, yytext());}

"."                   {return new Symbol(sym.tk_punto, yychar, yyline, yytext());}
{IDENTIFIER}          {return new Symbol(sym.tk_identificador, yychar, yyline, yytext());}

{WHITE}               {/*Ignore*/}
"#".*                 {/*Ignore*/}
 .                    {return new Symbol(sym.Error, yychar, yyline, yytext());}