public class Decrypt 
{
    public static byte[] Decode(byte[] arrencrypted, String sPassword)
    {
        byte arrdecrypted[] = new byte[arrencrypted.length];
        HashCoding p1 = new HashCoding(sPassword);
        int arrdigits[] = p1.calcDigits();
        for (int icounter = 0; icounter < arrencrypted.length; icounter++)
        {
            arrdecrypted[icounter] = (byte)((int)arrencrypted[icounter] - arrdigits[icounter % sPassword.length()]);
            if (icounter % p1.getDecimal() == 1)
            {
                arrdecrypted[icounter] = (byte)(-1 * (int)arrdecrypted[icounter]);
            }
            else
            {
                //arrdecrypted[icounter] = arrencrypted[icounter];
            }
            
        }
        return arrdecrypted;
    }
}