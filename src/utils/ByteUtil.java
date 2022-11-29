package utils;

public class ByteUtil {
    public static byte charNumberToValue(byte b){
        return (byte) (b == '1'? 1 : 0);
    }

    public static byte byteValueToCharNumber(byte b){
        return (byte) (b == 1? '1' : '0');
    }

    public static void changeSign(byte[] array, int index){
        var value = charNumberToValue(array[index]);
        array[index] = byteValueToCharNumber((byte) ((value + 1) % 2));
    }

    public static void xor(byte[] array, int target, int source){
        var targetValue = charNumberToValue(array[target]);
        var sourceValue = charNumberToValue(array[source]);
        var result = (byte) (targetValue ^ sourceValue);
        array[target] = byteValueToCharNumber(result);
    }
}
