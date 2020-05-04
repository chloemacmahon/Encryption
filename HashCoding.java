
public class HashCoding 
{
    private String sPassword;
    private int idecimal;
    public HashCoding(String sPassword) //Constructor 
    {
        setPassword(sPassword);
        setDecimal(calculateDecimal(this.sPassword));
    }

    public int calculateDecimal(String sPassword)
    {
        int icounter = 0;
        int itotal = 0;
        while(icounter < sPassword.length())
        {
            itotal += toOctal(Character.getNumericValue(sPassword.charAt(icounter)));
            icounter++;
        }
        return itotal;
    }

    public int toOctal(int iOriginal)
    {
        int iworkingnum = iOriginal;
        int icurrent = 0;

        while (iworkingnum  != 0 )
        {
            icurrent = icurrent * 10 + iworkingnum % 8;
            iworkingnum = (int)(iworkingnum / 8);
        }
        return icurrent;    
    }

    public int[] calcDigits()
    {
        int icounter = 0 ;
        int arrdigits[] = new int[sPassword.length()];
        while(icounter < sPassword.length())
        {
            arrdigits[icounter] = toOctal(Character.getNumericValue(sPassword.charAt(icounter)));
            icounter++;
        }
        return arrdigits;
    }

    public void setPassword(String sPassword) //Sets password
    {
        this.sPassword = sPassword ;
    }

    public void setDecimal(int idecimal)
    {
        this.idecimal = idecimal;
    }

    public String getPassword()
    {
        return this.sPassword;
    }

    public int getDecimal()
    {
        return  this.idecimal;
    }

    public String toString()
    {
        return "The int value is : " + this.idecimal ;
    }
}