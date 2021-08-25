import java.util.ArrayList;
import java.util.Iterator;

public class SearchInfoGroup extends SearchInfoComponent{
    // Contains any SearchInfos or SearchInfoGroups that are added
    // to this ArrayList
    private ArrayList<SearchInfoComponent> SearchInfoComponents = new ArrayList<SearchInfoComponent>();
    
    private int index;
    
    public SearchInfoGroup(int i)
    {
            index=i;
    }
    public int getIndex() { return index; }
    public void add(SearchInfoComponent newSearchInfoComponent) 
    {
            SearchInfoComponents.add(newSearchInfoComponent);
    }
    public void remove(SearchInfoComponent newSearchInfoComponent) 
    {    
        SearchInfoComponents.remove(newSearchInfoComponent);
    }
    public SearchInfoComponent getComponent(int componentIndex) 
    {
            return (SearchInfoComponent)SearchInfoComponents.get(componentIndex);
    }
    public Iterator createIterator()
    {
            return SearchInfoComponents.iterator();   
    }
    public int getSize()
    {
        return SearchInfoComponents.size();
    }
}