import java.util.NoSuchElementException;

public class WordNotFoundException extends NoSuchElementException{
    public WordNotFoundException(String s){
        super(s);
    }
}
