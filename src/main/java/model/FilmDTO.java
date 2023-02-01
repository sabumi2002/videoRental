package model;

public class FilmDTO {
    // film, film_category, category, actor, language
    // film_id, title, description, language, rental_duration, rental_rate, replacement_cost, category_name, actor_name

    private int film_id;
    private String title;
    private String description;
    private int release_year;    //개봉년도
    private int language_id;    //번역
    private int original_language_id;   //기존언어
    private int rental_duration;    // 대여기간
    private int rental_rate;    // 대여기간당 대여비용
    private int length;
    private int replacement_cost;   // 청구비용
    private String special_features;    // 특징

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

    public int getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(int language_id) {
        this.language_id = language_id;
    }

    public int getOriginal_language_id() {
        return original_language_id;
    }

    public void setOriginal_language_id(int original_language_id) {
        this.original_language_id = original_language_id;
    }

    public int getRental_duration() {
        return rental_duration;
    }

    public void setRental_duration(int rental_duration) {
        this.rental_duration = rental_duration;
    }

    public int getRental_rate() {
        return rental_rate;
    }

    public void setRental_rate(int rental_rate) {
        this.rental_rate = rental_rate;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getReplacement_cost() {
        return replacement_cost;
    }

    public void setReplacement_cost(int replacement_cost) {
        this.replacement_cost = replacement_cost;
    }

    public String getSpecial_features() {
        return special_features;
    }

    public void setSpecial_features(String special_features) {
        this.special_features = special_features;
    }
}
