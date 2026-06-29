public class Tag {
    private String tagId;
    private String name;

    public Tag() {}

    public Tag(String name) {
        this.tagId = generateTagId();
        this.name = name;
    }

    public String generateTagId() {
        return "TAG" + java.util.UUID.randomUUID().toString();
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
