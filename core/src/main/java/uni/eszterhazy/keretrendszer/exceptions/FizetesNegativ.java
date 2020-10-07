package uni.eszterhazy.keretrendszer.exceptions;

public class FizetesNegativ extends Throwable {
    public FizetesNegativ(double fizetes) {
        super(String.valueOf(fizetes));
    }
}
