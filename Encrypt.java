public class Encrypt 
{
    public static byte[] Encode(byte[] arrdecrypted, String sPassword)
    {
        byte arrencrypted[] = new byte[arrdecrypted.length]; //Creates new byte array
        HashCoding p1 = new HashCoding(sPassword); //Uses password to create HASH
        int arrdigits[] = p1.calcDigits();
        for (int icounter = 0 ; icounter < arrdecrypted.length ; icounter++) //Encrypts the file using the HASH
        {
            if (icounter % p1.getDecimal() == 1)
            {
                arrencrypted[icounter] = (byte)((int)arrdecrypted[icounter] * -1 );
            }
            else //Code by C Mac Mahon
            {
                arrencrypted[icounter] = arrdecrypted[icounter];
            }
            arrencrypted[icounter] = (byte)(arrdigits[icounter % sPassword.length()] + (int)arrencrypted[icounter]);
        }
        return arrencrypted; //Returns encrypted array
    } 
    
}