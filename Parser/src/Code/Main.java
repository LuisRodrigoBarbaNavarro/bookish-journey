package Code;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
	
    public static void main(String[] args) throws Exception {
        String lexer_path = "C:/Users/luisr/EclipseWorkspace/Parser/src/Code/Lexer.flex";
        String lexer_cup_path = "C:/Users/luisr/EclipseWorkspace/Parser/src/Code/LexerCup.flex";
        String[] sintax_path = {"-parser", "Sintax", "C:/Users/luisr/EclipseWorkspace/Parser/src/Code/Sintax.cup"};
        generate(lexer_path, lexer_cup_path, sintax_path);
    }
    
    public static void generate(String lexer_path, String lexer_cup_path, String[] sintax_path) throws IOException, Exception {
        File file;
        file = new File(lexer_path);
        JFlex.Main.generate(file);
        
        file = new File(lexer_cup_path);
        JFlex.Main.generate(file);
        java_cup.Main.main(sintax_path);
        
        Path sym_path = Paths.get("C:/Users/luisr/EclipseWorkspace/Parser/src/Code/sym.java");
        
        if (Files.exists(sym_path)) {
            Files.delete(sym_path);
        }
        
        Files.move(
                Paths.get("C:/Users/luisr/EclipseWorkspace/Parser/sym.java"), 
                Paths.get("C:/Users/luisr/EclipseWorkspace/Parser/src/Code/sym.java")
        );
        
        Path sintax_java_path = Paths.get("C:/Users/luisr/EclipseWorkspace/Parser/src/Code/Sintax.java");
        
        if (Files.exists(sintax_java_path)) {
            Files.delete(sintax_java_path);
        }
        
        Files.move(
                Paths.get("C:/Users/luisr/EclipseWorkspace/Parser/Sintax.java"), 
                Paths.get("C:/Users/luisr/EclipseWorkspace/Parser/src/Code/Sintax.java")
        );
    }
}
