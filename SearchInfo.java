import java.util.Iterator;
public class SearchInfo extends SearchInfoComponent
{
    private String filename;
    private int count;
    private String[] linenumber;
    private String[] lines;
    private String[] wordSearched;
    private boolean containsJoker;
    
    public SearchInfo(String f,int c,String ln,String l,String words,boolean j)
    {
        filename=f;
        count=c;
        linenumber=ln.split(",");
        lines=l.split("-_-");
        wordSearched=words.split("-_-");
        containsJoker=j;
    }
    
    public String getFilename(){return filename;}
    public int getCount(){return count;}
    public String[] getLinenumber(){return linenumber;}
    public String[] getLines(){return lines;}
    public String[] getWords(){return wordSearched;}
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