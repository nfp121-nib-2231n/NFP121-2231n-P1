// This acts as an interface for every SearchInfo (Leaf)
// and SearchInfoGroup (Composite) created
public abstract class SearchInfoComponent implements MainIterator{
    // We throw UnsupportedOperationException so that if
    // it doesn't make sense for a search info, or search info group
    // to inherit a method they can just inherit the
    // default implementation
    
    // This allows me to add components
    public void add(SearchInfoComponent newSearchInfoComponent){throw new UnsupportedOperationException();}
    // This allows me to remove components
    public void remove(SearchInfoComponent newSearchInfoComponent){throw new UnsupportedOperationException();}
    // This allows me to get components    
    public SearchInfoComponent getComponent(int componentIndex){throw new UnsupportedOperationException();}    
    // This allows me to retrieve the index
    public int getIndex(){throw new UnsupportedOperationException();}
    // This allows me to retrieve the filename
        public String getFilename(){throw new UnsupportedOperationException();}
        // This allows me to retrieve the count 
        public int getCount(){throw new UnsupportedOperationException();}
        // This allows me to retrieve the line numbers
        public String[] getLinenumber(){throw new UnsupportedOperationException();}
        // This allows me to retrieve the lines in which the words are
        public String[] getLines(){throw new UnsupportedOperationException();}
        // This allows me to retrieve the word searched for
        public String[] getWords(){throw new UnsupportedOperationException();}
        // This allows me to retrieve the boolean containsJoker
        public boolean getCJ(){throw new UnsupportedOperationException();}
}