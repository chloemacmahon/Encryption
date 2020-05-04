public class Decrypt 
{
    public static byte[] Decode(byte[] arrencrypted, String sPassword)
    {
        byte arrdecrypted[] = new byte[arrencrypted.length]; //Creates new byte array
        HashCoding p1 = new HashCoding(sPassword); //Uses password to create a hash 
        int arrdigits[] = p1.calcDigits(); //Code by C Mac Mahon
        for (int icounter = 0; icounter < arrencrypted.length; icounter++) //Applies the hash backwards on the array
        {
            arrdecrypted[icounter] = (byte)((int)arrencrypted[icounter] - arrdigits[icounter % sPassword.length()]);
            if (icounter % p1.getDecimal() == 1)
            {
                arrdecrypted[icounter] = (byte)(-1 * (int)arrdecrypted[icounter]);
            }            
        }
        return arrdecrypted; //Returns decrypted file 
    }
}