package api.PowerBank.ApiHelp.CardService;

public class Document {
    public String name;
    public String documentLink;

    public Document(){};

    public Document(String name, String documentLink) {
        this.name = name;
        this.documentLink = documentLink;
    }

    public String getName() {
        return name;
    }

    public String getDocumentLink() {
        return documentLink;
    }
}
