package all;

import java.io.IOException;

public class Buffer {
    private String memory;

    public Buffer() {
        memory = "";
    }

    public void add(String text){
        memory = memory+text;
    }

    public void flush(){
        memory = "";
    }


    public char what8bitItIs(String code){
        char sign = 0;
        for(int i = 0; i<8; i++){
            sign *= 2;
            sign += code.charAt(i) - 48;
        }
        return sign;
    }

    public String takeCodeFromSign(char sign){

        String code = "";
        for(int i = 0; i<8; i++){
            code = (int)sign%2 + code;
            sign /= 2;
        }
        return code;
    }


    public String drawByte(){
        String resultBytes = "";
        String temp;
        while(memory.length() >= 8){
            temp = memory.substring(0,8);
            memory = memory.substring(8);
            resultBytes = resultBytes + what8bitItIs(temp);
        }
        return resultBytes;
    }


    public String lastDraw(){
        String resultByte = memory;
        if(memory != "")
            resultByte = memory;
            while(resultByte.length() < 8){
                resultByte = resultByte + "1";
            }
        char lastChar = what8bitItIs(resultByte);
        resultByte = "";
        resultByte += lastChar;
        return resultByte;
    }

}
