import java.util.*;

public class Info<E> implements MainIterator
{
    private ArrayList<E> elements;
    
    public Info(ArrayList<E> elements)
    {
        this.elements=elements;
    }
    
    public Iterator createIterator()
    {
        return elements.iterator();
    }
    
    public int getSize()
    {
        return elements.size();
    }
}