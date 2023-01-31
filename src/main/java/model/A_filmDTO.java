package model;

public class A_filmDTO {
    // 영화id, 제목, 줄거리, 개봉연도, 개봉언어, 상영길이, 특징, 카테고리
    int film_id;
    String title;
    String description;
    int release_year;
    String ranguage;
    int length;
    String special_features;
    String category;

    public int getFilm_id() {
        return film_id;
    }

    public void setFilm_id(int film_id) {
        this.film_id = film_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRelease_year() {
        return release_year;
    }

    public void setRelease_year(int release_year) {
        this.release_year = release_year;
    }

    public String getRanguage() {
        return ranguage;
    }

    public void setRanguage(String ranguage) {
        this.ranguage = ranguage;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getSpecial_features() {
        return special_features;
    }

    public void setSpecial_features(String special_features) {
        this.special_features = special_features;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
