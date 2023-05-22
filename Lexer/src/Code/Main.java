package Code;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        String path = "C:/Users/luisr/EclipseWorkspace/Lexer/src/Code/Lexer.lex";
        generate_lexer(path);
    }
    public static void generate_lexer(String path){
        File lexeme_file = new File(path);
        JFlex.Main.generate(lexeme_file);
    }
}
