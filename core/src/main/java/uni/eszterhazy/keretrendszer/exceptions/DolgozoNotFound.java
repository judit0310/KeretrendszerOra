package uni.eszterhazy.keretrendszer.exceptions;

public class DolgozoNotFound extends Throwable {
    public DolgozoNotFound(String id) {
        super(id);
    }
}
