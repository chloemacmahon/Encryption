
public class HashCoding 
{
    private String sPassword;
    private int idecimal;
    public HashCoding(String sPassword) //Constructor 
    {
        setPassword(sPassword);
        setDecimal(calculateDecimal(this.sPassword));
    }

    public int calculateDecimal(String sPassword) //Calculates total decimal value of password 
    {
        int icounter = 0;
        int itotal = 0;
        while(icounter < sPassword.length())
        {
            itotal += toOctal(Character.getNumericValue(sPassword.charAt(icounter))); //Takes a reversed octal value and adds it to the total
            icounter++;
        }
        return itotal;
    }

    public int toOctal(int iOriginal) //Creates reverse octal of every char's decimal value
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

    public void setDecimal(int idecimal) //Sets decimal
    {
        this.idecimal = idecimal;
    }

    public String getPassword() //Returns password
    {
        return this.sPassword; //Code by C Mac Mahon
    }

    public int getDecimal() //Returns decimal value
    {
        return  this.idecimal;
    }

    public String toString() //Returns to string value 
    {
        return "The int value is : " + this.idecimal ;
    }
}