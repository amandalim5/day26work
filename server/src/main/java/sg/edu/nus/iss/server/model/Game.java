package sg.edu.nus.iss.server.model;

import java.io.Serializable;

import org.bson.Document;

public class Game implements Serializable{
    private int gid;
    private String name;
    private int year;
    private int ranking;
    private int users_rated;
    private String url;
    private String image;

    public int getGid() {
        return gid;
    }
    public void setGid(int gid) {
        this.gid = gid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public int getRanking() {
        return ranking;
    }
    public void setRanking(int ranking) {
        this.ranking = ranking;
    }
    public int getUsers_rated() {
        return users_rated;
    }
    public void setUsers_rated(int users_rated) {
        this.users_rated = users_rated;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public static Game createSummary(Document doc){
        Game g = new Game();
        g.setGid(doc.getInteger("gid"));
        g.setName(doc.getString("name"));
        g.setYear(doc.getInteger("year"));
        g.setRanking(doc.getInteger("ranking"));
        g.setUsers_rated(doc.getInteger("users_rated"));
        g.setUrl(doc.getString("url"));
        g.setImage(doc.getString("image"));
        return g;
    }
}
