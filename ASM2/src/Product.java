class Product {
    private String name;
    private String description;
    private String code;
    private float price;

    public Product(String name, String description, float price) {
        this.name = name;
        this.description = description;
        this.code = code;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", code='" + code + '\'' +
                ", price=" + price +
                '}';
    }
}