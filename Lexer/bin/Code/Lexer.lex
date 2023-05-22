package Code;
import static Code.Tokens.*;

%%
%class Lexer
%type Tokens

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
REAL_RFC=([A-Z�\x26]{3,4}([0-9]{2})(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1]))[A-Z0-9]{3}
REAL_CURP=[A-Z]{1}[AEIOU]{1}[A-Z]{2}[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])[HM]{1}(AS|BC|BS|CC|CS|CH|CL|CM|DF|DG|GT|GR|HG|JC|MC|MN|MS|NT|NL|OC|PL|QT|QR|SP|SL|SR|TC|TS|TL|VZ|YN|ZS|NE)[B-DF-HJ-NP-TV-Z]{3}[0-9A-Z]{1}[0-9]{1}
%{
    public String lexeme;
%}

%%
{REAL_TEXT}           {lexeme=yytext(); return tk_texto_real;}
{REAL_INTEGER}        {lexeme=yytext(); return tk_entero_real;}
{REAL_FLOAT}          {lexeme=yytext(); return tk_decimal_real;}
{REAL_SYMBOL}         {lexeme=yytext(); return tk_simbolo_real;}
{REAL_BOOLEAN}        {lexeme=yytext(); return tk_logico_real;}
{REAL_DATETIME}       {lexeme=yytext(); return tk_fecha_hora_real;}
{REAL_DATE}           {lexeme=yytext(); return tk_fecha_real;}
{REAL_TIME}           {lexeme=yytext(); return tk_hora_real;}
{REAL_RFC}            {lexeme=yytext(); return tk_rfc_real;}
{REAL_CURP}           {lexeme=yytext(); return tk_curp_real;}

variables             {lexeme=yytext(); return tk_variables;}
variable              {lexeme=yytext(); return tk_variable;}
entero                {lexeme=yytext(); return tk_entero;}
decimal               {lexeme=yytext(); return tk_decimal;}
simbolo               {lexeme=yytext(); return tk_simbolo;}
texto                 {lexeme=yytext(); return tk_texto;}
logico                {lexeme=yytext(); return tk_logico;}
fecha-hora            {lexeme=yytext(); return tk_fecha_hora;}
fecha                 {lexeme=yytext(); return tk_fecha;}
hora                  {lexeme=yytext(); return tk_hora;}
curp                  {lexeme=yytext(); return tk_curp;}
rfc                   {lexeme=yytext(); return tk_rfc;}
codigo-principal      {lexeme=yytext(); return tk_codigo_principal;}
funciones             {lexeme=yytext(); return tk_funciones;}
procedimientos        {lexeme=yytext(); return tk_procedimientos;}
funcion               {lexeme=yytext(); return tk_funcion;}
procedimiento         {lexeme=yytext(); return tk_procedimiento;}
devolver              {lexeme=yytext(); return tk_devolver;}
como                  {lexeme=yytext(); return tk_como;}
inicio                {lexeme=yytext(); return tk_inicio;}
fin                   {lexeme=yytext(); return tk_fin;}
si                    {lexeme=yytext(); return tk_si;}
entonces              {lexeme=yytext(); return tk_entonces;}
sino                  {lexeme=yytext(); return tk_sino;}
mientras              {lexeme=yytext(); return tk_mientras;}
para                  {lexeme=yytext(); return tk_para;}
romper                {lexeme=yytext(); return tk_romper;}

"("                   {return tk_parentesis_abierto;}
")"                   {return tk_parentesis_cerrado;}
":"                   {return tk_dos_puntos;}
"\,"                  {return tk_coma;}
"+"                   {return tk_suma;}
"-"                   {return tk_resta;}
"*"                   {return tk_multiplicacion;}
"/"                   {return tk_division;}
"%"                   {return tk_modulo;}
"++"                  {return tk_incremento;}
"--"                  {return tk_decremento;}
"="                   {return tk_asignacion;}
"+="                  {return tk_asignacion_suma;}
"-="                  {return tk_asignacion_resta;}
"*="                  {return tk_asignacion_multiplicacion;}
"/="                  {return tk_asignacion_division;}
"%="                  {return tk_asignacion_modulo;}
"=="                  {return tk_igualdad;}
"!="                  {return tk_desigualdad;}
">"                   {return tk_mayor;}
"<"                   {return tk_menor;}
">="                  {return tk_mayor_igual;}
"<="                  {return tk_menor_igual;}
"_Y_"                 {return tk_y;}
"_O_"                 {return tk_o;}
"~"                   {return tk_negacion;}

"."                   {return tk_punto;}
{IDENTIFIER}          {lexeme=yytext(); return tk_identificador;}

{WHITE}               {/*Ignore*/}
"#".*                 {/*Ignore*/}
 .                    {return Error;}