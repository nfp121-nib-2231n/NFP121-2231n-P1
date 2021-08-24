public class TableInfo
{
    private int num;
    private String description="";
    private String los="";
    private String joker="";
    private String oper="";
    private boolean multiple=false;
    private String type="";
    private String[] losParts;
    
    private StringDoctor se=new StringDoctor();
    
    public TableInfo(String n,String d,String l,String j,String o,String m,String t)
    {
        num=Integer.valueOf(n);//this is surely a number because its auto generated and not editable
        description=d;
        los=CheckLos(l);
        joker=CheckJoker(j);
        oper=o;
        multiple=CheckRech(m);
        type=CheckType(t);
    }
    private String CheckLos(String l)
    {
        if(se.isEmpty(l))
        {
            return description.toLowerCase();
        }
        return l.replaceAll("\\s","");
    }
    private String CheckJoker(String j)
    {
            String ptn="";
            
            if(se.isEmpty(j))
            {
                
            }else
            {      
                losParts=los.split(se.EscapeString(j));
                for(int x=0;x<losParts.length;x++)
                {
                    if(x==losParts.length-1)
                    {
                        ptn+=se.EscapeString(losParts[x]);
                    }else
                    {
                        ptn+=se.EscapeString(losParts[x])+"[^"+se.EscapeString(losParts[losParts.length-1])+"]*";
                    }
                }
            }       
            return ptn;
    }
    private boolean CheckRech(String m)
    {
        if(m.toLowerCase().equals("faux"))
            return false;
        return true;
    }
    private String CheckType(String t)
    {
        if(t.toLowerCase().equals("code"))
        {
            return ".java";
        }else if(t.toLowerCase().equals("texte"))
        {
            return ".txt";
        }
        return "";
    }
    public int getNum(){return num;}
    public String getDesc(){return description;}
    public String getLos(){return los;}
    public String getJoker(){return joker;}
    public String getOper(){return oper;}
    public boolean getRech(){return multiple;}
    public String getType(){return type;}
    public String[] getLosParts(){return losParts;}
}