public class StringDoctor
{
    public String EscapeString(String s)
    {
        String[] str={"[","]","(",")","{","}","*","+","?","|","^","."};
        for(int i=0;i<str.length;i++)
        {
            if(s.contains(str[i]))
            {
                s=s.replaceAll("\\"+str[i],"\\\\"+str[i]);
            }
        }
       
        return s;
    }
    public String EscapeHtml(String s)
    {
        String lt="<";
        String gt=">";
        //String str="";
        if(s.contains(lt))
        {
            s=s.replaceAll(lt,"&lt;");
        }
        if(s.contains(gt))
        {
            s=s.replaceAll(gt,"&gt;");
        }
        return s;
    }
    public boolean isEmpty(String s)
    {
        if(s.trim().length()>0)
        {
        return false;
        }
        return true;
    }
}
