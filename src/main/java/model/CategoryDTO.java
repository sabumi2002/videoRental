package model;

public class CategoryDTO {
    int category_id;
    String name;

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryDTO(){}
    public CategoryDTO(int category_id){
        this.category_id = category_id;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof CategoryDTO){
            CategoryDTO c = (CategoryDTO) o;
            return category_id == c.category_id;
        }

        return false;
    }
}
