package network;

import java.io.Serializable;

public class Message implements Serializable {

    public enum DataType {
        File,
        Text
    }

    private DataType type;
    private String data;
    private String destPseudo;
    private String srcPseudo;

    public Message(DataType type, String data, String destPseudo, String srcPseudo) {
        this.type = type;
        this.data = data;
        this.destPseudo = destPseudo;
        this.srcPseudo = srcPseudo;
    }

    public DataType getType() {
        return type;
    }

    public String getData() {
        return data;
    }

    public String getDestPseudo() {
        return destPseudo;
    }

    public String getSrcPseudo() {
        return srcPseudo;
    }

    @Override
    public String toString() {
        return "Message{" +
                "type=" + type +
                ", data='" + data + '\'' +
                ", destPseudo='" + destPseudo + '\'' +
                ", srcPseudo='" + srcPseudo + '\'' +
                '}';
    }
}