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
    public boolean CheckEmpty(String s)
    {
        if(s.equals(""))
        {
        return true;
        }
        return false;
    }
}
