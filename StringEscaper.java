public class StringEscaper
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
    
}
