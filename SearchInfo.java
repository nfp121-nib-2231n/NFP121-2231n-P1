import java.util.ArrayList;
import java.util.Iterator;
public class SearchInfo extends SearchInfoComponent
{
    private String filename;
    private int count;
    private ArrayList<String> linenumber;
    private ArrayList<String> lines;
    private ArrayList<ArrayList<String>> wordSearched;
    private boolean containsJoker;
    
    public SearchInfo(String f,int c,ArrayList<String> ln,ArrayList<String> l,ArrayList<ArrayList<String>> words,boolean j)
    {
        filename=f;
        count=c;
        linenumber=ln;
        lines=l;
        wordSearched=words;
        containsJoker=j;
    }
    
    public String getFilename(){return filename;}
    public int getCount(){return count;}
    public ArrayList<String> getLinenumber(){return linenumber;}
    public ArrayList<String> getLines(){return lines;}
    public ArrayList<ArrayList<String>> getWords(){return wordSearched;}
    public boolean getCJ(){return containsJoker;}
    public Iterator createIterator()
    {
        return null;
    }
    public int getSize()
    {
        return 0;
    }
}