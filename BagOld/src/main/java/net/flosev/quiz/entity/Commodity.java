package net.flosev.quiz.entity;

import javax.persistence.*;


@Cacheable
@Entity
@Table(name = "Commodities")
public class Commodity {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "article")
    private  String article;
    /*@ElementCollection
    @CollectionTable(name = "sizes")
    private List<Integer> sizes = new ArrayList<>();*/
    @Column(name = "length")
    private  int length;
    @Column(name = "width")
    private  int width;
    @Column(name = "heigth")
    private  int heigth;
    @Column(name = "price")
    private  int price;
    /*@Embedded*/
    /*@AttributeOverrides({
            @AttributeOverride(name="id", column=@Column(name="id")),
            @AttributeOverride(name="caption", column=@Column(name="caption"))
    })*/
    /*@Column(name = "fashion")*/
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fashion_id",referencedColumnName = "id")
    private  Fashion fashion;

    public Commodity(){

    }


    /*private List<Fashion> types = new ArrayList<>();*/

    /*public Commodity(int id,  String article, List<Integer> sizes, int price, Fashion fashion) {*/
        public Commodity(int id,  String article, int length, int width, int heigth, int price, Fashion fashion){
        this.id = id;
        this.article = article;
        /*this.sizes = new ArrayList<>(sizes);*/
        this.length=length;
        this.width=width;
        this.heigth=heigth;
        this.price= price;
        this.fashion = fashion;

    }

    public int getId() {
        return id;
    }

    public String getArticle () {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public int getLength () {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth () {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeigth () {
        return heigth;
    }

    public void setHeigth(int heigth) {
        this.heigth = heigth;
    }

    public int getPrice () {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Fashion getFashion() {
        return fashion;
    }

    public void setFashion(Fashion fashion) {
        this.fashion = fashion;
    }

   /* public List<Integer> getSizes() {
        return new ArrayList<>(sizes);
    }

    public void setSizes(List<Integer> sizes) {
        if (sizes == null) {
            throw new IllegalArgumentException();
        }
        this.sizes = new ArrayList<>(sizes);
    }*/

    @Override
    public String toString() {
        return "Commodity{" +
                "id=" + id +
                ", article=" + article +
                /*", size='" + sizes + '\'' +*/
                ", length='" + length + '\'' +
                ", width='" + width + '\'' +
                ", heigth='" + heigth + '\'' +
                ", price='" + price + '\'' +
                ", fashion=" + fashion +
                '}';
    }
}
