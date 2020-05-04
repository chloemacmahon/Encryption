public class Encrypt 
{
    public static byte[] Encode(byte[] arrdecrypted, String sPassword)
    {
        byte arrencrypted[] = new byte[arrdecrypted.length];
        HashCoding p1 = new HashCoding(sPassword);
        int arrdigits[] = p1.calcDigits();
        for (int icounter = 0 ; icounter < arrdecrypted.length ; icounter++)
        {
            if (icounter % p1.getDecimal() == 1)
            {
                arrencrypted[icounter] = (byte)((int)arrdecrypted[icounter] * -1 );
            }
            else
            {
                arrencrypted[icounter] = arrdecrypted[icounter];
            }
            arrencrypted[icounter] = (byte)(arrdigits[icounter % sPassword.length()] + (int)arrencrypted[icounter]);
        }
        return arrencrypted;
    } 
    
}