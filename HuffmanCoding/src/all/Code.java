package all;

public class Code {
    private char sign;
    private String code;
    private int frequency;

    public Code(char sign, String code) {
        this.sign = sign;
        this.code = code;
    }

    //do dekodowania
    public Code(char sign) {
        this.sign = sign;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getFrequency() {
        return frequency;
    }

    public char getSign() {
        return sign;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "znak: " + sign +  "\t\tkod: " + code + "\t\tczestość: " + frequency;
    }
}
