package hieu.ho;

public class CustomModel {
    int id;
    String name;
    String document;

    public void setId(int id) {
        this.id = id;
    }

    public void setDocument(String document) {
        this.document = document;
    }

   public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getDocument() {
        return document;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "{\"id\":" + id + ",\"name\":\"" + name + "\",\"document\":\"" + document + "\"}";
    }
}