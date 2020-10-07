package uni.eszterhazy.keretrendszer.exceptions;

public class DolgozoAlreadyAdded extends Throwable{
    public DolgozoAlreadyAdded(String id) {
        super(id);
    }
}
